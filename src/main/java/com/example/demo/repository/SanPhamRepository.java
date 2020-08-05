package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.SanPham;

public interface SanPhamRepository extends CrudRepository<SanPham, String> {
	@Query("SELECT s FROM SanPham s")
	Page<SanPham> findSanPhams(Pageable pageable);
	@Query("SELECT ss FROM SanPham ss WHERE ss.tenSanPham LIKE %:tenSanPham%")
	Page<SanPham> findSanPhamss(String tenSanPham,Pageable pageable);
}
