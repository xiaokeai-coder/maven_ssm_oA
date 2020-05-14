package com.tledu.tt.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tledu.tt.auth.Admin;
import com.tledu.tt.auth.RoleType;
import com.tledu.tt.model.User;
import com.tledu.tt.service.IUserService;
import com.tledu.tt.util.AjaxObj;
import com.tledu.tt.util.Pager;

@Controller
@Admin(RoleType.Admin)
@RequestMapping("/role")
public class RoleController  extends CommonController{

	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/list")
	public String list() {
		return "role/list";
	}

	/**
	 * 分页会自动向url发送两条数据 page 第几页 limit 每页显示多少条
	 * 
	 * 但是还要模糊查询,所以 需要提供一个额外参数
	 * 
	 * 单独创建一个Pager类 用于分页操作
	 * 
	 * @param page
	 * @param limit
	 * @param sreach
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/pager")
	@ResponseBody
	public Pager<User> pager(int page, int limit, String sreach)
			throws UnsupportedEncodingException {
		// 如果不需要模糊查询,设置为空字符串,这样不会影响SQL中的like模糊查询
		if (sreach == null || sreach.trim().equals("")) {
			sreach = "";
		} else {
			// GET请求,汉字会乱码,需要转码
			sreach = sreach.trim();
			sreach = new String(sreach.getBytes("ISO-8859-1"), "UTF-8");
			log("搜索员工角色 : { "+sreach+" }");
		}
		return userService.find(sreach, page, limit);
	}
	@RequestMapping(value="/updateRole",method=RequestMethod.POST)
	@ResponseBody
	public AjaxObj updateRole(int id , int role_id){
		log("更改员工角色 : { ID="+id+" , role_id="+role_id+" }");
		return userService.updateRole(id, role_id);
	}
}
