package com.example.demo.controller;

import java.util.Arrays;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.DTO.RegisterDTO;
import com.example.demo.model.KhachHang;
import com.example.demo.model.TaiKhoan;
import com.example.demo.repository.KhachHangRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.TaiKhoanRepository;
import com.example.demo.service.TaiKhoanService;

@Controller
public class TaiKhoanController {
	@Autowired
	private TaiKhoanRepository taiKhoanRepository;
	@Autowired
	private TaiKhoanService TaiKhoanService;
	@Autowired
	private KhachHangRepository khachHangRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	
	
	@RequestMapping("/dangnhap")
	public String enterLoginPage() {

		return "dangnhap";
	}

	@RequestMapping("/dangky")
	public String enterRegister(Model model) {
		TaiKhoan taiKhoan = new TaiKhoan();
		RegisterDTO tkDTO = new RegisterDTO();
		
		KhachHang khachHang = new KhachHang();
		taiKhoan.setKhachHang(khachHang);
		khachHang.setTaiKhoan(taiKhoan);
		model.addAttribute("tkDTO", tkDTO);
		model.addAttribute("taikhoan", taiKhoan);
		model.addAttribute("khachHang", khachHang);
		return "dangky";
	}

	@RequestMapping(value = "/dangky", method = RequestMethod.POST)
	public String createNewUser(@Valid @ModelAttribute("tkDTO") RegisterDTO tkDTO,BindingResult bindingResult1,@Valid @ModelAttribute("khachHang") KhachHang khachHang, BindingResult bindingResult) {

		if (bindingResult.hasErrors()|| bindingResult1.hasErrors()) {
			return "dangky";
		} else {

//			KhachHang kh = taiKhoan.getKhachHang()
			KhachHang kh = new KhachHang(tkDTO.getHoTenKhachHang(),tkDTO.getEmail(), tkDTO.getSoDienThoai(), tkDTO.getDiaChi());
			TaiKhoan taiKhoan = new TaiKhoan(tkDTO.getTenTaiKhoan(), tkDTO.getMatKhau());
			kh.setTaiKhoan(taiKhoan);
			taiKhoan.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByTen("user"))));
			taiKhoan.setKhachHang(kh);
			if (TaiKhoanService.save(taiKhoan)) {
				khachHangRepository.save(kh);
				return "redirect:/dangnhap";
			}
		}
		return "dangky";
	}

	@PostMapping(value = "ajax/dangky")
	@ResponseBody
	public Boolean createNewUser(@RequestBody() String username) {
		if (username.length() > 0) {
			TaiKhoan tk = taiKhoanRepository.findByTenTaiKhoan(username);
			if (tk != null) {
				return true;
			}
		}
		return false;

	}

	@RequestMapping(value = "/dangxuat", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/taikhoan", method = RequestMethod.GET)
	public String currentUserName(Model model, Authentication authentication) {
		if (authentication != null) {
			String name = authentication.getName();
			TaiKhoan taiKhoan = taiKhoanRepository.findByTenTaiKhoan(name);
			model.addAttribute("taikhoan", taiKhoan);
			return "taikhoan";
		}
		return "redirect:/";

	}

	@RequestMapping(value = "/update-tk", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("taikhoan") TaiKhoan taiKhoan) {
		TaiKhoanService.update(taiKhoan);
		return "redirect:/taikhoan";
	}

}
