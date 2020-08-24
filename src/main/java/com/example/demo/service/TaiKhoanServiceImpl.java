package com.example.demo.service;


import com.example.demo.model.KhachHang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.TaiKhoan;
import com.example.demo.repository.TaiKhoanRepository;


import java.util.Optional;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {
	@Autowired
	private TaiKhoanRepository taiKhoanRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public boolean save(TaiKhoan taikhoan) {
		if(taiKhoanRepository.findByTenTaiKhoan(taikhoan.getTenTaiKhoan()) == null) {
			taikhoan.setMatKhau(bCryptPasswordEncoder.encode(taikhoan.getMatKhau()));
			taiKhoanRepository.save(taikhoan);
			return true;
		}
		return false;
	}

	@Override
	public void update(TaiKhoan taiKhoan) {
		Optional<TaiKhoan> tk = Optional.ofNullable(taiKhoanRepository.findByTenTaiKhoan(SecurityContextHolder.getContext().getAuthentication().getName()));
		if(tk.isPresent()) {
			KhachHang kh = taiKhoan.getKhachHang();
			kh.setMaKhachHang(tk.get().getKhachHang().getMaKhachHang());
			tk.get().setKhachHang(kh);
			taiKhoanRepository.save(tk.get());
		}
	}

	@Override
	public TaiKhoan findByTen(String ten) {
		Iterable<TaiKhoan> taiKhoanList = taiKhoanRepository.findAll();
		for(TaiKhoan taiKhoan:taiKhoanList){
			if(taiKhoan.getTenTaiKhoan().equals(ten))
				return taiKhoan;
		}
		return null;
	}
}
