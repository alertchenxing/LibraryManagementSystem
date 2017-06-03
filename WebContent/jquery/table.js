/**
 * 用于所有表格的行的onclick，onmouseover，onmouseout事件
 */
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
function trclick(obj){
	if(clickedRow != null){
		clickedRow.style.background="#F8F8F8";
	}
	obj.style.background="#D8D8D8";
	clickedRow = obj;
	var cardnum = clickedRow.cells[4].innerText;
	window.location.href="../SelectReaderServlet?strkey="+cardnum;
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