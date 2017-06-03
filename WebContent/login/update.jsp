<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>添加用户</title>
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
<div class="box">
<br><br><br>
<p>删除或者修改用户</p>
<script type="text/javascript">
	var flag = 1;
	function checkPassword(){
		var password = document.getElementById("password").value;
		var password1 = document.getElementById("password1").value;
		if(flag != 0){
			if(password.length < 6){
				alert("密码小于6位！");
				return false;
			}
			if(password != password1){
				alert("两次密码不一致！");
				return false;
			}
		}
	}
	function dao(){
		flag = 0;
	}
</script>
<form name="form1"action="../updateadminServlet" method="post" onsubmit="return checkPassword()">
<p><label for="zhanghao">账&nbsp;&nbsp;号：</label><input type="text" id="zhanghao" name="zhanghao"></p>
<p><label for="password">设置密码：</label><input type="password" id="password" name="password" ></p>
<p><label for="password1">确认密码：</label><input type="password" id="password1" name="password1" ></p>
<p><label for="flag">是否设置为管理员：</label><select name="flag" id="flag" size="1">
					<option value="0">否</option>
					<option value="1">是</option>
				</select></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;<input class="button" type="submit" name="submit"value="修改">&nbsp;<input class="button" type="submit" name="submit"value="删除" onclick="dao()">&nbsp;<input class="button" type="reset" value="取消"></p>
</form>
</div>
</body>
</html>