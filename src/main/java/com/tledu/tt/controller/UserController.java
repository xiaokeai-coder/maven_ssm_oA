package com.tledu.tt.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tledu.tt.auth.Admin;
import com.tledu.tt.auth.RoleType;
import com.tledu.tt.dto.UserDto;
import com.tledu.tt.model.User;
import com.tledu.tt.service.IDeptService;
import com.tledu.tt.service.IRoleService;
import com.tledu.tt.service.IUserService;
import com.tledu.tt.util.AjaxObj;
import com.tledu.tt.util.Pager;

@Controller
@Admin(RoleType.Admin)
@RequestMapping("/user")
public class UserController extends IsRoleController {

	private IDeptService deptService;
	private IUserService userService;
	private IRoleService roleService;

	public IRoleService getRoleService() {
		return roleService;
	}

	@Autowired
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public IUserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IDeptService getDeptService() {
		return deptService;
	}

	@Autowired
	public void setDeptService(IDeptService deptService) {
		this.deptService = deptService;
	}

	@RequestMapping("/list")
	@Admin(RoleType.Login)
	public String list() {
		return "user/list";
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
	@Admin(RoleType.Login)
	public Pager<User> pager(int page, int limit, String sreach)
			throws UnsupportedEncodingException {
		// 如果不需要模糊查询,设置为空字符串,这样不会影响SQL中的like模糊查询
		if (sreach == null || sreach.trim().equals("")) {
			sreach = "";
		} else {
			// GET请求,汉字会乱码,需要转码
			sreach = sreach.trim();
			sreach = new String(sreach.getBytes("ISO-8859-1"), "UTF-8");
			log("搜索员工 : { " + sreach + " }");
		}
		Pager<User> pager = userService.find(sreach, page, limit);
		return pager;
	}

	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("depts", deptService.list());
		model.addAttribute("roles", roleService.list());
		return "user/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj add(User user) {

		try {
			userService.add(user);
			log("添加员工 : "
					+ JSON.toJSONString(user,
							SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "添加成功");
		} catch (Exception e) {
			return new AjaxObj(0, "添加失败");
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj delete(int id) {
		try {
			userService.delete(id);
			log("删除员工 : { ID : " + id + "}");
			return new AjaxObj(1, "删除成功");
		} catch (Exception e) {
			return new AjaxObj(0, e.getMessage());
		}
	}

	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
	@ResponseBody
	// 如果是数组传递,会在name后面添加一个[]
	// ids[] 来进行映射
	public AjaxObj delete(@RequestParam("ids[]") int[] ids) {
		try {
			String idsString = "";
			for (int id : ids) {
				userService.delete(id);
				idsString += "," + id;
			}
			log("删除员工 : { Ids : " + idsString.substring(1) + "}");
			return new AjaxObj(1, "删除成功");
		} catch (Exception e) {
			return new AjaxObj(0, e.getMessage());
		}
	}

	@RequestMapping("/update")
	public String update(int id, Model model) {
		User user = userService.load(id);
		model.addAttribute("user", user);
		model.addAttribute("depts", deptService.list());
		return "user/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj update(User user) {

		try {
		
			// JSON.toJSONString : 把对象转换为JSON {id:1,name:xxx}
			// SerializerFeature.WriteMapNullValue 如果值为null 就不显示该字段
			userService.update(user);
			log("更新员工 : "
					+ JSON.toJSONString(user,
							SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "更新成功");
		} catch (Exception e) {
			return new AjaxObj(0, "更新失败");
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj edit(UserDto userDto) {

		try {
			// JSON.toJSONString : 把对象转换为JSON {id:1,name:xxx}
			// SerializerFeature.WriteMapNullValue 如果值为null 就不显示该字段
			User user = userDto.getUser();
			userService.edit(user);
			log("edit更新员工 : "
					+ JSON.toJSONString(user,
							SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "更新成功");
		} catch (Exception e) {
			return new AjaxObj(0, "更新失败");
		}
	}
	
	@RequestMapping(value="/updateStatus",method=RequestMethod.POST)
	@ResponseBody
	public AjaxObj updateStatus(int id , int status){
		log("更改员工状态 : { ID : "+id+" , status = "+status+" }");
		return userService.updateStatus(id, status);
	} 
}
