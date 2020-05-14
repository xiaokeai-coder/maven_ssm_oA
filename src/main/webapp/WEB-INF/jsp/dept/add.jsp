<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="x-admin-sm">

<head>
<meta charset="UTF-8">
<title>欢迎页面-X-admin2.2</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/font.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/xadmin.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/lib/layui/layui.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/xadmin.js"></script>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-row">
			<form class="layui-form">
				<div class="layui-form-item">
					<label for="L_name" class="layui-form-label"> <span
						class="x-red">*</span>部门名称
					</label>
					<div class="layui-input-inline">
						<input type="text" id="L_name" name="name" required=""
							placeholder="请输入部门名称" lay-verify="name" autocomplete="off"
							class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label for="L_phone" class="layui-form-label"> <span
						class="x-red">*</span>移动电话
					</label>
					<div class="layui-input-inline">
						<input type="text" id="L_phone" name="phone" required=""
							placeholder="请输入移动电话" lay-verify="phone" autocomplete="off"
							class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label for="L_tel" class="layui-form-label"> <span
						class="x-red">*</span>部门电话
					</label>
					<div class="layui-input-inline">
						<input type="text" id="L_tel" name="tel" required=""
							placeholder="请输入部门电话" lay-verify="tel" autocomplete="off"
							class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label for="L_tel" class="layui-form-label">部门负责人
					</label>
					<div class="layui-input-block">
						<input type="radio"  name="user.id"  value="0"  title="无"  checked="checked" />
						<c:forEach items="${users }"  var="user">
							<input type="radio"  name="user.id"  value="${user.id }"  title="${user.name }"  />
						</c:forEach>
					</div>
				</div>

		<div class="layui-form-item">
					<label for="L_tel" class="layui-form-label">负责机构
					</label>
					<div class="layui-input-block">
						<input type="radio"  name="branch.id"  value="0"  title="无"  checked="checked" />
						<c:forEach items="${branchs }"  var="branch">
							<input type="radio"  name="branch.id"  value="${branch.id }"  title="${branch.name }"  />
						</c:forEach>
					</div>
				</div>

				<div class="layui-form-item">
					<label for="L_repass" class="layui-form-label"></label>
					<button class="layui-btn" lay-filter="add" lay-submit="">增加</button>
				</div>
			</form>
		</div>
	</div>
	<script>
		layui.use([ 'form', 'layer', 'jquery' ], function() {
			$ = layui.jquery;
			var form = layui.form, layer = layui.layer;


			//自定义验证规则
			form.verify({
				name : function(value) {
					if (value == null || value.trim() == "") {
						return '部门名称不能为空';
					}
				},
				tel : function(value) {
					if (value == null || value.trim() == "") {
						return '部门电话不能为空';
					}
				}
			});

			//监听提交
			form.on('submit(add)', function(data) {
				console.log(data);
				//发异步，把数据提交给php
				$.post("add", data.field, function(obj) {

					if (obj.result == 1) {
						layer.alert(obj.msg, {
							icon : 6
						}, function() {
							//关闭当前frame
							xadmin.close();
							// 可以对父窗口进行刷新 
							xadmin.father_reload();
						});
					} else {
						layer.alert(obj.msg, {
							icon : 5
						}, function() {
							//关闭当前frame
							xadmin.close();
						});
					}
				});
				return false;
			});
		});
	</script>
</body>

</html>
