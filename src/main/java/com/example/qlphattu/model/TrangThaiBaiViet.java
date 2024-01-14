package com.example.qlphattu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@Table(name = "trangthaibaiviet")
@Getter
@Setter
public class TrangThaiBaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tentrangthai")
    private String tenTrangThai;

    @OneToMany(mappedBy = "trangThaiBaiViet")
    @JsonIgnore
    private List<BaiViet> baiViets;
}
