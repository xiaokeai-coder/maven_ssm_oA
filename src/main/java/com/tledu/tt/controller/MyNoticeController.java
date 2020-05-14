package com.tledu.tt.controller;




import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tledu.tt.auth.Admin;
import com.tledu.tt.auth.RoleType;
import com.tledu.tt.model.MyMessage;
import com.tledu.tt.service.IMyMessageService;
import com.tledu.tt.util.Pager;


/**
 * 登陆就可以操作
 * @author 作者：xiaokeai
 * @Date 创建时间：2020年5月7日 下午7:26:57
 */
@Controller
@RequestMapping("/notice")
public class MyNoticeController extends IsRoleController {

	private IMyMessageService myMessageService;

	public IMyMessageService getMyMessageService() {
		return myMessageService;
	}

	@Autowired
	public void setMyMessageService(IMyMessageService myMessageService) {
		this.myMessageService = myMessageService;
	}

	@RequestMapping("/list")
	@Admin(RoleType.Login)
	public String list() {
		return "notice/list";
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
	public Pager<MyMessage> pager(int page, int limit, String sreach)
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
		Pager<MyMessage> pager = myMessageService.find(sreach, page, limit);
		return pager;
	}
		
}
