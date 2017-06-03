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


public class AddBorrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public AddBorrowServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置请求接收编码格式
		request.setCharacterEncoding("utf-8");
		//接收参数
		String readerid = request.getParameter("readerid");
		String booknum = request.getParameter("booknum");
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
		//传到bean类型中
		BorrowBean borrowBean = new BorrowBean();
		borrowBean.setAdminName(admin);
		borrowBean.setBookNum(booknum);
		borrowBean.setBorrowDate(borrowDate);
		borrowBean.setReaderId(readerid);
		BorrowService bService = new BorrowService();
		BookService bookService = new BookService();
		int flag3 = bookService.checkBorrow(booknum);
		if (flag3 <= 0) {
			response.sendRedirect("ReaderManager/fail.jsp");
			return;
		}else {
			boolean flag1 = bService.addBorrow(borrowBean);
			boolean flag2 = bookService.updateBnum(booknum);
			System.out.println(flag2);
			if (flag1 && flag2) {
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
