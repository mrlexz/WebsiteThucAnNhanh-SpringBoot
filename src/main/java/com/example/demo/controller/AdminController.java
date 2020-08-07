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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.ChiTietHoaDonDTO;
import com.example.demo.model.HoaDon;
import com.example.demo.model.KhachHang;
import com.example.demo.model.NhaSanXuat;
import com.example.demo.model.SanPham;
import com.example.demo.repository.HoaDonRepository;
import com.example.demo.repository.KhachHangRepository;
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

	@Autowired
	private KhachHangRepository khRepository;

	@RequestMapping(value = "/quanly")
	public String quanlyPage(Model model) {

		return "quanly-sanpham";
	}

//Quản lý đơn hàng
	@RequestMapping(value = "/quanly/donhang")
	public String listDonHang(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "1") Optional<Integer> page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort,
			@RequestParam(name = "seachTenKH", required = false, defaultValue = "") String searchTenKH) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("ngayLap").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("ngayLap").descending();
		}
		int currentPage = page.orElse(1);
		// Page nó đếm từ 0 - > end - Nên phải trừ giá trị hiện tại xuống 1 để khớp với
//		 cái Pageable
		Pageable pageable = PageRequest.of(currentPage - 1, size, sortable);
		Page<HoaDon> pageHoaDon = hoaDonRepository.findHoaDons(pageable);
		ArrayList<ChiTietHoaDonDTO> listDTO = new ArrayList<ChiTietHoaDonDTO>();
		for (int i = 0; i < pageHoaDon.getNumberOfElements(); i++) {
			ChiTietHoaDonDTO chiTietDTO = new ChiTietHoaDonDTO(pageHoaDon.getContent().get(i).getMaHoaDon(),
					pageHoaDon.getContent().get(i).getNgayLap(),
					pageHoaDon.getContent().get(i).getKhachHang().getHoTenKhachHang(),
					pageHoaDon.getContent().get(i).getKhachHang().getSoDienThoai(),
					pageHoaDon.getContent().get(i).getKhachHang().getDiaChi(),
					pageHoaDon.getContent().get(i).getTongTien(), pageHoaDon.getContent().get(i).getDssp().size());
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
			model.addAttribute("searchTenKH", searchTenKH);
			model.addAttribute("total", pageHoaDon.getNumberOfElements());
		}
		return "quanly-donhang";
	}

// Quản lý sản phẩm
	@RequestMapping(value = "/quanly/sanpham")
	public String listSanPham(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "1") Optional<Integer> page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
			@ModelAttribute(name = "sanPhamEdit") SanPham sanPham,
			@RequestParam(name = "name", defaultValue = "", required = false) String name) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("donGia").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("donGia").descending();
		}
		int currentPage = page.orElse(1);
		// Page nó đếm từ 0 - > end - Nên phải trừ giá trị hiện tại xuống 1 để khớp với
		// cái Pageable
		Pageable pageable = PageRequest.of(currentPage - 1, size, sortable);
		Page<SanPham> pageSanPham = sanPhamRepository.findSanPhamss(name, pageable);

		int totalPage = pageSanPham.getTotalPages();
		if (totalPage > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		SanPham sanphamnew = new SanPham();
		Random rd = new Random();
		int maSp = rd.nextInt();
		model.addAttribute("modalsanpham", new SanPham());
		model.addAttribute("name", name);
		model.addAttribute("listSanPham", sanPhamRepository.findSanPhamss(name, pageable));
		model.addAttribute("total", pageSanPham.getNumberOfElements());
		return "quanly-sanpham";
	}

// Thêm sản phẩm
	@RequestMapping(value = "/quanly/sanpham", method = RequestMethod.POST)
	public String addSanPham(@ModelAttribute(name = "modalsanpham") SanPham sanPham) {
		Random rd = new Random();
		int maSp = rd.nextInt(1000);
		sanPham.setMaSanPham("SP" + maSp);
		sanPham.setNhaSanXuat(new NhaSanXuat(sanPham.getNhaSanXuat().getMaNhaSanXuat(),
				sanPham.getNhaSanXuat().getTenNhaSanXuat(), sanPham.getNhaSanXuat().getDiaChi()));
		if (sanPhamRepository.save(sanPham).equals(sanPham)) {
			return "redirect:/quanly/sanpham";
		}
		return null;
	}

//Ajax thêm sản phẩm
	@PostMapping(value = "/ajax/createsanpham")
	@ResponseBody
	public boolean checkNameProduct(@RequestBody String nameProduct) {
		System.out.println(nameProduct);
		if (nameProduct.length() > 0) {
			SanPham sp = sanPhamRepository.findByTenSanPham(nameProduct);
			if (sp != null) {
				return true;
			}
		}
		return false;
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
	@PostMapping(value = "/quanly/sanpham/edit/{id}")
	public String editSanPham(@ModelAttribute(name = "sanPhamEdit") SanPham sanPham,
			@PathVariable(name = "id") String maSanPham) {

//	if(sanPhamRepository.findById(maSanPham).isPresent()) {
//		sanPham.setMaSanPham(maSanPham);
//		
//	}

		System.out.println(sanPham.getTenSanPham());
//		System.out.println(sanPhamRepository.findById(maSanPham) + "aaaaaaaaaaaaaa");
//		SanPham sp = sanPhamRepository.findById(maSanPham).get();
//		System.out.println(sp.getTenSanPham());
//		System.out.println(sanPham.getTenSanPham());
//		System.out.println(sanPham + "aaaaaaaaaaaaaasssss");
////		System.out.println(sanPham + "ádfghjk");
		return "redirect:/quanly/sanpham";
	}

//Quan ly nha san xuat
	@RequestMapping("/quanly/nhasanxuat")
	public String nhaSanXuatpage(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "1") Optional<Integer> page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort,
			@RequestParam(name = "searchNsx", required = false, defaultValue="") String searchNsx) {
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
		Page<NhaSanXuat> pageNhaSanXuat = nhaSanXuatRepository.findNhaSanXuatss(searchNsx, pageable);
		int totalPage = pageNhaSanXuat.getTotalPages();
		if (totalPage > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("modelnhasanxuat", new NhaSanXuat());
		model.addAttribute("searchNsx", searchNsx);
		model.addAttribute("listNhaSanXuat", nhaSanXuatRepository.findNhaSanXuatss(searchNsx, pageable));
		model.addAttribute("total", pageNhaSanXuat.getNumberOfElements());
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

//Ajax nhà sản xuất popup
	@GetMapping(value = "/ajax/nsx")
	@ResponseBody
	public List<NhaSanXuat> ajax() {
		List<NhaSanXuat> list = new ArrayList<NhaSanXuat>();
		list = (List<NhaSanXuat>) nhaSanXuatRepository.findAll();
		return list;
	}

//Ajax thêm nhà sản xuất
	@PostMapping(value = "/ajax/creatensx")
	@ResponseBody
	public boolean checkNameProducer(@RequestBody String nameProducer) {
		System.out.println(nameProducer);
		if (nameProducer.length() > 0) {
			NhaSanXuat nsx = nhaSanXuatRepository.findByTenNhaSanXuat(nameProducer);
			if (nsx != null) {
				return true;
			}
		}
		return false;
	}
}