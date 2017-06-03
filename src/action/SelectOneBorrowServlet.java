package action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BorrowBean;
import service.BorrowService;

public class SelectOneBorrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectOneBorrowServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置对客户端请求进行重新编码的编码
		request.setCharacterEncoding("utf-8");
		String bid = request.getParameter("bid");
		System.out.println(bid);
		int BID = Integer.valueOf(bid);
		BorrowService bService = new BorrowService();
		BorrowBean borrowBean = new BorrowBean();
		borrowBean = bService.selectOneBorrow(BID);
		HttpSession session = request.getSession();
		if (borrowBean.getFlag() == 0) {
			Calendar addDate = Calendar.getInstance();
			addDate.setTime(borrowBean.getBorrowDate());
			addDate.add(Calendar.MONTH, 1);
			Date shoulddate = addDate.getTime();
			session.setAttribute("shoulddate", shoulddate);
		}
		session.setAttribute("borrow", borrowBean);
		response.sendRedirect("BorrowManager/back.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
