package service;

import java.util.ArrayList;

import dao.BorrowDao;
import model.BorrowBean;

public class BorrowService {
	BorrowDao bDao = new BorrowDao();
	
	/**
	 * 添加借阅信息
	 * @param BorrowBean 所要添加的借阅信息
	 * @return 添加成功返回true，添加失败返回false
	 */
	public boolean addBorrow(BorrowBean borrowBean) {
		return bDao.doCreate(borrowBean);
	}
	
	/**
	 * 查询指定读者的借阅信息
	 * @param readerid要查询读者的证件号
	 * @return 返回借阅信息列表
	 */
	public ArrayList<BorrowBean> findBorrow(String readerid) {
		return bDao.doSelect(readerid);
	}
	
	/**
	 * 修改指定一条借阅信息
	 * @param borrowBean封装了所要修改的信息
	 * @return 修改成功返回TRUE失败返回FALSE
	 */
	public boolean alterBorrow(BorrowBean borrowBean) {
		System.out.println("servce");
		return bDao.doUpdate(borrowBean);
	}
	
	/**
	 * 删除指定一条借阅信息
	 * @param readerid要删除借阅信息的读者证件号
	 * @param booknum要删除借阅信息的图书编号
	 * @return 删除成功返回TRUE失败返回FALSE
	 */
	public boolean removeBorrow(String readerid, String booknum) {
		return bDao.doDelete(readerid, booknum);
	}
	
	/**
	 * 验证借阅信息是否存在
	 * @param readerid要验证借阅信息的读者证件号
	 * @param booknum要验证借阅信息的图书编号
	 * @return 存在返回TRUE不存在返回FALSE
	 */
	public boolean verifyBorrow(String readerid, String booknum) {
		return bDao.doCheck(readerid, booknum);
	}
}
