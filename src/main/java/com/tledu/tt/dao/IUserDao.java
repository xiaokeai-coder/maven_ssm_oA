package com.tledu.tt.dao;

import java.util.List;

import com.tledu.tt.model.User;
import com.tledu.tt.util.Pager;

public interface IUserDao {
	public User load(int id);

	public int find_count(String sreach);

	public Pager<User> find(String sreach, int page, int limit);

	/**
	 * 更改用户角色
	 */
	public void updateRole(User user);

	public List<User> list();

	public void add(User user);

	/**
	 * 删除员工时,需要判断,如果该员工是某部门负责人,就不能删除
	 * 
	 * @param id
	 */
	public void delete(int id);

	/**
	 * 不更新关联对象列
	 * 
	 * @param user
	 */
	public void edit(User user);

	/**
	 * 根据部门ID 查询部门下所有员工
	 * 
	 * @param deptId
	 * @return
	 */
	public List<User> listByDeptId(int deptId);

	/**
	 * 更改员工状态
	 * 
	 * @param user
	 */
	public void updateStatus(User user);
	public void update(User user);
}
