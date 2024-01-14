package com.example.qlphattu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "dondangky")
@Getter
@Setter
public class DonDangKy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ngayguidon")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime ngayGuiDon;

    @Column(name = "ngayxuly")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime ngayXuLy;

    @Column(name = "nguoixuly")
    private Integer nguoiXuLy;

    @ManyToOne
    @JoinColumn(name = "trangthaidonid")
    @JsonIgnore
    private TrangThaiDon trangThaiDon;

    @ManyToOne
    @JoinColumn(name = "daotrangid")
    @JsonIgnore
    private DaoTrang daoTrang;

    @ManyToOne
    @JoinColumn(name = "phattuid")
    @JsonIgnore
    private PhatTu phatTu;
}
