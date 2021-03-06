package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BookBean;
import service.BookService;


public class ShowBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookService bService = new BookService();  
    
    public ShowBookServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList login = (ArrayList)session.getAttribute("login");
		if (login == null || login.size() == 0) {
			response.sendRedirect("login/warn.jsp");
			return;
		}
		ArrayList<BookBean> bList = new ArrayList<>();
		bList = bService.showAll();
		if (bList == null || bList.size() == 0) {
			response.sendRedirect("login/message.jsp");
			return;
		}else {
			session.setAttribute("bookList", bList);
			response.sendRedirect("BookManager/showBook.jsp");
			return;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
