package com.tledu.tt.dto;

import java.util.Date;

import com.tledu.tt.model.Branch;

public class BranchDto {
	private int id;
	private String name;
	private String short_name;
	private String come_time;

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

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public String getCome_time() {
		return come_time;
	}

	public void setCome_time(String come_time) {
		this.come_time = come_time;
	}

	public Branch getBranch() {
		Branch branch = new Branch();
		branch.setId(id);
		branch.setName(name);
		branch.setShort_name(short_name);
		Date date = new Date();
		date.setTime(Long.parseLong(come_time));
		branch.setCome_time(date);
		return branch;
	}
}
