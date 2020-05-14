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
import com.tledu.tt.dto.BranchDto;
import com.tledu.tt.model.Branch;
import com.tledu.tt.service.IBranchService;
import com.tledu.tt.util.AjaxObj;
import com.tledu.tt.util.Pager;

@Controller
@Admin(RoleType.Admin)
@RequestMapping("/branch")
public class BranchController extends IsRoleController {

	private IBranchService branchService;

	public IBranchService getBranchService() {
		return branchService;
	}

	@Autowired
	public void setBranchService(IBranchService branchService) {
		this.branchService = branchService;
	}

	@RequestMapping("/list")
	@Admin(RoleType.Login)
	public String list() {
		return "branch/list";
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
	public Pager<Branch> pager(int page, int limit, String sreach)
			throws UnsupportedEncodingException {
		// 如果不需要模糊查询,设置为空字符串,这样不会影响SQL中的like模糊查询
		if (sreach == null || sreach.trim().equals("")) {
			sreach = "";
		} else {
			// GET请求,汉字会乱码,需要转码
			sreach = sreach.trim();
			sreach = new String(sreach.getBytes("ISO-8859-1"), "UTF-8");
			log("搜索机构 : { " + sreach + " }");
		}
		Pager<Branch> pager = branchService.find(sreach, page, limit);
		return pager;
	}

	@RequestMapping("/add")
	public String add() {
		return "branch/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj add(Branch branch) {

		try {
			// JSON.toJSONString : 把对象转换为JSON {id:1,name:xxx}
			// SerializerFeature.WriteMapNullValue 如果值为null 就不显示该字段
			branchService.add(branch);
			log("添加机构 : "
					+ JSON.toJSONString(branch,
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
			branchService.delete(id);
			log("删除机构 : { ID : " + id + "}");
			return new AjaxObj(1, "删除成功");
		} catch (Exception e) {
			return new AjaxObj(0, "删除失败");
		}
	}
	
	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
	@ResponseBody
	// 如果是数组传递,会在name后面添加一个[]
	// ids[] 来进行映射
	public AjaxObj delete(@RequestParam("ids[]") int[] ids) {
		try {
			String idsString ="";
			for (int id : ids) {
				branchService.delete(id);
				idsString+=","+id;
			}
			log("删除机构 : { Ids : " + idsString.substring(1) + "}");
			return new AjaxObj(1, "删除成功");
		} catch (Exception e) {
			return new AjaxObj(0, "删除失败");
		}
	}
	
	@RequestMapping("/update")
	public String update(int id,Model model ) {
		Branch branch = branchService.load(id);
		model.addAttribute("branch", branch);
		return "branch/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj update(Branch branch) {

		try {
			// JSON.toJSONString : 把对象转换为JSON {id:1,name:xxx}
			// SerializerFeature.WriteMapNullValue 如果值为null 就不显示该字段
			branchService.update(branch);
			log("更新机构 : "
					+ JSON.toJSONString(branch,
							SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "更新成功");
		} catch (Exception e) {
			return new AjaxObj(0, "更新失败");
		}
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj edit(BranchDto branchDto) {

		try {
			// JSON.toJSONString : 把对象转换为JSON {id:1,name:xxx}
			// SerializerFeature.WriteMapNullValue 如果值为null 就不显示该字段
			Branch branch = branchDto.getBranch();
			 branchService.update(branch);
			log("edit更新机构 : "
					+ JSON.toJSONString(branch,
							SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "更新成功");
		} catch (Exception e) {
			return new AjaxObj(0, "更新失败");
		}
	}
}
