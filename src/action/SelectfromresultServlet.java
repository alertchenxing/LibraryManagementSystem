package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BookBean;


public class SelectfromresultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SelectfromresultServlet() {
        super();
    }
    //查询所有字段
	public ArrayList<BookBean> selectbyall(ArrayList<BookBean> bArrayList, String strkey) {
		ArrayList<BookBean> bList = new ArrayList<>();
		for(BookBean book:bArrayList){
			if (book.getBookName().contains(strkey) || book.getBookNumm().contains(strkey) ||
					book.getBookWriter().contains(strkey) || book.getBookPublishr().contains(strkey)
					 || book.getBookType().contains(strkey)) {
				bList.add(book);
			}
			if (book.getBookTrans() != null && book.getBookTrans().contains(strkey)) {
				bList.add(book);
			}
		}
		return bList;
	}
	//查询名称
	public ArrayList<BookBean> selectbyname(ArrayList<BookBean> bArrayList, String strkey) {
		ArrayList<BookBean> bList = new ArrayList<>();
		for(BookBean book:bArrayList){
			if (book.getBookName().contains(strkey)) {
				bList.add(book);
			}
		}
		return bList;
	}
	//查询作者
	public ArrayList<BookBean> selectbywriter(ArrayList<BookBean> bArrayList, String strkey) {
		ArrayList<BookBean> bList = new ArrayList<>();
		for(BookBean book:bArrayList){
			if (book.getBookWriter().contains(strkey)) {
				bList.add(book);
			}
		}
		return bList;
	}
	//查询译者
	public ArrayList<BookBean> selectbytrans(ArrayList<BookBean> bArrayList, String strkey) {
		ArrayList<BookBean> bList = new ArrayList<>();
		for(BookBean book:bArrayList){
			if (book.getBookTrans() != null && book.getBookTrans().contains(strkey)) {
				bList.add(book);
			}
		}
		return bList;
	}
	//查询图书编号
	public ArrayList<BookBean> selectbynum(ArrayList<BookBean> bArrayList, String strkey) {
		ArrayList<BookBean> bList = new ArrayList<>();
		for(BookBean book:bArrayList){
			if (book.getBookNumm().contains(strkey)) {
				bList.add(book);
			}
		}
		return bList;
	}
	//查询类别
	public ArrayList<BookBean> selectbytype(ArrayList<BookBean> bArrayList, String strkey) {
		ArrayList<BookBean> bList = new ArrayList<>();
		for(BookBean book:bArrayList){
			if (book.getBookType().contains(strkey)) {
				bList.add(book);
			}
		}
		return bList;
	}
	//查询出版社
	public ArrayList<BookBean> selectbyBookPublishr(ArrayList<BookBean> bArrayList, String strkey) {
		ArrayList<BookBean> bList = new ArrayList<>();
		for(BookBean book:bArrayList){
			if (book.getBookPublishr().contains(strkey)) {
				bList.add(book);
			}
		}
		return bList;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String strsearchtype = request.getParameter("strsearchtype");
		String strkey = request.getParameter("strkey");
		ArrayList<BookBean> booksArrayList = new ArrayList<>();
		booksArrayList = (ArrayList)session.getAttribute("booksArrayList");
		if (strsearchtype.equals("allstr")) {
			booksArrayList = this.selectbyall(booksArrayList, strkey);
		}else if (strsearchtype.equals("bookname")){
			booksArrayList = this.selectbyname(booksArrayList, strkey);
		}else if (strsearchtype.equals("author")){
			booksArrayList = this.selectbywriter(booksArrayList, strkey);
		}else if (strsearchtype.equals("trans")){
			booksArrayList = this.selectbytrans(booksArrayList, strkey);
		}else if (strsearchtype.equals("booknum")){
			booksArrayList = this.selectbynum(booksArrayList, strkey);
		}else if (strsearchtype.equals("bookpublisher")){
			booksArrayList = this.selectbyBookPublishr(booksArrayList, strkey);
		}else if (strsearchtype.equals("type")){
			booksArrayList = this.selectbytype(booksArrayList, strkey);
		}
		if (booksArrayList == null || booksArrayList.size() == 0) {
			response.sendRedirect("BookManager/message.jsp");
			return;
		}else {
			session.setAttribute("booksArrayList", booksArrayList);
			response.sendRedirect("BookManager/showbooksbytable.jsp");
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
