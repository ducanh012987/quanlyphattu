package com.example.qlphattu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@Table(name = "loaibaiviet")
@Getter
@Setter
public class LoaiBaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tenloai")
    private String tenLoai;

    @OneToMany(mappedBy = "loaiBaiViet")
    @JsonIgnore
    private List<BaiViet> baiViets;
}
