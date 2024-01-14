package com.example.qlphattu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@Table(name = "quyenhan")
@Getter
@Setter
public class QuyenHan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tenquyenhan")
    private String tenQuyenHan;

    @OneToMany(mappedBy = "quyenHan")
    @JsonIgnore
    private List<PhatTu> phatTus;
}
