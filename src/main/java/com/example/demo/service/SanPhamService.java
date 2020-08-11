package com.example.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.SanPham;
@Service
public interface SanPhamService {
	
	ArrayList<SanPham> timSanPhamtheoTen();
}
