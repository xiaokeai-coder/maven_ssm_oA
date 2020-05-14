package com.tledu.tt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tledu.tt.dao.IMyMessageDao;
import com.tledu.tt.model.MyMessage;
import com.tledu.tt.model.Notice;
import com.tledu.tt.util.Pager;


@Repository("myMessageDao")
public class MyMessageDao extends SqlSessionDaoSupport implements
		IMyMessageDao {
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public MyMessage load(int id) {
		return getSqlSession().getMapper(IMyMessageDao.class).load(id);
	}

	@Override
	public int find_count(String sreach) {
		return getSqlSession().getMapper(IMyMessageDao.class).find_count(
				sreach);
	}

	@Override
	public Pager<MyMessage> find(String sreach, int page, int limit) {
		Pager<MyMessage> pager = new Pager<MyMessage>();
		// 查询总条数
		int count = find_count(sreach);
		pager.setCount(count);

		// 准备参数
		Map<String, Object> map = new HashMap<String, Object>();
		// 起始值
		// limit 起始值 , 条数 , 并且 起始值 0开始
		// 起始值是 页数 -1 乘 每页的条数
		map.put("pageOffset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("sreach", sreach);
		// 查询
		List<MyMessage> users = getSqlSession().selectList(
				"com.tledu.tt.dao.IMyMessageDao.find", map);
		pager.setData(users);
		return pager;
	}

	@Override
	public List<MyMessage> list() {
		return getSqlSession().getMapper(IMyMessageDao.class).list();
	}

	@Override
	public void add(MyMessage mySchedule) {
		getSqlSession().getMapper(IMyMessageDao.class).add(mySchedule);
	}

	@Override
	public void delete(int id) {
		getSqlSession().getMapper(IMyMessageDao.class).delete(id);
	}

	@Override
	public void update(MyMessage mySchedule) {
		getSqlSession().getMapper(IMyMessageDao.class).update(mySchedule);
	}

	@Override
	public void edit(MyMessage mySchedule) {
		getSqlSession().getMapper(IMyMessageDao.class).edit(mySchedule);
	}

	@Override
	public List<Notice> listNotice() {
		return getSqlSession().getMapper(IMyMessageDao.class).listNotice();
	}



}
