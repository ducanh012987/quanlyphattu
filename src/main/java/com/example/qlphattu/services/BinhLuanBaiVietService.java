package com.example.qlphattu.services;

import com.example.qlphattu.jwt.JwtService;
import com.example.qlphattu.model.BaiViet;
import com.example.qlphattu.model.BinhLuanBaiViet;
import com.example.qlphattu.model.PhatTu;
import com.example.qlphattu.repository.IBaiVietRepository;
import com.example.qlphattu.repository.IBinhLuanBaiVietRepository;
import com.example.qlphattu.repository.IPhatTuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BinhLuanBaiVietService implements IBinhLuanBaiVietService{
    @Autowired
    private IBinhLuanBaiVietRepository binhLuanBaiVietRepository;

    @Autowired
    private IPhatTuRepository phatTuRepository;

    @Autowired
    private IBaiVietRepository baiVietRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public String themBinhLuanBaiViet(int baiVietId, String binhLuan) {
        Optional<BaiViet> baiViet = baiVietRepository.findById(baiVietId);
        if (baiViet.isEmpty()){
            return "Bài viết không tồn tại!";
        }

        BinhLuanBaiViet binhLuanBaiViet = new BinhLuanBaiViet();
        Optional<PhatTu> phatTu = phatTuRepository.findById(jwtService.getCurrentUserId());
        binhLuanBaiViet.setPhatTu(phatTu.get());
        binhLuanBaiViet.setSoLuotThich(0);
        binhLuanBaiViet.setThoiGianTao(LocalDateTime.now());
        binhLuanBaiViet.setDaXoa(false);
        binhLuanBaiVietRepository.save(binhLuanBaiViet);

        baiViet.get().setSoLuotBinhLuan(baiViet.get().getSoLuotBinhLuan()+1);
        baiVietRepository.save(baiViet.get());

        return "Thêm bình luận thành công!";
    }

    @Override
    public String xoaBinhLuanBaiViet(int id) {
        Optional<BinhLuanBaiViet> binhLuanBaiViet = binhLuanBaiVietRepository.findById(id);
        if (binhLuanBaiViet.isEmpty()){
            return "Bình luận không tồn tại!";
        }
        Optional<BaiViet> baiViet = baiVietRepository.findById(binhLuanBaiViet.get().getBaiViet().getId());
        if (baiViet.isEmpty()){
            return "Bài viết không tồn tại!";
        }
        binhLuanBaiViet.get().setThoiGianXoa(LocalDateTime.now());
        binhLuanBaiViet.get().setDaXoa(true);
        binhLuanBaiVietRepository.save(binhLuanBaiViet.get());

        baiViet.get().setSoLuotBinhLuan(baiViet.get().getSoLuotBinhLuan()-1);
        baiVietRepository.save(baiViet.get());

        return "Xoá bình luận thành công!";
    }

    @Override
    public String suaBinhLuanBaiViet(int id, String binhLuanSua) {
        Optional<BinhLuanBaiViet> binhLuanBaiVietSua = binhLuanBaiVietRepository.findById(id);
        if (binhLuanBaiVietSua.isEmpty()){
            return "Bình luận không tồn tại!";
        }
        binhLuanBaiVietSua.get().setBinhLuan(binhLuanSua);
        binhLuanBaiVietSua.get().setThoiGianCapNhat(LocalDateTime.now());
        binhLuanBaiVietRepository.save(binhLuanBaiVietSua.get());
        return "Sửa bình luận thành công!";
    }
}
