package com.example.qlphattu.repository;

import com.example.qlphattu.model.PhatTu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPhatTuRepository extends JpaRepository<PhatTu, Integer> {
    public Optional<PhatTu> findByTenTaiKhoan(String tenTaiKhoan);
    public PhatTu findByEmail(String email);
    public PhatTu findBySoDienThoai(String soDienThoai);
    public Page<PhatTu> findAllByPhapDanhContains(String phapDanh, Pageable pageable);
    public Page<PhatTu> findAllByEmailContains(String email, Pageable pageable);
    public Page<PhatTu> findAllByGioiTinh(String gioiTinh, Pageable pageable);
}
