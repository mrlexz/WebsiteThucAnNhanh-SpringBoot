package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
@IdClass(ChiTietHoaDonPK.class)
public class ChiTietHoaDon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	@JoinColumn(name="mahoadon",referencedColumnName = "maHoaDon")
	private HoaDon hoaDon;
	@Id
	@ManyToOne
	@JoinColumn(name="maSanPham", referencedColumnName = "maSanPham")
	private SanPham sanPham;
	private double donGia;
	private int soLuong;
	public ChiTietHoaDon() {
		super();
	}
	public ChiTietHoaDon(double donGia, int soLuong, SanPham sanPham) {
		super();
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.sanPham = sanPham;
	}
	
	public ChiTietHoaDon(HoaDon hoaDon, SanPham sanPham, double donGia, int soLuong) {
		super();
		this.hoaDon = hoaDon;
		this.sanPham = sanPham;
		this.donGia = donGia;
		this.soLuong = soLuong;
	}
	
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	@Override
	public String toString() {
		return "ChiTietHoaDon [donGia=" + donGia + ", soLuong=" + soLuong + "]";
	}
	
	

}
