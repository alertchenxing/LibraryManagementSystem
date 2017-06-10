<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>检索图书信息</title>
<link href="../css/maincss.css" rel="stylesheet" type="text/css">
<link href="../css/infoadd.css" rel="stylesheet" type="text/css">
<script src="../jquery/main.js"></script>
<style type="text/css">
	.box{
		text-align:center;
	}
	.select-borrow{
		position:relative;
		left:-22px;
		top:10px;
	}
	.bookinfobyselect{
		position:relative;
		top:10px;
		height:340px;
		width:100%;
	}
	iframe{
		width:100%;
		height:100%;
	}
</style>
<script type="text/javascript">
	function checkSelect(){
		var str = document.getElementById("strkey").value;
		if(str == "" || str == null){
			alert("请输入关键字！");
			return false;
		}
	}
</script>
</head>
<body>
<div class="box">
<div class="select-borrow">
	<form action="../SelectBookServlet" method="get" onsubmit="return checkSelect()" target="bookinfobyselect">
		<p>
			<select name="strsearchtype">
				<option value="allstr">所有字段</option>
				<option value="bookname">图书名称</option>
				<option value="author">作者</option>
				<option value="trans">译者</option>
				<option value="booknum">图书编号</option>
				<option value="type">图书类别</option>
				<option value="bookpublisher">出版社</option>
			</select>
			<input type="text" name="strkey" id="strkey" size="30" maxlength='30' required>
			<input type="submit" value="检索" class="button">
		</p>
	</form></div>
<div class="bookinfobyselect"><iframe id="bookinfobyselect" name="bookinfobyselect"></iframe></div>
<div class="buttons">
		<button class="button" onclick="goBack()">返回</button> 
		<button class="button" onclick="goForward()">前进</button>
		<button class="button" onclick="renovate()">刷新</button>
</div>
</div>
</body>
</html>