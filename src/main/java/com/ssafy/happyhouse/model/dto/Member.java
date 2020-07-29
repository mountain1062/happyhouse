package com.ssafy.happyhouse.model.dto;

public class Member {
	private int no;
	private String id;
	private String password;
	private String name;
	private String address;
	private String phone;
	private boolean admin;
	
	public Member() {}
	public Member(String id, String password, String name, String address, String phone) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.admin = id.equals("admin");
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getNo() {
		return no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
		this.admin = id.equals("admin");
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String toString() {
		return "no="+no+", id="+id+", password="+password+", name="+name+", address"+address+", phone="+phone;
	}
	public boolean isAdmin() {
		return admin;
	}
}
