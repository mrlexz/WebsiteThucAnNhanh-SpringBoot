package com.example.demo.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
@Entity
public class TaiKhoan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String maTaiKhoan;
	@NotNull
	@Size(min=2, max= 20)
	private String tenTaiKhoan;
	@NotNull
	@Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}",
			    message = "Mật khẩu ít nhất 6 kí tự, 1 chữ số, 1 chữ thường và một chữ hoa")
	private String matKhau;
	@OneToOne
	@MapsId
	@JoinColumn(name="maTaiKhoan", referencedColumnName = "maKhachHang")
	@Cascade(CascadeType.ALL)
	@NotNull
	private KhachHang khachHang;

	@ManyToMany
	private Set<Role> roles;

	public TaiKhoan() {
		super();
	}

	public TaiKhoan(String tenTaiKhoan, String matKhau) {
		super();
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = matKhau;
	}

	public String getMaTaiKhoan() {
		return maTaiKhoan;
	}

	public void setMaTaiKhoan(String maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}

	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}

	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}
	
	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}



	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tenTaiKhoan == null) ? 0 : tenTaiKhoan.hashCode());
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
		TaiKhoan other = (TaiKhoan) obj;
		if (tenTaiKhoan == null) {
			if (other.tenTaiKhoan != null)
				return false;
		} else if (!tenTaiKhoan.equals(other.tenTaiKhoan))
			return false;
		return true;
	}
}
