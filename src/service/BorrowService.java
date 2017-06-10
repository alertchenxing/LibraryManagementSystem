package service;

import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
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
	 * ��ѯָ��ͼ�����ߵ����н�����Ϣ
	 * @param strkey ��Ҫ���ҵ���������֤���Ż���ͼ����
	 * @return ���ҳɹ�������װ��������Ϣ���б�
	 */
	public ArrayList<BorrowBean> selectBykey(String strkey){
		ArrayList<BorrowBean> bList = new ArrayList<>();
		bList = bDao.selectBykey(strkey);
		return bList;
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
		return bDao.doUpdate(borrowBean);
	}
	
	/**
	 * ɾ��ָ��һ��������Ϣ
	 * @param bidҪɾ��������Ϣ����ID
	 * @return ɾ���ɹ�����TRUEʧ�ܷ���FALSE
	 */
	public boolean removeBorrow(int bid) {
		return bDao.doDelete(bid);
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
	/**
	 * ��ѯָ��һ��������Ϣ
	 * @param bidҪ��ѯ������Ϣ������
	 * @return ���ڷ���TRUE�����ڷ���FALSE
	 */
	public BorrowBean selectOneBorrow(int bid){
		return bDao.selectOneBorrow(bid);
	}
	/**
	 * ����һ����
	 * @param borrowBean����������Ϣ
	 * @return ����ɹ�����true����ʧ�ܷ���false
	 */
	public boolean xujie(BorrowBean borrowBean) {
		return bDao.xujie(borrowBean);
	}
	/**
	 * ��ָ֤��ͼ���Ƿ��н���δ�黹
	 * @param booknumָ��ͼ����
	 * @return ���ڷ���true�����ڷ���false
	 */
	public boolean checkBorrowByBooknum(String booknum) {
		return bDao.checkBorrowByBooknum(booknum);
		
	}
}
