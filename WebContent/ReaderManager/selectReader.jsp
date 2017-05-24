<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查找读者信息</title>
<style type="text/css">
	*{
		margin: 0;
		padding: 0;
	}
	.box{
		position:relative;
		height:400px;
		width:100%;
		background-color:#F8F8F8;
		text-align:center;
	}
	.select{
		position:relative;
		top:150px;
	}
	.history{
		position:absolute;
		right:10px;
		bottom:10px;
	}
	.button{
		padding: 8px 16px 7px 16px;
		background-color:#99CC99;
		border:0px;
		font-size:15px;
		transition:background-color .3s;/*颜色渐变*/
		-webkit-transition:background-color .3s;
		-o-transition:background-color .3s;
	}
	.button:hover{
		background-color: #99CCCC;
	}
	#strkey{
		font-size:16px;
		padding: 4px 8px;
	}
</style>
<script type="text/javascript">
function trout(obj){
	if(obj != clickedRow){
		obj.style.background="#F8F8F8";
	}
}
function goBack(){
	window.history.back();
}
function goForward(){
	window.history.forward();
}
function renovate(){
	self.location.reload();
}
</script>
</head>
<body>
<div class="box">
<div class="select"><form action="../SelectReaderServlet" method="get">
	<label for="strkey">输入用姓名或者证件号进行查找：</label><input type="text" name="strkey" id="strkey" size="30" maxlength='30' required>
	<input type="submit" value="查找" class="button">
</form></div>
<div class="history">
		<button class="button" onclick="goBack()">返回</button> 
		<button class="button" onclick="goForward()">前进</button>
		<button class="button" onclick="renovate()">刷新</button>
</div>
</div>
</body>
</html>