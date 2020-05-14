package com.tledu.tt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tledu.tt.dao.ISidebarSupDao;
import com.tledu.tt.model.SidebarSup;

@Repository("sidebarSupDao")
public class SidebarSupDao extends SqlSessionDaoSupport implements
		ISidebarSupDao {

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public List<SidebarSup> list(int isAdmin) {
		// 传入单个int或 Integer对象的时候,在进行动态SQL解析会有问题,比如 <if>
		// return getSqlSession().getMapper(ISidebarSupDao.class).list(isAdmin);
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("isAdmin", isAdmin);
		return getSqlSession().selectList(
				"com.tledu.tt.dao.ISidebarSupDao.list", par);
	}

}
