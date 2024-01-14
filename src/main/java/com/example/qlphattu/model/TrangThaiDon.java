package com.example.qlphattu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@Table(name = "trangthaidon")
@Getter
@Setter
public class TrangThaiDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tentrangthai")
    private String tenTrangThai;

    @OneToMany(mappedBy = "trangThaiDon")
    @JsonIgnore
    private List<DonDangKy> donDangKys;
}
