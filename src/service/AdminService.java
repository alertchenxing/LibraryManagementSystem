package service;

import java.sql.SQLException;

import common.DBConnection;
import dao.AdminDao;
import model.AdminBean;

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
	/**
	 * ����û�
	 * @param aBean����˻���Ϣ��javabean
	 * @return ��ӳɹ�����true�����ʧ�ܷ���false
	 */
	public boolean inset(AdminBean aBean) {
		return aDao.inset(aBean);
		
	}
	/**
	 * ��֤�û���
	 * @param username �û���
	 * @return �û������ڷ���false�����ڷ���true
	 */
	public boolean check(String username) {
		return aDao.check(username);
	}
	/**
	 * �޸��û�
	 * @param username ��Ҫ�޸ĵ��û�
	 * @param password �޸�֮�������
	 * @param flag1 �޸�֮���Ȩ�� 
	 * @return �޸�ʧ�ܷ���false���޸ĳɹ�����true
	 */
	public boolean update(String username, String password, int flag) {
		return aDao.update(username, password,flag);
	}
	/**
	 * ɾ���û�
	 * @param username ��Ҫɾ�����û�
	 * @return ɾ��ʧ�ܷ���false��ɾ���ɹ�����true
	 */
	public boolean delete(String username) {
		
		return aDao.delete(username);
	}
}
