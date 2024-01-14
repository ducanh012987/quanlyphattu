package com.example.qlphattu.services;

import com.example.qlphattu.model.DaoTrang;
import com.example.qlphattu.model.DonDangKy;
import com.example.qlphattu.repository.IDaoTrangRepository;
import com.example.qlphattu.repository.IDonDangKyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DaoTrangService implements IDaoTrangService{
    @Autowired
    private IDaoTrangRepository daoTrangRepository;

    @Autowired
    IDonDangKyRepository donDangKyRepository;

    @Override
    public String themDaoTrang(DaoTrang daoTrang) {
        daoTrang.setDaKetThuc(false);
        daoTrang.setSoThanhVienThamGia(0);
        daoTrangRepository.save(daoTrang);
        return "Thêm thành công!";
    }

    @Override
    public String xoaDaoTrang(int id) {
        Optional<DaoTrang> daoTrang = daoTrangRepository.findById(id);
        if(daoTrang.isEmpty()){
            return "Đạo tràng không tồn tại!";
        }
        donDangKyRepository.deleteAll(donDangKyRepository.findAllByDaoTrangId(daoTrang.get().getId()));
        daoTrangRepository.delete(daoTrang.get());
        return "Xoá thành công!";
    }

    @Override
    public String suaDaoTrang(DaoTrang daoTrang) {
        DaoTrang daoTrangSua = daoTrangRepository.findById(daoTrang.getId()).get();
        if (daoTrangSua.getId() == null){
            return "Đạo tràng không tồn tại!";
        }
        daoTrangSua.setNoiDung(daoTrang.getNoiDung());
        daoTrangSua.setNoiToChuc(daoTrang.getNoiToChuc());
        daoTrangSua.setThoiGianBatDau(daoTrang.getThoiGianBatDau());
        daoTrangSua.setNguoiTruTri(daoTrang.getNguoiTruTri());
        daoTrangRepository.save(daoTrangSua);
        return "Sửa thành công!";
    }

    @Override
    public Page<DaoTrang> timDaoTrangTheoKhoangThoiGian(String tgStart, String tgEnd, Pageable pageable) {
//        if (tgStart.isAfter(tgEnd)){
//            LocalDateTime temp = tgStart;
//            tgStart = tgEnd;
//            tgEnd = temp;
//        }
        return daoTrangRepository.timDaoTrangThoiGianBatDauBetween(tgStart, tgEnd, pageable);
    }

    @Override
    public Page<DaoTrang> timDaoTrangTheoTruTri(int trutri, Pageable pageable) {
        return daoTrangRepository.findAllByNguoiTruTri(trutri, pageable);
    }
}
