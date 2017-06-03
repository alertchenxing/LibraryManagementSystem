<%@page import="model.AdminBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page  import="java.util.*" %>
<%@ page  import="model.ReaderBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>读者信息显示</title>
<script src="../jquery/jquery-3.2.0.js"></script>
<link href="../css/maincss.css" rel="stylesheet" type="text/css">
<link href="../css/infoadd.css" rel="stylesheet" type="text/css">
<script src="../jquery/main.js"></script>
<style type="text/css">
	.box{
		text-align:center;
	}
	.info-form{
		position:relative;
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
	.buttons{
		position:fixed;
		right:10px;
		bottom:10px;
	}
	.message, .message1{
		color:red;
	}
	.hidden{
		display:none;
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
                var reader = new FileReader();  
                reader.onload = function (e) {  
                    imgURL = e.target.result;  
                };  
                reader.readAsDataURL(node.files[0]);  
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
	//验证表单
	//表单验证函数
	function validateForm(){
		var flag = 0;
		var reg = /^[\u4Eoo-\u9FA5]+$/;
		var name = document.getElementById("readername").value;
		var sex;
		var obj = document.getElementsByName("readersex");
		for(var i = 0; i < obj.length; i ++){
			if(obj[i].checked){
				sex = obj[i].value;
			}
		}
		var age = document.getElementById("readerage").value;
		var tel = document.getElementById("readertel").value;
		var maxnum = document.getElementById("maxnum").value;
		var money = document.getElementById("money").value;
		$("label.message").remove();
		if(name == null || name == ""){
			$("p").eq(0).append("<label class='message'>请输姓名</label>");
			flag++;
		}else{
			if(!reg.test(name)){
				$("p").eq(0).append("<label class='message'>请输入中文</label>");
				flag++;
			}else{
				if(name.length < 2 || name.length > 5){
					$("p").eq(0).append("<label class='message'>请输入2-5个字符</label>");
					flag++;
				}
			}
		}
		if(sex == null || sex == ""){
			$("p").eq(1).append("<label class='message'>请选择性别</label>");
			flag++;
		}
		if(age == null || age == ""){
			$("p").eq(2).append("<label class='message'>请输入年龄</label>");
			flag++;
		}else if(isNaN(age) || age < 10 || age > 100){
			$("p").eq(2).append("<label class='message'>请输入10-100的数字</label>");
			flag++;
		}
		if(tel == null || tel == ""){
			$("p").eq(3).append("<label class='message'>请输入电话号码</label>");
			flag++;
		}else if(isNaN(tel) || tel.length < 3 || tel.length > 11){
			$("p").eq(3).append("<label class='message'>请输入最多11位电话号码</label>");
			flag++;
		}
		if(maxnum != null || maxnum != ""){
			if(isNaN(maxnum) || maxnum < 0 || maxnum > 10){
				$("p").eq(7).append("<label class='message'>请输入0-10的数字</label>");
				flag++;
			}
		}
		if(money != null || money != ""){
			if(isNaN(money)){
				$("p").eq(8).append("<label class='message'>请输入数字</label>");
				flag++;
			}
		}
		if(flag != 0){
			return false;
		}
	}
</script>
</head>
<body>
<%
	ArrayList<ReaderBean> rList = new ArrayList<>();
	rList = (ArrayList)session.getAttribute("rList1");
	if(rList == null || rList.size() == 0){
		response.sendRedirect("../login/message.jsp");
	}
	ReaderBean rBean = new ReaderBean();
	for(int i = 0; i < rList.size(); i++){
		rBean = (ReaderBean)rList.get(i);
	}
	ArrayList<AdminBean> loginrList = new ArrayList<>();
	loginrList = (ArrayList)session.getAttribute("login");
	AdminBean aBean = new AdminBean();
	int flag = 0;
	for(int i = 0; i < loginrList.size(); i++){
		aBean = (AdminBean)loginrList.get(i);
		flag = aBean.getFlag();
	}
%>
<div class="box">
<form action="../UpdateReaderServlet" method="post" enctype="multipart/form-data" onsubmit="return validateForm()"><div class="info-form">
		<h1>请按要求输入读者信息<span>(带*内容不能为空)</span></h1>
		<div class="info1">
		<p><label for="readername">*姓名：</label><input type="text" id="readername" name="readername" value="<%=rBean.getReaderName() %>"></p>
		<p><label for="readersex">*性别：</label>
		<%if(rBean.getReaderSex().equals("男")){%>
			<input id="readersex" type="radio" name="readersex" value="男" checked="checked">男
		<%}else{%>
			<input id="readersex" type="radio" name="readersex" value="男" >男
		<%} %>
		<%if(rBean.getReaderSex().equals("女")){%>
			<input id="readersex" type="radio" name="readersex" value="女" checked="checked">女
		<%}else{%>
			<input id="readersex" type="radio" name="readersex" value="女">女
		<%} %></p>
		<p><label for="readerage">*年龄：</label><input type="text" name="readerage" id="readerage" size="5"value="<%=rBean.getReaderAge() %>" maxlength='3'>岁</p>
		<p><label for="readertel">*电话：</label><input type="text" name="readertel" id="readertel" value="<%=rBean.getReaderTel() %>"></p>
		<p><label for="cardtype">*证件类型：</label><select name="cardtype" id="cardtype" size="1">
		<%if(rBean.getCardType().equals("身份证")){%>
			<option value="身份证" selected>身份证</option>
		<%}else{%>
			<option value="身份证">身份证</option>
		<%} %>
		<%if(rBean.getCardType().equals("学生证")){%>
			<option value="学生证" selected>学生证</option>
		<%}else{%>
			<option value="学生证">学生证</option>
		<%} %>
		</select></p>
		<p><label for="cardnum">*证件号：</label><input type="text" name="cardnum" id="cardnum" value="<%=rBean.getCardNum() %>" readonly="readonly"></p>
		</div>
		<div class="info2">
		<div id="previewImg">
		<%if(rBean.getPhotoPath()==null){%>
			<img src="../image/moren.jpg" width="120px" height="140px" />
		<%}else{%>
			<img src="<%=rBean.getPhotoPath() %>" width="120px" height="140px" />
		<%} %></div>
		<p><label for="photopath">照片：</label><input type="file" name="photopath" id="photopath" accept="image/png,image/jpeg,image/gif" onchange="showPreview(this)"></p>
		<div id="large"></div>
		<%
			if(flag == 1){%>
				<p><label for="maxnum">最大借书量：</label><input type="text" name="maxnum" id="maxnum" maxlength='2' align="right" size="5"value="<%=rBean.getMaxNum() %>">本</p>
				<p><label for="money">押金：</label><input type="text" name="money" id="money" value="<%=rBean.getMoney() %>" size="5">元</p>
			<%}else{%>
			<p><label for="maxnum">最大借书量：</label><input type="text" name="maxnum" id="maxnum" maxlength='2' align="right" size="5"value="<%=rBean.getMaxNum() %>" readonly="readonly">本</p>
			<p class="hidden"><label for="money">押金：</label><input type="text" name="money" id="money" value="<%=rBean.getMoney() %>" size="5" >元</p>
		<%}
		%>
		</div>
		<div class="buttons">
		<input class="button" type="submit" value="修改" name="sub">
		<%
			if(flag == 1){%>
				<input class="button" type="submit" value="删除" name="sub">
			<%}
		%>
		<button class="button" onclick="goBack()">返回</button> 
		</div>
		</div>
</form>
</div>
</body>
</html>