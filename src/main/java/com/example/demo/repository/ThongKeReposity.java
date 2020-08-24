package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.DTO.ThongKeDTO;
import com.example.demo.model.HoaDon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ThongKeReposity  extends JpaRepository<HoaDon, Integer> {


@Query(value = "SELECT  hd.ngay_lap as ngayBan , sum(cthd.so_luong) as soLuong FROM hoa_don  hd join chi_tiet_hoa_don cthd on  hd.ma_hoa_don = cthd.mahoadon where hd.ngay_lap BETWEEN :startDate AND :endDate GROUP BY hd.ngay_lap order by hd.ngay_lap ",nativeQuery = true)
public List<ThongKeDTO> transactions(LocalDate startDate, LocalDate endDate);



}