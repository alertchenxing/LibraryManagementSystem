package service;

import java.util.ArrayList;

import dao.BorrowDao;
import model.BorrowBean;

public class BorrowService {
	BorrowDao bDao = new BorrowDao();
	
	/**
	 * ��ӽ�����Ϣ
	 * @param BorrowBean ��Ҫ��ӵĽ�����Ϣ
	 * @return ��ӳɹ�����true�����ʧ�ܷ���false
	 */
	public boolean addBorrow(BorrowBean borrowBean) {
		return bDao.doCreate(borrowBean);
	}
	
	/**
	 * ��ѯָ�����ߵĽ�����Ϣ
	 * @param readeridҪ��ѯ���ߵ�֤����
	 * @return ���ؽ�����Ϣ�б�
	 */
	public ArrayList<BorrowBean> findBorrow(String readerid) {
		return bDao.doSelect(readerid);
	}
	
	/**
	 * �޸�ָ��һ��������Ϣ
	 * @param borrowBean��װ����Ҫ�޸ĵ���Ϣ
	 * @return �޸ĳɹ�����TRUEʧ�ܷ���FALSE
	 */
	public boolean alterBorrow(BorrowBean borrowBean) {
		System.out.println("servce");
		return bDao.doUpdate(borrowBean);
	}
	
	/**
	 * ɾ��ָ��һ��������Ϣ
	 * @param readeridҪɾ��������Ϣ�Ķ���֤����
	 * @param booknumҪɾ��������Ϣ��ͼ����
	 * @return ɾ���ɹ�����TRUEʧ�ܷ���FALSE
	 */
	public boolean removeBorrow(String readerid, String booknum) {
		return bDao.doDelete(readerid, booknum);
	}
	
	/**
	 * ��֤������Ϣ�Ƿ����
	 * @param readeridҪ��֤������Ϣ�Ķ���֤����
	 * @param booknumҪ��֤������Ϣ��ͼ����
	 * @return ���ڷ���TRUE�����ڷ���FALSE
	 */
	public boolean verifyBorrow(String readerid, String booknum) {
		return bDao.doCheck(readerid, booknum);
	}
}
