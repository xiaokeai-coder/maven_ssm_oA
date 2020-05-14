package com.tledu.tt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tledu.tt.auth.Admin;
import com.tledu.tt.auth.RoleType;
import com.tledu.tt.util.AjaxObj;

@Admin(RoleType.Admin)
@Controller
public class IsRoleController extends CommonController{
	@RequestMapping("/isadd")
	@ResponseBody
	public AjaxObj isadd() {
		// 如果没有权限访问,在LogInterceptor拦截器中 就会返回 new AjaxObj(0);
		return new AjaxObj(1);
	}

	@RequestMapping("/isupdate")
	@ResponseBody
	public AjaxObj isupdate() {
		// 如果没有权限访问,在LogInterceptor拦截器中 就会返回 new AjaxObj(0);
		return new AjaxObj(1);
	}
}
