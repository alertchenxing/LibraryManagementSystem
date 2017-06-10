<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示图书信息</title>
<link href="../css/maincss.css" rel="stylesheet" type="text/css">
<link href="../css/infoadd.css" rel="stylesheet" type="text/css">
<script src="../jquery/main.js"></script>
<script src="../jquery/jquery-3.2.0.js"></script>
<style type="text/css">
	.box{
		text-align:center;
	}
	.info-form{
		text-align:left;
	}
	.info1, .info2{
		position:relative;
		width:50%;
		float:left;
	}
	#previewImg{
		position:relative;
		left:200px;
		width:120px;
		height:140px;
	}
	#mouseimg{
		max-width:800px;
		max-height:390px;
	}
	.message, .message1{
		color:red;
	}
	a{
		color:black;
		text-decoration:none;/*去掉下划线*/
	}
</style>
<script type="text/javascript">
	//js实现上传头像预览
	var imgurl = ""; 
	function showPreview(node){
		var imgURL = "";  
        try{  
            var file = null;  
            if(node.files && node.files[0] ){  
                file = node.files[0];  
            }else if(node.files && node.files.item(0)) {  
                file = node.files.item(0);  
            }  
            //Firefox 因安全性问题已无法直接通过input[file].value 获取完整的文件路径  
            try{  
                imgURL =  file.getAsDataURL();  
            }catch(e){  
                imgRUL = window.URL.createObjectURL(file);  
            }  
        }catch(e){  
            if (node.files && node.files[0]) {  
                var book = new FileBook();  
                book.onload = function (e) {  
                    imgURL = e.target.result;  
                };  
                book.bookAsDataURL(node.files[0]);  
            }  
        }  
        creatImg(imgRUL);  
        return imgURL;
	}
	function creatImg(imgRUL){
    	document.getElementById("previewImg").innerHTML = 
			"<img src='"+imgRUL+"' width='120px' height='140px'/>"; 
    }
	//jQuery实现鼠标移动预览原图
	$(function(){
		var la = $("#large");
		la.hide();
		$("#previewImg").mousemove(function(e){
			la.css({
				"position":"fixed",
				"top":(e.pageY-100)+"px",
				"left":(e.pageX-500)+"px"
			}).html('<img id="mouseimg" src="'+ $(this).children("img").attr("src")+'"/>').show();
		}).mouseout(function(){
			la.hide();
		});
	});
	
	//表单验证函数
	function validateForm(){
		var flag = 0;
		var name = document.getElementById("bookname").value;
		var writer = document.getElementById("bookwriter").value;
		var publisher = document.getElementById("bookpublisher").value;
		var num = document.getElementById("num").value;
		var price = document.getElementById("price").value;
		$("label.message").remove();
		$("label.message1").remove();
		if(name == null || name ==""){
			$("p").eq(0).append("<label class='message'>请输入名称</label>");
			flag++;
		}
		if(writer == null || writer ==""){
			$("p").eq(2).append("<label class='message'>请输入作者</label>");
			flag++;
		}
		if(publisher == null || publisher ==""){
			$("p").eq(4).append("<label class='message'>请输入出版社</label>");
			flag++;
		}
		if(num == null || num =="" || num == 0){
			$("p").eq(8).append("<label class='message'>请输入库存</label>");
			flag++;
		}else if(isNaN(num)){
			$("p").eq(8).append("<label class='message'>请输入数字</label>");
			flag++;
		}
		if(price == null || price =="" || price == 0){
			$("p").eq(7).append("<label class='message'>请输入单价</label>");
			flag++;
		}else if(isNaN(price)){
			$("p").eq(7).append("<label class='message'>请输入数字</label>");
			flag++;
		}
		if(flag != 0){
			return false;
		}
	}
</script>
</head>
<body>
<c:forEach var="log" items="${login}" begin="${fn:length(login) - 1}" end="${fn:length(login) - 1}">
	   <c:set var="flag" value="${log.flag}"></c:set>
</c:forEach>
<div class="box">
<form id="addform" action="../UpdateBookServlet" method="post" enctype="multipart/form-data" onsubmit="return validateForm()"><div class="info-form">
		<h1>请按修改输入图书信息<span>(带*内容不能为空)</span></h1>
		<div class="info1">
		<p><label for="booknum">*图书编号：</label><input type="text" id="booknum" name="booknum" value="${bookbean.bookNumm}" readonly="readonly"></p>
		<p><label for="bookname">*图书名称：</label><input type="text" id="bookname" name="bookname" value="${bookbean.bookName}"></p>
		<p><label for="booktype">*图书类别：</label><select name="booktype" id="booktype" size="1" >
			<c:choose>
				<c:when test="${bookbean.bookType eq '其他类'}">
					<option value="其他类" selected>其他类</option>
				</c:when>
				<c:otherwise>
					<option value="其他类">其他类</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${bookbean.bookType eq '计算机类'}">
					<option value="计算机类" selected>计算机类</option>
				</c:when>
				<c:otherwise>
					<option value="计算机类">其他类</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${bookbean.bookType eq '数学类'}">
					<option value="数学类" selected>数学类</option>
				</c:when>
				<c:otherwise>
					<option value="数学类">数学类</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${bookbean.bookType eq '文学类'}">
					<option value="文学类" selected>文学类</option>
				</c:when>
				<c:otherwise>
					<option value="文学类">文学类</option>
				</c:otherwise>
			</c:choose>
		</select></p>
		<p><label for="bookwriter">*作&nbsp;&nbsp;者：</label><input type="text" name="bookwriter" id="bookwriter" value="${bookbean.bookWriter}"></p>
		<p><label for="booktranslator"> 译  &nbsp; 者：</label><input type="text" name="booktranslator" id="booktranslator" value="${bookbean.bookTrans}"></p>
		<p><label for="bookpublisher">*出 版 社：</label><input type="text" name="bookpublisher" id="bookpublisher" value="${bookbean.bookPublishr}"></p>
		<p><label for="bookdate">出版日期：</label><input type="date" name="bookdate" id="bookdate" value="${bookbean.bookDate}"></p>
		</div>
		<div class="info2">
		<div id="previewImg">
		<c:choose>
				<c:when test="${bookbean.bookphoto==null}">
					<img src="../image/tushutubiao.jpg" width="120px" height="140px" />
				</c:when>
				<c:otherwise>
					<img src="${bookbean.bookphoto}" width="120px" height="140px" />
					<input class="hidden" type="text" name="photopath" value="${bookbean.bookphoto}">
				</c:otherwise>
		</c:choose>
		</div>
		<p><label for="photo">照片：</label><input type="file" name="photo" id="photo" accept="image/png,image/jpeg,image/gif" onchange="showPreview(this)"></p>
		<div id="large"></div>
		<p><label for="price">*单价：</label><input type="text" name="price" id="price" size='5' value="${bookbean.bookPrice}">元</p>
		<p><label for="bnum">在借数量：</label><input type="text" name="bnum" id="bnum" maxlength='2' size='5' value="${bookbean.b_numb}" readonly="readonly">本<label for="num"> *库存：</label><input type="text" name="num" id="num" maxlength='2' size='5' value="${bookbean.numb}">本</p>
		</div>
		<div class="buttons">
		<c:choose>
				<c:when test="${flag==1}">
					<input class="button" type="submit" value="修改" name="submit">
					<input class="button" type="submit" value="删除" name="submit">
					<a class="button" href="../BorrowManager/borrow.jsp?booknum=${bookbean.bookNumm}">借阅</a>
				</c:when>
				<c:otherwise>
					<a class="button" href="../BorrowManager/borrow.jsp?booknum=${bookbean.bookNumm}">借阅</a>
				</c:otherwise>
		</c:choose>
		<input class="button" type="reset" value="取消">
		<button class="button" onclick="goBack()">返回</button> 
		<button class="button" onclick="goForward()">前进</button>
		<button class="button" onclick="renovate()">刷新</button>
		</div>
		</div>
</form>
</div>
</body>
</html>