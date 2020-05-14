package com.tledu.tt.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tledu.tt.dao.IMyMessageDao;
import com.tledu.tt.model.MyMessage;
import com.tledu.tt.model.Notice;
import com.tledu.tt.service.IMyMessageService;
import com.tledu.tt.util.Pager;


@Service("myMessageService")
public class MyMessageService implements IMyMessageService {

	private IMyMessageDao myMessageDao;

	public IMyMessageDao getMyMessageDao() {
		return myMessageDao;
	}

	@Autowired
	public void setMyMessageDao(IMyMessageDao myMessageDao) {
		this.myMessageDao = myMessageDao;
	}

	@Override
	public MyMessage load(int id) {
		return myMessageDao.load(id);
	}

	@Override
	public Pager<MyMessage> find(String sreach, int page, int limit) {
		sreach = "%" + sreach + "%";
		return myMessageDao.find(sreach, page, limit);
	}

	@Override
	public List<MyMessage> list() {
		return myMessageDao.list();
	}

	@Override
	public void add(MyMessage myMessage) {
		myMessageDao.add(myMessage);
	}

	@Override
	public void delete(int id) {
		myMessageDao.delete(id);
	}

	@Override
	public void update(MyMessage myMessage) {
		myMessageDao.update(myMessage);
	}

	@Override
	public void edit(MyMessage myMessage) {
		myMessageDao.edit(myMessage);
	}

	@Override
	public List<Notice> listNotice() {
		return myMessageDao.listNotice();
	}

}
