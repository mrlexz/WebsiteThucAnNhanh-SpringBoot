package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class SanPham implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
//	@GeneratedValue(generator="system-uuid")
//	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String maSanPham;
	private double donGia;
	@Column(columnDefinition = "NVARCHAR(255)")
	private String tenSanPham;
	@Column(columnDefinition = "NVARCHAR(255)")
	private String moTa;
	private int namSanXuat;
	private String imgURL;
	@OneToMany(mappedBy ="sanPham" )
	private List<DanhGia> danhGias;
	@OneToMany(mappedBy = "sanPham")
	private List<ChiTietHoaDon> chiTietHoaDons;
	@ManyToOne
	@JoinColumn(name="maNhaSanXuat", referencedColumnName = "maNhaSanXuat")
	private NhaSanXuat nhaSanXuat;
	
	
	public SanPham() {
		super();
	}
	public SanPham(String maSanPham, double donGia, String tenSanPham, String moTa, int namSanXuat,String imgURL) {
		super();
		this.maSanPham = maSanPham;
		this.donGia = donGia;
		this.tenSanPham = tenSanPham;
		this.moTa = moTa;
		this.namSanXuat = namSanXuat;
		this.imgURL = imgURL;
	}

	public List<DanhGia> getDanhGias() {
		return danhGias;
	}

	public void setDanhGias(List<DanhGia> danhGias) {
		this.danhGias = danhGias;
	}

	public List<ChiTietHoaDon> getChiTietHoaDons() {
		return chiTietHoaDons;
	}
	public void setChiTietHoaDons(List<ChiTietHoaDon> chiTietHoaDons) {
		this.chiTietHoaDons = chiTietHoaDons;
	}
	public String getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public int getNamSanXuat() {
		return namSanXuat;
	}
	public void setNamSanXuat(int namSanXuat) {
		this.namSanXuat = namSanXuat;
	}
	public NhaSanXuat getNhaSanXuat() {
		return nhaSanXuat;
	}
	public void setNhaSanXuat(NhaSanXuat nhaSanXuat) {
		this.nhaSanXuat = nhaSanXuat;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maSanPham == null) ? 0 : maSanPham.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPham other = (SanPham) obj;
		if (maSanPham == null) {
			if (other.maSanPham != null)
				return false;
		} else if (!maSanPham.equals(other.maSanPham))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SanPham [maSanPham=" + maSanPham + ", donGia=" + donGia + ", tenSanPham=" + tenSanPham + ", moTa="
				+ moTa + ", namSanXuat=" + namSanXuat + "]";
	}
	
	
}
 