package com.example.qlphattu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "chua")
@Getter
@Setter
public class Chua {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tenchua")
    private String tenChua;

    @Column(name = "diachi")
    private String diaChi;

    @Column(name = "ngaythanhlap")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime ngayThanhLap;

    @Column(name = "nguoitrutri")
    private String nguoiTruTri;

    @OneToMany(mappedBy = "chua")
    @JsonIgnore
    private List<PhatTu> phatTus;
}
