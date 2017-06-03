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


public class SelectReaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReaderService rService = new ReaderService(); 
    
    public SelectReaderServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置对客户端请求进行重新编码的编码
		request.setCharacterEncoding("utf-8");
		//指定对服务器响应进行重新编码的编码
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
		ArrayList<ReaderBean> rList1 = new ArrayList<>();
		rList1 = rService.findReader(strkey);
		if(rList1 == null || rList1.size() == 0){
			response.sendRedirect("login/message.jsp");
			return;
		}else if(rList1.size() == 1){
			session.setAttribute("rList1", rList1);
			response.sendRedirect("ReaderManager/showOneReader.jsp");
			return;
		}else {
			session.setAttribute("rList1", rList1);
			session.setAttribute("flag", flag);
			response.sendRedirect("ReaderManager/showReaders.jsp");
			return;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
