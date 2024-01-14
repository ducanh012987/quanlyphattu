package com.example.qlphattu.controller.user;

import com.example.qlphattu.model.PhatTu;
import com.example.qlphattu.services.PhatTuService;
import com.example.qlphattu.services.XacNhanEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class PhatTuController {
    @Autowired
    private XacNhanEmailService xacNhanEmailService;

    @Autowired
    private PhatTuService phatTuService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> dangKy(@RequestBody PhatTu phatTu){
        return ResponseEntity.ok(phatTuService.dangKy(phatTu));
    }

    @GetMapping(value = "/login")
    public ResponseEntity<?> dangNhap(@RequestParam String tentaikhoan, @RequestParam String matkhau){
        return ResponseEntity.ok(phatTuService.dangNhap(tentaikhoan, matkhau));
    }

    @PostMapping(value = "/doimatkhau")
    public ResponseEntity<?> doiMatKhau(@RequestParam String matkhauhientai, @RequestParam String matkhaumoi){
        return ResponseEntity.ok(phatTuService.doiMatKhau(matkhauhientai, matkhaumoi));
    }

    @PostMapping("/xacNhan")
    public ResponseEntity<?> xacNhanEmail(@RequestParam String email, @RequestParam String maxacnhan){
        return ResponseEntity.ok(xacNhanEmailService.xacNhanEmail(email, maxacnhan));
    }

    @PostMapping("/quenmatkhau")
    public ResponseEntity<?> quenMatKhau(@RequestParam String tentaikhoan, @RequestParam String email){
        return ResponseEntity.ok(phatTuService.quenMatKhau(tentaikhoan, email));
    }

    @PostMapping("/taomatkhaumoi")
    public ResponseEntity<?> taoMatKhauMoi(@RequestParam String maxacnhan, @RequestParam String matkhaumoi){
        return ResponseEntity.ok(phatTuService.taoMatKhauMoi(matkhaumoi, maxacnhan));
    }

    @PostMapping("/uploadimage")
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file){
        Map data = phatTuService.uploadImage(file);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/phattu/timtheophapdanh")
    public Page<PhatTu> timPhatTuTheoPhapDanh(@RequestParam String phapdanh){
        Pageable page = PageRequest.of(0, 2);
        return phatTuService.timPhatTuTheoPhapDanh(phapdanh, page);
    }
}
