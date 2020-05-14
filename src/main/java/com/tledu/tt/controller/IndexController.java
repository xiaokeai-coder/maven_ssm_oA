package com.tledu.tt.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tledu.tt.auth.Admin;
import com.tledu.tt.auth.RoleType;

import com.tledu.tt.model.User;
import com.tledu.tt.service.IMyMessageService;
import com.tledu.tt.service.ISidebarSupService;

@Controller
public class IndexController {
	private ISidebarSupService sidebarSupService;
	
	
	public ISidebarSupService getSidebarSupService() {
		return sidebarSupService;
	}
@Autowired
	public void setSidebarSupService(ISidebarSupService sidebarSupService) {
		this.sidebarSupService = sidebarSupService;
	}
private IMyMessageService myMessageService;

public IMyMessageService getMyMessageService() {
	return myMessageService;
}

@Autowired
public void setMyMessageService(IMyMessageService myMessageService) {
	this.myMessageService = myMessageService;
}
	@RequestMapping({"/","/login"})
	@Admin(RoleType.NoLogin)
	public String login(){
		return "login";
	}
	
	@RequestMapping("/index")
	public String index(Model model,HttpSession session){
		model.addAttribute("messages", myMessageService.list());
		User loginUser = (User) session.getAttribute("loginUser");
		model.addAttribute("sidebarSups", sidebarSupService.list(loginUser));
		//List<MyMessage>messages=myMessageService.list();
	//	model.addAttribute("notices", myMessageService.listNotice());
		//System.out.println("--------------"+messages.size());
		return "index";
	}
	
	@RequestMapping("/welcome")
	public String welcome(){
		return "welcome";
	}
}
