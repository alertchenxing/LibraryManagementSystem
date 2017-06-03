<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="model.AdminBean"%>
<%@ page  import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>设置密码</title>
<link href="../css/maincss.css" rel="stylesheet" type="text/css">
<link href="../css/infoadd.css" rel="stylesheet" type="text/css">
<style type="text/css">
	.box{
		text-align:center;
	}
	.hidden{
		display:none;
	}
</style>
</head>
<body>
<% 
	String username = null;
	int flag = 0;
	ArrayList login = (ArrayList)session.getAttribute("login");
	if (login == null || login.size() == 0) {
		response.sendRedirect("../login/login.jsp");
	}else{
		for(int i = login.size() - 1; i >= 0; i --){
			AdminBean aBean = (AdminBean)login.get(i);
			username = aBean.getUsername();
			flag = aBean.getFlag();
		}
	}
%>
<div class="box">
<br><br><br>
<p>修改密码</p>
<script type="text/javascript">
	function checkPassword(){
		var password = document.getElementById("password").value;
		var password1 = document.getElementById("password1").value;
		if(password.length < 6){
			alert("密码小于6位！");
			return false;
		}
		if(password != password1){
			alert("两次密码不一致！");
			return false;
		}
	}
</script>
<form action="../updateadminServlet" method="post" onsubmit="return checkPassword()">
<p><label for="zhanghao">账&nbsp;&nbsp;号：</label><input type="text" id="zhanghao" name="zhanghao" value="<%=username%>" readonly="readonly"></p>
<p><label for="password">设置密码：</label><input type="password" id="password" name="password" ></p>
<p><label for="password1">确认密码：</label><input type="password" id="password1" name="password1" ></p>
<p class="hidden"><label for="flag">是否设置为管理员：</label><select name="flag" id="flag" size="1">
<%
			if(flag == 0){%>
				<option value="0">否</option>
			<%}else{%>
				<option value="1">是</option>
			<%}
%>
				</select></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;<input class="button" type="submit" name="submit"value="修改">&nbsp;<input class="button" type="reset" value="取消"></p>
</form>
</div>
</body>
</html>