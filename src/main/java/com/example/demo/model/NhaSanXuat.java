package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class NhaSanXuat implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String maNhaSanXuat;
	@Column(columnDefinition = "NVARCHAR(255)")
	private String tenNhaSanXuat;
	@Column(columnDefinition = "NVARCHAR(255)")
	private String diaChi;
	@OneToMany(mappedBy = "nhaSanXuat")
	private List<SanPham> sanPhams;

	@Override
	public String toString() {
		return "NhaSanXuat [maNhaSanXuat=" + maNhaSanXuat + ", tenNhaSanXuat=" + tenNhaSanXuat + ", diaChi=" + diaChi
				;
	}

	public NhaSanXuat() {
		super();
	}

	public NhaSanXuat(String maNhaSanXuat, String tenNhaSanXuat, String diaChi) {
		super();
		this.maNhaSanXuat = maNhaSanXuat;
		this.tenNhaSanXuat = tenNhaSanXuat;
		this.diaChi = diaChi;
	}
	
	public NhaSanXuat( String tenNhaSanXuat, String diaChi) {
		super();
		this.tenNhaSanXuat = tenNhaSanXuat;
		this.diaChi = diaChi;
	}
	
	public String getMaNhaSanXuat() {
		return maNhaSanXuat;
	}

	public void setMaNhaSanXuat(String maNhaSanXuat) {
		this.maNhaSanXuat = maNhaSanXuat;
	}

	public String getTenNhaSanXuat() {
		return tenNhaSanXuat;
	}

	public void setTenNhaSanXuat(String tenNhaSanXuat) {
		this.tenNhaSanXuat = tenNhaSanXuat;
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
		result = prime * result + ((maNhaSanXuat == null) ? 0 : maNhaSanXuat.hashCode());
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
		NhaSanXuat other = (NhaSanXuat) obj;
		if (maNhaSanXuat == null) {
			if (other.maNhaSanXuat != null)
				return false;
		} else if (!maNhaSanXuat.equals(other.maNhaSanXuat))
			return false;
		return true;
	}

}
