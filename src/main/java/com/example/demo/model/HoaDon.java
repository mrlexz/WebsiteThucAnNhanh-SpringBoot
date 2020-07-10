package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
@Entity
public class HoaDon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String maHoaDon;
	private LocalDate ngayLap;
	@ManyToOne
	@JoinColumn(name="maKhachHang",referencedColumnName = "maKhachHang")
	private KhachHang khachHang;
	@OneToMany(mappedBy ="hoaDon", cascade = CascadeType.ALL)
	private List<ChiTietHoaDon> dssp;
	
	public HoaDon() {
		super();
	}
	
	public HoaDon(LocalDate ngayLap, KhachHang khachHang, List<ChiTietHoaDon> dssp) {
		super();
		this.ngayLap = ngayLap;
		this.khachHang = khachHang;
		this.dssp = dssp;
	}

	public List<ChiTietHoaDon> getDssp() {
		return dssp;
	}
	public void themSanPham(SanPham sp,int soluong) {
		if(this.dssp==null)
			this.dssp = new ArrayList<ChiTietHoaDon>();
		ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(this, sp, sp.getDonGia(), soluong);
		dssp.add(chiTietHoaDon);
	}
	public void setDssp(List<ChiTietHoaDon> dssp) {
		this.dssp = dssp;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public LocalDate getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}
	public double getTongTien() {
		double sum = 0.0;
		for (int i = 0; i < dssp.size(); i++)
			sum+=dssp.get(i).getDonGia()*dssp.get(i).getSoLuong();
		return sum;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maHoaDon == null) ? 0 : maHoaDon.hashCode());
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
		HoaDon other = (HoaDon) obj;
		if (maHoaDon == null) {
			if (other.maHoaDon != null)
				return false;
		} else if (!maHoaDon.equals(other.maHoaDon))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", ngayLap=" + ngayLap + "]";
	}
	
	
	

}
