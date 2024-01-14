package com.example.qlphattu.repository;

import com.example.qlphattu.model.XacNhanEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IXacNhanEmailRepository extends JpaRepository<XacNhanEmail, Integer> {
    public XacNhanEmail findByPhatTuId(Integer phatTu_id);
}
