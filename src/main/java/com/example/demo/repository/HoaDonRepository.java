package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.HoaDon;

public interface HoaDonRepository extends JpaRepository<HoaDon, String> {
    @Query("SELECT s FROM HoaDon s")
    Page<HoaDon> findHoaDons(Pageable pageable);

}

