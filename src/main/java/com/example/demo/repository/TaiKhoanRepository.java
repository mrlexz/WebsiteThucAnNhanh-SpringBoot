package com.example.demo.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.SanPham;
import com.example.demo.model.TaiKhoan;
public interface TaiKhoanRepository extends CrudRepository<TaiKhoan, String>{
	
	@Query("SELECT s FROM TaiKhoan s")
	Page<TaiKhoan> findTaiKhoans(Pageable pageable);
	TaiKhoan findByTenTaiKhoan(String ten);
}
