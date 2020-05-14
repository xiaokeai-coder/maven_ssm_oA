<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="x-admin-sm">
<head>
<meta charset="UTF-8">
<title>天亮OA系统</title>
		<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<STYLE type=text/css>
#demo a {
overflow:hidden;
font:16px/20px tahoma;
display:block;
text-decoration:none;
margin:3px;
color:#04564e;
padding-left:2px;
text-align:center;

}
#demo a:hover {
color:#ff6620;

}
#demo{
position:absolute;
left:500px ;
}
</STYLE>

<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/font.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/xadmin.css">
<!-- <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/theme5.css"> -->
<script src="<%=request.getContextPath()%>/resources/lib/layui/layui.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/xadmin.js"></script>
</head>
<body class="index">
	<!-- 顶部开始 -->
	<div class="container">
		<div class="logo">
			<a href="./index.html">天亮OA系统</a>
		</div>
		<div class="left_open">
			<a><i title="展开左侧栏" class="iconfont">&#xe699;</i></a>
		</div>
		<ul class="layui-nav left fast-add" lay-filter="">
			<li class="layui-nav-item"><a href="javascript:;">+新增</a>
				<dl class="layui-nav-child">
					<!-- 二级菜单 -->
					<dd>
						<a onclick="xadmin.open('最大化','http://www.baidu.com','','',true)">
							<i class="iconfont">&#xe6a2;</i>弹出最大化
						</a>
					</dd>
					<dd>
						<a onclick="xadmin.open('弹出自动宽高','http://www.baidu.com')"> <i
							class="iconfont">&#xe6a8;</i>弹出自动宽高
						</a>
					</dd>
					<dd>
						<a onclick="xadmin.open('弹出指定宽高','http://www.baidu.com',500,300)">
							<i class="iconfont">&#xe6a8;</i>弹出指定宽高
						</a>
					</dd>
					<dd>
						<a onclick="xadmin.add_tab('在tab打开','member-list.html')"> <i
							class="iconfont">&#xe6b8;</i>在tab打开
						</a>
					</dd>
					<dd>
						<a onclick="xadmin.add_tab('在tab打开刷新','member-del.html',true)">
							<i class="iconfont">&#xe6b8;</i>在tab打开刷新
						</a>
					</dd>
				</dl></li>
		</ul>		
<div id="demo" style="overflow:hidden;height:30px;width:600px; ">
<div id="demo1">
   
         <c:forEach items="${messages }"  var="message" >
 
						<a href="#" ><span>【公告】❥</span> ${message.notice.type}<span>：</span>${message.content}</a>
				
						</c:forEach>
				


</div>
<div id="demo2"></div>
</div>

		<ul class="layui-nav right" lay-filter="">
			<li class="layui-nav-item"><a href="javascript:;">${loginUser.name }</a>
				<dl class="layui-nav-child">
					<!-- 二级菜单 -->
					<dd>
						<a onclick="xadmin.open('个人信息','index')">个人信息</a>
					</dd>

					<dd>
						<a href="logout">退出</a>
					</dd>
				</dl></li>
			<li class="layui-nav-item to-index"><a href="index">前台首页</a></li>
		</ul>
	</div>
	<!-- 顶部结束 -->
	<!-- 中部开始 -->
	<!-- 左侧菜单开始 -->
	<div class="left-nav">
		<div id="side-nav">
			<ul id="nav">
			<c:forEach items="${sidebarSups }" var="sidebarSup">
				<li><a href="javascript:;"> <i class="iconfont left-nav-li"
						lay-tips="${sidebarSup.name }">&#xe6b8;</i> <cite>${sidebarSup.name }</cite> <i
						class="iconfont nav_right">&#xe697;</i></a>
						
					<ul class="sub-menu">
					<c:forEach items="${sidebarSup.sidebarSubs }" var="sidebarSub">
						<li><a onclick="xadmin.add_tab('${sidebarSub.name}','${sidebarSub.uri }')">
								<i class="iconfont">&#xe6a7;</i> <cite>${sidebarSub.name }</cite>
						</a></li>
						</c:forEach>
					</ul>
					
				</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<!-- <div class="x-slide_left"></div> -->
	<!-- 左侧菜单结束 -->
	<!-- 右侧主体开始 -->
	<div class="page-content">
		<div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
			<ul class="layui-tab-title">
				<li class="home"><i class="layui-icon">&#xe68e;</i>我的桌面</li>
			</ul>
			<div class="layui-unselect layui-form-select layui-form-selected"
				id="tab_right">
				<dl>
					<dd data-type="this">关闭当前</dd>
					<dd data-type="other">关闭其它</dd>
					<dd data-type="all">关闭全部</dd>
				</dl>
			</div>
			<div class="layui-tab-content">
				<div class="layui-tab-item layui-show">
					<iframe src='welcome' frameborder="0" scrolling="yes"
						class="x-iframe"></iframe>
				</div>
			</div>
			<div id="tab_show"></div>
		</div>
	</div>
	<div class="page-content-bg"></div>
	<style id="theme_style"></style>

</body>
<script>
var speed=55
var demo=document.getElementById("demo");
var demo2=document.getElementById("demo2");
var demo1=document.getElementById("demo1");
demo2.innerHTML=demo1.innerHTML
function Marquee(){
if(demo2.offsetTop-demo.scrollTop<=0)
demo.scrollTop-=demo1.offsetHeight
else{
demo.scrollTop++
}
}
var MyMar=setInterval(Marquee,speed)
demo.οnmοuseοver=function() {clearInterval(MyMar)}
demo.οnmοuseοut=function() {MyMar=setInterval(Marquee,speed)}
</script>
</html>
