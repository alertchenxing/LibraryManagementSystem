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
			out.print("�û�������Ϊ��");
		}else if (password.equals("")) {
			out.print("���벻��Ϊ��");
		}else {
			flag = aService.login(username, password);
			if (flag == 0) {
				out.print("�û�"+username+"�����ڣ���¼ʧ�ܣ�");
			}else if(flag == 1){
				out.print("������󣬵�¼ʧ�ܣ�");
			}else if(flag == 2){
				AdminBean aBean = new AdminBean();
				aBean.setUsername(username);
				aBean.setPassword(password);
				//��ȡsession����
				HttpSession session = request.getSession();
				ArrayList login = new ArrayList();//ʵ�����б����
				login.add(aBean);//������Ϣ��ӵ��б���
				/*���б��浽session�����У��Ա��ڱ��ҳ���ȡ������Ϣ*/
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
