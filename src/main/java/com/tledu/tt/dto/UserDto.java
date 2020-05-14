package com.tledu.tt.dto;

import com.tledu.tt.model.User;

public class UserDto {
	private int id;
	private String name;
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
	
	
	public User getUser(){
		User user = new User();
		user.setId(id);
		user.setName(name);
		return user;
	}
}
