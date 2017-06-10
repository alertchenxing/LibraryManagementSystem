package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

import model.ReaderBean;
import service.ReaderService;



public class UpdateReaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateReaderServlet() {
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
		String submit = su.getRequest().getParameter("sub");
		String cardnum = su.getRequest().getParameter("cardnum");
		if (submit.equals("修改")) {
			String name = su.getRequest().getParameter("readername");
			String sex = su.getRequest().getParameter("readersex");
			int age = Integer.parseInt(su.getRequest().getParameter("readerage"));
			String tel = su.getRequest().getParameter("readertel");
			String cardtype = su.getRequest().getParameter("cardtype");
			String temp1 = su.getRequest().getParameter("maxnum");
			if (temp1.equals("")) {
				temp1 = "0";
			}
			int maxnum = Integer.parseInt(temp1);
			String temp2 = su.getRequest().getParameter("money");
			if (temp2.equals("")) {
				temp2 = "0";
			}
			float money = Float.parseFloat(temp2);
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
			if (rService.updateReader(rBean)) {
				response.sendRedirect("ReaderManager/success.jsp");
			}else {
				response.sendRedirect("ReaderManager/fail.jsp");
			}
		}else if (submit.equals("删除")) {
			if (rService.removeReader(cardnum)) {
				response.sendRedirect("ReaderManager/success.jsp");
			}else {
				response.sendRedirect("ReaderManager/fail.jsp");
			}
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
