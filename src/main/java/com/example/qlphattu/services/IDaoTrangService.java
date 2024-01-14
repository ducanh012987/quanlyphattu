package com.example.qlphattu.services;

import com.example.qlphattu.model.DaoTrang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDaoTrangService {
    public String themDaoTrang(DaoTrang daoTrang);
    public String xoaDaoTrang(int id);
    public String suaDaoTrang(DaoTrang daoTrang);
    public Page<DaoTrang> timDaoTrangTheoKhoangThoiGian(String tgStart, String tgEnd, Pageable pageable);

    public Page<DaoTrang> timDaoTrangTheoTruTri(int trutri, Pageable pageable);
}
