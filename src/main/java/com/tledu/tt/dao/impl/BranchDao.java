package com.tledu.tt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.tledu.tt.dao.IBranchDao;
import com.tledu.tt.model.Branch;
import com.tledu.tt.util.Pager;

@Repository("branchDao")
public class BranchDao extends SqlSessionDaoSupport implements IBranchDao {

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public int find_count(String sreach) {
		return getSqlSession().getMapper(IBranchDao.class).find_count(sreach);
	}

	@Override
	public Pager<Branch> find(String sreach, int page, int limit) {
		Pager<Branch> pager = new Pager<Branch>();
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
		List<Branch> branchs = getSqlSession().selectList(
				"com.tledu.tt.dao.IBranchDao.find", map);
		pager.setData(branchs);
		return pager;
	}

	@Override
	public void add(Branch branch) {
		getSqlSession().getMapper(IBranchDao.class).add(branch);
	}

	@Override
	public void update(Branch branch) {
		getSqlSession().getMapper(IBranchDao.class).update(branch);

	}

	@Override
	public void delete(int id) {
		getSqlSession().getMapper(IBranchDao.class).delete(id);

	}

	@Override
	public List<Branch> list() {

		return getSqlSession().getMapper(IBranchDao.class).list();
	}

	@Override
	public Branch load(int id) {
		return   getSqlSession().getMapper(IBranchDao.class).load(id);
	}

}
