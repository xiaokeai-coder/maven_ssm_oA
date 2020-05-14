package com.tledu.tt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tledu.tt.dao.IMyScheduleDao;
import com.tledu.tt.model.Meeting;
import com.tledu.tt.model.MySchedule;
import com.tledu.tt.service.IMyScheduleService;
import com.tledu.tt.util.Pager;

@Service("myScheduleService")
public class MyScheduleService implements IMyScheduleService {

	private IMyScheduleDao myScheduleDao;

	public IMyScheduleDao getMyScheduleDao() {
		return myScheduleDao;
	}

	@Autowired
	public void setMyScheduleDao(IMyScheduleDao myScheduleDao) {
		this.myScheduleDao = myScheduleDao;
	}

	@Override
	public MySchedule load(int id) {
		return myScheduleDao.load(id);
	}

	@Override
	public Pager<MySchedule> find(String sreach, int page, int limit) {
		sreach = "%" + sreach + "%";
		return myScheduleDao.find(sreach, page, limit);
	}

	@Override
	public List<MySchedule> list() {
		return myScheduleDao.list();
	}

	@Override
	public void add(MySchedule mySchedule) {
		myScheduleDao.add(mySchedule);
	}

	@Override
	public void delete(int id) {
		myScheduleDao.delete(id);
	}

	@Override
	public void update(MySchedule mySchedule) {
		myScheduleDao.update(mySchedule);
	}

	@Override
	public void edit(MySchedule mySchedule) {
		myScheduleDao.edit(mySchedule);
	}

	@Override
	public List<Meeting> listMeeting() {
		return myScheduleDao.listMeeting();
	}

}
