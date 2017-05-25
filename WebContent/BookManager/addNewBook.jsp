<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加图书信息</title>
<script src="../jquery/jquery-3.2.0.js"></script>
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
	.info-form{
		position:relative;
		text-align:left;
	}
	p{
		padding: 10px 10px 10px 60px;
	}
	input, select{
		font-size:16px;	
		padding: 4px 8px;
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
		position:fixed;
		right:10px;
		bottom:10px;
	}
	span{
		font-size: 14px;
		color: #FFF;
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
		var bookname = document.getElementById("bookname").value;
		var booknumm = document.getElementById("booknum").value;
		var bookwriter = document.getElementById("writer").value;
		var booktrans = document.getElementById("translator").value;
		var bookdate = document.getElementById("bdate").value;
		var bookpublisher = document.getElementById("publisher").value;
		var booktype = document.getElementById("booktype").value;
		var bookprice = document.getElementById("price").value;
		var booknum = document.getElementById("num").value;
		var bookphoto = document.getElementById("photo").value;
		var b_num = document.getElementById("b_num").value;
		if(name == null || name ==""){
			$("p").eq(0).append("请填写图书名称");
			flag++;
		}
		if(flag != 0){
			return false;
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
<form id="addform" action="../AddReaderServlet" method="post" enctype="multipart/form-data" onsubmit="return validateForm()"><div class="info-form">
		<h1>请按要求输入图书信息<span>(带*内容不能为空)</span></h1>
		<div class="info1">
		<p><label for="bookname">*图书名称：</label><input type="text" id="bookname" name="bookname" required></p>
		<p><label for="booknumm">*编号：</label><input id="booknumm" type="text" name="bookname" required></p>
		<p><label for="bookwriter">*作者：</label><input type="text" name="bookwriter" id="bookwriter" required></p>
		<p><label for="booktranslator">*译者：</label><input type="text" name="booktranslator" id="booktranslator" required></p>
		<p><label for="bookdate">*出版日期：</label><input type="text" name="bookdate" id="bookdate" required></p>
		<p><label for="bookpublisher">*出版社：</label><input type="text" name="bookpublisher" id="bookpublisher" required></p>
		<p><label for="booktype">*图书类别：</label><input type="text" name="booktype" id="booktype" required></p>
		</div>
		<div class="info2">
		<div id="previewImg"><img src="../image/moren.jpg" width="120px" height="140px" /></div>
		<p><label for="photo">照片：</label><input type="file" name="photo" id="photo" accept="image/png,image/jpeg,image/gif" onchange="showPreview(this)"></p>
		<div id="large"></div>
		<p><label for="maxnum">图书总数量：</label><input type="text" name="maxnum" id="maxnum" maxlength='2'>本</p>
		<p><label for="price">押金：</label><input type="text" name="price" id="price">元</p>
		<p><label for="b_num">在借数量：</label><input type="text" name="b_num" id="b_num" maxlength='2'>本</p>
		</div>
		<div class="buttons">
		<input class="button" type="submit" value="添加">
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