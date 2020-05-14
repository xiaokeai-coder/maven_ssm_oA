package com.tledu.tt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tledu.tt.dao.ISidebarSupDao;
import com.tledu.tt.model.SidebarSup;
import com.tledu.tt.model.User;
import com.tledu.tt.service.ISidebarSupService;

@Service("sidebarSupService")
public class SidebarSupService  implements ISidebarSupService{
	private ISidebarSupDao sidebarSupDao;
	

	public ISidebarSupDao getSidebarSupDao() {
		return sidebarSupDao;
	}

@Autowired
	public void setSidebarSupDao(ISidebarSupDao sidebarSupDao) {
		this.sidebarSupDao = sidebarSupDao;
	}


	@Override
	public List<SidebarSup> list(User user) {
		if (user.getRole().getId() == 1) {
			return sidebarSupDao.list(1);
		}else{
			return sidebarSupDao.list(0);
		}
	}

}
