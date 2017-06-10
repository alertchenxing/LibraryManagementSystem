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

import com.jspsmart.upload.SmartUpload;

import model.BookBean;
import service.BookService;
import service.BorrowService;


public class UpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateBookServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		BookService bService = new BookService();
		SmartUpload su = new SmartUpload();
		//��ʼ������
		su.initialize(getServletConfig(), request, response);
		//�����ϴ��ļ��Ĵ�С
		su.setMaxFileSize(1024*1024*40);
		//���������ļ��Ĵ�С
		su.setTotalMaxFileSize(1024*1024*100);
		//���������ϴ��ļ�����
		su.setAllowedFilesList("jpg,png,gif,PNG");
		try {
			//���ò������ϴ����ļ�����
			su.setDeniedFilesList("rar,jsp,doc,exe,bat,htm,html,");
			//�ϴ��ļ�
			su.upload();
			
			int count = su.save("image/");
			System.out.println("�ϴ��ɹ�"+count+"���ļ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//��ȡ�ϴ��ļ�����Ϣ
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
		String submit = su.getRequest().getParameter("submit");
		if (submit.equals("�޸�")) {
			String booknum = su.getRequest().getParameter("booknum");
			String name = su.getRequest().getParameter("bookname");
			String writer=su.getRequest().getParameter("bookwriter");
			String trans = su.getRequest().getParameter("booktranslator");
			String bookdate	 = su.getRequest().getParameter("bookdate");
			String bpub = su.getRequest().getParameter("bookpublisher");
			String booktype = su.getRequest().getParameter("booktype");
			String photopath = su.getRequest().getParameter("photopath");
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
			if (photo == null) {
				photo = photopath;
			}
			BookBean bBean = new BookBean();
			bBean.setBookNumm(booknum);
			bBean.setBookName(name);
			bBean.setBookWriter(writer);
			bBean.setBookTrans(trans);
			bBean.setBookDate(date);
			bBean.setBookPublishr(bpub);
			bBean.setBookType(booktype);
			bBean.setBookPrice(price);
			bBean.setNumb(num);
			bBean.setBookphoto(photo);
			boolean flag = bService.updateBook(bBean);
			if (flag) {
				response.sendRedirect("ReaderManager/success.jsp");
			}else {
				response.sendRedirect("ReaderManager/fail.jsp");
			}
		}else if (submit.equals("ɾ��")) {
			BorrowService borrowService = new BorrowService();
			String booknum = su.getRequest().getParameter("booknum");
			boolean flag = borrowService.checkBorrowByBooknum(booknum);
			if (flag) {
				response.sendRedirect("BookManager/canotdelete.jsp");
			}else{
				boolean flag1 = bService.removeBook(booknum);
				if (flag1) {
					response.sendRedirect("ReaderManager/success.jsp");
				}else {
					response.sendRedirect("ReaderManager/fail.jsp");
				}
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}