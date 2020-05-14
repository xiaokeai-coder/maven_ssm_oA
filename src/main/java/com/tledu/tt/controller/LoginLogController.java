package com.tledu.tt.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tledu.tt.auth.Admin;
import com.tledu.tt.auth.RoleType;
import com.tledu.tt.model.LoginLog;
import com.tledu.tt.model.User;
import com.tledu.tt.service.ILoginLogService;
import com.tledu.tt.util.AjaxObj;
import com.tledu.tt.util.OAException;
import com.tledu.tt.util.Pager;

@Controller
public class LoginLogController  extends CommonController{
	private ILoginLogService loginLogService;

	public ILoginLogService getLoginLogService() {
		return loginLogService;
	}

	@Autowired
	public void setLoginLogService(ILoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	// -------------
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	@Admin(RoleType.NoLogin)
	public AjaxObj login(User user, HttpSession session,
			HttpServletRequest request) {
		try {
			User loginUser = loginLogService.login(user);
			// 把登陆信息保存到session中
			session.setAttribute("loginUser", loginUser);
			// 创建登陆日志对象
			LoginLog loginLog = new LoginLog();
			loginLog.setCome_time(new Date());
			loginLog.setUser(loginUser);
			loginLog.setIp(request.getRemoteAddr());
			loginLogService.add(loginLog);
			return new AjaxObj(1, "登陆成功 ");
		} catch (OAException e) {
			return new AjaxObj(0, e.getMessage());
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

	@RequestMapping("/loginLog/list")
	public String list() {
		return "loginLog/list";
	}
	
	@RequestMapping("/loginLog/pager")
	@ResponseBody
	public Pager<LoginLog> pager(int page, int limit, String sreach)
			throws UnsupportedEncodingException {

		// 如果不需要模糊查询,设置为空字符串,这样不会影响SQL中的like模糊查询
		if (sreach == null || sreach.trim().equals("")) {
			sreach = "";
		} else {
			// GET请求,汉字会乱码,需要转码
			sreach = sreach.trim();
			sreach = new String(sreach.getBytes("ISO-8859-1"), "UTF-8");
			log("搜索日志 : { "+sreach+" }");
		}
		return loginLogService.find(sreach, page, limit);
	}
}
