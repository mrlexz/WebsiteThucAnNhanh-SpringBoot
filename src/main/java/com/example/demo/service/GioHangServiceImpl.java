package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.model.ChiTietHoaDon;
import com.example.demo.model.HoaDon;
import com.example.demo.model.KhachHang;
import com.example.demo.model.SanPham;
import com.example.demo.repository.HoaDonRepository;
import com.example.demo.repository.SanPhamRepository;


@Service
@Scope(value = 	WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class GioHangServiceImpl implements GioHangService {
	@Autowired
	private SanPhamRepository sanPhamRepository;

	@Autowired
	private HoaDonRepository hoaDonRepository;

	private Map<SanPham, Integer> danhSachSanPham = new HashMap<>();
	
	@Override
	public void themSanPham(SanPham sanpham) {
		// TODO Auto-generated method stub
		if(danhSachSanPham.containsKey(sanpham)) {
			danhSachSanPham.replace(sanpham, danhSachSanPham.get(sanpham)+1);
		}
		else {
			danhSachSanPham.put(sanpham, 1);
		}
	}

	@Override
	public void xoaSanPham(SanPham sanpham) {
		// TODO Auto-generated method stub
		if(danhSachSanPham.containsKey(sanpham)) {
			if(danhSachSanPham.get(sanpham) > 1 )
				danhSachSanPham.replace(sanpham, danhSachSanPham.get(sanpham)-1);
			else 
				danhSachSanPham.remove(sanpham);
		}

	}

	@Override
	public Map<SanPham, Integer> getDanhSachSanPham() {
		// TODO Auto-generated method stub
		return Collections.unmodifiableMap(danhSachSanPham);
	}

	@Override
	public void thanhToan(KhachHang khachHang) {
		HoaDon hd = new HoaDon();
		List<ChiTietHoaDon> listct = new ArrayList<>();
		hd.setNgayLap(LocalDate.now());
		SanPham sanpham;
		for (Map.Entry<SanPham, Integer> entry : danhSachSanPham.entrySet()) {
			Optional<SanPham> temp = sanPhamRepository.findById(entry.getKey().getMaSanPham());
			sanpham = temp.get();
			ChiTietHoaDon cthd = new ChiTietHoaDon(sanpham.getDonGia(),entry.getValue(),sanpham);
			cthd.setHoaDon(hd);
			listct.add(cthd);
		}
		hd.setDssp(listct);
		hd.setKhachHang(khachHang);
		hoaDonRepository.save(hd);
		sanPhamRepository.saveAll(danhSachSanPham.keySet());
		danhSachSanPham.clear();
	}
	
	@Override
	public double getTongTien() {
		double tongTien = 0;
		for (Map.Entry<SanPham, Integer> entry : danhSachSanPham.entrySet()) {
			tongTien+=entry.getKey().getDonGia()*entry.getValue();
		}
		return tongTien;
	}

}
