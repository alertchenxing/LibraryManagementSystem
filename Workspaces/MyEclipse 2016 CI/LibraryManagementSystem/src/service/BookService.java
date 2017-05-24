package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.BookDao;
import model.BookBean;

public class BookService {
	BookDao bDao = new BookDao();
	
	/**
	 * ��ѯ���������Ϣ
	 * @return ����װ��������Ϣ���б�
	 * @throws SQLException 
	 */
	public ArrayList<BookBean> showAll() throws SQLException{
		ArrayList<BookBean> bList = new ArrayList<>();
		bList = bDao.getAll();
		return bList;
	}
	
	/**
	 * ������ѯͼ�����Ϣ
	 * @param strkey ��Ҫ���ҵ�����
	 * @return ���ҳɹ�������װ��������Ϣ���б�
	 */
	public ArrayList<BookBean> findbook(String strkey) {
		ArrayList<BookBean> bList = new ArrayList<>();
		bList = bDao.selectBykey(strkey);
		return bList;	
	}
	
	/**
	 * ������ѯ�鼮����Ϣ
	 * @param strkey ��Ҫ���ҵ��鼮�ı��
	 * @return ���ҳɹ�������װ��������Ϣ���б�
	 * @throws SQLException 
	 */
	public ArrayList<BookBean> findBookbynum(String strkey) throws SQLException {
		ArrayList<BookBean> bList = new ArrayList<>();
		bList = bDao.selectBynum(strkey);
		return bList;
		
	}
	/**
	 * ������ѯ�鼮����Ϣ
	 * @param strkey ��Ҫ���ҵ��鼮 ������
	 * @return ���ҳɹ�������װ��������Ϣ���б�
	 */
	public ArrayList<BookBean> findBookbyname(String strkey) {
		ArrayList<BookBean> bList = new ArrayList<>();
		bList = bDao.selectByname(strkey);
		return bList;
		
	}
	
	/**
	 * ����鼮
	 * @param bBean ��Ҫ��ӵ��鼮
	 * @return ��ӳɹ�����true�����ʧ�ܷ���false
	 * @throws SQLException 
	 */
	public boolean addBook(BookBean bBean) throws SQLException {
		return bDao.doCreate(bBean);
	}
	
	/**
	 * ɾ���鼮
	 * @param bnum ��Ҫɾ���鼮���
	 * @return ɾ���ɹ�����true�����ʧ�ܷ���false
	 * @throws SQLException 
	 */
	public boolean removeBook(String booknum) throws SQLException {
		return bDao.doDelete(booknum);
	}
	
	/**
	 * �޸��鼮
	 * @param rBean ��Ҫ�޸ĵ��鼮
	 * @return �޸ĳɹ�����true�����ʧ�ܷ���false
	 * @throws SQLException 
	 */
	public boolean updateBook(BookBean bBean) throws SQLException {
		return bDao.doUpdate(bBean);	
	}
	
	/**
	 * ��֤�鼮����Ƿ�治����
	 * @param Ҫ��֤���鼮���
	 * @return �鼮�����ڷ���0�����ڷ���1
	 */
	public int checkBookNum(String booknum){
		return bDao.checkBookNum(booknum);
	}
}
