<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生注册主页</title>
<script src="../jquery/jquery-3.2.0.js"></script>
<script type="text/javascript">
$(function() {
	var Accordion = function(el, multiple) {
		this.el = el || {};
		this.multiple = multiple || false;
		var links = this.el.find('.link');
		links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
	}

	Accordion.prototype.dropdown = function(e) {
		var $el = e.data.el;
			$this = $(this),
			$next = $this.next();
		$next.slideToggle();
		$this.parent().toggleClass('open');
		if (!e.data.multiple) {
			$el.find('.submenu').not($next).slideUp().parent().removeClass('open');
		};
	}	
	var accordion = new Accordion($('#accordion'), false);
});
function logOut(){
	var flag = confirm("是否退出系统？");
	if(flag){
		var xmlhttp;
		if(window.XMLHttpRequest){
			xmlhttp=new XMLHttpRequest();
		}else{
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function(){
			if(xmlhttp.readyState==4 && xmlhttp.status==200){
				if(xmlhttp.responseText == 0){
					window.location.href='login.jsp';
				}
			}
		}
		xmlhttp.open("GET","../LogoutServlet",true);
		xmlhttp.send();
	}
}
</script>
<style type="text/css">
	*{
		margin: 0;
		padding: 0;
	}
	.container{
		position:relative;
		width:100%;
		line-height:150%;/*行高*/
	}
	.top{
		position:relative;
		background-image:url(../image/1_088.jpg);
		background-repeat:repeat;
		height:125px;
	}
	.middle{
		position:relative;
		background-image:url(../image/booklogin.jpg);
		background-repeat:no-repeat;
		background-size:cover;
		height:420px;
	}
	.bottom{
		position:relative;
		background-image:url(../image/1_088.jpg);
		background-repeat:repeat;
		height:70px;
	}
	.content{
		position:absolute;
		height:400px;
		margin-left:10%;
		margin-top:10px;
		width:80%;
	}
	iframe{
		width:100%;
		height:100%;
		border-radius:8px;
		-webkit-border-radius:8px;
		-moz-border-radius:8px;
	}
</style>
</head>
<body>
<div class="container">
	<div class="top"></div>
	<div class="middle">
		<div class="content"><iframe id="main" name="main" src="addstudent.jsp"></iframe></div>
	</div>
	<div class="bottom"></div>
</div>
</body>
</html>