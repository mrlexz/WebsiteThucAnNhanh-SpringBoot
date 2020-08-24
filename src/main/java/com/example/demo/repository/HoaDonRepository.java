package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.DTO.ThongKeDTO;
import com.example.demo.model.HoaDon;

public interface HoaDonRepository extends JpaRepository<HoaDon, String> {
    @Query("SELECT s FROM HoaDon s")
    Page<HoaDon> findHoaDons(Pageable pageable);
  
   @Query("SELECT hd FROM HoaDon hd  JOIN  KhachHang kh ON hd.khachHang.maKhachHang = kh.maKhachHang where kh.hoTenKhachHang  LIKE %:hoTenKhachHang%")
   
 Page<HoaDon> findHoaDonhd( String hoTenKhachHang, Pageable pageable);

 @Query(value = "SELECT count(s) as soLuong , s.ngayLap as ngayLap FROM HoaDon s where s.ngayLap BETWEEN :startDate AND :endDate GROUP BY s.ngayLap")
  public List<ThongKeDTO> getHoaDonBetweenDatesTest(LocalDate startDate, LocalDate endDate);
  
    
    
}

