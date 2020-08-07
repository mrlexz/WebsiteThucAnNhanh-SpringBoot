package com.example.demo.DTO;

import java.time.LocalDate;

import com.example.demo.model.SanPham;

public class ChiTietHoaDonDTO {
	private String maHoaDon;
	private LocalDate ngayLap;
	private String hoTenKhachHang;
	private String soDienThoai;
	private String diaChi;
	private double tongTien;
	private String tenSanPham;
	private int soLuong;
	private double donGia;

	public ChiTietHoaDonDTO(String maHoaDon, LocalDate ngayLap, String hoTenKhachHang, String soDienThoai, String diaChi,
			double tongTien, String tenSanPham, int soLuong, double donGia) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayLap = ngayLap;
		this.hoTenKhachHang = hoTenKhachHang;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.tongTien = tongTien;
		this.tenSanPham = tenSanPham;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}
	

	public ChiTietHoaDonDTO(String maHoaDon, LocalDate ngayLap, String hoTenKhachHang, String soDienThoai, String diaChi,
			double tongTien, int soLuong) {
		
		this.maHoaDon = maHoaDon;
		this.ngayLap = ngayLap;
		this.hoTenKhachHang = hoTenKhachHang;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.tongTien = tongTien;
		this.soLuong = soLuong;
	}


	public LocalDate getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}

	public String getHoTenKhachHang() {
		return hoTenKhachHang;
	}

	public void setHoTenKhachHang(String hoTenKhachHang) {
		this.hoTenKhachHang = hoTenKhachHang;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDonDTO [maHoaDon=" + maHoaDon + ", ngayLap=" + ngayLap + ", tenKhachHang=" + hoTenKhachHang
				+ ", soDienThoai=" + soDienThoai + ", diaChi=" + diaChi + ", tongTien=" + tongTien + ", tenSanPham="
				+ tenSanPham + ", soLuong=" + soLuong + ", donGia=" + donGia + "]";
	}

}
