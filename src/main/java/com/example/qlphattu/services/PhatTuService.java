package com.example.qlphattu.services;

import com.cloudinary.Cloudinary;
import com.example.qlphattu.jwt.JwtService;
import com.example.qlphattu.model.*;
import com.example.qlphattu.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class PhatTuService implements IPhatTuService{
    @Autowired
    private EmailService emailService;

    @Autowired
    private IPhatTuRepository phatTuRepository;

    @Autowired
    private IXacNhanEmailRepository xacNhanEmailRepository;

    @Autowired
    private IQuyenHanRepository quyenHanRepository;

    @Autowired
    private IRefeshTokenRepository refeshTokenRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    Cloudinary cloudinary;

    @Override
    public String dangKy(PhatTu phatTu) {
        // đăng ký
        var user = phatTuRepository.findByTenTaiKhoan(phatTu.getTenTaiKhoan());
        if(user.isPresent()){
            return "Tên tài khoản đã tồn tại";
        }
        if (phatTu.getMatKhau().length() <= 6){
            return "Mật khẩu quá ngắn";
        }
        if (phatTuRepository.findByEmail(phatTu.getEmail()) != null){
            return "Email đã tồn tại";
        }
        if (phatTuRepository.findBySoDienThoai(phatTu.getSoDienThoai()) != null){
            return "Số điện thoại đã tồn tại";
        }
        var quyenHan = quyenHanRepository.findById(2);
        phatTu.setQuyenHan(quyenHan.get());
        phatTu.setMatKhau(passwordEncoder.encode(phatTu.getMatKhau()));
        phatTuRepository.save(phatTu);

        //gửi mã xác nhận
        Random random = new Random();
        String ma = "";
        for (int i = 0; i < 6; i++) {
            ma += random.nextInt(10) + "";
        }
        emailService.sendEmail(phatTu.getEmail(), "Xác minh tài khoản", "Mã xác nhận là: "+ma);

        XacNhanEmail xacNhanEmail = new XacNhanEmail();
        xacNhanEmail.setPhatTu(phatTu);
        xacNhanEmail.setThoiGianHetHan(LocalDateTime.now().plusMinutes(5)); //5 phút hết hạn mã xác nhận
        xacNhanEmail.setMaXacNhan(ma);
        xacNhanEmail.setDaXacNhan(false);
        xacNhanEmailRepository.save(xacNhanEmail);

        return "Vui lòng nhập mã xác nhận được gửi qua email!";
    }

    @Override
    public String dangNhap(String tenTaiKhoan, String matKhau) {
        Optional<PhatTu> optional = phatTuRepository.findByTenTaiKhoan(tenTaiKhoan);
        if (optional.isEmpty()){
            return "Sai tên tài khoản";
        }

        if (!passwordEncoder.matches(matKhau, optional.get().getMatKhau())){
            return "Sai mật khẩu";
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(tenTaiKhoan, matKhau));
        } catch (Exception e){
            return e.getMessage();
        }

        int dem = 0;
        String token = "";
        for (RefeshToken item : optional.get().getRefeshTokens()){
            if(item.getPhatTu().getId() == optional.get().getId()){
                if(item.getThoiGianHetHan().isBefore(LocalDateTime.now())){
                    refeshTokenRepository.delete(item);
                    dem++;
                }else {
                    token = item.getToKen();
                }
            }
        }

        String refreshtoken = jwtService.generateToken(new UserDetailsCustom(optional.get()));
        if (dem == optional.get().getRefeshTokens().size()){
            token = refreshtoken;
            RefeshToken refeshToken = new RefeshToken();
            refeshToken.setToKen(token);
            refeshToken.setThoiGianHetHan(LocalDateTime.now().plusHours(24));
            refeshToken.setPhatTu(optional.get());
            refeshTokenRepository.save(refeshToken);
        }

        return "Đăng nhập thành công!\nToken: "+token;
    }

    @Override
    public String doiMatKhau(String matKhauHienTai, String matKhauMoi) {
        Optional<PhatTu> phatTu = phatTuRepository.findById(jwtService.getCurrentUserId());
        if (!passwordEncoder.matches(matKhauHienTai, phatTu.get().getMatKhau())){
            return "Mật khẩu hiện tại không đúng";
        }
        if(passwordEncoder.matches(matKhauMoi, matKhauHienTai)){
            return "Mật khẩu mới trùng với mật khẩu hiện tại";
        }
        if (matKhauMoi.length()<=6){
            return "Mật khẩu mới quá ngắn";
        }
        phatTu.get().setMatKhau(passwordEncoder.encode(matKhauMoi));
        phatTu.get().setNgayCapNhat(LocalDateTime.now());
        phatTuRepository.save(phatTu.get());

        return "Đổi mật khẩu thành công!";
    }

    @Override
    public String quenMatKhau(String tenTaiKhoan, String email) {
        Optional<PhatTu> phatTu = phatTuRepository.findByTenTaiKhoan(tenTaiKhoan);
        if(phatTu.isEmpty()){
            return "Tên tài khoản không đúng!";
        }
        if(!phatTu.get().getEmail().equals(email)){
            return "Email không đúng!";
        }
        XacNhanEmail xacNhanEmail = xacNhanEmailRepository.findByPhatTuId(phatTu.get().getId());

        //gửi mã xác nhận
        guiMaXacNhan(phatTu.get(), xacNhanEmail);

        return "Vui lòng nhập mã xác nhận được gửi qua email!";
    }

    @Override
    public String taoMatKhauMoi(String matKhauMoi, String maXacNhan) {
        Optional<PhatTu> phatTu = phatTuRepository.findById(jwtService.getCurrentUserId());

        if (maXacNhan.isEmpty()){
            return "Mã xác nhận không được để trống!";
        }

        XacNhanEmail xacNhanEmail = xacNhanEmailRepository.findByPhatTuId(phatTu.get().getId());
        if(xacNhanEmail.getThoiGianHetHan().isBefore(LocalDateTime.now())){
            //mã xác nhận hết hiệu lực
            guiMaXacNhan(phatTu.get(), xacNhanEmail);
            return "Mã xác nhận hết hiệu lực!\nVui lòng nhập mã xác nhận mới được gửi qua email!";
        }

        if (maXacNhan.equals(xacNhanEmail.getMaXacNhan()) && xacNhanEmail.getThoiGianHetHan().isAfter(LocalDateTime.now())){
            //Xác thực thành công và mã vẫn còn hiệu lực
            xacNhanEmail.setMaXacNhan(null);
            xacNhanEmail.setThoiGianHetHan(null);
            xacNhanEmail.setDaXacNhan(true);
            xacNhanEmailRepository.save(xacNhanEmail);

            //tạo mật khẩu mới
            if (matKhauMoi.length() <= 6){
                return "Mật khẩu quá ngắn";
            }
            phatTu.get().setMatKhau(matKhauMoi);
            phatTuRepository.save(phatTu.get());

            return "Tạo lại mật khẩu thành công!";
        }

        return "Sai mã xác nhận!";
    }

    @Override
    public Page<PhatTu> timPhatTuTheoPhapDanh(String phapDanh, Pageable pageable) {
        return phatTuRepository.findAllByPhapDanhContains(phapDanh, pageable);
    }

    @Override
    public Page<PhatTu> timPhatTuTheoEmail(String email, Pageable pageable) {
        return phatTuRepository.findAllByEmailContains(email, pageable);
    }

    @Override
    public Page<PhatTu> timPhatTuTheoGioiTinh(String gioiTinh, Pageable pageable) {
        return phatTuRepository.findAllByGioiTinh(gioiTinh, pageable);
    }

    @Override
    public Map uploadImage(MultipartFile file) {
        try{
            Optional<PhatTu> phatTu = phatTuRepository.findById(jwtService.getCurrentUserId());
            if (phatTu.isEmpty()){
                return null;
            }

            Map data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());
            phatTu.get().setAnhChup(data.toString());
            phatTuRepository.save(phatTu.get());
            return data;
        }catch (IOException io){
            throw new RuntimeException("Image upload fail");
        }
    }

    private void guiMaXacNhan(PhatTu phatTu, XacNhanEmail xacNhanEmail){
        Random random = new Random();
        String ma = "";
        for (int i = 0; i < 6; i++) {
            ma += random.nextInt(10) + "";
        }
        emailService.sendEmail(phatTu.getEmail(), "Mã xác nhận quên mật khẩu", "Mã xác nhận là: "+ma);

        xacNhanEmail.setPhatTu(phatTu);
        xacNhanEmail.setThoiGianHetHan(LocalDateTime.now().plusMinutes(1)); //5 phút hết hạn mã xác nhận
        xacNhanEmail.setMaXacNhan(ma);
        xacNhanEmail.setDaXacNhan(false);
        xacNhanEmailRepository.save(xacNhanEmail);
    }
}
