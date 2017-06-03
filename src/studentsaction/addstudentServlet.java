package studentsaction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.SmartUpload;

import model.ReaderBean;
import service.ReaderService;



public class addstudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public addstudentServlet() {
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
		String photopath = null;
		String filename = null;
		for (int i = 0; i < su.getFiles().getCount(); i++) {
			file =su.getFiles().getFile(i);
			if (file.isMissing()) {
				continue;
			}
			filename = file.getFileName();
			photopath = request.getContextPath()+"/image/"+filename;
		}
		ReaderService rService = new ReaderService();
		String cardnum = request.getParameter("cardnum");
		int result = rService.checkCardNum(cardnum);
		PrintWriter out = response.getWriter();
		if(result == 1){
			out.print(1);
			return;
		}
		cardnum = su.getRequest().getParameter("cardnum");
		String name = su.getRequest().getParameter("readername");
		String sex = su.getRequest().getParameter("readersex");
		int age = Integer.parseInt(su.getRequest().getParameter("readerage"));
		String tel = su.getRequest().getParameter("readertel");
		String cardtype = su.getRequest().getParameter("cardtype");
		int maxnum = 5;
		float money = 0;
		if (photopath==null) {
			photopath = "none";
		}
		ReaderBean rBean = new ReaderBean();
		rBean.setReaderName(name);
		rBean.setReaderSex(sex);
		rBean.setReaderAge(age);
		rBean.setReaderTel(tel);
		rBean.setCardType(cardtype);
		rBean.setCardNum(cardnum);
		rBean.setMaxNum(maxnum);
		rBean.setMoney(money);
		rBean.setPhotoPath(photopath);
		if (rService.addReader(rBean)) {
			HttpSession session = request.getSession();
			session.setAttribute("zhanghao", cardnum);
			response.sendRedirect("StudentManager/setpassword.jsp");
		}else {
			response.sendRedirect("ReaderManager/fail.jsp");
		}
		out.flush();
		out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
