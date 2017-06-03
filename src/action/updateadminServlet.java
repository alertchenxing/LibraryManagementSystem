package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdminBean;
import service.AdminService;

public class updateadminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public updateadminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		AdminService aService = new AdminService();
		String submit = request.getParameter("submit");
		if (submit.equals("ÐÞ¸Ä")) {
			String username = request.getParameter("zhanghao");
			String password = request.getParameter("password");
			String flag1 = request.getParameter("flag");
			boolean flag2 = aService.check(username);
			boolean flag = aService.update(username, password, Integer.parseInt(flag1));
			if (flag2) {
				if (flag) {
					String username1 = null;
					HttpSession session = request.getSession();
					ArrayList login = (ArrayList)session.getAttribute("login");
					if (login == null || login.size() == 0) {
						response.sendRedirect("../login/login.jsp");
					}else{
						for(int i = login.size() - 1; i >= 0; i --){
							AdminBean aBean = (AdminBean)login.get(i);
							username1 = aBean.getUsername();
						}
					}
					if (username.equals(username1)) {
						response.sendRedirect("StudentManager/timeout.jsp");
					}else{
						response.sendRedirect("ReaderManager/success.jsp");
					}
					
				}else {
					response.sendRedirect("ReaderManager/fail.jsp");
				}
				
			}else{
				response.sendRedirect("StudentManager/notexcit.jsp");
			}
		}else if(submit.equals("É¾³ý")){
			String username = request.getParameter("zhanghao");
			boolean flag1 = aService.check(username);
			if (!flag1) {
				response.sendRedirect("StudentManager/notexcit.jsp");
			}else if(flag1){
				boolean flag2 = aService.delete(username);
				if (flag2) {
					response.sendRedirect("ReaderManager/success.jsp");
				}else if (!flag2) {
					response.sendRedirect("ReaderManager/fail.jsp");
				}
			}
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
