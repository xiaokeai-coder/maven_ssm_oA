package com.tledu.tt.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tledu.tt.dao.IRoleDao;
import com.tledu.tt.model.Role;
import com.tledu.tt.service.IRoleService;



@Service("roleService")
public class RoleService implements IRoleService {

	private IRoleDao roleDao;

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	@Autowired
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public List<Role> list() {
		return roleDao.list();
	}

}
