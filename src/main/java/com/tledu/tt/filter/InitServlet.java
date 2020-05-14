package com.tledu.tt.filter;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tledu.tt.auth.RoleType;
import com.tledu.tt.util.AuthUtil;

public class InitServlet extends HttpServlet {
	/**
	 * Spring工厂
	 */
	private static WebApplicationContext wc;

	@Override
	public void init() throws ServletException {
		wc = WebApplicationContextUtils.getWebApplicationContext(this
				.getServletContext());
		// 获取 rolePackage.properties 中的路径
		Properties properties = new Properties();
		String packageName = null;
		try {
			properties.load(InitServlet.class.getClassLoader()
					.getResourceAsStream("rolePackage.properties"));
			packageName = properties.getProperty("path");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 设置默认值
		if (packageName == null || packageName.trim().equals("")) {
			packageName = "com.tledu.tt.controller";
		}
		
		Map<RoleType, Set<String>> auths = AuthUtil.initAuth(packageName);
		
		// 把权限初始化到 application中
		this.getServletContext().setAttribute("allAuths", auths);
		System.out.println("--------------系统初始化成功:"+auths+"-----------------");
	}
}
