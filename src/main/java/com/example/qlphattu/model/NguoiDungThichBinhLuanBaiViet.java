package com.example.qlphattu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "nguoidungthichbinhluanbaiviet")
@Getter
@Setter
public class NguoiDungThichBinhLuanBaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "phattuid")
    @JsonIgnore
    private PhatTu phatTu;

    @ManyToOne
    @JoinColumn(name = "binhluanbaivietid")
    @JsonIgnore
    private BinhLuanBaiViet binhLuanBaiViet;

    @Column(name = "thoigianlike")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime thoiGianLike;

    @Column(name = "daxoa")
    private Boolean daXoa;
}
