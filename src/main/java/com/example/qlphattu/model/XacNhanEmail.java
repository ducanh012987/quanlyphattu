package com.example.qlphattu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "xacnhanemail")
@Getter
@Setter
@ToString
public class XacNhanEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "phattuid")
    @JsonIgnore
    private PhatTu phatTu;

    @Column(name = "thoigianhethan")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime thoiGianHetHan;

    @Column(name = "maxacnhan")
    private String maXacNhan;

    @Column(name = "daxacnhan")
    private Boolean daXacNhan;
}
