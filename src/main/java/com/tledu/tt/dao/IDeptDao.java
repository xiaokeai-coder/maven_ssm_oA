package com.tledu.tt.dao;

import java.util.List;

import com.tledu.tt.model.Dept;
import com.tledu.tt.util.Pager;

public interface IDeptDao {
	public Dept load(int id);

	public List<Dept> list();

	public void add(Dept dept);

	/**
	 * 部门下有员工,不能删除该部门
	 * 
	 * @param id
	 */
	public void delete(int id);

	public void update(Dept dept);

	/**
	 * 不更新关联列表的列(外键列)
	 * 
	 * @param dept
	 */
	public void edit(Dept dept);

	public int find_count(String sreach);

	public Pager<Dept> find(String sreach, int page, int limit);
	
	/**
	 * 根据员工ID查询负责的部门
	 */
	public List<Dept> listByUserId(int userId);
}
