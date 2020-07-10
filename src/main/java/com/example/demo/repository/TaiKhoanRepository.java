package com.example.demo.repository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.TaiKhoan;
public interface TaiKhoanRepository extends CrudRepository<TaiKhoan, String>{
	TaiKhoan findByTenTaiKhoan(String ten);
}
