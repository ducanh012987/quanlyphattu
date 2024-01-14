package com.example.qlphattu.services;

import com.example.qlphattu.jwt.JwtService;
import com.example.qlphattu.model.*;
import com.example.qlphattu.repository.IBaiVietRepository;
import com.example.qlphattu.repository.ILoaiBaiVietRepository;
import com.example.qlphattu.repository.IPhatTuRepository;
import com.example.qlphattu.repository.ITrangThaiBaiVietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BaiVietService implements IBaiVietService{
    @Autowired
    private ILoaiBaiVietRepository loaiBaiVietRepository;

    @Autowired
    private IBaiVietRepository baiVietRepository;

    @Autowired
    private IPhatTuRepository phatTuRepository;

    @Autowired
    private ITrangThaiBaiVietRepository trangThaiBaiVietRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public String themBaiViet(BaiViet baiViet) {
        Optional<LoaiBaiViet> loaiBaiViet = loaiBaiVietRepository.findById(3);
        baiViet.setLoaiBaiViet(loaiBaiViet.get());
        Optional<PhatTu> phatTu = phatTuRepository.findById(jwtService.getCurrentUserId());
        baiViet.setPhatTu(phatTu.get());
        baiViet.setSoLuotThich(0);
        baiViet.setSoLuotBinhLuan(0);
        baiViet.setThoiGianDang(LocalDateTime.now());
        baiViet.setDaXoa(false);
        Optional<TrangThaiBaiViet> trangThaiBaiViet = trangThaiBaiVietRepository.findById(1);
        baiViet.setTrangThaiBaiViet(trangThaiBaiViet.get());
        baiVietRepository.save(baiViet);

        return "Thêm bài viết thành công!";
    }

    @Override
    public String xoaBaiViet(int id) {
        Optional<BaiViet> baiViet = baiVietRepository.findById(id);
        if(baiViet.isEmpty()){
            return "Bài viết không tồn tại!";
        }
        baiViet.get().setThoiGianXoa(LocalDateTime.now());
        baiViet.get().setDaXoa(true);
        baiVietRepository.save(baiViet.get());
        return "Xoá bài viết thành công!";
    }

    @Override
    public String suaBaiViet(BaiViet baiViet) {
        Optional<BaiViet> baiVietSua = baiVietRepository.findById(baiViet.getId());
        if(baiVietSua.isEmpty()){
            return "Bài viết không tồn tại!";
        }
        baiVietSua.get().setTieuDe(baiViet.getTieuDe());
        baiVietSua.get().setMoTa(baiViet.getMoTa());
        baiVietSua.get().setNoiDung(baiViet.getNoiDung());
        baiVietSua.get().setThoiGianCapNhat(LocalDateTime.now());
        baiVietRepository.save(baiVietSua.get());
        return "Sửa bài viết thành công!";
    }
}
