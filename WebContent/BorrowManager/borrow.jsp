<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书借阅</title>
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
	h1{
		background-color:#99CC99;
		font-size: 25px;
		text-align:left;
		padding: 10px 0px 10px 60px;
		display: block;
		border-bottom:1px solid #89AF4C;
		color: #FFF;
	}
	input, select{
		font-size:16px;	
		padding: 4px 8px;
	}
	p{
		padding: 10px 10px 10px 60px;
	}
	span{
		font-size: 14px;
		color: #FFF;
	}
	.info1{
		position:relative;
		width:40%;
		float:left;
		text-align:left;
	}
	.info2{
		position:relative;
		width:60%;
		float:left;
		text-align:left;
	}
	.buttons{
		position:absolute;
		bottom:10px;
		right:10px;
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
	.buttons{
		position:absolute;
		right:10px;
		bottom:10px;
	}
	span{
		font-size: 14px;
		color: #FFF;
	}
	
	#borrows-table{
		width:99%;
		border-collapse:collapse;
		text-align:left;
		table-layout:fixed;
	}
	#borrows-table th, #borrows-table td{
		border:1px solid #99CC77;
		padding:3px 7px 2px 7px;
		height:20px;
		width:25%;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
	}
	#borrows-table th{
		background-color:#99CC88;
		padding-top:5px;
		padding-bottom:4px;
		color:#ffffff;
	}
	
</style>
<script type="text/javascript">
	var xmlhttp;
	function showReader(){
		clearContents();
		var readerid = document.getElementById("readerid").value;
		if(readerid == ""){
			return;
		}
		var url = "../SelectBorrowServlet?readerid="+readerid;
		xmlhttp = creatXMLHttp();
		xmlhttp.onreadystatechange = readerDao;
		sendUrl(url);
	}
	function showBook(obj){
			
			var booknum = obj.value;
			if(booknum == ""){
				return;
			}
			var url = "../SelectBorrowServlet?booknum="+booknum;
			xmlhttp = creatXMLHttp();
			xmlhttp.onreadystatechange = BookDao;
			sendUrl(url);
		}
	
	function BookDao(){
		if(xmlhttp.readyState==4 && xmlhttp.status==200){
			var result = xmlhttp.responseText;
			alert(result);
		}
	}
	function readerDao(){
		if(xmlhttp.readyState==4 && xmlhttp.status==200){
			var result = xmlhttp.responseText;
			if(result == 1){
				alert("读者不存在！");
			}else{
				var num = document.getElementById("maxnum").value;
				var json = eval("("+result+")");
				document.getElementById("readername").value = json.readerName;
				var size;
				if(typeof(json.borrows) ==  "undefined"){
					document.getElementById("maxnum").value = json.maxNum;
				}else{
					size = json.borrows.length;
					document.getElementById("maxnum").value = json.maxNum - size;
					for(var i = 0; i < size; i++){
						var tb = document.getElementById("borrows-table");
						tb.rows[i+1].cells[0].innerHTML = json.borrows[i].bookNum;
						tb.rows[i+1].cells[1].innerHTML = json.borrows[i].bookName;
						tb.rows[i+1].cells[2].innerHTML = json.borrows[i].borrowDate;
						tb.rows[i+1].cells[3].innerHTML = json.borrows[i].shouldDate;
					}
				}
			}
			
		}
	}
	function clearContents(){
		document.getElementById("readername").value = "";
		document.getElementById("maxnum").value = "";
		var tb = document.getElementById("borrows-table");
		var i = tb.rows.length;
		var j = tb.rows[0].cells.length;
		for(var a=1;a<i;a++){
			for(var b = 0; b < j; b++){
				tb.rows[a].cells[b].innerHTML = "";
			}
		}
	}
	function creatXMLHttp(){
		var xmlhttp;
		if(window.XMLHttpRequest){
			xmlhttp=new XMLHttpRequest();
		}else{
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		return xmlhttp;
	}
	function sendUrl(url){
		xmlhttp.open("GET",url,true);
		xmlhttp.send();
	}
	function selectReader(obj){
		var readername = obj.value;
		if(readername == ""){
			return;
		}else{
			var id = document.getElementById("readerid").value
			window.location.href='../SelectReaderServlet?strkey='+id;
		}
	}
	function selectBook(obj){
		var bookname = obj.value;
		if(bookname == ""){
			return;
		}else{
			var id = document.getElementById("booknum").value
			window.location.href="url";
		}
	}
	window.onload = function(){
		clearContents();
		showReader();
	}
</script>
</head>
<body>
<form action="">
<div class="box">

<h1>图书借阅<span>(输入读者证件号和图书编号)</span></h1>
<div class="info1">
<p><label for="readerid">读者证件：</label><input name="readerid" id="readerid" type="text" onchange="showReader()"></p>
<p><label for="readername">读者姓名：</label><input name="readername" id="readername" type="text" readonly="readonly" onclick="selectReader(this)"></p>
<p><label for="maxnum">可借数量：</label><input name="maxnum" id="maxnum" type="text" readonly="readonly"></p>
<hr>
<p><label for="booknum">图书编号：</label><input name="booknum" id="booknum" type="text" onchange="showBook(this)"></p>
<p><label for="bookname">图书名称：</label><input name="bookname" id="bookname" type="text" readonly="readonly" onclick="selectBook(this)"></p>
<p><label for="booktype">图书类别：</label><input name="booktype" id="booktype" type="text" readonly="readonly"></p>
</div>
<div class="info2">
<div id="borrows">
<table id="borrows-table">
<tr>
	<th>图书编号</th>
	<th>图书名称</th>
	<th>借阅日期</th>
	<th>应还日期</th>
</tr>
<tr>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
</tr>
<tr>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
</tr>
<tr>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
</tr>
<tr>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
</tr>
<tr>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
</tr>
</table>
</div>
<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" var="bb"/>
<p><label for="time">当前时间：</label><input name="time" id="time" type="text" value="${bb}" readonly="readonly"></p>
<c:forEach var="log" items="${login}" begin="${fn:length(login) - 1}" end="${fn:length(login) - 1}">
    <p><label for="admin">操作员：</label><input name="admin" id="admin" type="text" value="${log.username}" readonly="readonly"></p>		
</c:forEach>

</div>

<div class="buttons">
		<input class="button" type="submit" value="借出">
		<input class="button" type="reset" value="取消">
		<button class="button" onclick="goBack()">返回</button> 
		<button class="button" onclick="renovate()">刷新</button>
</div>
</div>
</form>
</body>
</html>