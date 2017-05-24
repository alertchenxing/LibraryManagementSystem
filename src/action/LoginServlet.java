package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AdminBean;
import service.AdminService;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    AdminService aService = new AdminService(); 
   
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
	    PrintWriter out = response.getWriter();
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    int flag;
		//String username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
		//String password = new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8");
		//String address = "http://localhost:8080/LibraryManagementSystem/login/login.jsp";
		if (username.equals("")) {
			out.print("用户名不能为空");
		}else if (password.equals("")) {
			out.print("密码不能为空");
		}else {
			flag = aService.login(username, password);
			if (flag == 0) {
				out.print("用户"+username+"不存在，登录失败！");
			}else if(flag == 1){
				out.print("密码错误，登录失败！");
			}else if(flag == 2){
				AdminBean aBean = new AdminBean();
				aBean.setUsername(username);
				aBean.setPassword(password);
				//获取session对象
				HttpSession session = request.getSession();
				ArrayList login = new ArrayList();//实例化列表对象
				login.add(aBean);//个人信息添加到列表中
				/*把列表保存到session对象中，以便在别的页面获取个人信息*/
				out.print(1);
				session.setAttribute("login", login);
				//response.sendRedirect("http://localhost:8080/LibraryManagementSystem/login/main.jsp");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
