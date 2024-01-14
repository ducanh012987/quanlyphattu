package com.example.qlphattu.repository;

import com.example.qlphattu.model.BinhLuanBaiViet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBinhLuanBaiVietRepository extends JpaRepository<BinhLuanBaiViet, Integer> {
}
