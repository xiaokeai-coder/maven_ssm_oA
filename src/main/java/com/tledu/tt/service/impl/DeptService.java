package com.tledu.tt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tledu.tt.dao.IDeptDao;
import com.tledu.tt.dao.IUserDao;
import com.tledu.tt.model.Dept;
import com.tledu.tt.model.User;
import com.tledu.tt.service.IDeptService;
import com.tledu.tt.util.OAException;
import com.tledu.tt.util.Pager;

@Service("deptService")
public class DeptService implements IDeptService {

	private IDeptDao deptDao;
	private IUserDao userDao;

	public IUserDao getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IDeptDao getDeptDao() {
		return deptDao;
	}

	@Autowired
	public void setDeptDao(IDeptDao deptDao) {
		this.deptDao = deptDao;
	}

	@Override
	public Dept load(int id) {
		return deptDao.load(id);
	}

	@Override
	public List<Dept> list() {
		return deptDao.list();
	}

	@Override
	public void add(Dept dept) {
		deptDao.add(dept);
	}

	@Override
	public void delete(int id) throws OAException{
		List<User> users = userDao.listByDeptId(id);
		if (users != null && users.size() > 0) {
			throw new OAException("该部门下有员工,不能删除");
		}
		deptDao.delete(id);
	}

	@Override
	public void update(Dept dept) {
		deptDao.update(dept);
	}

	@Override
	public void edit(Dept dept) {
		deptDao.edit(dept);
	}

	@Override
	public Pager<Dept> find(String sreach, int page, int limit) {
		sreach = "%" + sreach + "%";
		return deptDao.find(sreach, page, limit);
	}

}
