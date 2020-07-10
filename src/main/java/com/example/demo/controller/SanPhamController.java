package com.example.demo.controller;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.demo.model.DanhGia;
import com.example.demo.model.KhachHang;
import com.example.demo.model.TaiKhoan;
import com.example.demo.repository.DanhGiaRepository;
import com.example.demo.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.SanPham;
import com.example.demo.repository.NhaSanXuatRepository;
import com.example.demo.repository.SanPhamRepository;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SanPhamController {
	@Autowired
	private SanPhamRepository sanPhamRepository;
	@Autowired
	private NhaSanXuatRepository nhaSanXuatRepository;
	@Autowired
	private DanhGiaRepository danhGiaRepository;
	@Autowired
	private TaiKhoanRepository taiKhoanRepository;

	@RequestMapping(value = "/sanpham")
	public String listSanPham(Model model,
			@RequestParam(name="page",required = false, defaultValue = "1") Optional<Integer> page ,
			@RequestParam(name="size",required = false, defaultValue = "6") Integer size,
			@RequestParam(name="sort",required = false, defaultValue = "ASC") String sort
			) {
		Sort sortable = null;
		if(sort.equals("ASC")) {
			sortable = Sort.by("id").ascending();
		}
		if(sort.equals("DESC")) {
			sortable = Sort.by("id").descending();
		}
		int currentPage = page.orElse(1);
		// Page nó đếm từ 0  - > end - Nên phải trừ giá trị hiện tại xuống 1 để khớp với cái Pageable
		Pageable pageable = PageRequest.of(currentPage - 1, size, sortable);
		Page<SanPham> pageSanPham = sanPhamRepository.findSanPhams(pageable);
		int totalPage = pageSanPham.getTotalPages();
		if(totalPage>0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("listSanPham", sanPhamRepository.findSanPhams(pageable));
		model.addAttribute("listNhaSanXuat",nhaSanXuatRepository.findAll());
		return "sanpham";
	}
	
	@RequestMapping(value = "/sanpham/{maSanPham}")
	public String getChitietSanPham(Model model,Model model2,
									@PathVariable(name = "maSanPham") String maSanPham) {
		Optional<SanPham> sp = sanPhamRepository.findById(maSanPham);
		DanhGia danhgia = new DanhGia();
		danhgia.setSanPham(sp.get());
		if(sp.isPresent()) {
			model.addAttribute("sanpham", sp.get());
			model.addAttribute("danhgianew",danhgia);
			return "chitiet-sanpham";
		}
		return "home";
	}
	@RequestMapping(value = "/sanpham_danhgia/{maSanPham}", method = RequestMethod.POST)
	public String danhGiaSP(@ModelAttribute("danhgianew") DanhGia danhGia) {
		Optional<TaiKhoan> tk = Optional.ofNullable(taiKhoanRepository.findByTenTaiKhoan(SecurityContextHolder.getContext().getAuthentication().getName()));
		if(tk.isPresent()){
			danhGia.setKhachHang(tk.get().getKhachHang());
			danhGiaRepository.save(danhGia);
		}
		return "redirect:/sanpham/{maSanPham}";
	}

}
