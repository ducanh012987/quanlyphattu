package com.example.qlphattu.controller.user;

import com.example.qlphattu.model.BaiViet;
import com.example.qlphattu.services.BaiVietService;
import com.example.qlphattu.services.NguoiDungThichBaiVietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/baiviet")
public class BaiVietController {
    @Autowired
    private BaiVietService baiVietService;

    @Autowired
    private NguoiDungThichBaiVietService nguoiDungThichBaiVietService;

    @PostMapping(value = "/them")
    public ResponseEntity<?> themBaiViet(@RequestBody BaiViet baiViet){
        return ResponseEntity.ok(baiVietService.themBaiViet(baiViet));
    }

    @DeleteMapping(value = "/xoa")
    public ResponseEntity<?> xoaBaiViet(@RequestParam int id){
        return ResponseEntity.ok(baiVietService.xoaBaiViet(id));
    }

    @PutMapping(value = "/sua")
    public ResponseEntity<?> suaBaiViet(@RequestBody BaiViet baiViet){
        return ResponseEntity.ok(baiVietService.suaBaiViet(baiViet));
    }

    @PostMapping(value = "/like")
    public ResponseEntity<?> likeBaiViet(@RequestParam int baivietid){
        return ResponseEntity.ok(nguoiDungThichBaiVietService.likeBaiViet(baivietid));
    }

    @DeleteMapping(value = "/bolike")
    public ResponseEntity<?> boLikeBaiViet(@RequestParam int id, @RequestParam int baivietid){
        return ResponseEntity.ok(nguoiDungThichBaiVietService.boLikeBaiViet(id, baivietid));
    }
}
