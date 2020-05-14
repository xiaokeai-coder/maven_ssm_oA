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
<script src="<%=request.getContextPath()%>/resources/lib/layui/layui.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/xadmin.js"></script>
<!--[if lt IE 9]>
          <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
          <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a href="">首页</a> <a href="">人事管理</a>
			<a> <cite>部门管理</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			onclick="location.reload()" title="刷新"> <i
			class="layui-icon layui-icon-refresh" style="line-height: 30px"></i></a>
	</div>
	<div class="layui-fluid">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-body ">
						<form class="layui-form layui-col-space5">
							<div class="layui-inline layui-show-xs-block">
								<input type="text" name="sreach" placeholder="请输入部门名称"
									autocomplete="off" class="layui-input" id="sreach">
							</div>
							<div class="layui-inline layui-show-xs-block">
								<button class="layui-btn" lay-submit="" lay-filter="sreach">
									<i class="layui-icon">&#xe615;</i>
								</button>
							</div>
						</form>
					</div>
					<div class="layui-card-header">
						<button class="layui-btn layui-btn-danger" onclick="delAll()">
							<i class="layui-icon"></i>批量删除
						</button>
						<button class="layui-btn" onclick="openUp('添加部门','add',600,520)">
							<i class="layui-icon"></i>添加部门
						</button>
					</div>
					<div class="layui-card-body layui-table-body layui-table-main">
						<table class="layui-table" id="table" lay-filter="test">


						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	layui
			.use(
					[ 'laydate', 'form', 'table', 'util' ],
					function() {
						var laydate = layui.laydate;
						var form = layui.form;
						var table = layui.table;
						var util = layui.util;
						//第一个实例
						// 需要引入table模块,会自动向url发送两条数据 page 第几页 limit 每页显示多少条
						table
								.render({
									elem : '#table',
									height : 312,
									url : 'pager' //数据接口
									,
									page : true //开启分页
									,
									cols : [ [ //表头
											{
												title : "<input type=\"checkbox\" lay-filter='checkall'  lay-skin=\"primary\">",
												width : 80,
												templet : function(obj) {
													return "<input type=\"checkbox\" name='id' value='"+obj.id+"'   lay-skin=\"primary\">";
												}
											},
											{
												field : 'id',
												title : '编号',
												width : 80,
												sort : true,
												align : 'center'
											},
											{
												field : 'name',
												title : '部门名称',
												sort : true,
												align : 'center',
												// 可编辑,参考table控件
												edit : "text"
											},
											{
												field : 'phone',
												title : '移动电话',
												sort : true,
												align : 'center',
												edit : "text"
											},
											{
												field : 'tel',
												title : '部门电话',
												sort : true,
												align : 'center',
												edit : "text"
											},
											{
												field : 'user.name',
												title : '部门负责人 ',
												sort : true,
												align : 'center',
												templet : function(obj) {
													if (obj.user == null) {
														return "<div>无负责人</div>";
													} else {
														return "<div>"
																+ obj.user.name
																+ "</div>";
													}
												}
											},
											{
												field : 'branch.name',
												title : '负责机构 ',
												sort : true,
												align : 'center',
												templet : function(obj) {
													if (obj.branch == null) {
														return "<div>无负责机构</div>";
													} else {
														return "<div>"
																+ obj.branch.name
																+ "</div>";
													}
												}
											},
											{
												title : '操作',
												align : 'center',
												templet : function(obj) {
													return "<a title=\"编辑\"  onclick=\"openUp('编辑','update?id="
															+ obj.id
															+ "',600,570)\" href='javascript:;'>"
															+ "<i class='layui-icon'>&#xe642;</i></a>"
															+ "<a title=\"删除\" onclick=\"member_del(this,'"
															+ obj.id
															+ "')\" href='javascript:;'>"
															+ "<i class='layui-icon'>&#xe640;</i></a>";
												}
											}, ] ]
								});

						// 监听全选
						form.on('checkbox(checkall)', function(data) {

							if (data.elem.checked) {
								$('tbody input').prop('checked', true);
							} else {
								$('tbody input').prop('checked', false);
							}
							form.render('checkbox');
						});

						// 搜索监听
						form.on('submit(sreach)', function(data) {
							//				console.log($("#sreach").val())
							// table 模块 有个重载表格数据
							table.reload('table', {
								where : { //设定异步数据接口的额外参数，任意设
									sreach : $("#sreach").val()
								},
								page : {
									curr : 1
								//重新从第 1 页开始
								}
							});
							// 只重载数据
							return false;
						});
					});

	/*删除一个*/
	function member_del(obj, id) {
		layer.confirm('确认要删除吗？', function(index) {
			// 异步请求删除
			$.post("delete", {
				"id" : id
			}, function(obj) {
				if (obj.result == 1) {
					layer.msg(obj.msg, {
						icon : 1,
						time : 1000
					}, function() {
						// 刷新当前窗口
						window.location.reload();
					});
				} else {
					layer.msg(obj.msg, {
						icon : 2,
						time : 1000
					});
				}
			});
		});
	}

	/**
		删除多个
	 */
	function delAll(argument) {
		var ids = [];

		// 获取选中的id 
		$('tbody input').each(function(index, el) {
			if ($(this).prop('checked')) {
				ids.push($(this).val())
			}
		});

		if (ids.length <= 0) {
			layer.msg('请选择要删除的', {
				icon : 2,
				time : 1000
			});
		} else {
			layer.confirm('确认要删除吗？' + ids.toString(), function(index) {
				// 异步请求删除
				$.post("deleteAll", {
					"ids" : ids
				}, function(obj) {
					if (obj.result == 1) {
						layer.msg(obj.msg, {
							icon : 1,
							time : 1000
						}, function() {
							// 刷新当前窗口
							window.location.reload();
						});
					} else {
						layer.msg(obj.msg, {
							icon : 2,
							time : 1000
						});
					}
				});
			});
		}

	}

	/**
	打开弹框
	 */
	function openUp(title, url, width, height) {
		$.ajax({
			url : "is" + url,
			type : "get",
			success : function(obj) {
				if (obj.result == 1) {
					// 1 说明有权限
					xadmin.open(title, url, width, height);
				} else {
					layer.msg(obj.msg, {
						icon : 2,
						time : 1000
					});
				}
			}
		});
	}
</script>

<script type="text/javascript">
	layui.use("table", function(obj) {
		var table = layui.table;

		// 监听单元格编辑
		// 参考 table控件  注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
		table.on('edit(test)', function(obj) {
			// 			console.log(obj.value); //得到修改后的值
			// 			console.log(obj.field); //当前编辑的字段名
			console.log(obj.data); //所在行的所有相关数据

			var data = obj.data;
			var value = obj.value;
			// 得到更改所对应的表头
			var th = $("table>thead>tr>th[data-field=" + obj.field + "]")
			var span = th.find("span")[0];
			$.post("edit", data, function(obj) {
				if (obj.result == 1) {
					layer.alert(obj.msg, {
						icon : 6,
						time : 1000
					}, function() {
						layer.msg("[编号: " + data.id + " ]  " + span.innerHTML
								+ " 字段更改为: " + value, {
							time : 1000
						});
					});
				} else {
					layer.msg(obj.msg, {
						icon : 5,
						time : 1000
					}, function() {
						// 刷新当前窗口
						window.location.reload();
					});
				}
			});
		});
	})
</script>
</html>
