package service;

import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dao.ReaderDao;
import model.ReaderBean;

public class ReaderService {
	ReaderDao rDao = new ReaderDao();
	
	/**
	 * ��ѯ���������Ϣ
	 * @return ����װ��������Ϣ���б�
	 */
	public ArrayList<ReaderBean> showAll(){
		ArrayList<ReaderBean> rList = new ArrayList<>();
		rList = rDao.getAll();
		return rList;
	}
	
	/**
	 * ������ѯ���ߵ���Ϣ
	 * @param strkey ��Ҫ���ҵ�����
	 * @return ���ҳɹ�������װ��������Ϣ���б�
	 */
	public ArrayList<ReaderBean> findReader(String strkey) {
		ArrayList<ReaderBean> rList = new ArrayList<>();
		rList = rDao.selectBykey(strkey);
		return rList;
		
	}
	/**
	 * ������ѯ���ߵ���Ϣ
	 * @param strkey ��Ҫ���ҵ�֤����
	 * @return ���ҳɹ�������װ��������Ϣ���б�
	 */
	public ArrayList<ReaderBean> findReaderbynum(String strkey) {
		ArrayList<ReaderBean> rList = new ArrayList<>();
		rList = rDao.selectBynum(strkey);
		return rList;
		
	}
	/**
	 * ������ѯ���ߵ���Ϣ
	 * @param strkey ��Ҫ���ҵĶ�������
	 * @return ���ҳɹ�������װ��������Ϣ���б�
	 */
	public ArrayList<ReaderBean> findReaderbyname(String strkey) {
		ArrayList<ReaderBean> rList = new ArrayList<>();
		rList = rDao.selectByname(strkey);
		return rList;
		
	}
	/**
	 * ��Ӷ���
	 * @param rBean ��Ҫ��ӵĶ���
	 * @return ��ӳɹ�����true�����ʧ�ܷ���false
	 */
	public boolean addReader(ReaderBean rBean) {
		return rDao.doCreate(rBean);
	}
	/**
	 * ɾ������
	 * @param cardnum ��Ҫɾ�����ߵ�֤����
	 * @return ɾ���ɹ�����true�����ʧ�ܷ���false
	 */
	public boolean removeReader(String cardnum) {
		return rDao.doDelete(cardnum);
	}
	/**
	 * �޸Ķ���
	 * @param rBean ��Ҫ�޸ĵĶ���
	 * @return �޸ĳɹ�����true�����ʧ�ܷ���false
	 */
	public boolean updateReader(ReaderBean rBean) {
		return rDao.doUpdate(rBean);
		
	}
	/**
	 * ��֤֤���Ŵ治����
	 * @param Ҫ��֤��֤����
	 * @return �û������ڷ���0�����ڷ���1
	 */
	public int checkCardNum(String cardnum){
		return rDao.checkCardNum(cardnum);
	}
}
