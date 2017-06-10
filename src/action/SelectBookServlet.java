package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BookBean;
import service.BookService;


public class SelectBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookService bService = new BookService(); 
    
    public SelectBookServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String strsearchtype = request.getParameter("strsearchtype");
		String strkey = request.getParameter("strkey");
		ArrayList<BookBean> booksArrayList = new ArrayList<>();
		BookService bService = new BookService();
		if (strsearchtype.equals("allstr")) {
			booksArrayList = bService.selectByallkey(strkey);
		}else if (strsearchtype.equals("bookname")){
			booksArrayList = bService.selectBynamekey(strkey);
		}else if (strsearchtype.equals("author")){
			booksArrayList = bService.selectBywriterkey(strkey);
		}else if (strsearchtype.equals("trans")){
			booksArrayList = bService.selectBytranslatorkey(strkey);
		}else if (strsearchtype.equals("booknum")){
			booksArrayList = bService.selectBybooknumkey(strkey);
		}else if (strsearchtype.equals("bookpublisher")){
			booksArrayList = bService.selectBypublisherkey(strkey);
		}else if (strsearchtype.equals("type")){
			booksArrayList = bService.selectBybooktypekey(strkey);
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
