package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

import model.BookBean;
import service.BookService;


@WebServlet(asyncSupported = true, urlPatterns = { "/addBookServlet" })
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public AddBookServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SmartUpload su = new SmartUpload();
		//��ʼ������
		su.initialize(getServletConfig(), request, response);
		//�����ϴ��ļ��Ĵ�С
		su.setMaxFileSize(1024*1024*40);
		//���������ļ��Ĵ�С
		su.setTotalMaxFileSize(1024*1024*100);
		//���������ϴ��ļ�����
		su.setAllowedFilesList("jpg,png,gif");
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
		BookService bService = new BookService();
		String booknum = su.getRequest().getParameter("booknum");
		System.out.println(booknum);
		int result = bService.checkBookNum(booknum);
		PrintWriter out = response.getWriter();
		if (result == 0) {
			out.print(0);
		}else if(result == 1){
			out.print(1);
			return;
		}
		String name = su.getRequest().getParameter("bookname");
		String bnum = su.getRequest().getParameter("booknum");
		String writer=su.getRequest().getParameter("writer");
		String trans = su.getRequest().getParameter("translator");
		String bookdate	 = su.getRequest().getParameter("bdate");
		String bpub = su.getRequest().getParameter("publisher");
		String booktype = su.getRequest().getParameter("booktype");
		int b_num = Integer.parseInt(su.getRequest().getParameter("b_num"));
		int numb = Integer.parseInt(su.getRequest().getParameter("numb"));
		float price = Float.parseFloat(su.getRequest().getParameter("price"));
		System.out.println(request.getContextPath()+"/image/"+filename);
		BookBean bBean = new BookBean();
		bBean.setBookName(name);
		bBean.setBookNumm(bnum);
		bBean.setBookWriter(writer);
		bBean.setBookTrans(trans);
		bBean.setBookDate(bookdate);
		bBean.setBookPublishr(bpub);
		bBean.setBookType(booktype);
		bBean.setBookPrice(price);
		bBean.setNumb(numb);
		bBean.setBookphoto(photo);
		bBean.setB_numb(b_num);
		try {
			if (bService.addBook(bBean)) {
				System.out.println("��ӳɹ�");
			}else {
				System.out.println("���ʧ��");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.flush();
		out.close();
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
