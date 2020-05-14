package com.tledu.tt.model;

public class SidebarSub {
	private int id;
	private String name;
	private String uri;
	private SidebarSup sidebarSup;

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

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public SidebarSup getSidebarSup() {
		return sidebarSup;
	}

	public void setSidebarSup(SidebarSup sidebarSup) {
		this.sidebarSup = sidebarSup;
	}

}
