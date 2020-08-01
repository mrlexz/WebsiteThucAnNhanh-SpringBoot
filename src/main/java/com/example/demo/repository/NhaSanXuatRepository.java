package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.NhaSanXuat;

public interface NhaSanXuatRepository extends CrudRepository<NhaSanXuat, String> {
	@Query("SELECT s FROM NhaSanXuat s")
    Page<NhaSanXuat> findNhaSanXuats(Pageable pageable);
}
