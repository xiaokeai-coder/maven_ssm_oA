package com.tledu.tt.dao;

import com.tledu.tt.model.LoginLog;
import com.tledu.tt.model.User;
import com.tledu.tt.util.Pager;

public interface ILoginLogDao {
	public User login(String name);
	
	public void add(LoginLog loginLog);
	
	public int find_count(String sreach);

	public Pager<LoginLog> find(String sreach, int page, int limit);
}
