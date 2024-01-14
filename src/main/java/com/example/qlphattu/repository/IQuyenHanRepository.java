package com.example.qlphattu.repository;

import com.example.qlphattu.model.QuyenHan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuyenHanRepository extends JpaRepository<QuyenHan, Integer> {
}
