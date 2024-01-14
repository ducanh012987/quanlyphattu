package com.example.qlphattu.repository;

import com.example.qlphattu.model.LoaiBaiViet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoaiBaiVietRepository extends JpaRepository<LoaiBaiViet, Integer> {
}
