package action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BorrowBean;
import service.BookService;
import service.BorrowService;


public class UpdateBorrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public UpdateBorrowServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BorrowBean borrowBean = new BorrowBean();
		BorrowService bService = new BorrowService();
		String submit = request.getParameter("submit");
		if (submit.equals("归还") || submit.equals("修改")) {
			String message = request.getParameter("message");
			String flag = request.getParameter("flag");
			String bid = request.getParameter("bid");
			String backdate = request.getParameter("time");
			String admin = request.getParameter("admin");
			String deposit = request.getParameter("deposit");
			String booknum = request.getParameter("booknum");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date backDate = null;
			try {
				backDate = format.parse(backdate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			borrowBean.setAdminName(admin);
			borrowBean.setBackDate(backDate);
			borrowBean.setBid(Integer.parseInt(bid));
			borrowBean.setMessage(Integer.parseInt(message));
			borrowBean.setDeposit(Float.parseFloat(deposit));
			borrowBean.setFlag(Integer.parseInt(flag));
			boolean flag1 = bService.alterBorrow(borrowBean);
			if (flag1) {
				BookService bookService = new BookService();
				boolean flag2 = bookService.updateBnum(booknum);
				if (flag2) {
					response.sendRedirect("ReaderManager/success.jsp");
				}
			}else{
				response.sendRedirect("ReaderManager/fail.jsp");
			}
		}else if (submit.equals("续借")) {
			String bid = request.getParameter("bid");
			String admin = request.getParameter("admin");
			String borrowdate = request.getParameter("time");
			//把接收的时间字符串转换为时间类型数据
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date borrowDate = null;
			try {
				borrowDate = format.parse(borrowdate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			borrowBean.setAdminName(admin);
			borrowBean.setBorrowDate(borrowDate);
			borrowBean.setBid(Integer.parseInt(bid));
			boolean flag3 = bService.xujie(borrowBean);
			if (flag3) {
				response.sendRedirect("ReaderManager/success.jsp");
			}else{
				response.sendRedirect("ReaderManager/fail.jsp");
			}
		}else if(submit.equals("删除")){
			String bid = request.getParameter("bid");
			boolean flag4 = bService.removeBorrow(Integer.parseInt(bid));
			if (flag4) {
				response.sendRedirect("ReaderManager/success.jsp");
			}else{
				response.sendRedirect("ReaderManager/fail.jsp");
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
