package com.tledu.tt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tledu.tt.dao.IMyNodeDao;
import com.tledu.tt.model.MyNode;
import com.tledu.tt.service.IMyNodeService;
import com.tledu.tt.util.Pager;





@Service("myNodeService")
public class MyNodeService implements IMyNodeService {

	private IMyNodeDao myNodeDao;

	public IMyNodeDao getMyNodeDao() {
		return myNodeDao;
	}

	@Autowired
	public void setMyNodeDao(IMyNodeDao myNodeDao) {
		this.myNodeDao = myNodeDao;
	}

	@Override
	public MyNode load(int id) {
		
		return myNodeDao.load(id);
	}

	@Override
	public Pager<MyNode> find(String sreach, int page, int limit) {
		sreach = "%" + sreach + "%";
		return myNodeDao.find(sreach, page, limit);
	}

	@Override
	public List<MyNode> list() {
		
		return myNodeDao.list();
	}

	@Override
	public void add(MyNode myNode) {
		myNodeDao.add(myNode);
		
	}

	@Override
	public void delete(int id) {
		
		myNodeDao.delete(id);
	}

	@Override
	public void update(MyNode myNode) {
		
		myNodeDao.update(myNode);
	}

	@Override
	public void edit(MyNode myNode) {
		myNodeDao.edit(myNode);
	}

	
}
