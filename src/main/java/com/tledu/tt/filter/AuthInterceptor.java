package com.tledu.tt.filter;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tledu.tt.auth.RoleType;
import com.tledu.tt.model.User;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		// 获取session
		HttpSession session = request.getSession(true);

		// 获取请求方法所在的类名
		String accessMethod = handlerMethod.getBean().getClass().getName();
		// com.tledu.zrz.controller.IndexController$$EnhancerBySpringCGLIB$$95b0ef0c.login
		// 由于代理问题,获取的类名发生了转变
		accessMethod = accessMethod.substring(0, accessMethod.indexOf("$")) + "." + handlerMethod.getMethod().getName();
		// System.out.println(accessMethod);
		// 获取所有权限
		Map<RoleType, Set<String>> allAuths = (Map<RoleType, Set<String>>) session
				.getServletContext().getAttribute("allAuths");

		/**
		 * 判断当前请求的方法在map中的哪个set集合中
		 * 
		 * 如果在NoLogin这个set中,说明当前请求的这个方法都可以访问
		 * 
		 * 如果在Login这个set集合中,说当前请求的这个方法需要登陆才能访问
		 * 
		 * 如果 不再map中,说明这个方法需要管理员才能访问
		 */
		// 判断当前请求的方法 是否不登录也可以访问
		if (!allAuths.get(RoleType.NoLogin).contains(accessMethod)) {
			// 能到这里,说明 这个方法 要么登陆可以访问,要么是管理员才可以访问
			// 不管是哪种,都必须先登陆,不登录怎么知道你是管理员?

			// 获取登陆的对象
			User user = (User) session.getAttribute("loginUser");
			if (user == null) {
				// 说明没有登陆
				response.sendRedirect(request.getContextPath()+"/");
				return false;
			}else{
				// 说明登陆了
				if (user.getRole().getId() != 1) {
					// 到这里说明登陆的不是管理员
					if (!allAuths.get(RoleType.Login).contains(accessMethod)) {
						// 到这里 说明 login和nologin中 都不包含 当前请求的这个方法,说明这个方法是管理员访问
						// 但是 到这里 说明你又不是管理员
						request.setAttribute("isRole", "您没有权限做此操作");
					}
				}
			}
		}
		return super.preHandle(request, response, handler);
	}
}
