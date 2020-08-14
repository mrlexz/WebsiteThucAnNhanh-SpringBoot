package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.DanhGia;
import com.example.demo.model.SanPham;
import com.example.demo.model.TaiKhoan;
import com.example.demo.repository.DanhGiaRepository;
import com.example.demo.repository.NhaSanXuatRepository;
import com.example.demo.repository.SanPhamRepository;
import com.example.demo.repository.TaiKhoanRepository;

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
			@RequestParam(name = "page", required = false, defaultValue = "1") Optional<Integer> page,
			@RequestParam(name = "size", required = false, defaultValue = "6") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
			@RequestParam(name = "name", required = false, defaultValue = "") String name,
			@RequestParam(name = "price", required = false, defaultValue ="") String price
	) {
		if (!price.equals("")) {
			double start = 0;
			double end = 50;
			if (price.equals("loai1")) {
				start = 0;
				end = 50;
			}
			if (price.equals("loai2")) {
				start = 50;
				end = 100;
			}
			if (price.equals("loai3")) {
				start = 100;
				end = 1000;
			}
			Sort sortable = null;
			if (sort.equals("ASC")) {
				sortable = Sort.by("id").ascending();
			}
			if (sort.equals("DESC")) {
				sortable = Sort.by("id").descending();
			}
			int currentPage = page.orElse(1);
			Pageable pageable = PageRequest.of(currentPage - 1, size, sortable);
			Page<SanPham> pageSanPham = sanPhamRepository.findSanPhamsp(name, start, end, pageable);
			int totalPage = pageSanPham.getTotalPages();
			if (totalPage > 0) {
				List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
				model.addAttribute("pageNumbers", pageNumbers);
				model.addAttribute("total", pageSanPham.getTotalElements());

			}
			model.addAttribute("listSanPham", sanPhamRepository.findSanPhamsp(name, start, end, pageable));
			model.addAttribute("name", name);
			model.addAttribute("price", price);
			return "sanpham";

		} else {
			Sort sortable = null;
			if (sort.equals("ASC")) {
				sortable = Sort.by("id").ascending();
			}
			if (sort.equals("DESC")) {
				sortable = Sort.by("id").descending();
			}
			int currentPage = page.orElse(1);
			Pageable pageable = PageRequest.of(currentPage - 1, size, sortable);
			Page<SanPham> pageSanPham = sanPhamRepository.findSanPhamss(name, pageable);
			int totalPage = pageSanPham.getTotalPages();
			if (totalPage > 0) {
				List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
				model.addAttribute("pageNumbers", pageNumbers);
			}
			model.addAttribute("listSanPham", sanPhamRepository.findSanPhamss(name, pageable));
			model.addAttribute("name", name);
			model.addAttribute("price", price);
			
			model.addAttribute("total", pageSanPham.getTotalElements());
			model.addAttribute("listNhaSanXuat", nhaSanXuatRepository.findAll());
			return "sanpham";
		}
	}

	@RequestMapping(value = "/sanpham/{maSanPham}")
	public String getChitietSanPham(Model model, Model model2, @PathVariable(name = "maSanPham") String maSanPham) {
		Optional<SanPham> sp = sanPhamRepository.findById(maSanPham);
		DanhGia danhgia = new DanhGia();
		danhgia.setSanPham(sp.get());
		if (sp.isPresent()) {
			model.addAttribute("sanpham", sp.orElse(null));
			model.addAttribute("danhgianew", danhgia);

		}
		return "chitiet-sanpham";
	}

	@RequestMapping(value = "/sanpham_danhgia/{maSanPham}", method = RequestMethod.POST)
	public String danhGiaSP(@ModelAttribute("danhgianew") DanhGia danhGia) {
		Optional<TaiKhoan> tk = Optional.ofNullable(
				taiKhoanRepository.findByTenTaiKhoan(SecurityContextHolder.getContext().getAuthentication().getName()));
		if (tk.isPresent()) {
			danhGia.setKhachHang(tk.get().getKhachHang());
			danhGiaRepository.save(danhGia);
		}
		return "redirect:/sanpham/{maSanPham}";
	}

}
