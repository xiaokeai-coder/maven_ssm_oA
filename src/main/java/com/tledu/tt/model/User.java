package com.tledu.tt.model;

/**
 * 
 * @author 作者：xiaokeai
 * @Date 创建时间：2020年5月7日 下午6:34:36
 * 用户类
 */
public class User {
	private int id;
	private String name;
	private String password;
	private String sex;
	/**
	 * 1 为启用, 0 为 禁用,禁用不可以登陆
	 */
	private int status;

	/**
	 * 权限
	 */
	private Role role;

	private Dept dept;

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	
}
