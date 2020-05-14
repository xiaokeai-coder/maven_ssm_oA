package com.tledu.tt.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpSession;

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
import com.tledu.tt.dto.MyScheduleDto;
import com.tledu.tt.dto.UserDto;
import com.tledu.tt.model.Dept;
import com.tledu.tt.model.MySchedule;
import com.tledu.tt.model.User;
import com.tledu.tt.service.IBranchService;
import com.tledu.tt.service.IDeptService;
import com.tledu.tt.service.IMyScheduleService;
import com.tledu.tt.service.IRoleService;
import com.tledu.tt.service.IUserService;
import com.tledu.tt.util.AjaxObj;
import com.tledu.tt.util.Pager;

/**
 * 登陆就可以操作
 * @author 作者：xiaokeai
 * @Date 创建时间：2020年5月7日 下午7:26:57
 */
@Controller
@RequestMapping("/mySchedule")
public class MyScheduleController extends IsRoleController {

	private IMyScheduleService myScheduleService;

	public IMyScheduleService getMyScheduleService() {
		return myScheduleService;
	}

	@Autowired
	public void setMyScheduleService(IMyScheduleService myScheduleService) {
		this.myScheduleService = myScheduleService;
	}

	@RequestMapping("/list")
	@Admin(RoleType.Login)
	public String list() {
		return "mySchedule/list";
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
	public Pager<MySchedule> pager(int page, int limit, String sreach)
			throws UnsupportedEncodingException {
		// 如果不需要模糊查询,设置为空字符串,这样不会影响SQL中的like模糊查询
		if (sreach == null || sreach.trim().equals("")) {
			sreach = "";
		} else {
			// GET请求,汉字会乱码,需要转码
			sreach = sreach.trim();
			sreach = new String(sreach.getBytes("ISO-8859-1"), "UTF-8");
			log("搜索日程 : { " + sreach + " }");
		}
		Pager<MySchedule> pager = myScheduleService.find(sreach, page, limit);
		return pager;
	}

	@RequestMapping("/add")
	public String add(Model model, HttpSession session) {
		// 需要把登陆的用户和会议类型传递到页面
		model.addAttribute("mettings", myScheduleService.listMeeting());
		User user = (User) session.getAttribute("loginUser");
		model.addAttribute("user", user);
		return "mySchedule/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj add(MySchedule mySchedule) {

		try {
			// 设置创建时间
			mySchedule.setCreate_date(new Date());
			myScheduleService.add(mySchedule);
			log("添加日程 : "
					+ JSON.toJSONString(mySchedule,
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
			myScheduleService.delete(id);
			log("删除日程 : { ID : " + id + "}");
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
				myScheduleService.delete(id);
				idsString += "," + id;
			}
			log("删除日程 : { Ids : " + idsString.substring(1) + "}");
			return new AjaxObj(1, "删除成功");
		} catch (Exception e) {
			return new AjaxObj(0, e.getMessage());
		}
	}

	@RequestMapping("/update")
	public String update(int id, Model model,HttpSession session) {
		MySchedule mySchedule = myScheduleService.load(id);
		model.addAttribute("mySchedule", mySchedule);
		model.addAttribute("mettings", myScheduleService.listMeeting());
		User user = (User) session.getAttribute("loginUser");
		model.addAttribute("user", user);
		return "mySchedule/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj update(MySchedule mySchedule) {

		try {

			// JSON.toJSONString : 把对象转换为JSON {id:1,name:xxx}
			// SerializerFeature.WriteMapNullValue 如果值为null 就不显示该字段
			myScheduleService.update(mySchedule);
			log("更新日程 : "
					+ JSON.toJSONString(mySchedule,
							SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "更新成功");
		} catch (Exception e) {
			return new AjaxObj(0, "更新失败");
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj edit(MyScheduleDto myScheduleDto) {

		try {
			// JSON.toJSONString : 把对象转换为JSON {id:1,name:xxx}
			// SerializerFeature.WriteMapNullValue 如果值为null 就不显示该字段
			MySchedule mySchedule = myScheduleDto.getMySchedule();
			myScheduleService.edit(mySchedule);
			log("edit更新日程 : "
					+ JSON.toJSONString(mySchedule,
							SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "更新成功");
		} catch (Exception e) {
			return new AjaxObj(0, "更新失败");
		}
	}
}
