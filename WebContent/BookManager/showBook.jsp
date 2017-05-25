<%@page import="model.BookBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page  import="java.util.*" %>
<%@ page  import="model.BookBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>书籍信息显示</title>
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
	}
	table{
		border-collapse:collapse;/*合并边框*/
		width:100%;
	}
	th{
		background-color:#99CC99;
		height:40px;
		
	}
	td, th{
		text-align:center;
		border:1px solid #99CC66;
		width:20%;
	}
	td{
		height:30px;
	}
	.tel{
		width:40%;
	}
	.pagination{
		display:inline-block;
	}
	#info{
		padding: 8px 16px;
		background-color:#F0F0F0;
	}
	.pagination li{
		display:inline;
	}
	.pagination li a{
		text-align:center;
		color:black;
		padding: 8px 16px;
		text-decoration:none;/*去掉下划线*/
		transition:background-color .3s;/*颜色渐变*/
		-webkit-transition:background-color .3s;
		-o-transition:background-color .3s;
	}
	.pagination li a.active{
		background-color:#99CC99;
		color:white;
	}
	.pagination li a:hover:not(.active){
		background-color: #99CCCC;
	}
	#page{
		text-align:center;
		position:relative;
		top:20px;
	}
	form{
		display:inline;
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
	#text{
		height:28px;
		text-align:center;
		font-size:16px;
	}
	.hidden{
		display:none;
	}
	.history{
		position:absolute;
		right:10px;
		bottom:10px;
	}
</style>
<script type="text/javascript">
	var clickedRow;
	function trclick(obj){
		if(clickedRow != null){
			clickedRow.style.background="#F8F8F8";
		}
		obj.style.background="#D8D8D8";
		clickedRow = obj;
		var numd = clickedRow.cells[4].innerText;
		window.location.href="../SelectBookServlet?strkey="+numd;
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
<body >

	<%
		int flag1 = (int)session.getAttribute("flag");
		ArrayList<BookBean> bList = new ArrayList<>();
		if(flag1 == 2){
			bList = (ArrayList)session.getAttribute("bList");
		}else if(flag1 == 1){
			bList = (ArrayList)session.getAttribute("bList1");
		}
		if (bList == null || bList.size() == 0) {
			response.sendRedirect("../login/message.jsp");
		}else {
	%>
<div class="box">
	<div class="showtb">
	<%
			int intPageSize;//一页显示的记录数量
			int intRowCount;//记录总数
			int intPageCount;//总页数
			int intPage;//待显示页码
			String strPage;
			int i;
			intPageSize = 2;//每页显示八条数据
			strPage = request.getParameter("page");
			if(strPage == null){
				intPage = 1;//显示第一页
			}else{
				intPage = Integer.parseInt(strPage);
				if(intPage < 1){
					intPage = 1;
				}
			}
			intRowCount = bList.size();//计算记录总数
			intPageCount = (intRowCount + intPageSize - 1) / intPageSize;//计算总页数
			if(intPage > intPageCount){
				intPage = intPageCount;//调整显示的页码
			}
			if(intPageCount > 0){
				i =  (intPage - 1) * intPageSize;//定位到每一页开头记录
				int temp = i;
				while(i < temp + intPageSize && i < bList.size()){
					BookBean bBean = (BookBean)bList.get(i);
	%>
					<tr onclick="trclick(this)" onmouseover="trhover(this)" onmouseout="trout(this)">
							<div class="responsive">
 			 <div class="img">
   				 <a target="_blank" href="bBean.getBookPhoto">
  				    <img src="bBean.getBookPhoto" alt="图片文本描述" width="300" height="200">
 			  </a>
 			   <div class="desc"><%=bBean.getBookName() %></div>
 		 	</div>
			</div>
							
					</tr>
	<%
					i++;
				}
				if(intPage == intPageCount){
					int flag = bList.size() % intPageSize;
					if(flag != 0) {
						int j = intPageSize - flag;
					}
				}
			}
	%>
		<div id="page">
		</div>
		<script type="text/javascript">
			var totalpage, pagesize, cpage,count, outstr;
			//初始化
			cpage = "<%=intPage %>";//页面计数默认为第一页
			toltalpage = "<%=intPageCount %>";//总页数
			pagesize = 3;//分页导航的个数
			outstr = "";
			function gotopage(target){
				cpage = target;//把页面计数定位到跳转页数
				setpage();
			}
			function setpage(){
				if(toltalpage <= pagesize){//如果总页数小于等于10页
					for(count = 1; count <= toltalpage; count ++){
						if(count != cpage){//跳转页数不等于当前页数
							outstr = outstr + "<li><a href='showReaders.jsp?page="+count+"' onclick='gotopage("+count+")'>"+count+"</a></li>";
						}else{
							outstr = outstr + "<li><a class='active'>"+count+"</a></li>";
						}
					}
				}
				if(toltalpage > pagesize){//总数大于10
					if(parseInt((cpage - 1) / pagesize) == 0){//小于10页显示
						for(count = 1; count <= pagesize; count ++){
							if(count != cpage){//跳转页数不等于当前页数
								outstr = outstr + "<li><a href='showReaders.jsp?page="+count+"' onclick='gotopage("+count+")'>"+count+"</a></li>";
							}else{
								outstr = outstr + "<li><a class='active'>"+count+"</a></li>";
							}
						}
						outstr = outstr + "<li><a href='showReaders.jsp?page="+count+"' onclick='gotopage("+count+")'>>></a></li>";
					}else if(parseInt((cpage - 1) / pagesize) == parseInt(toltalpage/ pagesize)){//最后几页显示
						outstr = outstr + "<li><a href='showReaders.jsp?page="+(parseInt((cpage - 1) / 10) * 10)+"' onclick='gotopage("+(parseInt((cpage - 1) / 10) * 10)+")'><<</a></li>";
						for(count = parseInt(toltalpage/ pagesize) * pagesize + 1; count <= toltalpage; count ++){
							if(count != cpage){//跳转页数不等于当前页数
								outstr = outstr + "<li><a href='showReaders.jsp?page="+count+"' onclick='gotopage("+count+")'>"+count+"</a></li>";
							}else{
								outstr = outstr + "<li><a class='active'>"+count+"</a></li>";
							}
						}
					}else{
						outstr = outstr + "<li><a href='showReaders.jsp?page="+(parseInt((cpage - 1) / pagesize) * pagesize)+"' onclick='gotopage("+(parseInt((cpage - 1) / pagesize) * pagesize)+")'><<</a></li>";
						for(count = parseInt((cpage - 1) / pagesize) * pagesize + 1; count <= parseInt((cpage - 1) / pagesize) * pagesize + pagesize; count ++){//中间页数的显示
							if(count != cpage){//跳转页数不等于当前页数
								outstr = outstr + "<li><a href='showReaders.jsp?page="+count+"' onclick='gotopage("+count+")'>"+count+"</a></li>";
							}else{
								outstr = outstr + "<li><a class='active'>"+count+"</a></li>";
							}
						}
						outstr = outstr + "<li><a href='showReaders.jsp?page="+count+"' onclick='gotopage("+count+")'>>></a></li>";
					}
				}
				document.getElementById("page").innerHTML = "<div id='setpage'><span id='info'>共"+toltalpage+"页|第"+cpage+"页</span><ul class='pagination'>"+outstr+"</ul><form action='showReaders.jsp' method='get'"+
				"onsubmit='return checkpage()'><span id='info'>到第</span><input name='page'"+
				"type='text' size='4' id='text' maxlength='4' required><span id='info'>页</span><input class='button' type='submit' value='确定'></form></div>";
				outstr="";
			}
			setpage();
			function checkpage(){
				var num = document.getElementById("text").value;
				if(isNaN(num)){
					alert("请输入数字！");
					document.getElementById("text").value = "";
					document.getElementById("text").focus();
					return false;
				}else if(num > parseInt(toltalpage)){
					alert("请求"+num+"页不存在!");
					document.getElementById("text").value = "";
					document.getElementById("text").focus();
					return false;
				}
			}
		</script>
	</div>
	<div class="history">
		<button class="button" onclick="goBack()">返回</button> 
		<button class="button" onclick="goForward()">前进</button>
		<button class="button" onclick="renovate()">刷新</button>
	</div>
</div>	
	<%
		}
	%>
		
</body>
</html>