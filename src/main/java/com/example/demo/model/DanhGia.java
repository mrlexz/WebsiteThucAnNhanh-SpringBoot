package com.example.demo.model;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class DanhGia implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String idBinhLuan;
	@Column(name = "NoiDung",columnDefinition = "NVARCHAR(255)")
	private String noiDung;
	@ManyToOne
	@JoinColumn(name = "maKhachHang",referencedColumnName = "maKhachHang")
	private KhachHang khachHang;
	@ManyToOne
	@JoinColumn(name="maSanPham",referencedColumnName = "maSanPham")
	private SanPham sanPham;
	
	public DanhGia() {
		super();
	}
	public DanhGia(String idBinhLuan, String noiDung, KhachHang khachHang, SanPham sanPham) {
		super();
		this.idBinhLuan = idBinhLuan;
		this.noiDung = noiDung;
		this.khachHang = khachHang;
		this.sanPham = sanPham;
	}
	public String getIdBinhLuan() {
		return idBinhLuan;
	}
	public void setIdBinhLuan(String idBinhLuan) {
		this.idBinhLuan = idBinhLuan;
	}
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	public String getKhachHang() {
		return khachHang.getHoTenKhachHang();
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idBinhLuan == null) ? 0 : idBinhLuan.hashCode());
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
		DanhGia other = (DanhGia) obj;
		if (idBinhLuan == null) {
			if (other.idBinhLuan != null)
				return false;
		} else if (!idBinhLuan.equals(other.idBinhLuan))
			return false;
		return true;
	}
	

}
