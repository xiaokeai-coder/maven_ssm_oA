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
					<label for="L_tiltle" class="layui-form-label"> <span
						class="x-red">*</span>标题
					</label>
					<div class="layui-input-inline">
						<input type="text" id="L_tilte" name="title" required=""
							placeholder="请输入标题" lay-verify="title" autocomplete="off"
							class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label for="L_address" class="layui-form-label"> <span
						class="x-red">*</span>会议地址
					</label>
					<div class="layui-input-inline">
						<input type="text" id="L_address" name="address" required=""
							placeholder="请输入会议地址" lay-verify="address" autocomplete="off"
							class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label for="L_metting" class="layui-form-label"> 会议类型</label>
						<div class="layui-input-block">
						<c:forEach items="${mettings }"  var="meeting" varStatus="v">
						<c:if test="${v.count eq 1 }">
						<input type="radio" name="meeting.id" value="${meeting.id }"
								title="${meeting.type }"  checked="checked"/>
						</c:if>
						<c:if test="${v.count ne 1 }">
							<input type="radio"  name="meeting.id"  value="${meeting.id }"  title="${meeting.type }"  />
							</c:if>
						</c:forEach>
					</div>
				</div>

				<div class="layui-form-item">
					<label for="begin_time" class="layui-form-label"> <span
						class="x-red">*</span>开始时间
					</label>
					<div class="layui-input-inline">
						<input type="text" id="begin_time" name="begin_time"
							required="" placeholder="请输入起始时间" lay-verify="begin_time"
							autocomplete="off" class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label for="end_time" class="layui-form-label"> <span
						class="x-red">*</span>结束时间
					</label>
					<div class="layui-input-inline">
						<input type="text" id="end_time" name="end_time" required=""
							placeholder="请输入结束时间" lay-verify="end_time" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_content" class="layui-form-label"> <span
						class="x-red">*</span>内容
					</label>
					<div class="layui-input-inline">
						<input type="text" id="L_content" name="content" required=""
							placeholder="请输入内容" lay-verify="content" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_user" class="layui-form-label"> <span
						class="x-red">*</span>创建者
					</label>
					<div class="layui-input-inline">
					<input type="radio" name="user.id" value="${user.id}"
									title="${user.name }" checked="checked" />
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
		layui.use([ 'form', 'layer', 'jquery', 'laydate' ], function() {
			$ = layui.jquery;
			var form = layui.form, layer = layui.layer;
			var laydate = layui.laydate;

			// 时间插件
			laydate.render({
				elem : '#begin_time',
				type : "datetime",
				calendar : true
			});
			laydate.render({
				elem : '#end_time',
				type : "datetime",
				calendar : true
			});
			//自定义验证规则
			form.verify({
				tiltle : function(value) {
					if (value == null || value.trim() == "") {
						return '标题不能为空';
					}
				},
				address : function(value) {
					if (value == null || value.trim() == "") {
						return '会议地址不能为空';
					}
				},
				content : function(value) {
					if (value == null || value.trim() == "") {
						return '会议内容不能为空';
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
