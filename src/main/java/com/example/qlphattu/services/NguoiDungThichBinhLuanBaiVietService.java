package com.example.qlphattu.services;

import com.example.qlphattu.jwt.JwtService;
import com.example.qlphattu.model.BinhLuanBaiViet;
import com.example.qlphattu.model.NguoiDungThichBinhLuanBaiViet;
import com.example.qlphattu.model.PhatTu;
import com.example.qlphattu.repository.IBinhLuanBaiVietRepository;
import com.example.qlphattu.repository.INguoiDungThichBinhLuanBaiVietRepository;
import com.example.qlphattu.repository.IPhatTuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class NguoiDungThichBinhLuanBaiVietService implements INguoiDungThichBinhLuanBaiVietService {
    @Autowired
    private IPhatTuRepository phatTuRepository;

    @Autowired
    private IBinhLuanBaiVietRepository binhLuanBaiVietRepository;

    @Autowired
    private INguoiDungThichBinhLuanBaiVietRepository nguoiDungThichBinhLuanBaiVietRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public String likeBinhLuanBaiViet(int binhLuanBaiVietId) {
        Optional<BinhLuanBaiViet> binhLuanBaiViet = binhLuanBaiVietRepository.findById(binhLuanBaiVietId);
        if (binhLuanBaiViet.isEmpty()){
            return "Bình luận không tồn tại!";
        }

        NguoiDungThichBinhLuanBaiViet nguoiDungThichBinhLuanBaiViet = new NguoiDungThichBinhLuanBaiViet();
        Optional<PhatTu> phatTu = phatTuRepository.findById(jwtService.getCurrentUserId());
        nguoiDungThichBinhLuanBaiViet.setPhatTu(phatTu.get());
        nguoiDungThichBinhLuanBaiViet.setBinhLuanBaiViet(binhLuanBaiViet.get());
        nguoiDungThichBinhLuanBaiViet.setThoiGianLike(LocalDateTime.now());
        nguoiDungThichBinhLuanBaiViet.setDaXoa(false);
        nguoiDungThichBinhLuanBaiVietRepository.save(nguoiDungThichBinhLuanBaiViet);

        binhLuanBaiViet.get().setSoLuotThich(binhLuanBaiViet.get().getSoLuotThich()+1);
        binhLuanBaiVietRepository.save(binhLuanBaiViet.get());

        return "Like bình luận bài viết thành công!";
    }

    @Override
    public String boLikeBinhLuanBaiViet(int id, int binhLuanBaiVietId) {
        Optional<NguoiDungThichBinhLuanBaiViet> nguoiDungThichBinhLuanBaiViet = nguoiDungThichBinhLuanBaiVietRepository.findById(id);
        if (nguoiDungThichBinhLuanBaiViet.isEmpty()){
            return "Người dùng thích bình luận không tồn tại!";
        }

        nguoiDungThichBinhLuanBaiViet.get().setDaXoa(true);
        nguoiDungThichBinhLuanBaiVietRepository.save(nguoiDungThichBinhLuanBaiViet.get());

        Optional<BinhLuanBaiViet> binhLuanBaiViet = binhLuanBaiVietRepository.findById(binhLuanBaiVietId);
        binhLuanBaiViet.get().setSoLuotThich(binhLuanBaiViet.get().getSoLuotThich()-1);
        binhLuanBaiVietRepository.save(binhLuanBaiViet.get());

        return "Bỏ Like bình luận bài viết thành công!";
    }
}
