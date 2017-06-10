package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

import model.BookBean;
import service.BookService;



public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public AddBookServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		SmartUpload su = new SmartUpload();
		//初始化对象
		su.initialize(getServletConfig(), request, response);
		//设置上传文件的大小
		su.setMaxFileSize(1024*1024*40);
		//设置所有文件的大小
		su.setTotalMaxFileSize(1024*1024*100);
		//设置允许上传文件类型
		su.setAllowedFilesList("jpg,png,gif,PNG");
		try {
			//设置不允许上传的文件类型
			su.setDeniedFilesList("rar,jsp,doc,exe,bat,htm,html,");
			//上传文件
			su.upload();
			
			int count = su.save("image/");
			System.out.println("上传成功"+count+"个文件");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//提取上传文件的信息
		com.jspsmart.upload.File file = null;
		String photo = null;
		String filename = null;
		for (int i = 0; i < su.getFiles().getCount(); i++) {
			file =su.getFiles().getFile(i);
			if (file.isMissing()) {
				continue;
			}
			filename = file.getFileName();
			photo = request.getContextPath()+"/image/"+filename;
		}
		BookService bService = new BookService();
		//接收参数
		String name = su.getRequest().getParameter("bookname");
		String writer=su.getRequest().getParameter("bookwriter");
		String trans = su.getRequest().getParameter("booktranslator");
		String bookdate	 = su.getRequest().getParameter("bookdate");
		String bpub = su.getRequest().getParameter("bookpublisher");
		String booktype = su.getRequest().getParameter("booktype");
		int num = Integer.parseInt(su.getRequest().getParameter("num"));
		float price = Float.parseFloat(su.getRequest().getParameter("price"));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			if(!bookdate.equals("")){
				date = format.parse(bookdate);
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		BookBean bBean = new BookBean();
		bBean.setBookName(name);
		bBean.setBookWriter(writer);
		bBean.setBookTrans(trans);
		bBean.setBookDate(date);
		bBean.setBookPublishr(bpub);
		bBean.setBookType(booktype);
		bBean.setBookPrice(price);
		bBean.setNumb(num);
		bBean.setBookphoto(photo);
		boolean flag = bService.addBook(bBean);
		if (flag) {
			response.sendRedirect("ReaderManager/success.jsp");
		}else {
			response.sendRedirect("ReaderManager/fail.jsp");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
