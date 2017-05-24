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

import model.ReaderBean;
import service.ReaderService;


public class ShowReadersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ReaderService rService = new ReaderService();  
    
    public ShowReadersServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int flag = 2;
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		ArrayList login = (ArrayList)session.getAttribute("login");
		if (login == null || login.size() == 0) {
			response.sendRedirect("login/warn.jsp");
			return;
		}
		ArrayList<ReaderBean> rList = new ArrayList<>();
		rList = rService.showAll();
		if (rList == null || rList.size() == 0) {
			response.sendRedirect("login/message.jsp");
			return;
		}else {
			session.setAttribute("rList", rList);
			session.setAttribute("flag", flag);
			response.sendRedirect("ReaderManager/showReaders.jsp");
			return;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
