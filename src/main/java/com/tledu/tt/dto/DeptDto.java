package com.tledu.tt.dto;

import com.tledu.tt.model.Dept;

public class DeptDto {
	private int id;
	private String name;
	private String phone;
	private String tel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Dept getDept() {
		Dept dept = new Dept();
		dept.setId(id);
		dept.setName(name);
		dept.setPhone(phone);
		dept.setTel(tel);
		return dept;
	}
}
