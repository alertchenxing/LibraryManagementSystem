<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作成功</title>
<style type="text/css">
	*{
		margin: 0;
		padding: 0;
	}
	.box{
		position:relative;
		height:400px;
		width:100%;
		background-color:#F0F0F0;
	}
	.warn{
		position:absolute;
		font-size:20px;
		margin:40px;
	}
</style>
<script>
function goBack(){
	window.history.back();
	self.location.reload();
}
</script>
</head>
<body>
<div class="box"><div class="warn">操作成功！<a href="" onclick="goBack()" >点击此处</a>返回上一级
<br><br><br><br><hr></div></div>
</body>
</html>