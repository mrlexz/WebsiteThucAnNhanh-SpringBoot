package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.HoaDon;
import com.example.demo.model.KhachHang;

public interface HoaDonRepository extends JpaRepository<HoaDon, String> {
    @Query("SELECT s FROM HoaDon s")
    Page<HoaDon> findHoaDons(Pageable pageable);
  
   @Query("SELECT hd FROM HoaDon hd  JOIN  KhachHang kh ON hd.khachHang.maKhachHang = kh.maKhachHang where kh.hoTenKhachHang  LIKE %:hoTenKhachHang%")
  Page<HoaDon> findHoaDonhd( String hoTenKhachHang, Pageable pageable);
//    @Query("SELECT ss FROM HoaDon ss")
//    Page<HoaDon> findHoaDonss(Pageable pageable);
    // List<HoaDon> findBykhachHang(KhachHang kh);
    
}

