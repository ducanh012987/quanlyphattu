package com.example.qlphattu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "phattudaotrang")
@Getter
@Setter
public class PhatTuDaoTrang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dathamgia")
    private Boolean daThamGia;

    @Column(name = "lydokhongthamgia")
    private String lyDoKhongThamGia;

    @ManyToOne
    @JoinColumn(name = "daotrangid")
    @JsonIgnore
    private DaoTrang daoTrang;

    @ManyToOne
    @JoinColumn(name = "phattuid")
    @JsonIgnore
    private PhatTu phatTu;
}
