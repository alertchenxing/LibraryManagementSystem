package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AdminBean;
import service.AdminService;

public class addadminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public addadminServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		AdminService aService = new AdminService();
		AdminBean aBean = new AdminBean();
		String zhanghao = request.getParameter("zhanghao");
		String password = request.getParameter("password");
		String flag = request.getParameter("flag");
		aBean.setUsername(zhanghao);
		aBean.setPassword(password);
		aBean.setFlag(Integer.parseInt(flag));
		boolean flag1 = aService.check(zhanghao);
		if (flag1) {
			response.sendRedirect("StudentManager/warn.jsp");
		}else{
			boolean flag2 = aService.inset(aBean);
			if(flag2){
				response.sendRedirect("StudentManager/succes.jsp");
			}else {
				response.sendRedirect("StudentManager/fail.jsp");
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
