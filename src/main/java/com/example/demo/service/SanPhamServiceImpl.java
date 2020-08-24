package com.example.demo.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.model.KhachHang;
import com.example.demo.model.SanPham;
import com.example.demo.model.TaiKhoan;
import com.example.demo.repository.SanPhamRepository;
@Service
public class SanPhamServiceImpl implements SanPhamService {
	@Autowired
	private SanPhamRepository re;

	@Override
	public ArrayList<SanPham> timSanPhamtheoTen() {
		return null;
	}

}
