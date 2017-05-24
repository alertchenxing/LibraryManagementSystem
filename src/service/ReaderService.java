package service;

import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dao.ReaderDao;
import model.ReaderBean;

public class ReaderService {
	ReaderDao rDao = new ReaderDao();
	
	/**
	 * 查询表的所有信息
	 * @return 返回装有所有信息的列表
	 */
	public ArrayList<ReaderBean> showAll(){
		ArrayList<ReaderBean> rList = new ArrayList<>();
		rList = rDao.getAll();
		return rList;
	}
	
	/**
	 * 条件查询读者的信息
	 * @param strkey 所要查找的条件
	 * @return 查找成功返返回装有所有信息的列表
	 */
	public ArrayList<ReaderBean> findReader(String strkey) {
		ArrayList<ReaderBean> rList = new ArrayList<>();
		rList = rDao.selectBykey(strkey);
		return rList;
		
	}
	/**
	 * 条件查询读者的信息
	 * @param strkey 所要查找的证件号
	 * @return 查找成功返返回装有所有信息的列表
	 */
	public ArrayList<ReaderBean> findReaderbynum(String strkey) {
		ArrayList<ReaderBean> rList = new ArrayList<>();
		rList = rDao.selectBynum(strkey);
		return rList;
		
	}
	/**
	 * 条件查询读者的信息
	 * @param strkey 所要查找的读者名字
	 * @return 查找成功返返回装有所有信息的列表
	 */
	public ArrayList<ReaderBean> findReaderbyname(String strkey) {
		ArrayList<ReaderBean> rList = new ArrayList<>();
		rList = rDao.selectByname(strkey);
		return rList;
		
	}
	/**
	 * 添加读者
	 * @param rBean 所要添加的读者
	 * @return 添加成功返回true，添加失败返回false
	 */
	public boolean addReader(ReaderBean rBean) {
		return rDao.doCreate(rBean);
	}
	/**
	 * 删除读者
	 * @param cardnum 所要删除读者的证件号
	 * @return 删除成功返回true，添加失败返回false
	 */
	public boolean removeReader(String cardnum) {
		return rDao.doDelete(cardnum);
	}
	/**
	 * 修改读者
	 * @param rBean 所要修改的读者
	 * @return 修改成功返回true，添加失败返回false
	 */
	public boolean updateReader(ReaderBean rBean) {
		return rDao.doUpdate(rBean);
		
	}
	/**
	 * 验证证件号存不存在
	 * @param 要验证的证件号
	 * @return 用户不存在返回0，存在返回1
	 */
	public int checkCardNum(String cardnum){
		return rDao.checkCardNum(cardnum);
	}
}
