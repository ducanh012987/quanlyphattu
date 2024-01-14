package com.example.qlphattu.services;

import com.example.qlphattu.model.PhatTu;
import com.example.qlphattu.model.XacNhanEmail;
import com.example.qlphattu.repository.IPhatTuRepository;
import com.example.qlphattu.repository.IXacNhanEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class XacNhanEmailService implements IXacNhanEmailService{
    @Autowired
    private EmailService emailService;

    @Autowired
    private IPhatTuRepository phatTuRepository;

    @Autowired
    private IXacNhanEmailRepository xacNhanEmailRepository;

    @Override
    public String xacNhanEmail(String email, String maXacNhan) {
        PhatTu phatTu = phatTuRepository.findByEmail(email);
        XacNhanEmail xacNhanEmail = xacNhanEmailRepository.findByPhatTuId(phatTu.getId());

        if(xacNhanEmail.getThoiGianHetHan().isBefore(LocalDateTime.now())){
            //mã xác nhận hết hiệu lực
            guiMaXacNhan(phatTu, xacNhanEmail);
            return "Mã xác nhận hết hiệu lực!\nVui lòng nhập mã xác nhận mới được gửi qua email!";
        }

        if (maXacNhan.equals(xacNhanEmail.getMaXacNhan()) && xacNhanEmail.getThoiGianHetHan().isAfter(LocalDateTime.now())){
            //Xác thực thành công và mã vẫn còn hiệu lực
            xacNhanEmail.setMaXacNhan(null);
            xacNhanEmail.setThoiGianHetHan(null);
            xacNhanEmail.setDaXacNhan(true);
            xacNhanEmailRepository.save(xacNhanEmail);
            return "Đăng ký thành công";
        }

        return "Sai mã xác nhận!";
    }

    private void guiMaXacNhan(PhatTu phatTu, XacNhanEmail xacNhanEmail){
        Random random = new Random();
        String ma = "";
        for (int i = 0; i < 6; i++) {
            ma += random.nextInt(10) + "";
        }
        emailService.sendEmail(phatTu.getEmail(), "Xác minh tài khoản", "Mã xác nhận là: "+ma);

        xacNhanEmail.setPhatTu(phatTu);
        xacNhanEmail.setThoiGianHetHan(LocalDateTime.now().plusMinutes(5)); //5 phút hết hạn mã xác nhận
        xacNhanEmail.setMaXacNhan(ma);
        xacNhanEmail.setDaXacNhan(false);
        xacNhanEmailRepository.save(xacNhanEmail);
    }
}
