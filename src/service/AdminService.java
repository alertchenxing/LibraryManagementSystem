package service;

import dao.AdminDao;

public class AdminService {
	AdminDao aDao = new AdminDao();
	
	/**
	 * �û���½
	 * @param username �û���
	 * @param password ��½����
	 * @return �û������ڷ���0��������󷵻�1����½�ɹ�����2
	 */
	public int login(String username, String password) {
		int flag = aDao.checkAdmin(username, password);
		return flag;
	}
}
