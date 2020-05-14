package com.tledu.tt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.tledu.tt.dao.IDeptDao;
import com.tledu.tt.model.Dept;
import com.tledu.tt.util.Pager;

@Repository("deptDao")
public class DeptDao extends SqlSessionDaoSupport implements IDeptDao{
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public Dept load(int id) {
		return getSqlSession().getMapper(IDeptDao.class).load(id);
	}

	@Override
	public List<Dept> list() {
		return getSqlSession().getMapper(IDeptDao.class).list();
	}

	@Override
	public void add(Dept dept) {
		getSqlSession().getMapper(IDeptDao.class).add(dept);
		
	}

	@Override
	public void delete(int id) {
		getSqlSession().getMapper(IDeptDao.class).delete(id);
		
	}

	@Override
	public void update(Dept dept) {
		getSqlSession().getMapper(IDeptDao.class).update(dept);
		
	}

	@Override
	public void edit(Dept dept) {
		getSqlSession().getMapper(IDeptDao.class).edit(dept);
		
	}

	@Override
	public int find_count(String sreach) {
		return getSqlSession().getMapper(IDeptDao.class).find_count(sreach);
	}

	@Override
	public Pager<Dept> find(String sreach, int page, int limit) {
		Pager<Dept> pager = new Pager<Dept>();
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
		List<Dept> branchs = getSqlSession().selectList(
				"com.tledu.tt.dao.IDeptDao.find", map);
		pager.setData(branchs);
		return pager;
	}

	@Override
	public List<Dept> listByUserId(int userId) {
		return getSqlSession().getMapper(IDeptDao.class).listByUserId(userId);
	}
}
