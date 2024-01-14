package com.example.qlphattu.repository;

import com.example.qlphattu.model.TrangThaiDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrangThaiDonRepository extends JpaRepository<TrangThaiDon, Integer> {
}
