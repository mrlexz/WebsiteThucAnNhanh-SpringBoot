package com.example.demo.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.PaypalPaymentIntent;
import com.example.demo.PaypalPaymentMethod;
import com.example.demo.model.KhachHang;
import com.example.demo.model.TaiKhoan;
import com.example.demo.repository.KhachHangRepository;
import com.example.demo.repository.SanPhamRepository;
import com.example.demo.service.GioHangService;
import com.example.demo.service.PaypalService;
import com.example.demo.service.TaiKhoanService;
import com.example.demo.utils.Utils;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GioHangController {
	public static final String URL_PAYPAL_SUCCESS = "pay/success";
	public static final String URL_PAYPAL_CANCEL = "pay/cancel";

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private PaypalService paypalService;

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
		model.addAttribute("giohang", gioHangService.getDanhSachSanPham());
		model.addAttribute("tongtien", gioHangService.getTongTien());
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
		if (khachHang.isPresent()) {
			gioHangService.thanhToan(khachHang.get());
			return "redirect:/hoantat";
		}
		return "redirect:/giohang";
	}

	@GetMapping("/giohang/checkout")
	public String checkOut(Model model) {
		TaiKhoan taiKhoan = taiKhoanService.findByTen(SecurityContextHolder.getContext().getAuthentication().getName());
		Optional<KhachHang> khachHang = khachHangRepository.findById(taiKhoan.getMaTaiKhoan());
		model.addAttribute("giohang", gioHangService.getDanhSachSanPham());
		model.addAttribute("tongtien", gioHangService.getTongTien());
		model.addAttribute("khachhang", khachHang.get());
		return "thanhtoan";
	}

	@PostMapping("/pay")
	public String pay(HttpServletRequest request, @RequestParam("price") double price) {
		String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
		String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
		try {
			Payment payment = paypalService.createPayment(price, "USD", PaypalPaymentMethod.paypal,
					PaypalPaymentIntent.sale, "payment description", cancelUrl, successUrl);
			for (Links links : payment.getLinks()) {
				if (links.getRel().equals("approval_url")) {
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}

	@GetMapping(URL_PAYPAL_CANCEL)
	public String cancelPay() {
		return "cancel";
	}

	@GetMapping(URL_PAYPAL_SUCCESS)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,
			Authentication authentication) {
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if (payment.getState().equals("approved")) {
				TaiKhoan taiKhoan = taiKhoanService.findByTen(authentication.getName());
				Optional<KhachHang> khachHang = khachHangRepository.findById(taiKhoan.getMaTaiKhoan());
				if (khachHang.isPresent()) {
					gioHangService.thanhToan(khachHang.get());
					return "redirect:/hoantat";
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}

}
