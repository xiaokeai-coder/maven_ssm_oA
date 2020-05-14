<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
						class="x-red">*</span>机构名称
					</label>
					<div class="layui-input-inline">
						<input type="text" id="L_name" name="name" required=""
							placeholder="请输入机构名称" lay-verify="name" autocomplete="off"
							class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label for="L_short_name" class="layui-form-label"> <span
						class="x-red">*</span>机构简称
					</label>
					<div class="layui-input-inline">
						<input type="text" id="L_short_name" name="short_name" required=""
							placeholder="请输入机构简称" lay-verify="short_name" autocomplete="off"
							class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label for="come_time" class="layui-form-label"> <span
						class="x-red">*</span>加入时间
					</label>
					<div class="layui-input-inline">
						<input type="text" id="come_time" name="come_time" required=""
							placeholder="请输入加入时间"  lay-verify="come_time" autocomplete="off"
							readonly="readonly" class="layui-input">
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
				elem : '#come_time',
				type : "datetime",
				calendar : true
			});
			//自定义验证规则
			form.verify({
				name : function(value) {
					if (value == null || value.trim() == "") {
						return '机构名称不能为空';
					}
				},
				short_name : function(value) {
					if (value == null || value.trim() == "") {
						return '机构简称不能为空';
					}
				},
				come_time : function(value) {
					if (value == null || value.trim() == "") {
						return '请选择加入时间';
					}
				},
			});

			//监听提交
			form.on('submit(add)', function(data) {
				console.log(data);
				//发异步，把数据提交给php
				$.post("add",data.field,function(obj){
					
					if(obj.result == 1){
						layer.alert(obj.msg, {
							icon : 6
						}, function() {
							//关闭当前frame
							xadmin.close();
							// 可以对父窗口进行刷新 
							xadmin.father_reload();
						});
					}else{
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
