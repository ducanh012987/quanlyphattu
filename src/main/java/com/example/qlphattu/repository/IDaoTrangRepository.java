package com.example.qlphattu.repository;

import com.example.qlphattu.model.DaoTrang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IDaoTrangRepository extends JpaRepository<DaoTrang, Integer> {
    @Query(value = "SELECT * FROM qlphattu.daotrang where thoigianbatdau between ?1 and ?2", nativeQuery = true)
    public Page<DaoTrang> timDaoTrangThoiGianBatDauBetween(String tgStart, String tgEnd, Pageable pageable);

    public Page<DaoTrang> findAllByNguoiTruTri(Integer nguoiTruTri, Pageable pageable);
}
