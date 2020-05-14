package com.tledu.tt.dto;

import com.tledu.tt.model.MyMessage;
import com.tledu.tt.model.Notice;

public class MyMessageDto {
	private int id;
	private String title;
	private String content;
	private Notice notice;
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

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	public MyMessage getMyMessage(){
		MyMessage myMessage = new MyMessage();
		myMessage.setId(id);
		myMessage.setTitle(title);
		myMessage.setContent(content);
		myMessage.setNotice(notice);
		return myMessage;
	}
}
