package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.KhachHang;

public interface KhachHangRepository extends CrudRepository<KhachHang, String> {
	  @Query("SELECT s FROM KhachHang s")
	    Page<KhachHang> findKhachHangs(Pageable pageable);
	  @Query("SELECT ss FROM KhachHang ss WHERE ss.hoTenKhachHang LIKE %:hoTenKhachHang%")
		Page<KhachHang> findKhachHangss(String hoTenKhachHang,Pageable pageable);
	  
	KhachHang findBymaKhachHang(String name);
	List<KhachHang> findByhoTenKhachHang(String name);
}
