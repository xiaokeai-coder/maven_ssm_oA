/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-05-14 01:27:46 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!doctype html>\r\n");
      out.write("<html class=\"x-admin-sm\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>天亮OA系统</title>\r\n");
      out.write("<meta name=\"renderer\" content=\"webkit|ie-comp|ie-stand\">\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\r\n");
      out.write("<meta name=\"viewport\"\r\n");
      out.write("\tcontent=\"width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi\" />\r\n");
      out.write("<meta http-equiv=\"Cache-Control\" content=\"no-siteapp\" />\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"");
      out.print(request.getContextPath());
      out.write("/resources/css/font.css\">\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"");
      out.print(request.getContextPath());
      out.write("/resources/css/login.css\">\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"");
      out.print(request.getContextPath());
      out.write("/resources/css/xadmin.css\">\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.print(request.getContextPath());
      out.write("/resources/js/jquery.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/resources/lib/layui/layui.js\"\r\n");
      out.write("\tcharset=\"utf-8\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"login-bg\">\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"login layui-anim layui-anim-up\">\r\n");
      out.write("\t\t<div class=\"message\">天亮OA管理系统登录</div>\r\n");
      out.write("\t\t<div id=\"darkbannerwrap\"></div>\r\n");
      out.write("\r\n");
      out.write("\t\t<form method=\"post\" class=\"layui-form\">\r\n");
      out.write("\t\t\t<input name=\"name\" placeholder=\"用户名\" type=\"text\"\r\n");
      out.write("\t\t\t\tlay-verify=\"required\" class=\"layui-input\">\r\n");
      out.write("\t\t\t<hr class=\"hr15\">\r\n");
      out.write("\t\t\t<input name=\"password\" lay-verify=\"required\" placeholder=\"密码\"\r\n");
      out.write("\t\t\t\ttype=\"password\" class=\"layui-input\">\r\n");
      out.write("\t\t\t<hr class=\"hr15\">\r\n");
      out.write("\t\t\t<input value=\"登录\" lay-submit lay-filter=\"login\" style=\"width: 100%;\"\r\n");
      out.write("\t\t\t\ttype=\"submit\">\r\n");
      out.write("\t\t\t<hr class=\"hr20\">\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<script>\r\n");
      out.write("\t\t$(function() {\r\n");
      out.write("\t\t\tlayui.use('form', function() {\r\n");
      out.write("\t\t\t\tvar form = layui.form;\r\n");
      out.write("\t\t\t\t//监听提交\r\n");
      out.write("\t\t\t\tform.on('submit(login)', function(data) {\r\n");
      out.write("\t\t\t\t\t$.post('login',data.field,function(obj){\r\n");
      out.write("\t\t\t\t\t\tif(obj.result == 1){\r\n");
      out.write("\t\t\t\t\t\t\tlayer.msg(obj.msg);\r\n");
      out.write("\t\t\t\t\t\t\t// 跳转到主页面\r\n");
      out.write("\t\t\t\t\t\t\tlocation.href='index';\r\n");
      out.write("\t\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t\t// 展示错误信息 \r\n");
      out.write("\t\t\t\t\t\t\tlayer.msg(obj.msg);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t})\r\n");
      out.write("\t</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
