package com.tledu.tt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.tledu.tt.dao.ILoginLogDao;
import com.tledu.tt.model.LoginLog;
import com.tledu.tt.model.User;
import com.tledu.tt.util.Pager;

@Repository("loginLogDao")
public class LoginLogDao extends SqlSessionDaoSupport implements ILoginLogDao {

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public User login(String name) {
		return getSqlSession().getMapper(ILoginLogDao.class).login(name);
	}

	@Override
	public void add(LoginLog loginLog) {
		getSqlSession().getMapper(ILoginLogDao.class).add(loginLog);
	}

	@Override
	public int find_count(String sreach) {

		return getSqlSession().getMapper(ILoginLogDao.class).find_count(sreach);
	}

	@Override
	public Pager<LoginLog> find(String sreach, int page, int limit) {

		Pager<LoginLog> pager = new Pager<LoginLog>();
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
		List<LoginLog> users = getSqlSession().selectList(
				"com.tledu.tt.dao.ILoginLogDao.find", map);
		pager.setData(users);
		return pager;
	}
}
