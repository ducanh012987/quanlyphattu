package com.example.qlphattu.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "daotrang")
@Getter
@Setter
public class DaoTrang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "daketthuc")
    private Boolean daKetThuc;

    @Column(name = "noidung")
    private String noiDung;

    @Column(name = "noitochuc")
    private String noiToChuc;

    @Column(name = "sothanhvienthamgia")
    private Integer soThanhVienThamGia;

    @Column(name = "thoigianbatdau")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime thoiGianBatDau;

    @Column(name = "nguoitrutri")
    private Integer nguoiTruTri;

    @OneToMany(mappedBy = "daoTrang")
    @JsonIgnore
    private List<PhatTuDaoTrang> phatTuDaoTrangs;

    @OneToMany(mappedBy = "daoTrang")
    @JsonIgnore
    private List<DonDangKy> donDangKys;
}
