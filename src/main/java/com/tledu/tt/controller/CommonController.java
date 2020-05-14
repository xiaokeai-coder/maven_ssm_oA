package com.tledu.tt.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tledu.tt.model.OperationLog;
import com.tledu.tt.model.User;
import com.tledu.tt.service.IOperationLogService;

/**
 *  公共模块 记录日志用
 * @author 作者：xiaokeai
 * @Date 创建时间：2020年5月7日 下午7:24:33
 */
@Controller
public class CommonController {
	private IOperationLogService operationLogService;
	private HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}

	@Autowired
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public IOperationLogService getOperationLogService() {
		return operationLogService;
	}

	@Autowired
	public void setOperationLogService(IOperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

	public void log(String desc){
		OperationLog operationLog = new OperationLog();
		operationLog.setCome_time(new Date());
		operationLog.setDesc(desc);
		User user = (User) request.getSession().getAttribute("loginUser");
		operationLog.setUser(user);
		
		operationLogService.add(operationLog);
	}
}
