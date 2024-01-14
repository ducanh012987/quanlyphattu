package com.example.qlphattu.repository;

import com.example.qlphattu.model.TrangThaiBaiViet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrangThaiBaiVietRepository extends JpaRepository<TrangThaiBaiViet, Integer> {
}
