package com.tledu.tt.service;

import java.util.List;

import com.tledu.tt.model.SidebarSup;
import com.tledu.tt.model.User;

public interface ISidebarSupService {
	public List<SidebarSup> list(User user);
}
