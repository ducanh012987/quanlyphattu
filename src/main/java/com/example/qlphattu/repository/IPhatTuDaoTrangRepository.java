package com.example.qlphattu.repository;

import com.example.qlphattu.model.PhatTuDaoTrang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPhatTuDaoTrangRepository extends JpaRepository<PhatTuDaoTrang, Integer> {
}
