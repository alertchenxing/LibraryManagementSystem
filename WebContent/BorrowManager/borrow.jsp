<%@page import="model.AdminBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page  import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书借阅</title>
<link href="../css/maincss.css" rel="stylesheet" type="text/css">
<link href="../css/infoadd.css" rel="stylesheet" type="text/css">
<script src="../jquery/main.js"></script>
<style type="text/css">
	.hidden{
			display:none;
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
<script src="../jquery/table.js"></script>
<script type="text/javascript">
	var xmlhttp;
	var clickedRow;
	function trclickofBorrow(obj){
		if(clickedRow != null){
			clickedRow.style.background="#F8F8F8";
		}
		obj.style.background="#D8D8D8";
		clickedRow = obj;
		var bid = clickedRow.cells[4].innerHTML;
		if(bid != ""){
			window.location.href="../SelectOneBorrowServlet?bid="+bid;
		}
		
	}
	function trhover(obj){
		if(obj != clickedRow){
			obj.style.background="#F0F0F0";
		}
	}
	function trout(obj){
		if(obj != clickedRow){
			obj.style.background="#F8F8F8";
		}
	}
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
			if(result == 0){
				alert("该图书不存在！");
			}else{
				var json = eval("("+result+")");
				document.getElementById("bookname").value = json.bookName;
				document.getElementById("booktype").value = json.bookType;
				var flag = json.numb - json.b_numb;
				if(flag <= 0){
					alert("该图书已达到最大借书数量！不可再借！");
				}
			}
		}
	}
	function readerDao(){
		if(xmlhttp.readyState==4 && xmlhttp.status==200){
			var result = xmlhttp.responseText;
			if(result == 1){
				alert("读者不存在！");
			}else{
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
						tb.rows[i+1].cells[4].innerHTML = json.borrows[i].bid;
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
			var booknum = document.getElementById("booknum").value
			window.location.href="../SelectBookServlet?strkey="+booknum;
		}
	}
	function checkInfo(){
		var readername = document.getElementById("readername").value;
		var bookname = document.getElementById("bookname").value;
		if(readername == ""){
			alert("请输入正确的读者证件号！");
			return false;
		}
		if(bookname == ""){
			alert("请输入正确的图书编号！");
			return false;
		}
	}
	window.onload = function(){
		clearContents();
		showReader();
	}
</script>
</head>
<body>
<%
	ArrayList<AdminBean> loginrList = new ArrayList<>();
	loginrList = (ArrayList)session.getAttribute("login");
	AdminBean aBean = new AdminBean();
	int flag = 0;
	String username = null;
	for(int i = 0; i < loginrList.size(); i++){
		aBean = (AdminBean)loginrList.get(i);
		username = aBean.getUsername();
		flag = aBean.getFlag();
	}
%>
<form action="../AddBorrowServlet" method="post" onsubmit="return checkInfo()">
<div class="box">

<h1>图书借阅<span>(输入读者证件号和图书编号)</span></h1>
<div class="info1">
<%
			if(flag == 0){%>
				<p><label for="readerid">读者证件：</label><input name="readerid" id="readerid" type="text" onchange="showReader()" value="<%=username%>"></p>
			<%}else{%>
			<p><label for="readerid">读者证件：</label><input name="readerid" id="readerid" type="text" onchange="showReader()"></p>
		<%}
%>
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
<tr onclick="trclickofBorrow(this)" onmouseover="trhover(this)" onmouseout="trout(this)">
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
</tr>
<tr onclick="trclickofBorrow(this)" onmouseover="trhover(this)" onmouseout="trout(this)">
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
</tr>
<tr onclick="trclickofBorrow(this)" onmouseover="trhover(this)" onmouseout="trout(this)">
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
</tr>
<tr onclick="trclickofBorrow(this)" onmouseover="trhover(this)" onmouseout="trout(this)">
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
</tr>
<tr onclick="trclickofBorrow(this)" onmouseover="trhover(this)" onmouseout="trout(this)">
	<td></td>
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