package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.BorrowBean;
import model.ReaderBean;
import service.BorrowService;
import service.ReaderService;



public class SelectBorrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public SelectBorrowServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String readerid = request.getParameter("readerid");
		String bookNum = request.getParameter("booknum");
		if (readerid != null) {
			ReaderService rService = new ReaderService();
			ReaderBean rBean = new ReaderBean();
			ArrayList<ReaderBean> temp = rService.findReaderbynum(readerid);
			if (temp.size() != 0) {
				rBean = temp.get(0);
				Gson gson=new Gson();
				BorrowService bService = new BorrowService();
				ArrayList<BorrowBean> bList = bService.findBorrow(readerid);
				if (bList.size() != 0) {
					HashMap hm = new HashMap<>();
					hm.put("readerName", rBean.getReaderName());
					hm.put("maxNum", rBean.getMaxNum());
					ArrayList list=new ArrayList();
					hm.put("borrows",list);
					Iterator i= bList.iterator();
					Calendar addDate = Calendar.getInstance();
					while(i.hasNext()){
						HashMap m=new HashMap();
						BorrowBean bean=(BorrowBean)i.next();
						m.put("bookNum", bean.getBookNum());
						m.put("bookName", bean.getBookName());
						m.put("borrowDate", bean.getBorrowDate().toString());
						addDate.setTime(bean.getBorrowDate());
						addDate.add(Calendar.MONTH, 1);
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						String tempDate = dateFormat.format(addDate.getTime());
						m.put("shouldDate", tempDate);
						list.add(m);
					}
					String readerJson=gson.toJson(hm);
					System.out.println(readerJson);
					out.print(readerJson);
				}else {
					String readerJson=gson.toJson(rBean);
					System.out.println("zheli"+readerJson);
					out.print(readerJson);
				}
			}else {
				out.print(1);
			}
		}
		if (bookNum != null) {
			out.print("图书模块还没写");
		}
		out.flush();
		out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
