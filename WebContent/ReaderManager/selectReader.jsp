<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查找读者信息</title>
<link href="../css/maincss.css" rel="stylesheet" type="text/css">
<script src="../jquery/main.js"></script>
<style type="text/css">
	.box{
		text-align:center;
	}
</style>
</head>
<body>
<div class="box">
<div class="select"><form action="../SelectReaderServlet" method="get">
	<label for="strkey">输入用姓名或者证件号进行查找：</label><input type="text" name="strkey" id="strkey" size="30" maxlength='30' required>
	<input type="submit" value="查找" class="button">
</form></div>
<div class="buttons">
		<button class="button" onclick="goBack()">返回</button> 
		<button class="button" onclick="goForward()">前进</button>
		<button class="button" onclick="renovate()">刷新</button>
</div>
</div>
</body>
</html>