<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆系统</title>
<script src="../jquery/jquery-3.2.0.js"></script>
<script src="../jquery/messages_zh.js"></script>
<script src="../jquery/jquery.validate.min.js"></script>
<style type="text/css">
	*{
		margin: 0;
		padding: 0;
	}
	div{
			display:block;	
		}
	.top{
			background-image:url(../image/1_088.jpg);
			background-repeat:repeat;
			height:125px;
		}
	.midl{
			background-image:url(../image/booklogin.jpg);
			background-repeat:no-repeat;
			background-size:cover;
			height:420px;
			}
	.bottom{
			background-image:url(../image/1_088.jpg);
			background-repeat:repeat;
			height:70px;
			}
	.form{
			position:relative;	
			width:370px;
			height:190px;
			background-color:#A7C942;
			border-radius: 25px;
			opacity:0.6;
  			filter:alpha(opacity=60);
  			top:110px;
	}
	table{
			border-collapse:collapse;
			width:360px;
			height:180px;
			position:relative;	
			top:-90px;
			}
	table, tr, td{
			left:55px;
		}
	#message{
		margin:0px;
		margin-top:10px;
		left:55px;
		font-size:16px;
		height:18px;
	}
	#form{
			height:120px;
		}
	.button {
   	 	background-color: #A7C942;
    	border: none;
    	color: white;
    	padding: 8px 20px;
    	text-align: center;
    	text-decoration: none;
    	display: inline-block;
    	font-size: 16px;
    	margin: 4px 2px;
    	cursor: pointer;
    	border-radius: 8px;
    	box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
}
.button:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);
}
.zhuce{
	position:relative;	
	left:200px;
}
</style>
<script type="text/javascript">
	$().ready(function(){
		$("#loginform").validate({
			debug:true,
			rules: {
					username: {
					required:true,
					minlength:2,
				},
				password: {
					required:true,
					minlength:6
				}
			},
			messages: {
				username: {
					required:"请输入用户名！",
					minlength:"低于两个字符！"
				},
				password: {
					required:"请输入密码！",
					minlength:"低于6个字符！"
				}
			}
		});
	});
	function checklogin(){
		var username = document.getElementById("username").value;  
	    var password = document.getElementById("password").value; 
		var xmlhttp;
		if (window.XMLHttpRequest)
		  {
		    // IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		    xmlhttp=new XMLHttpRequest();
		  }
		  else
		  {
		    // IE6, IE5 浏览器执行代码
		    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		xmlhttp.onreadystatechange=function()
		  {
		    if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
		    	if(xmlhttp.responseText == 1){
		    		window.location.href='main.jsp';
		    	}else if(xmlhttp.responseText == 2){
		    		window.location.href='../StudentManager/main.jsp';
		    	}else{
		    		document.getElementById("message").innerHTML=xmlhttp.responseText;
		    	}
		    }
		  }
		xmlhttp.open("GET","../LoginServlet?username="+username+"&password="+password,true);
		xmlhttp.send();
	}
</script>
</head>
<body>
<div>
<div class="top"></div>
<div class="midl">
	<center><div class="form"></div><form id="loginform">
						<table>
							<tr id=form >
								<td>
									<br>
									<label for="username">用户：</label><input id="username" type="text"
									name="username" size="20" required>
									<br><br>
									<label for="password">密码：</label><input id="password"type="password" 
									name="password" size="20" required>
									<p id="message"></p>
								</td>
							</tr>
							<tr><td><a href="../StudentManager/register.jsp" target="_top" class="zhuce">学生注册</a></td></tr>
							<tr>
								<td>
									<input type="submit" value="登陆" class="button" onclick="checklogin()">&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" value="清除" class="button">
								</td>
							</tr>
							
							
						</table>	
		</form></div></center></div>
<div class="bottom"></div>
</div>
</body>
</html>