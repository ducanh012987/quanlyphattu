package com.example.qlphattu.controller.admin;

import com.example.qlphattu.model.DaoTrang;
import com.example.qlphattu.model.PhatTu;
import com.example.qlphattu.services.DaoTrangService;
import com.example.qlphattu.services.DonDangKyService;
import com.example.qlphattu.services.PhatTuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PhatTuService phatTuService;

    @Autowired
    private DaoTrangService daoTrangService;

    @Autowired
    private DonDangKyService donDangKyService;

    @GetMapping("/timphattutheophapdanh")
    public Page<PhatTu> timPhatTuTheoPhapDanh(@RequestParam String phapdanh){
        Pageable page = PageRequest.of(0, 2);
        return phatTuService.timPhatTuTheoPhapDanh(phapdanh, page);
    }

    @GetMapping("/timphattutheoemail")
    public Page<PhatTu> timPhatTuTheoEmail(@RequestParam String email){
        Pageable page = PageRequest.of(0, 2);
        return phatTuService.timPhatTuTheoEmail(email, page);
    }

    @GetMapping("/timphattutheogioitinh")
    public Page<PhatTu> timPhatTuTheoGioiTinh(@RequestParam String gioitinh){
        Pageable page = PageRequest.of(0, 2);
        return phatTuService.timPhatTuTheoGioiTinh(gioitinh, page);
    }

    @PostMapping("/themdaotrang")
    public ResponseEntity<?> themDaoTrang(@RequestBody DaoTrang daoTrang){
        return ResponseEntity.ok(daoTrangService.themDaoTrang(daoTrang));
    }

    @DeleteMapping("/xoadaotrang")
    public ResponseEntity<?> xoaDaoTrang(@RequestParam int id){
        return ResponseEntity.ok(daoTrangService.xoaDaoTrang(id));
    }

    @PutMapping("/suadaotrang")
    public ResponseEntity<?> suaDaoTrang(@RequestBody DaoTrang daoTrang){
        return ResponseEntity.ok(daoTrangService.suaDaoTrang(daoTrang));
    }

    @PatchMapping("/duyetdondangky")
    public ResponseEntity<?> duyetDonDangKy(@RequestParam int id){
        return ResponseEntity.ok(donDangKyService.duyetDonDangKy(id));
    }

    @GetMapping("/timdondangky")
    public ResponseEntity<?> timDonDangKyTheoTrangThai(@RequestParam int trangthaidonid){
        Pageable page = PageRequest.of(0, 2);
        return ResponseEntity.ok(donDangKyService.timDonDangKyTheoTrangThai(trangthaidonid, page));
    }
}
