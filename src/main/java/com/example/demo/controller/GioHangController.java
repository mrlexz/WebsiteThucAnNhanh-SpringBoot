package com.example.demo.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.KhachHang;
import com.example.demo.model.TaiKhoan;
import com.example.demo.repository.KhachHangRepository;
import com.example.demo.repository.SanPhamRepository;
import com.example.demo.service.GioHangService;
import com.example.demo.service.TaiKhoanService;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GioHangController {

	@Autowired
	private GioHangService gioHangService;

	@Autowired
	private SanPhamRepository sanPhamRepository;

	@Autowired
	private KhachHangRepository khachHangRepository;
	
	@Autowired
	private TaiKhoanService taiKhoanService;
	@RequestMapping("/hoantat")
	public String hoanTatDatHang() {
		return "hoantat";
	}
	@GetMapping("/giohang")
	public String xemGioHang(Model model) {
		model.addAttribute("giohang",gioHangService.getDanhSachSanPham());
		model.addAttribute("tongtien",gioHangService.getTongTien());
		return "giohang";
	}
	@GetMapping("/giohang/themsanpham/{maSanPham}")
	public String themSanPham(@PathVariable("maSanPham") String maSanPham) {
		sanPhamRepository.findById(maSanPham).ifPresent(gioHangService::themSanPham);
		return "redirect:/giohang";
	}
	@GetMapping("/giohang/xoasanpham/{maSanPham}")
	public String xoaSanPham(@PathVariable("maSanPham") String maSanPham) {
		sanPhamRepository.findById(maSanPham).ifPresent(gioHangService::xoaSanPham);
		return "redirect:/giohang";
	}
	@GetMapping("/giohang/thanhtoan")
	public String thanhtoan(Authentication authentication) {
		TaiKhoan taiKhoan = taiKhoanService.findByTen(authentication.getName());
		Optional<KhachHang> khachHang = khachHangRepository.findById(taiKhoan.getMaTaiKhoan());
		if(khachHang.isPresent()) {
			gioHangService.thanhToan(khachHang.get());
			return "redirect:/hoantat";
		}
		return "redirect:/giohang";
	}
	@GetMapping("/giohang/checkout")
	public String checkOut(Model model) {
		TaiKhoan taiKhoan = taiKhoanService.findByTen(SecurityContextHolder.getContext().getAuthentication().getName());
		Optional<KhachHang> khachHang = khachHangRepository.findById(taiKhoan.getMaTaiKhoan());
		model.addAttribute("giohang",gioHangService.getDanhSachSanPham());
		model.addAttribute("tongtien",gioHangService.getTongTien());
		model.addAttribute("khachhang",khachHang.get());
		return "thanhtoan";
	}

}
