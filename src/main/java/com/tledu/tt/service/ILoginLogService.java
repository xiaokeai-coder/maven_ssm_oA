package com.tledu.tt.service;

import com.tledu.tt.model.LoginLog;
import com.tledu.tt.model.User;
import com.tledu.tt.util.OAException;
import com.tledu.tt.util.Pager;

public interface ILoginLogService {

	public User login(User user)  throws OAException;
	
	public void add(LoginLog loginLog);
	
	public Pager<LoginLog> find(String sreach, int page, int limit);
}
