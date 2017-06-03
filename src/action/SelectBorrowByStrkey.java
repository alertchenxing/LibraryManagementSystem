package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BorrowBean;
import service.BorrowService;

public class SelectBorrowByStrkey extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public SelectBorrowByStrkey() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String strkey = request.getParameter("strkey");
		HttpSession session = request.getSession();
		BorrowService bService = new BorrowService();
		ArrayList login = (ArrayList)session.getAttribute("login");
		if (login == null || login.size() == 0) {
			response.sendRedirect("login/warn.jsp");
			return;
		}
		ArrayList<BorrowBean> bList = new ArrayList<>();
		bList = bService.selectBykey(strkey);
		if(bList == null || bList.size() == 0){
			response.sendRedirect("login/message.jsp");
			return;
		}else{
			session.setAttribute("borrowList", bList);
			response.sendRedirect("BorrowManager/showborrow.jsp");
			return;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
