package com.tledu.tt.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 类注解 决定类中 的所有方法 , 方法注解只决定当前方法
 *
 * 如果 某个类或者某个方法 没有加着 @Admin 注解 说明该类中 所有的方法 都是登陆可以访问
 *
 * 如果某个类加着 @Admin(RoleType.NoLogin) 并且 方法上 没有加着注解,说明这个类中的所有方法都是不登录也能访问
 *
 * 如果某个类加着 @Admin(RoleType.NoLogin) 并且 有几个方法上 加着注解 @Admin(RoleType.Login)
 * ,说明这个类中除了这几个方法是登陆访问,别的都是不登录也可以访问
 *
 * 没加 @Admin 注解,默认为登陆访问
 *
 * 
 * 	如果类设置权限,当前类中所有没有加注解的方法,默认使用类权限
 * 
 * 1 类加 方法没加   ---> 方法使用类权限
 * 
 * 2  类加 方法加     --->  方法优先
 * 
 * 3 类不加 方法加   ---> 方法优先
 * 
 * 4 类不加 方法不加  ---> 登陆访问
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Admin {
	public RoleType value() default RoleType.Login;
}
