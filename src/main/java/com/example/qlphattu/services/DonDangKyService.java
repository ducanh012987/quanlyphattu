package com.example.qlphattu.services;

import com.example.qlphattu.jwt.JwtService;
import com.example.qlphattu.model.*;
import com.example.qlphattu.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DonDangKyService implements IDonDangKyService{
    @Autowired
    private IDaoTrangRepository daoTrangRepository;

    @Autowired
    private IDonDangKyRepository donDangKyRepository;

    @Autowired
    private ITrangThaiDonRepository trangThaiDonRepository;

    @Autowired
    private IPhatTuDaoTrangRepository phatTuDaoTrangRepository;

    @Autowired
    private IPhatTuRepository phatTuRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public String taoDonDangKy(int daoTrangId) {
        Optional<DaoTrang> daoTrang = daoTrangRepository.findById(daoTrangId);
        if (daoTrang.isEmpty()){
            return "Đạo tràng không tồn tại!";
        }
        DonDangKy donDangKy = new DonDangKy();
        donDangKy.setNgayGuiDon(LocalDateTime.now());
        Optional<TrangThaiDon> trangThaiDon = trangThaiDonRepository.findById(1);
        donDangKy.setTrangThaiDon(trangThaiDon.get());
        donDangKy.setDaoTrang(daoTrang.get());
        Optional<PhatTu> phatTu = phatTuRepository.findById(jwtService.getCurrentUserId());
        donDangKy.setPhatTu(phatTu.get());
        donDangKyRepository.save(donDangKy);
        return "Gửi đơn thành công!\nVui lòng chờ duyệt!";
    }

    @Override
    public String xoaDonDangKy(int id) {
        Optional<DonDangKy> donDangKy = donDangKyRepository.findById(id);
        if (donDangKy.isEmpty()){
            return "Đơn đăng ký không tồn tại";
        }
        donDangKyRepository.delete(donDangKy.get());
        return "Xoá đơn thành công";
    }

    @Override
    public String duyetDonDangKy(int id) {
        Optional<DonDangKy> donDangKy = donDangKyRepository.findById(id);
        if (donDangKy.isEmpty()){
            return "Đơn đăng ký không tồn tại";
        }
        Optional<TrangThaiDon> trangThaiDon = trangThaiDonRepository.findById(2);
        donDangKy.get().setTrangThaiDon(trangThaiDon.get());
        donDangKy.get().setNgayXuLy(LocalDateTime.now());
        donDangKy.get().setNguoiXuLy(jwtService.getCurrentUserId());
        donDangKyRepository.save(donDangKy.get());

        Optional<DaoTrang> daoTrang = daoTrangRepository.findById(donDangKy.get().getDaoTrang().getId());
        daoTrang.get().setSoThanhVienThamGia(daoTrang.get().getSoThanhVienThamGia()+1);
        daoTrangRepository.save(daoTrang.get());

        PhatTuDaoTrang phatTuDaoTrang = new PhatTuDaoTrang();
        phatTuDaoTrang.setDaThamGia(true);
        phatTuDaoTrang.setDaoTrang(daoTrang.get());
        phatTuDaoTrang.setPhatTu(donDangKy.get().getPhatTu());
        phatTuDaoTrangRepository.save(phatTuDaoTrang);

        return "Duyệt đơn thành công";
    }

    @Override
    public Page<DonDangKy> timDonDangKyTheoTrangThai(int trangThaiDonId, Pageable pageable) {
        return donDangKyRepository.findAllByTrangThaiDonId(trangThaiDonId, pageable);
    }
}
