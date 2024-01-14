package com.example.qlphattu.services;

import com.example.qlphattu.model.DonDangKy;
import com.example.qlphattu.model.PhatTu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface IPhatTuService {
    public String dangKy(PhatTu phatTu);

    public String dangNhap(String tenTaiKhoan, String matKhau);

    public String doiMatKhau(String matKhauHienTai, String matKhauMoi);

    public String quenMatKhau(String tenTaiKhoan, String email);

    public String taoMatKhauMoi(String matKhauMoi, String maXacNhan);

    public Page<PhatTu> timPhatTuTheoPhapDanh(String phapDanh, Pageable pageable);

    public Page<PhatTu> timPhatTuTheoEmail(String email, Pageable pageable);

    public Page<PhatTu> timPhatTuTheoGioiTinh(String gioiTinh, Pageable pageable);

    public Map uploadImage(MultipartFile file);
}
