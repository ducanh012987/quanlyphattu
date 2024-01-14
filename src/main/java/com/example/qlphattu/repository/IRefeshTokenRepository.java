package com.example.qlphattu.repository;

import com.example.qlphattu.model.RefeshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRefeshTokenRepository extends JpaRepository<RefeshToken, Integer> {
}
