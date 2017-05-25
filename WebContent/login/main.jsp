<%@page import="model.AdminBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page  import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书借阅系统——主页</title>
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
	.header{
		position:absolute;
		height:25px;
		margin-top:100px;
		width:100%;
		background-color:#F0F0F0;
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
	.menu{
		position:absolute;
		height:400px;
		margin-left:5%;
		margin-top:10px;
		width:20%;
		/*border-left:2px solid gray;*/
	}
	.content{
		position:absolute;
		height:400px;
		margin-left:26%;
		margin-top:10px;
		width:69%;
	}
	.middle .menu ul{
		list-style-type: none;
		text-align:center;
	}
	.accordion{
		width:100%;
		border-radius:8px;/*圆角*/
		-webkit-border-radius:8px;
		-moz-border-radius:8px;
		background:#F0F0F0;
	}
	.accordion .link{
		cursor:pointer;/*光标*/
		display:block;
		color:#4d4d4d;
		padding:15px 15px 15px 15px;
		font-size:16px;
		font-weight:700;/*加粗字体*/
		border-bottom:1px solid #ccc;
		position:relative;
		-webkit-transition:all 0.4s ease;/*渐变*/
		-o-transition:all 0.4s ease;
		transition:all 0.4s ease;
	}
	.accordion li:last-child .link{
		border-bottom:0;/*去掉最后一个导航的下边框*/
	}
	.accordion li i.fa-chevron-down{
		background-image:url(../image/expanded.jpg);
		background-size:cover;
		position:absolute;
		padding:15px 15px 15px 15px;
		right:15px;
		-webkit-transition:all 0.4s ease;/*渐变*/
		-o-transition:all 0.4s ease;
		transition:all 0.4s ease;
	}
	.accordion li.open .link{
		color:#b63b4d;
	}
	.accordion li.open i.fa-chevron-down{
		-webkit-transform: rotate(180deg);/*旋转180度*/
		-ms-transform: rotate(180deg);
		-o-transform: rotate(180deg);
		transform: rotate(180deg);
	}
	.submenu{
		display:none;
		background:#444359;
		font-size:14px;
	}
	.submenu li{
		border-bottom:1px solid #4b4a5e;
	}
	.submenu a{
		display:block;
		text-decoration:none;/*文本修饰*/
		color:#d9d9d9;
		padding:10px;
		-webkit-transition: all 0.25s ease;
 		-o-transition: all 0.25s ease;
 		transition: all 0.25s ease;
	}
	.submenu a:hover{
		background:#b63b4d;
		color: #FFF;
	}
	iframe{
		width:100%;
		height:100%;
		border-radius:8px;
		-webkit-border-radius:8px;
		-moz-border-radius:8px;
	}
	.header ul{
		position:absolute;
		list-style-type:none;/*移除列表前小标志*/
		right:6%;
		overflow:hidden;/*隐藏溢出*/
	}
	.header ul li{
		float:left;/*使li浮动，彼此相邻*/
	}
	.header ul li a:link,a:visited{
		display:block;
		width:120px;
		font-weight:700;
		color:#000;
		background-color:#D8D8D8;
		text-align:center;
		padding:2px;
		text-decoration:none;/*去掉文本修饰，这里去掉了下划线*/
	}
	.header ul li a:hover,a:active
	{
		background-color:#C0C0C0;
	}
</style>
</head>
<body>
<% 
	String username = null;
	ArrayList login = (ArrayList)session.getAttribute("login");
	if (login == null || login.size() == 0) {
		response.sendRedirect("login.jsp");
	}else{
		for(int i = login.size() - 1; i >= 0; i --){
			AdminBean aBean = (AdminBean)login.get(i);
			username = aBean.getUsername();
		}
	}
%>
<div class="container">
	<div class="top"><div class="header">
		<ul>
			<li><a href="#"><%=username%>已登录</a></li>
			<li><a href="#" onclick="logOut()">退出登陆</a></li>
		</ul>
	</div></div>
	<div class="middle">
		<div class="menu">
			<ul id="accordion" class="accordion">
				<li>
					<div class="link">读者信息管理<i class="fa-chevron-down"></i></div>
					<ul class="submenu">
						<li><a href="../ShowReadersServlet" target="main">信息显示</a></li>
						<li><a href="../ReaderManager/addNewReader.jsp" target="main">读者添加</a></li>
						<li><a href="../ReaderManager/selectReader.jsp" target="main">读者查询</a></li>
					</ul>
				</li>
				<li>
					<div class="link">图书信息管理<i class="fa fa-chevron-down"></i></div>
					<ul class="submenu">
						<li><a href="../ShowBookServlet" target="main">信息显示</a></li>
						<li><a href="../BookManager/addNewBook.jsp" target="main">图书添加</a></li>
						<li><a href="../BookManager/selectBook.jsp" target="main">图书查询</a></li>
					</ul>
				</li>
				<li>
					<div class="link">图书借阅管理<i class="fa fa-chevron-down"></i></div>
					<ul class="submenu">
						<li><a href="../BorrowManager/borrow.jsp" target="main">图书借阅</a></li>
						<li><a href="#">图书归还</a></li>
					</ul>
				</li>
				<li>
					<div class="link">新书订购管理<i class="fa fa-chevron-down"></i></div>
					<ul class="submenu">
						<li><a href="#">订单查询</a></li>
						<li><a href="#">订单建立</a></li>
					</ul>
				</li>
				<li>
					<div class="link">账户管理<i class="fa fa-chevron-down"></i></div>
					<ul class="submenu">
						<li><a href="#">修改密码</a></li>
						<li><a href="#">用户添加</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<div class="content"><iframe id="main" name="main" src="welcome.jsp"></iframe></div>
	</div>
	<div class="bottom"></div>
</div>
</body>
</html>