<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>密码修改成功</title>
<link href="../css/maincss.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	var time = 2;
	function logout(){
		setInterval(function(){
			var xmlhttp;
			if(window.XMLHttpRequest){
				xmlhttp=new XMLHttpRequest();
			}else{
				xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange=function(){
				if(xmlhttp.readyState==4 && xmlhttp.status==200){
					if(xmlhttp.responseText == 0){
					}
				}
			}
			xmlhttp.open("GET","../LogoutServlet",true);
			xmlhttp.send();	
			document.getElementById("demo").innerHTML = time;
			time = time - 1;
			if(time == 0){
				window.top.location.href='../login/login.jsp';
			}
		},1000);
	}
	window.onload = function(){
		logout();
	}
</script>
</head>
<body>
<div class="box"><div class="warn">密码修改成功！<div id="demo">3</div>秒后返回登录
<br><br><br><br><hr></div></div>
</body>
</html>