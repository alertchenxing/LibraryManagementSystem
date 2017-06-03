<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page  import="java.util.*" %>
<%@ page  import="model.BorrowBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>显示借阅信息</title>
<link href="../css/maincss.css" rel="stylesheet" type="text/css">
<link href="../css/fenye.css" rel="stylesheet" type="text/css">
<style type="text/css">
	.hidden{
		display:none;
	}
</style>
<script src="../jquery/main.js"></script>
<script>
var clickedRow;
function trclickofBorrow(obj){
	if(clickedRow != null){
		clickedRow.style.background="#F8F8F8";
	}
	obj.style.background="#D8D8D8";
	clickedRow = obj;
	var bid = clickedRow.cells[0].innerText;
	window.location.href="../SelectOneBorrowServlet?bid="+bid;
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
</script>
</head>
<body>
<%
	ArrayList<BorrowBean> bList = new ArrayList<>();
	bList = (ArrayList)session.getAttribute("borrowList");
%>
<div class="box">
	<div class="showtb">
		<table>
			<tr>
					<th>图书编号</th>
					<th>图书名称</th>
					<th>读者证件</th>
					<th>读者姓名</th>
					<th>是否归还</th>
			</tr>
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
					BorrowBean bBean = (BorrowBean)bList.get(i);
	%>
					<tr onclick="trclickofBorrow(this)" onmouseover="trhover(this)" onmouseout="trout(this)">
							<td class="hidden"><%=bBean.getBid() %></td>
							<td><%=bBean.getBookNum() %></td>
							<td><%=bBean.getBookName() %></td>
							<td><%=bBean.getReaderId() %></td>
							<td><%=bBean.getReaderName() %></td>
							<%
								if(bBean.getFlag() == 0){
							%>	
							<td>否</td>
							<%	
								}else{
							%>	
							<td >是</td>
							<%	
								}
							%>
					</tr>
	<%
					i++;
				}
				if(intPage == intPageCount){
					int flag = bList.size() % intPageSize;
					if(flag != 0) {
						int j = intPageSize - flag;
						for(int ii = 0; ii < j; ii ++){
		%>
									<tr>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
									</tr>
		<%
						}
					}
				}
			}
	%>
		</table>
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
							outstr = outstr + "<li><a href='showborrow.jsp?page="+count+"' onclick='gotopage("+count+")'>"+count+"</a></li>";
						}else{
							outstr = outstr + "<li><a class='active'>"+count+"</a></li>";
						}
					}
				}
				if(toltalpage > pagesize){//总数大于10
					if(parseInt((cpage - 1) / pagesize) == 0){//小于10页显示
						for(count = 1; count <= pagesize; count ++){
							if(count != cpage){//跳转页数不等于当前页数
								outstr = outstr + "<li><a href='showborrow.jsp?page="+count+"' onclick='gotopage("+count+")'>"+count+"</a></li>";
							}else{
								outstr = outstr + "<li><a class='active'>"+count+"</a></li>";
							}
						}
						outstr = outstr + "<li><a href='showborrow.jsp?page="+count+"' onclick='gotopage("+count+")'>>></a></li>";
					}else if(parseInt((cpage - 1) / pagesize) == parseInt(toltalpage/ pagesize)){//最后几页显示
						outstr = outstr + "<li><a href='showborrow.jsp?page="+(parseInt((cpage - 1) / 10) * 10)+"' onclick='gotopage("+(parseInt((cpage - 1) / 10) * 10)+")'><<</a></li>";
						for(count = parseInt(toltalpage/ pagesize) * pagesize + 1; count <= toltalpage; count ++){
							if(count != cpage){//跳转页数不等于当前页数
								outstr = outstr + "<li><a href='showborrow.jsp?page="+count+"' onclick='gotopage("+count+")'>"+count+"</a></li>";
							}else{
								outstr = outstr + "<li><a class='active'>"+count+"</a></li>";
							}
						}
					}else{
						outstr = outstr + "<li><a href='showborrow.jsp.jsp?page="+(parseInt((cpage - 1) / pagesize) * pagesize)+"' onclick='gotopage("+(parseInt((cpage - 1) / pagesize) * pagesize)+")'><<</a></li>";
						for(count = parseInt((cpage - 1) / pagesize) * pagesize + 1; count <= parseInt((cpage - 1) / pagesize) * pagesize + pagesize; count ++){//中间页数的显示
							if(count != cpage){//跳转页数不等于当前页数
								outstr = outstr + "<li><a href='showborrow.jsp.jsp?page="+count+"' onclick='gotopage("+count+")'>"+count+"</a></li>";
							}else{
								outstr = outstr + "<li><a class='active'>"+count+"</a></li>";
							}
						}
						outstr = outstr + "<li><a href='showborrow.jsp.jsp?page="+count+"' onclick='gotopage("+count+")'>>></a></li>";
					}
				}
				document.getElementById("page").innerHTML = "<div id='setpage'><span id='info'>共"+toltalpage+"页|第"+cpage+"页</span><ul class='pagination'>"+outstr+"</ul><form action='showborrow.jsp' method='get'"+
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
	<div class="buttons">
		<button class="button" onclick="goBack()">返回</button> 
		<button class="button" onclick="goForward()">前进</button>
		<button class="button" onclick="renovate()">刷新</button>
	</div>
</div>	
</body>
</html>