package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BookBean;
import service.BookService;


public class SelectOneBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public SelectOneBookServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		BookBean bookBean = new BookBean();
		BookService bookService = new BookService();
		String booknum = request.getParameter("booknum");
		bookBean = bookService.findBookbynum(booknum);
		if (bookBean != null) {
			session.setAttribute("bookbean",bookBean);
			response.sendRedirect("BookManager/showOneBook.jsp");
		}else{
			response.sendRedirect("login/message.jsp");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
