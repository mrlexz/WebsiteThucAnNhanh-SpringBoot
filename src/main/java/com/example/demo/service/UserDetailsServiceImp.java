package com.example.demo.service;


import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

import com.example.demo.model.Role;
import com.example.demo.model.TaiKhoan;
import com.example.demo.repository.TaiKhoanRepository;

public class UserDetailsServiceImp  implements UserDetailsService {

	@Autowired
	private TaiKhoanRepository taiKhoanRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TaiKhoan taikhoan = taiKhoanRepository.findByTenTaiKhoan(username);
		if(taikhoan!=null) {
			return toUserDetails(taikhoan);
		}
		else {
			throw new  UsernameNotFoundException("Tai khoan khong tim thay");
		}
	}
	private UserDetails toUserDetails(TaiKhoan taikhoan) {
		Set<Role> rolelist = taikhoan.getRoles();
		ArrayList<String> strs = new ArrayList<String>();
		for (Role role : rolelist) {
			strs.add(role.getTen());
		}
		String[] roles = strs.toArray(new String[0]);
		
		System.out.println( User.withUsername(taikhoan.getTenTaiKhoan())
				.password(taikhoan.getMatKhau())
				.roles(roles).build());
		return User.withUsername(taikhoan.getTenTaiKhoan())
				.password(taikhoan.getMatKhau())
				.roles(roles).build();
	}

}
