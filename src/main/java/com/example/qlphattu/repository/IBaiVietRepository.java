package com.example.qlphattu.repository;

import com.example.qlphattu.model.BaiViet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBaiVietRepository extends JpaRepository<BaiViet, Integer> {
}
