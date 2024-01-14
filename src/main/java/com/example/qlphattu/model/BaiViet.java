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
@Table(name = "baiviet")
@Getter
@Setter
public class BaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "loaibaivietid")
    @JsonIgnore
    private LoaiBaiViet loaiBaiViet;

    @Column(name = "tieude")
    private String tieuDe;

    @Column(name = "mota")
    private String moTa;

    @Column(name = "noidung")
    private String noiDung;

    @ManyToOne
    @JoinColumn(name = "phattuid")
    @JsonIgnore
    private PhatTu phatTu;

    @Column(name = "nguoiduyetbaivietid")
    private Integer nguoiDuyetBaiVietId;

    @Column(name = "soluotthich")
    private Integer soLuotThich;

    @Column(name = "soluotbinhluan")
    private Integer soLuotBinhLuan;

    @Column(name = "thoigiandang")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime thoiGianDang;

    @Column(name = "thoigiancapnhat")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime thoiGianCapNhat;

    @Column(name = "thoigianxoa")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime thoiGianXoa;

    @Column(name = "daxoa")
    private Boolean daXoa;

    @ManyToOne
    @JoinColumn(name = "trangthaibaivietid")
    @JsonIgnore
    private TrangThaiBaiViet trangThaiBaiViet;

    @OneToMany(mappedBy = "baiViet")
    @JsonIgnore
    private List<BinhLuanBaiViet> binhLuanBaiViets;

    @OneToMany(mappedBy = "baiViet")
    @JsonIgnore
    private List<NguoiDungThichBaiViet> nguoiDungThichBaiViets;
}
