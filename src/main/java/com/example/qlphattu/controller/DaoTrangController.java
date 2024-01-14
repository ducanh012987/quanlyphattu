package com.example.qlphattu.controller;

import com.example.qlphattu.model.DaoTrang;
import com.example.qlphattu.services.DaoTrangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/daotrang")
public class DaoTrangController {
    @Autowired
    private DaoTrangService daoTrangService;

    @GetMapping("/timtheothoigian")
    public Page<DaoTrang> timDaoTrangTheoKhoangThoiGian(@RequestParam String tgStart, @RequestParam String tgEnd){
        Pageable page = PageRequest.of(0, 2);
        return daoTrangService.timDaoTrangTheoKhoangThoiGian(tgStart, tgEnd, page);
    }

    @GetMapping("/timtheotrutri")
    public Page<DaoTrang> timDaoTrangTheoTruTri(@RequestParam int trutri){
        Pageable page = PageRequest.of(0, 2);
        return daoTrangService.timDaoTrangTheoTruTri(trutri, page);
    }
}
