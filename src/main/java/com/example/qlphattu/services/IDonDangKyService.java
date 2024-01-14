package com.example.qlphattu.services;

import com.example.qlphattu.model.DonDangKy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDonDangKyService {
    public String taoDonDangKy(int daoTrangId);

    public String xoaDonDangKy(int id);

    public String duyetDonDangKy(int id);

    public Page<DonDangKy> timDonDangKyTheoTrangThai(int trangThaiDonId, Pageable pageable);
}
