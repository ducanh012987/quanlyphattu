package com.example.qlphattu.repository;

import com.example.qlphattu.model.DonDangKy;
import com.example.qlphattu.model.TrangThaiDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDonDangKyRepository extends JpaRepository<DonDangKy, Integer> {
    public List<DonDangKy> findAllByDaoTrangId(Integer daoTrang_id);

    public Page<DonDangKy> findAllByTrangThaiDonId(Integer trangThaiDon_id, Pageable pageable);
}
