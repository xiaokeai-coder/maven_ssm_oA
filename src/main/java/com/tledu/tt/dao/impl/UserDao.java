package com.tledu.tt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tledu.tt.dao.IUserDao;
import com.tledu.tt.model.User;
import com.tledu.tt.util.Pager;

@Repository("userDao")
public class UserDao extends SqlSessionDaoSupport implements IUserDao {

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public int find_count(String sreach) {
		return getSqlSession().getMapper(IUserDao.class).find_count(sreach);
	}

	@Override
	public Pager<User> find(String sreach, int page, int limit) {
		Pager<User> pager = new Pager<User>();
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
		List<User> users = getSqlSession().selectList(
				"com.tledu.tt.dao.IUserDao.find", map);
		pager.setData(users);
		return pager;
	}

	@Override
	public void updateRole(User user) {
		getSqlSession().getMapper(IUserDao.class).updateRole(user);
	}

	@Override
	public User load(int id) {
		return getSqlSession().getMapper(IUserDao.class).load(id);
	}

	@Override
	public List<User> list() {
		return getSqlSession().getMapper(IUserDao.class).list();
	}

	@Override
	public void add(User user) {
		getSqlSession().getMapper(IUserDao.class).add(user);

	}

	@Override
	public void delete(int id) {
		getSqlSession().getMapper(IUserDao.class).delete(id);

	}

	@Override
	public void edit(User user) {
		getSqlSession().getMapper(IUserDao.class).edit(user);

	}

	@Override
	public List<User> listByDeptId(int deptId) {
		return getSqlSession().getMapper(IUserDao.class).listByDeptId(deptId);
	}

	@Override
	public void updateStatus(User user) {
		getSqlSession().getMapper(IUserDao.class).updateStatus(user);
	}

	@Override
	public void update(User user) {
		getSqlSession().getMapper(IUserDao.class).update(user);
	}

}
