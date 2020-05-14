<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html class="x-admin-sm">
<head>
<meta charset="UTF-8">
<title>天亮OA系统</title>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/font.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/login.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/xadmin.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/lib/layui/layui.js"
	charset="utf-8"></script>
</head>
<body class="login-bg">

	<div class="login layui-anim layui-anim-up">
		<div class="message">天亮OA管理系统登录</div>
		<div id="darkbannerwrap"></div>

		<form method="post" class="layui-form">
			<input name="name" placeholder="用户名" type="text"
				lay-verify="required" class="layui-input">
			<hr class="hr15">
			<input name="password" lay-verify="required" placeholder="密码"
				type="password" class="layui-input">
			<hr class="hr15">
			<input value="登录" lay-submit lay-filter="login" style="width: 100%;"
				type="submit">
			<hr class="hr20">
		</form>
	</div>

	<script>
		$(function() {
			layui.use('form', function() {
				var form = layui.form;
				//监听提交
				form.on('submit(login)', function(data) {
					$.post('login',data.field,function(obj){
						if(obj.result == 1){
							layer.msg(obj.msg);
							// 跳转到主页面
							location.href='index';
						}else{
							// 展示错误信息 
							layer.msg(obj.msg);
						}
					});
					return false;
				});
			});
		})
	</script>
</body>
</html>
