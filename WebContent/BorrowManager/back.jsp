<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="model.AdminBean"%>
<%@ page  import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>归还图书</title>
<link href="../css/maincss.css" rel="stylesheet" type="text/css">
<link href="../css/infoadd.css" rel="stylesheet" type="text/css">
<script src="../jquery/main.js"></script>
<style type="text/css">
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
	.hidden{
		display:none;
	}
</style>

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
<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
<form action="../UpdateBorrowServlet" method="post" onsubmit="return check()">
<div class="box">
	<h1>图书归还</h1>
	<div class="info1">
		<p class="hidden"><label for="bid">借阅ID：</label><input name="bid" id="bid" type="text" value="${borrow.bid}" readonly="readonly"></p>
		<p><label for="readerid">读者证件：</label><input name="readerid" id="readerid" type="text" onchange="showReader()" value="${borrow.readerId}" readonly="readonly"></p>
		<p><label for="readername">读者姓名：</label><input name="readername" id="readername" type="text" readonly="readonly" onclick="selectReader(this)" value="${borrow.readerName}"></p>
		<hr>
		<p><label for="booknum">图书编号：</label><input name="booknum" id="booknum" type="text" onchange="showBook(this)" readonly="readonly" value="${borrow.bookNum}"></p>
		<p><label for="bookname">图书名称：</label><input name="bookname" id="bookname" type="text" readonly="readonly" onclick="selectBook(this)" value="${borrow.bookName}"></p>
		<c:choose>
			<c:when test="${borrow.flag==0}">
				<p class="hidden"><label for="flag">是否归还：</label><select name="flag" id="flag" size="1">
					<option value="1">是</option>
					<option value="0">否</option>
				</select></p>
			</c:when>
			<c:otherwise>
				<p><label for="flag">是否归还：</label><select name="flag" id="flag" size="1">
					<option value="1" selected>是</option>
					<option value="0">否</option>
				</select></p>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="info2">
	<c:choose>
		<c:when test="${borrow.flag==0}">
			<p><label for="borrowdate">借阅日期：</label><input name="borrowdate" id="borrowdate" type="text" readonly="readonly" value="${borrow.borrowDate}"></p>
			<fmt:formatDate value="${shoulddate}" type="both" dateStyle="long" pattern="yyyy-MM-dd" var="sd"/>
			<p><label for="shoulddate">应还日期：</label><input name="shoulddate" id="shoulddate" type="text" readonly="readonly" value="${sd}"></p>
			<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" var="bb"/>
			<p><label for="time">当前时间：</label><input name="time" id="time" type="text" value="${bb}" readonly="readonly"></p>
			<%
			if(flag == 1){%>
				<p><label for="message">罚款原因：</label><select name="message" id="message" size="1" >
					<option value="1">无</option>
					<option value="2">超期</option>
					<option value="3">损伤</option>
					<option value="4">超期并损伤</option>
					<option value="5">遗失</option>
			</select>
			<label for="deposit">罚款金额：</label><input type="text" size="5" name="deposit" id="deposit" value="0.0">元
			</p>
			<%}else{%>
			<p class="hidden"><label for="message">罚款原因：</label><select name="message" id="message" size="1" >
					<option value="1">无</option>
					<option value="2">超期</option>
					<option value="3">损伤</option>
					<option value="4">超期并损伤</option>
					<option value="5">遗失</option>
			</select>
			<label for="deposit">罚款金额：</label><input type="text" size="5" name="deposit" id="deposit" value="0.0">元
			</p>
			<%}
			%>
			
			<c:choose>
				<c:when test="${bb>sd}">
					<c:set var="timeMinus" value="${now.time - shoulddate.time}"/>
					<fmt:formatNumber value="${timeMinus/1000/3600/24}" pattern="#0" var="result"/>
					<p><label for="moredate">超期天数：</label><input type="text" size="5" name="moredate" id="moredate" value="${result}">天</p>	
				</c:when>
				<c:otherwise>
					<p><label for="moredate">超期天数：</label><input type="text" size="5" name="moredate" id="moredate" value="0">天</p>	
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<p><label for="borrowdate">借阅日期：</label><input name="borrowdate" id="borrowdate" type="text" readonly="readonly" value="${borrow.borrowDate}"></p>
			<p><label for="shoulddate">归还日期：</label><input name="shoulddate" id="shoulddate" type="text" readonly="readonly" value="${borrow.backDate}"></p>
			<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" var="bb"/>
			<p><label for="time">当前时间：</label><input name="time" id="time" type="text" value="${bb}" readonly="readonly"></p>
			<p><label for="message">罚款原因：</label><select name="message" id="message" size="1" >
			<c:choose>
				<c:when test="${borrow.message==1}">
					<option value="1" selected>无</option>
				</c:when>
				<c:otherwise>
					<option value="1">无</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${borrow.message==2}">
					<option value="2" selected>超期</option>
				</c:when>
				<c:otherwise>
					<option value="2">超期</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${borrow.message==3}">
					<option value="3" selected>损伤</option>
				</c:when>
				<c:otherwise>
					<option value="3">损伤</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${borrow.message==4}">
					<option value="4" selected>超期并损伤</option>
				</c:when>
				<c:otherwise>
					<option value="4">超期并损</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${borrow.message==4}">
					<option value="5" selected>遗失</option>
				</c:when>
				<c:otherwise>
					<option value="5">遗失</option>
				</c:otherwise>
			</c:choose>
			</select>
			<label for="deposit">罚款金额：</label><input type="text" size="5" name="deposit" id="deposit" value="${borrow.deposit}">元
			</p>
		</c:otherwise>
	</c:choose>
	<c:forEach var="log" items="${login}" begin="${fn:length(login) - 1}" end="${fn:length(login) - 1}">
	    <p><label for="admin">操作员：</label><input name="admin" id="admin" type="text" value="${log.username}" readonly="readonly"></p>		
	</c:forEach>
	</div>
	<script type="text/javascript">
	function check(){
		//罚金大于零时必须选择罚款原因
		var money = document.getElementById("deposit").value;
		var message = document.getElementById("message");
		var msgvalue = message.options[message.selectedIndex].value;
		var more = document.getElementById("moredate").value;
		if(money > 0 && msgvalue == 1){
			alert("请选择罚款原因！");
			return false;
		}
		var flag = <%=flag%>
		if(more > 0 && flag ==0){
			alert("已超期！请到前台办理");
			return false;
		}
	}
</script>
	<div class="buttons">
		<c:choose>
			<c:when test="${borrow.flag==0}">
				<input class="button" type="submit" value="归还" name="submit">
				<input class="button" type="submit" value="续借" name="submit">
			</c:when>
			<c:otherwise>
				<input class="button" type="submit" value="修改" name="submit">
				<input class="button" type="submit" value="删除" name="submit">
			</c:otherwise>
		</c:choose>	
		<input class="button" type="reset" value="取消">
		<button class="button" onclick="goBack()">返回</button> 
		<button class="button" onclick="renovate()">刷新</button>
	</div>
</div>
</form>
</body>
</html>