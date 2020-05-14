package com.tledu.tt.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tledu.tt.auth.Admin;
import com.tledu.tt.auth.RoleType;
import com.tledu.tt.model.OperationLog;
import com.tledu.tt.service.IOperationLogService;
import com.tledu.tt.util.Pager;

@Admin(RoleType.Admin)
@Controller
@RequestMapping("/operationLog")
public class OperationLogController  extends CommonController{
	private IOperationLogService operationLogService;
	public IOperationLogService getOperationLogService() {
		return operationLogService;
	}

	@Autowired
	public void setOperationLogService(IOperationLogService operationLogService) {
		// 这个类中 由于继承了 CommonController 所以 可以直接使用 getOperationLogService() 获取对象
		// 但是 如果在当前类中注入,就需要 在set方法中 调用父类的set方法
		// 否则 父类的set方法不执行,导致 父类中 没有对象注入
		super.setOperationLogService(operationLogService);
		this.operationLogService = operationLogService;
	}
	@RequestMapping("/list")
	public String list() {
		return "operationLog/list";
	}


	@RequestMapping("/pager")
	@ResponseBody
	public Pager<OperationLog> pager(int page, int limit, String sreach)
			throws UnsupportedEncodingException {
		// 如果不需要模糊查询,设置为空字符串,这样不会影响SQL中的like模糊查询
		if (sreach == null || sreach.trim().equals("")) {
			sreach = "";
		} else {
			// GET请求,汉字会乱码,需要转码
			sreach = sreach.trim();
			sreach = new String(sreach.getBytes("ISO-8859-1"), "UTF-8");
			log("搜索操作 : { "+sreach+" }");
		}
		return operationLogService.find(sreach, page, limit);
	}
	
}
