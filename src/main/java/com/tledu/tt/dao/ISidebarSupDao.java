package com.tledu.tt.dao;

import java.util.List;

import com.tledu.tt.model.SidebarSup;

public interface ISidebarSupDao {
	public List<SidebarSup> list(int isAdmin);
}
