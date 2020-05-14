package com.tledu.tt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tledu.tt.dao.IMyNodeDao;
import com.tledu.tt.model.MyNode;
import com.tledu.tt.util.Pager;


@Repository("myNodeDao")
public class MyNodeDao extends SqlSessionDaoSupport implements
		IMyNodeDao {
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public MyNode load(int id) {
		return getSqlSession().getMapper(IMyNodeDao.class).load(id);
	}

	@Override
	public int find_count(String sreach) {
		return getSqlSession().getMapper(IMyNodeDao.class).find_count(
				sreach);
	}

	@Override
	public Pager<MyNode> find(String sreach, int page, int limit) {
		Pager<MyNode> pager = new Pager<MyNode>();
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
		List<MyNode> nodes = getSqlSession().selectList(
				"com.tledu.tt.dao.IMyNodeDao.find", map);
		pager.setData(nodes);
		return pager;
	}

	@Override
	public List<MyNode> list() {
		return getSqlSession().getMapper(IMyNodeDao.class).list();
	}

	@Override
	public void add(MyNode myNode) {
		System.out.println(myNode+"---------------------");
		getSqlSession().getMapper(IMyNodeDao.class).add(myNode);
	}

	@Override
	public void delete(int id) {
		getSqlSession().getMapper(IMyNodeDao.class).delete(id);
	}

	@Override
	public void update(MyNode myNode) {
		getSqlSession().getMapper(IMyNodeDao.class).update(myNode);
	}

	@Override
	public void edit(MyNode  myNode) {
		getSqlSession().getMapper(IMyNodeDao.class).edit(myNode);
	}



}
