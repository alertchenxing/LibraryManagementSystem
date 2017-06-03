<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加学生信息</title>
<script src="../jquery/jquery-3.2.0.js"></script>
<link href="../css/maincss.css" rel="stylesheet" type="text/css">
<link href="../css/infoadd.css" rel="stylesheet" type="text/css">
<script src="../jquery/main.js"></script>
<style type="text/css">
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
		width:200px;
		height:250px;
	}
	#mouseimg{
		max-width:800px;
		max-height:390px;
	}
	.message, .message1{
		color:red;
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
			"<img src='"+imgRUL+"' width='200px' height='250px'/>"; 
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
	var ajax;
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
		var cardnum = document.getElementById("cardnum").value;
		var maxnum = document.getElementById("maxnum").value;
		var money = document.getElementById("money").value;
		$("label.message").remove();
		$("label.message1").remove();
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
			$("p").eq(3).append("<label class='message'>请输入3-11位的电话号码</label>");
			flag++;
		}
		if(cardnum == null || cardnum == ""){
			$("p").eq(5).append("<label class='message1'>请输入证件号</label>");
			flag++;
		}else {
			if(cardnum.length > 20){
				$("p").eq(5).append("<label class='message1'>请输入小于20个字符</label>");
				flag++;
			}else{
				if(ajax == 1){
					flag ++;
				}
			}
		}
		if(maxnum != null || maxnum != ""){
			if(isNaN(maxnum) || maxnum < 0 || maxnum > 10 ){
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
	function checkCardnum(){
		var cardnum = document.getElementById("cardnum").value;
		ajax = 0;
		var xmlhttp;
		$("label.message1").remove();
		if (window.XMLHttpRequest)
		  {
		    // IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		    xmlhttp=new XMLHttpRequest();
		  }
		  else
		  {
		    // IE6, IE5 浏览器执行代码
		    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		xmlhttp.onreadystatechange=function()
		  {
		    if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
		    	if(xmlhttp.responseText == 1){
		    		$("p").eq(5).append("<label class='message1'>证件号已存在</label>");
		    		var flag = confirm("证件号已存在是否直接注册！"+cardnum);
		    		if(flag){
		    				window.location.href='setpassword.jsp?zhanghao='+cardnum;
		    		}
		    		ajax = 1;
		    	}
		    }
		  }
		xmlhttp.open("GET","../AddReaderServlet?cardnum="+cardnum,true);
		xmlhttp.send();
	}
</script>
</head>
<body>
<div class="box">
<form id="addform" action="../addstudentServlet" method="post" enctype="multipart/form-data" onsubmit="return validateForm()"><div class="info-form">
		<h1>请按要求输入基本信息<span>(带*内容不能为空)</span></h1>
		<div class="info1">
		<p><label for="readername">*姓名：</label><input type="text" id="readername" name="readername" ></p>
		<p><label for="readersex">*性别：</label><input id="readersex" type="radio" name="readersex" value="男" >男
		<input id="readersex" type="radio" name="readersex" value="女" >女</p>
		<p><label for="readerage">*年龄：</label><input type="text" name="readerage" maxlength='3'size="5" id="readerage" min="10" max="100" ></p>
		<p><label for="readertel">*电话：</label><input type="text" name="readertel" id="readertel" ></p>
		<p><label for="cardtype">*证件类型：</label><select name="cardtype" id="cardtype" size="1" >
			<option value="学生证">学生证</option>
			<option value="身份证">身份证</option>
		</select></p>
		<p><label for="cardnum">*证件号：</label><input type="text" name="cardnum" id="cardnum" onchange="checkCardnum()"></p>
		</div>
		<div class="info2">
		<div id="previewImg"><img src="../image/moren.jpg" width="200px" height="250px" /></div>
		<p><label for="photopath">照片：</label><input type="file" name="photopath" id="photopath" accept="image/png,image/jpeg,image/gif" onchange="showPreview(this)"></p>
		<div id="large"></div>
		</div>
		</div>
		<div class="buttons">
		<input class="button" onmouseover="checkCardnum()" type="submit" value="添加">
		<input class="button" type="reset" value="取消">
		<button class="button" onclick="goBack()">返回</button> 
		<button class="button" onclick="renovate()">刷新</button>
		</div>
</form>
</div>
</body>
</html>