package com.tledu.tt.dao;

import java.util.List;

import com.tledu.tt.model.MyMessage;
import com.tledu.tt.model.Notice;
import com.tledu.tt.util.Pager;

public interface IMyMessageDao {

	public MyMessage load(int id);

	public int find_count(String sreach);

	public Pager<MyMessage> find(String sreach, int page, int limit);

	public List<MyMessage> list();

	public void add(MyMessage myMessage);

	public void delete(int id);

	/**
	 * 创建时间和创建者 不可以更改
	 * 
	 * @param mySchedule
	 */
	public void update(MyMessage myMessage);

	/**
	 * 不更新关联对象列,时间相关的也不更改
	 * 
	 * @param user
	 */
	public void edit(MyMessage myMessage);

	/**
	 * 查询所有的会议类型,添加和更新 会用到
	 * 
	 * @return
	 */
	public List<Notice> listNotice();

}
