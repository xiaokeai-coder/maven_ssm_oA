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
import com.tledu.tt.dto.DeptDto;
import com.tledu.tt.model.Dept;
import com.tledu.tt.service.IBranchService;
import com.tledu.tt.service.IDeptService;
import com.tledu.tt.service.IUserService;
import com.tledu.tt.util.AjaxObj;
import com.tledu.tt.util.Pager;

@Controller
@Admin(RoleType.Admin)
@RequestMapping("/dept")
public class DeptController extends IsRoleController {

	private IDeptService deptService;
	private IUserService userService;
	private IBranchService branchService;

	public IUserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IBranchService getBranchService() {
		return branchService;
	}

	@Autowired
	public void setBranchService(IBranchService branchService) {
		this.branchService = branchService;
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
		return "dept/list";
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
	public Pager<Dept> pager(int page, int limit, String sreach)
			throws UnsupportedEncodingException {
		// 如果不需要模糊查询,设置为空字符串,这样不会影响SQL中的like模糊查询
		if (sreach == null || sreach.trim().equals("")) {
			sreach = "";
		} else {
			// GET请求,汉字会乱码,需要转码
			sreach = sreach.trim();
			sreach = new String(sreach.getBytes("ISO-8859-1"), "UTF-8");
			log("搜索部门 : { " + sreach + " }");
		}
		Pager<Dept> pager = deptService.find(sreach, page, limit);
		return pager;
	}

	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("users", userService.list());
		model.addAttribute("branchs", branchService.list());
		return "dept/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj add(Dept dept) {

		try {
			// 如果 为 0 说明选择的是 无
			if (dept.getUser() == null || dept.getUser().getId() == 0) {
				dept.setUser(null);
			}
			if (dept.getBranch() == null || dept.getBranch().getId() == 0) {
				dept.setBranch(null);
			}
			deptService.add(dept);
			log("添加部门 : "
					+ JSON.toJSONString(dept,
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
			deptService.delete(id);
			log("删除部门 : { ID : " + id + "}");
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
				deptService.delete(id);
				idsString += "," + id;
			}
			log("删除部门 : { Ids : " + idsString.substring(1) + "}");
			return new AjaxObj(1, "删除成功");
		} catch (Exception e) {
			return new AjaxObj(0, e.getMessage());
		}
	}

	@RequestMapping("/update")
	public String update(int id, Model model) {
		Dept dept = deptService.load(id);
		model.addAttribute("dept", dept);
		model.addAttribute("users", userService.list());
		model.addAttribute("branchs", branchService.list());
		return "dept/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj update(Dept dept) {

		try {
			// 如果 为 0 说明选择的是 无
			if (dept.getUser() == null || dept.getUser().getId() == 0) {
				dept.setUser(null);
			}
			if (dept.getBranch() == null || dept.getBranch().getId() == 0) {
				dept.setBranch(null);
			}
			// JSON.toJSONString : 把对象转换为JSON {id:1,name:xxx}
			// SerializerFeature.WriteMapNullValue 如果值为null 就不显示该字段
			deptService.update(dept);
			log("更新部门 : "
					+ JSON.toJSONString(dept,
							SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "更新成功");
		} catch (Exception e) {
			return new AjaxObj(0, "更新失败");
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj edit(DeptDto deptDto) {

		try {
			// JSON.toJSONString : 把对象转换为JSON {id:1,name:xxx}
			// SerializerFeature.WriteMapNullValue 如果值为null 就不显示该字段
			Dept dept = deptDto.getDept();
			deptService.edit(dept);
			log("edit更新部门 : "
					+ JSON.toJSONString(dept,
							SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "更新成功");
		} catch (Exception e) {
			return new AjaxObj(0, "更新失败");
		}
	}
}
