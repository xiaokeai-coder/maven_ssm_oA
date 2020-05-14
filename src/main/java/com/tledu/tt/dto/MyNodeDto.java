package com.tledu.tt.dto;

import com.tledu.tt.model.MyNode;
import com.tledu.tt.model.User;



public class MyNodeDto {
	private int id;
	private String title;

 
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


	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	public MyNode getMyNode(){
		MyNode myNode = new MyNode();
		 myNode.setId(id);
		 myNode.setTitle(title);
		 myNode.setContent(content);
	
		return myNode;
	}
}
