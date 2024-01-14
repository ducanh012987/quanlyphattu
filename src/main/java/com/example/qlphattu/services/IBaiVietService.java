package com.example.qlphattu.services;

import com.example.qlphattu.model.BaiViet;

public interface IBaiVietService {
    public String themBaiViet(BaiViet baiViet);

    public String xoaBaiViet(int id);

    public String suaBaiViet(BaiViet baiViet);
}
