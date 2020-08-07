package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.NhaSanXuat;
import com.example.demo.model.SanPham;

public interface NhaSanXuatRepository extends CrudRepository<NhaSanXuat, String> {
	@Query("SELECT s FROM NhaSanXuat s")
    Page<NhaSanXuat> findNhaSanXuats(Pageable pageable);
	
	@Query("SELECT ss FROM NhaSanXuat ss WHERE ss.tenNhaSanXuat LIKE %:tenNhaSanXuat%")
	Page<NhaSanXuat> findNhaSanXuatss(String tenNhaSanXuat,Pageable pageable);
	NhaSanXuat findByTenNhaSanXuat(String ten);
}
