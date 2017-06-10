package service;

import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
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
	 * 查询指定图书或读者的所有借阅信息
	 * @param strkey 所要查找的条件读者证件号或者图书编号
	 * @return 查找成功返返回装有所有信息的列表
	 */
	public ArrayList<BorrowBean> selectBykey(String strkey){
		ArrayList<BorrowBean> bList = new ArrayList<>();
		bList = bDao.selectBykey(strkey);
		return bList;
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
		return bDao.doUpdate(borrowBean);
	}
	
	/**
	 * 删除指定一条借阅信息
	 * @param bid要删除借阅信息主键ID
	 * @return 删除成功返回TRUE失败返回FALSE
	 */
	public boolean removeBorrow(int bid) {
		return bDao.doDelete(bid);
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
	/**
	 * 查询指定一条借阅信息
	 * @param bid要查询借阅信息的主键
	 * @return 存在返回TRUE不存在返回FALSE
	 */
	public BorrowBean selectOneBorrow(int bid){
		return bDao.selectOneBorrow(bid);
	}
	/**
	 * 续借一本书
	 * @param borrowBean包含借阅信息
	 * @return 续借成功返回true续借失败返回false
	 */
	public boolean xujie(BorrowBean borrowBean) {
		return bDao.xujie(borrowBean);
	}
	/**
	 * 验证指定图书是否有借阅未归还
	 * @param booknum指定图书编号
	 * @return 存在返回true不存在返回false
	 */
	public boolean checkBorrowByBooknum(String booknum) {
		return bDao.checkBorrowByBooknum(booknum);
		
	}
}
