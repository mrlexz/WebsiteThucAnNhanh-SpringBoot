package com.example.demo.service;

import java.math.BigDecimal;
import java.util.Map;

import com.example.demo.model.KhachHang;
import com.example.demo.model.SanPham;

public interface GioHangService {
	void themSanPham(SanPham sanpham);

    void xoaSanPham(SanPham sanpham);

    Map<SanPham, Integer> getDanhSachSanPham();

    void thanhToan(KhachHang khachHang);

    double getTongTien();
}
