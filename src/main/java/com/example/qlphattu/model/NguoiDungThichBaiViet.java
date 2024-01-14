package com.example.qlphattu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "nguoidungthichbaiviet")
@Getter
@Setter
public class NguoiDungThichBaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "phattuid")
    @JsonIgnore
    private PhatTu phatTu;

    @ManyToOne
    @JoinColumn(name = "baivietid")
    @JsonIgnore
    private BaiViet baiViet;

    @Column(name = "thoigianthich")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime thoiGianThich;

    @Column(name = "daxoa")
    private Boolean daXoa;
}
