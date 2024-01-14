package com.example.qlphattu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "phattu")
@Getter
@Setter
@ToString
public class PhatTu{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tentaikhoan")
    private String tenTaiKhoan;

    @Column(name = "anhchup")
    private String anhChup;

    @Column(name = "dahoantuc")
    private Boolean daHoanTuc;

    @Column(name = "email")
    private String email;

    @Column(name = "gioitinh")
    private String gioiTinh;

    @Column(name = "ngaycapnhat")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime ngayCapNhat;

    @Column(name = "ngayhoantuc")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime ngayHoanTuc;

    @Column(name = "ngaysinh")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime ngaySinh;

    @Column(name = "matkhau")
    private String matKhau;

    @Column(name = "phapdanh")
    private String phapDanh;

    @Column(name = "sodienthoai")
    private String soDienThoai;

    @ManyToOne
    @JoinColumn(name = "chuaid")
    @JsonIgnore
    private Chua chua;

    @ManyToOne
    @JoinColumn(name = "quyenhanid")
    @JsonIgnore
    private QuyenHan quyenHan;

    @OneToMany(mappedBy = "phatTu")
    @JsonIgnore
    private List<RefeshToken> refeshTokens;

    @OneToMany(mappedBy = "phatTu")
    @JsonIgnore
    private List<BinhLuanBaiViet> binhLuanBaiViets;

    @OneToMany(mappedBy = "phatTu")
    @JsonIgnore
    private List<NguoiDungThichBinhLuanBaiViet> nguoiDungThichBinhLuanBaiViets;

    @OneToMany(mappedBy = "phatTu")
    @JsonIgnore
    private List<NguoiDungThichBaiViet> nguoiDungThichBaiViets;

    @OneToMany(mappedBy = "phatTu")
    @JsonIgnore
    private List<XacNhanEmail> xacNhanEmails;

    @OneToMany(mappedBy = "phatTu")
    @JsonIgnore
    private List<PhatTuDaoTrang> phatTuDaoTrangs;

    @OneToMany(mappedBy = "phatTu")
    @JsonIgnore
    private List<DonDangKy> donDangKys;
}
