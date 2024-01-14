package com.example.qlphattu.controller.user;

import com.example.qlphattu.services.BinhLuanBaiVietService;
import com.example.qlphattu.services.NguoiDungThichBinhLuanBaiVietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/binhluan")
public class BinhLuanBVController {
    @Autowired
    private BinhLuanBaiVietService binhLuanBaiVietService;

    @Autowired
    private NguoiDungThichBinhLuanBaiVietService nguoiDungThichBinhLuanBaiVietService;

    @PostMapping(value = "/them")
    public ResponseEntity<?> themBaiViet(@RequestParam int baivietid, @RequestParam String binhluan){
        return ResponseEntity.ok(binhLuanBaiVietService.themBinhLuanBaiViet(baivietid, binhluan));
    }

    @DeleteMapping(value = "/xoa")
    public ResponseEntity<?> xoaBaiViet(@RequestParam int id){
        return ResponseEntity.ok(binhLuanBaiVietService.xoaBinhLuanBaiViet(id));
    }

    @PutMapping(value = "/sua")
    public ResponseEntity<?> suaBaiViet(@RequestParam int id, @RequestParam String binhluansua){
        return ResponseEntity.ok(binhLuanBaiVietService.suaBinhLuanBaiViet(id, binhluansua));
    }

    @PostMapping(value = "/like")
    public ResponseEntity<?> likeBinhLuanBaiViet(@RequestParam int binhluanbvid){
        return ResponseEntity.ok(nguoiDungThichBinhLuanBaiVietService.likeBinhLuanBaiViet(binhluanbvid));
    }

    @DeleteMapping(value = "/bolike")
    public ResponseEntity<?> boLikeBinhLuanBaiViet(@RequestParam int id, @RequestParam int binhluanbvid){
        return ResponseEntity.ok(nguoiDungThichBinhLuanBaiVietService.boLikeBinhLuanBaiViet(id, binhluanbvid));
    }
}
