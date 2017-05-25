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
		PrintWriter out = response.getWriter();
		String strkey = request.getParameter("strkey");
		HttpSession session = request.getSession();
		ArrayList login = (ArrayList)session.getAttribute("login");
		int flag = 1;
		if (login == null || login.size() == 0) {
			response.sendRedirect("login/warn.jsp");
			return;
		}
		ArrayList<BookBean> bList1 = new ArrayList<>();
		bList1 = bService.findbook(strkey);
		if(bList1 == null || bList1.size() == 0){
			response.sendRedirect("login/message.jsp");
			return;
		}else if(bList1.size() == 1){
			session.setAttribute("bList1", bList1);
			response.sendRedirect("BookManager/showOneBook.jsp");
			return;
		}else {
			session.setAttribute("bList1", bList1);
			session.setAttribute("flag", flag);
			response.sendRedirect("BookManager/showBook.jsp");
			return;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
