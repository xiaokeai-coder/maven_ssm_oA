package com.tledu.tt.dto;

import com.tledu.tt.model.MySchedule;

public class MyScheduleDto {
	private int id;
	private String title;
	/**
	 * 会议地址
	 */
	private String address;
	private String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	public MySchedule getMySchedule(){
		MySchedule mySchedule = new MySchedule();
		mySchedule.setId(id);
		mySchedule.setTitle(title);
		mySchedule.setContent(content);
		mySchedule.setAddress(address);
		return mySchedule;
	}
}
