package com.example.demo.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterDTO {
	
	@NotNull
	@Size(min=2, max=20)
	private String hoTenKhachHang;
	@NotNull
	@Size(min=2, max= 20)
	private String diaChi;
	@NotNull
	@Email
	private String email;
	@NotNull
	@Pattern(regexp = "(\\+61|0)[0-9]{9}", message= "Số điện thoại không đúng định dạng")
	private String soDienThoai;
	@NotNull
	@Size(min= 2, max =20)
	private String tenTaiKhoan;
	@NotNull
	@Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}",
		    message = "Mật khẩu ít nhất 6 kí tự, 1 chữ số, 1 chữ thường và một chữ hoa")
	private String matKhau;
	
	RegisterDTO(String hoTenKhachHang, String diaChi, String email, String soDienThoai, String tenTaiKhoan,
			String matKhau) {
		super();
		this.hoTenKhachHang = hoTenKhachHang;
		this.diaChi = diaChi;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = matKhau;
	}

	public RegisterDTO() {
	}

	public String getHoTenKhachHang() {
		return hoTenKhachHang;
	}

	public void setHoTenKhachHang(String hoTenKhachHang) {
		this.hoTenKhachHang = hoTenKhachHang;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
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

	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}

	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	@Override
	public String toString() {
		return "UserRegisterDTO [hoTenKhachHang=" + hoTenKhachHang + ", diaChi=" + diaChi + ", email=" + email
				+ ", soDienThoai=" + soDienThoai + ", tenTaiKhoan=" + tenTaiKhoan + ", matKhau=" + matKhau + "]";
	}

	

}
