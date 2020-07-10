package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
@Entity
public class KhachHang implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String maKhachHang;
	@Column(columnDefinition = "NVARCHAR(255)")
	private String hoTenKhachHang;
	private String email;
	private String soDienThoai;
	@Column(columnDefinition = "NVARCHAR(255)")
	private String diaChi;
	@OneToOne(mappedBy = "khachHang")
	@PrimaryKeyJoinColumn
	private TaiKhoan taiKhoan;
	@OneToMany(mappedBy = "khachHang")
	private List<HoaDon> dshd;
	public KhachHang() {
		super();
	}

	public KhachHang(String hoTenKhachHang, String email, String soDienThoai, String diaChi) {
		super();
		this.hoTenKhachHang = hoTenKhachHang;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
	}

	
	
	public List<HoaDon> getDshd() {
		return dshd;
	}

	public void setDshd(List<HoaDon> dshd) {
		this.dshd = dshd;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getHoTenKhachHang() {
		return hoTenKhachHang;
	}

	public void setHoTenKhachHang(String hoTenKhachHang) {
		this.hoTenKhachHang = hoTenKhachHang;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maKhachHang == null) ? 0 : maKhachHang.hashCode());
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
		KhachHang other = (KhachHang) obj;
		if (maKhachHang == null) {
			if (other.maKhachHang != null)
				return false;
		} else if (!maKhachHang.equals(other.maKhachHang))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", hoTenKhachHang=" + hoTenKhachHang + ", email=" + email
				+ ", soDienThoai=" + soDienThoai + ", diaChi=" + diaChi + "]";
	}
	

	
}
