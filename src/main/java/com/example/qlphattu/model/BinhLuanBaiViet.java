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
@Table(name = "binhluanbaiviet")
@Getter
@Setter
public class BinhLuanBaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "baivietid")
    @JsonIgnore
    private BaiViet baiViet;

    @ManyToOne
    @JoinColumn(name = "phattuid")
    @JsonIgnore
    private PhatTu phatTu;

    @Column(name = "binhluan")
    private String binhLuan;

    @Column(name = "soluotthich")
    private Integer soLuotThich;

    @Column(name = "thoigiantao")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime thoiGianTao;

    @Column(name = "thoigiancapnhat")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime thoiGianCapNhat;

    @Column(name = "thoigianxoa")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime thoiGianXoa;

    @Column(name = "daxoa")
    private Boolean daXoa;

    @OneToMany(mappedBy = "binhLuanBaiViet")
    @JsonIgnore
    private List<NguoiDungThichBinhLuanBaiViet> nguoiDungThichBinhLuanBaiViets;
}
