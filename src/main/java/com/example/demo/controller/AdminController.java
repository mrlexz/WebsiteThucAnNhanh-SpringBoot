package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DTO.ChiTietHoaDonDTO;
import com.example.demo.model.HoaDon;
import com.example.demo.model.NhaSanXuat;
import com.example.demo.model.SanPham;
import com.example.demo.repository.HoaDonRepository;
import com.example.demo.repository.NhaSanXuatRepository;
import com.example.demo.repository.SanPhamRepository;

@Controller
public class AdminController {
	@Autowired
	private SanPhamRepository sanPhamRepository;
	@Autowired
	private HoaDonRepository hoaDonRepository;

	@Autowired
	private NhaSanXuatRepository nhaSanXuatRepository;

	@RequestMapping(value = "/quanly")
	public String quanlyPage(Model model) {

		return "quanly-sanpham";
	}

	@RequestMapping(value = "/quanly/donhang")
	public String listDonHang(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "1") Optional<Integer> page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("ngayLap").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("ngayLap").descending();
		}
		int currentPage = page.orElse(1);
		// Page nó đếm từ 0 - > end - Nên phải trừ giá trị hiện tại xuống 1 để khớp với
		// cái Pageable
		Pageable pageable = PageRequest.of(currentPage - 1, size, sortable);
		Page<HoaDon> pageHoaDon = hoaDonRepository.findHoaDons(pageable);
		ArrayList<ChiTietHoaDonDTO> listDTO = new ArrayList<ChiTietHoaDonDTO>();

		for (int i = 0; i < pageHoaDon.getSize() - 1; i++) {
			ChiTietHoaDonDTO chiTietDTO = new ChiTietHoaDonDTO(pageHoaDon.getContent().get(i).getMaHoaDon(),
					pageHoaDon.getContent().get(i).getNgayLap(),
					pageHoaDon.getContent().get(i).getKhachHang().getHoTenKhachHang(),
					pageHoaDon.getContent().get(i).getKhachHang().getSoDienThoai(),
					pageHoaDon.getContent().get(i).getKhachHang().getDiaChi(),
					pageHoaDon.getContent().get(i).getTongTien(),
					pageHoaDon.getContent().get(i).getDssp().get(0).getSanPham().getTenSanPham(),
					pageHoaDon.getContent().get(i).getDssp().get(0).getSoLuong(),
					pageHoaDon.getContent().get(i).getDssp().get(0).getDonGia());

			listDTO.add(chiTietDTO);
		}
		Page<ChiTietHoaDonDTO> cthdDTO = new PageImpl<ChiTietHoaDonDTO>(listDTO, pageable, listDTO.size());
		cthdDTO.getSort().descending();
		model.addAttribute("listDTO", cthdDTO);
		for (int j = 0; j < cthdDTO.getContent().size(); j++) {
			System.out.println(cthdDTO.getContent().get(j).getHoTenKhachHang());
		}

		int totalPage = pageHoaDon.getTotalPages();
		if (totalPage > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "quanly-donhang";
	}

	// Quản lý sản phẩm
	@RequestMapping(value = "/quanly/sanpham")
	public String listSanPham(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "1") Optional<Integer> page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("id").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("id").descending();
		}
		int currentPage = page.orElse(1);
		// Page nó đếm từ 0 - > end - Nên phải trừ giá trị hiện tại xuống 1 để khớp với
		// cái Pageable
		Pageable pageable = PageRequest.of(currentPage - 1, size, sortable);
		Page<SanPham> pageSanPham = sanPhamRepository.findSanPhams(pageable);
		int totalPage = pageSanPham.getTotalPages();
		if (totalPage > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		SanPham sanphamnew = new SanPham();
		// TEST
		Random rd = new Random();
		int maSp = rd.nextInt();
		// ----
//		sanphamnew.setMaSanPham("" + maSp);
//		sanphamnew.setNhaSanXuat(new NhaSanXuat("nsx" + maSp, "abc", "odaudo"));
		model.addAttribute("modalsanpham", new SanPham());
//		sanPhamRepository.save(sanphamnew);
		model.addAttribute("listSanPham", sanPhamRepository.findSanPhams(pageable));
		return "quanly-sanpham";
	}

	// Thêm sản phẩm
	@RequestMapping(value = "/quanly/sanpham", method = RequestMethod.POST)
	public String addSanPham(@ModelAttribute(name = "modalsanpham") SanPham sanPham) {
		Random rd = new Random();
		int maSp = rd.nextInt();
		// ----
		sanPham.setMaSanPham("" + maSp);
		sanPham.setNhaSanXuat(new NhaSanXuat("1", "abc", "odaudo"));
		if (sanPhamRepository.save(sanPham).equals(sanPham)) {
			return "redirect:/quanly/sanpham";
		}
		return null;
	}

//Xóa sản phẩm
	@RequestMapping(value = "/quanly/sanpham/delete/{id}", method = RequestMethod.DELETE)
	public String deleteSanPham(Model model, @PathVariable(name = "id") String maSanPham) {
		System.out.println(maSanPham);
		if (sanPhamRepository.findById(maSanPham).isPresent()) {
			sanPhamRepository.delete(sanPhamRepository.findById(maSanPham).get());
			return "redirect:/quanly/sanpham";
		}
		return null;
	}
// Sửa sản phẩm
	@RequestMapping(value = "/quanly/sanpham/edit/{id}", method = RequestMethod.POST)
	public String editSanPham(@ModelAttribute(name = "modalupdatesanpham") SanPham sanPham, @PathVariable(name = "id") String maSanPham) {
//		if(sanPhamRepository.findById(maSanPham).isPresent()) {
		System.out.println(sanPham.getTenSanPham());
		System.out.println(sanPham + "aaaaaaaaaaaaaasssss");
//		System.out.println(sanPham + "ádfghjk");
			return "redirect:/quanly/sanpham";
	}

	// Quan ly nha san xuat
	@RequestMapping("/quanly/nhasanxuat")
	public String nhaSanXuatpage(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "1") Optional<Integer> page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("maNhaSanXuat").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("maNhaSanXuat").descending();
		}
		int currentPage = page.orElse(1);
		// Page nó đếm từ 0 - > end - Nên phải trừ giá trị hiện tại xuống 1 để khớp với
		// cái Pageable
		Pageable pageable = PageRequest.of(currentPage - 1, size, sortable);
		Page<NhaSanXuat> pageNhaSanXuat = nhaSanXuatRepository.findNhaSanXuats(pageable);
		int totalPage = pageNhaSanXuat.getTotalPages();
		if (totalPage > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("modelnhasanxuat", new NhaSanXuat());
		model.addAttribute("listNhaSanXuat", nhaSanXuatRepository.findNhaSanXuats(pageable));
//		System.out.println(nhaSanXuatRepository.findAll());
		return "nhasanxuat";
	}

//Thêm nhà sản xuất
	@PostMapping("/quanly/nhasanxuat")
	public String addNhaSanXuat(@ModelAttribute(name = "modelnhasanxuat") NhaSanXuat nsx) {
		
		nsx.getTenNhaSanXuat();
		if (nhaSanXuatRepository.save(nsx).equals(nsx)) {
			return "redirect:/quanly/nhasanxuat";
		}
		return null;
	}

//Xóa nhà sản xuất
	@RequestMapping(value = "/quanly/nhasanxuat/delete/{id}", method = RequestMethod.DELETE)
	public String deleteNhaSanXuat(Model model, @PathVariable(name = "id") String maNhaSanXuat) {
		if (nhaSanXuatRepository.findById(maNhaSanXuat).isPresent()) {
			nhaSanXuatRepository.delete(nhaSanXuatRepository.findById(maNhaSanXuat).get());
			return "redirect:/quanly/nhasanxuat";
		}
		return null;
	}

}
