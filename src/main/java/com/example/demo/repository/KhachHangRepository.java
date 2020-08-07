package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.KhachHang;

public interface KhachHangRepository extends CrudRepository<KhachHang, String> {

	KhachHang findBymaKhachHang(String name);
	List<KhachHang> findByhoTenKhachHang(String name);
}
