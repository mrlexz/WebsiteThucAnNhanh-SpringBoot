package com.example.demo.DTO;

public class UserRegisterDTO {
	private String hoTen;
	private String diaChi;
	private String email;
	private int phoneNumber;
	private String tenDangNhap;
	private String pass;

	public UserRegisterDTO(String hoTen, String diaChi, String email, int phoneNumber, String tenDangNhap,
			String pass) {
		super();
		this.hoTen = hoTen;
		this.diaChi = diaChi;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.tenDangNhap = tenDangNhap;
		this.pass = pass;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
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

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
