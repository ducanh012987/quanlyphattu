package com.example.qlphattu.services;

public interface IBinhLuanBaiVietService {
    public String themBinhLuanBaiViet(int baiVietId, String binhLuan);

    public String xoaBinhLuanBaiViet(int id);

    public String suaBinhLuanBaiViet(int id, String binhLuanSua);
}
