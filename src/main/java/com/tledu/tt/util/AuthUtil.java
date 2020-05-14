package com.tledu.tt.util;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.tledu.tt.auth.Admin;
import com.tledu.tt.auth.RoleType;

public class AuthUtil {
	public static Map<RoleType, Set<String>> initAuth(String packageName){
		/**
		 * 用于存储权限,set中保存方法名
		 */
		Map<RoleType, Set<String>> auths = new HashMap<RoleType, Set<String>>();
		/**
		 * admin 不需要初始化,因为可以进行所有操作
		 */
		auths.put(RoleType.Login, new HashSet<String>());
		auths.put(RoleType.NoLogin, new HashSet<String>());
		
		// 获取包下所有类
		String[] clzName = getClassByPackage(packageName);
		
		try {
			for (String clz : clzName) {
				// 得到类全名
				String allName = packageName+"."+clz.substring(0, clz.lastIndexOf("."));
				// 获取对应的运行时类
				Class clzObj = Class.forName(allName);
				
				// 判断是否加着 @Admin注解
				if (!clzObj.isAnnotationPresent(Admin.class)) {
					// 没有加 说明是登陆访问
					// 判断类中是否有方法加着 @Admin 如果没有 就默认是登陆访问,因为类上面没加
					Method[] methods = clzObj.getDeclaredMethods();
					for (Method method : methods) {
						// 如果方法没有加着注解,说明是登陆可以访问
						if (!method.isAnnotationPresent(Admin.class)) {
							auths.get(RoleType.Login).add(allName+"."+method.getName());
						}else{
							// 如果 方法上有 @Admin 注解,就需要 获取注解的值 
							Admin admin = method.getAnnotation(Admin.class);
							RoleType roleType = admin.value();
							// 如果是管理员,就跳过,因为管理员可以访问任何方法,所以不需要进行存储
							if (roleType == RoleType.Admin) {
								continue;
							}
							auths.get(roleType).add(allName+"."+method.getName());
						}
					}
				}else{
					// 到这里说明类上加着 @Admin 注解
					Admin classAdmin = (Admin) clzObj.getAnnotation(Admin.class);
					RoleType classRoleType = classAdmin.value();
					// 获取所有的方法
					Method[] methods = clzObj.getDeclaredMethods();
					for (Method method : methods) {
						// 如果方法没有注解,说明默认使用 类注解权限
						if (!method.isAnnotationPresent(Admin.class)) {
							// 判断权限是否是管理员 如果是 就直接跳过,不存储
							if (classRoleType == RoleType.Admin) {
								continue;
							}
							auths.get(classRoleType).add(allName+"."+method.getName());
						}else{
							// 如果方法上有注解,就获取
							Admin methodAdmin = method.getAnnotation(Admin.class);
							RoleType methodRoleType = methodAdmin.value();
							if (methodRoleType == RoleType.Admin) {
								continue;
							}
							auths.get(methodRoleType).add(allName+"."+method.getName());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return auths;
	}
	/**
	 * 获取包下所有类
	 * @param packageName
	 * @return
	 */
	private static String[] getClassByPackage(String packageName){
		// 把 .换成 / 用于 获取文件对象
		String pr = packageName.replace(".", "/");
		String packagePath = AuthUtil.class.getClassLoader().getResource(pr).getPath();
		
		File file = new File(packagePath);
		// 文件名过滤器,通过匿名内部类实现
		String[] classfiles = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				// 只要.class文件
				if (name.endsWith(".class")) {
					return true;
				}
				return false;
			}
		});
		return classfiles;
	}
	public static void main(String[] args) {
		String[] name = getClassByPackage("com.tledu.tt.controller");
		for (String string : name) {
			System.out.println("com.tledu.tt.controller."+string.substring(0, string.lastIndexOf(".")));
		}
	}
}
