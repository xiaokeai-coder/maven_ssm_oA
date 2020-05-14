package com.tledu.tt.dao;

import java.util.List;
import com.tledu.tt.model.MyNode;
import com.tledu.tt.util.Pager;

public interface IMyNodeDao {

	public MyNode load(int id);

	public int find_count(String sreach);

	public Pager<MyNode> find(String sreach, int page, int limit);

	public List<MyNode> list();

	public void add(MyNode myNode);

	public void delete(int id);

	/**
	 * 创建时间和创建者 不可以更改
	 * 
	 * @param mySchedule
	 */
	public void update(MyNode myNode);

	/**
	 * 不更新关联对象列,时间相关的也不更改
	 * 
	 * @param user
	 */
	public void edit(MyNode myNode);



}
