package com.example.qlphattu.controller.user;

import com.example.qlphattu.model.DonDangKy;
import com.example.qlphattu.services.DonDangKyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/dondangky")
public class DonDangKyController {
    @Autowired
    private DonDangKyService donDangKyService;

    @PostMapping("/tao")
    public ResponseEntity<?> taoDonDangKy(@RequestParam int daotrangid){
        return ResponseEntity.ok(donDangKyService.taoDonDangKy(daotrangid));
    }

    @DeleteMapping("/xoa")
    public ResponseEntity<?> xoaDonDangKy(@RequestParam int id){
        return ResponseEntity.ok(donDangKyService.xoaDonDangKy(id));
    }
}
