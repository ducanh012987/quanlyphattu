package com.example.qlphattu.repository;

import com.example.qlphattu.model.NguoiDungThichBaiViet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INguoiDungThichBaiVietRepository extends JpaRepository<NguoiDungThichBaiViet, Integer> {
}
