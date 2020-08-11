package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.repository.KhachHangRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.TaiKhoanRepository;
import com.example.demo.service.TaiKhoanService;

@SpringBootApplication
public class DemoApplication {
	
	@Autowired
	private TaiKhoanRepository taiKhoanRepository;
	@Autowired
    private TaiKhoanService taiKhoanService;

	@Autowired
	private RoleRepository roleRepository;
	@Autowired 
	private KhachHangRepository khachHangRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
