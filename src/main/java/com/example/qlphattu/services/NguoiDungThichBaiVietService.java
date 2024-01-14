package com.example.qlphattu.services;

import com.example.qlphattu.jwt.JwtService;
import com.example.qlphattu.model.BaiViet;
import com.example.qlphattu.model.NguoiDungThichBaiViet;
import com.example.qlphattu.model.PhatTu;
import com.example.qlphattu.repository.IBaiVietRepository;
import com.example.qlphattu.repository.INguoiDungThichBaiVietRepository;
import com.example.qlphattu.repository.IPhatTuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class NguoiDungThichBaiVietService implements INguoiDungThichBaiVietService{
    @Autowired
    private IBaiVietRepository baiVietRepository;

    @Autowired
    private IPhatTuRepository phatTuRepository;

    @Autowired
    private INguoiDungThichBaiVietRepository nguoiDungThichBaiVietRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public String likeBaiViet(int baiVietId) {
        Optional<BaiViet> baiViet = baiVietRepository.findById(baiVietId);
        if (baiViet.isEmpty()){
            return "Bài viết không tồn tại!";
        }

        NguoiDungThichBaiViet nguoiDungThichBaiViet = new NguoiDungThichBaiViet();
        Optional<PhatTu> phatTu = phatTuRepository.findById(jwtService.getCurrentUserId());
        nguoiDungThichBaiViet.setPhatTu(phatTu.get());
        nguoiDungThichBaiViet.setBaiViet(baiViet.get());
        nguoiDungThichBaiViet.setThoiGianThich(LocalDateTime.now());
        nguoiDungThichBaiViet.setDaXoa(false);
        nguoiDungThichBaiVietRepository.save(nguoiDungThichBaiViet);

        baiViet.get().setSoLuotThich(baiViet.get().getSoLuotThich()+1);
        baiVietRepository.save(baiViet.get());

        return "Like bài viết thành công";
    }

    @Override
    public String boLikeBaiViet(int id, int baiVietId) {
        Optional<NguoiDungThichBaiViet> nguoiDungThichBaiViet = nguoiDungThichBaiVietRepository.findById(id);
        if (nguoiDungThichBaiViet.isEmpty()){
            return "Người dùng thích bài viết không tồn tại!";
        }

        nguoiDungThichBaiViet.get().setDaXoa(true);
        nguoiDungThichBaiVietRepository.save(nguoiDungThichBaiViet.get());

        Optional<BaiViet> baiViet = baiVietRepository.findById(baiVietId);
        baiViet.get().setSoLuotThich(baiViet.get().getSoLuotThich()-1);
        baiVietRepository.save(baiViet.get());

        return "Bỏ like thành công!";
    }
}
