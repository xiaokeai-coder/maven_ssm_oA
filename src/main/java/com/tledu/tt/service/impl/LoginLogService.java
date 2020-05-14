package com.tledu.tt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tledu.tt.dao.ILoginLogDao;
import com.tledu.tt.model.LoginLog;
import com.tledu.tt.model.User;
import com.tledu.tt.service.ILoginLogService;
import com.tledu.tt.util.OAException;
import com.tledu.tt.util.Pager;

@Service("loginLogService")
public class LoginLogService implements ILoginLogService {
	private ILoginLogDao loginLogDao;

	public ILoginLogDao getLoginLogDao() {
		return loginLogDao;
	}

	@Autowired
	public void setLoginLogDao(ILoginLogDao loginLogDao) {
		this.loginLogDao = loginLogDao;
	}

	// -------------------

	@Override
	public User login(User user) throws OAException{
		User oldUser = loginLogDao.login(user.getName());
		if (oldUser == null) {
			throw new OAException("用户名不存在");
		}
		if (oldUser.getStatus() == 0) {
			throw new OAException("您的账户已被禁用,请于管理员联系!");
		}
		if (!oldUser.getPassword().equals(user.getPassword().trim())) {
			throw new OAException("密码不正确");
		}
		return oldUser;
	}

	@Override
	public Pager<LoginLog> find(String sreach, int page, int limit) {
		sreach = "%" + sreach + "%";
		return loginLogDao.find(sreach, page, limit);
	}

	@Override
	public void add(LoginLog loginLog) {
			loginLogDao.add(loginLog);
	}
}
