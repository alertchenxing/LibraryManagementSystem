package service;

import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dao.BookDao;
import model.BookBean;

public class BookService {
	BookDao bDao = new BookDao();
	
	/**
	 * 添加图书信息
	 * @param bBean 所要添加的图书信息
	 * @return 添加成功返回true，添加失败返回false
	 */
	public boolean addBook(BookBean bBean){
		return bDao.doCreate(bBean);
	}
	/**
	 * 修改图书信息
	 * @param bBean 所要修改的读者信息
	 * @return 修改成功返回true，添加失败返回false
	 */
	public boolean updateBook(BookBean bBean){
		return bDao.doUpdate(bBean);	
	}
	/**
	 * 删除图书信息
	 * @param booknum需要删除的图书的编号
	 * @return 修改成功返回true，添加失败返回false
	 */
	public boolean removeBook(String booknum){
		return bDao.doDelete(booknum);
	}
	
	/**
	 * 查询表的所有信息
	 * @return 返回装有所有信息的列表
	 */
	public ArrayList<BookBean> showAll(){
		ArrayList<BookBean> bList = new ArrayList<>();
		bList = bDao.getAll();
		return bList;
	}
	
	/**
	 * 验证图书是否可借
	 * @param 要验证的图书编号
	 * @return 可借返回大于1的值，不可借返回0
	 * */
	public int checkBorrow(String booknum) {
		return bDao.checkBorrow(booknum);
	}
	/**
	 * 借阅之后修改图书信息中的可借图书数量
	 * @param 所要修改的图书编号
	 * @return 失败返回false，成功返回true
	 * **/
	public boolean updateBnum(String booknum) {
		return bDao.updateBnum(booknum);
	}
	
	/**
	 * 查询指定图书
	 * @param booknum 所要查找图书的编号
	 * @return 查找成功返存有指定信息的javabean
	 */
	public BookBean findBookbynum(String booknum){
		return bDao.selectBynum(booknum);
		
	}
	
	/**
	 * 按所有字段检索图书
	 * @param strkey特征字段
	 * @return 返回装有查询结果的列表
	 * **/
	public ArrayList<BookBean> selectByallkey(String strkey) {
		return bDao.selectByallkey(strkey);
	}
	
	/**
	 * 按所有书名检索图书
	 * @param strkey书名部分字段
	 * @return 返回装有查询结果的列表
	 * **/
	public ArrayList<BookBean> selectBynamekey(String strkey) {
		return bDao.selectBynamekey(strkey);
	}
	
	/**
	 * 按作者检索图书
	 * @param strkey作者部分字段
	 * @return 返回装有查询结果的列表
	 * **/
	public ArrayList<BookBean> selectBywriterkey(String strkey) {
		return bDao.selectBywriterkey(strkey);
	}
	
	/**
	 * 按译者检索图书
	 * @param strkey译者部分字段
	 * @return 返回装有查询结果的列表
	 * **/
	public ArrayList<BookBean> selectBytranslatorkey(String strkey) {
		return bDao.selectBytranslatorkey(strkey);
	}
	
	/**
	 * 按出版社检索图书
	 * @param strkey出版社部分字段
	 * @return 返回装有查询结果的列表
	 * **/
	public ArrayList<BookBean> selectBypublisherkey(String strkey) {
		return bDao.selectBypublisherkey(strkey);
	}
	
	/**
	 * 按图书编号检索图书
	 * @param strkey图书编号部分字段
	 * @return 返回装有查询结果的列表
	 * **/
	public ArrayList<BookBean> selectBybooknumkey(String strkey) {
		return bDao.selectBybooknumkey(strkey);
	}
	
	/**
	 * 按图书类别检索图书
	 * @param strkey图书类别部分字段
	 * @return 返回装有查询结果的列表
	 * **/
	public ArrayList<BookBean> selectBybooktypekey(String strkey) {
		return bDao.selectBybooktypekey(strkey);
	}
	
	/**
	 * 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷疯鍥鹃敓鏂ゆ嫹閿熸枻鎷烽敓杈冿拷
	 * @param strkey 閿熸枻鎷疯閿熸枻鎷烽敓鎻鎷烽敓鏂ゆ嫹閿熸枻鎷�
	 * @return 閿熸枻鎷烽敓鎻垚鐧告嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷疯閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹鎭敓鏂ゆ嫹閿熷彨鎲嬫嫹
	 */
	public ArrayList<BookBean> findbook(String strkey) {
		ArrayList<BookBean> bList = new ArrayList<>();
		bList = bDao.selectBykey(strkey);
		return bList;	
	}
	
	
	/**
	 * 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷疯閿熶粙绫嶉敓鏂ゆ嫹閿熸枻鎷锋伅
	 * @param strkey 閿熸枻鎷疯閿熸枻鎷烽敓鎻鎷烽敓浠嬬睄 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�
	 * @return 閿熸枻鎷烽敓鎻垚鐧告嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷疯閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹鎭敓鏂ゆ嫹閿熷彨鎲嬫嫹
	 */
	public ArrayList<BookBean> findBookbyname(String strkey) {
		ArrayList<BookBean> bList = new ArrayList<>();
		bList = bDao.selectByname(strkey);
		return bList;
		
	}
	
	/**
	 * 閿熸枻鎷疯瘉閿熶粙绫嶉敓鏂ゆ嫹閿熸枻鎷锋瑺閿熻姤涓嶉敓鏂ゆ嫹閿熸枻鎷�
	 * @param 瑕侀敓鏂ゆ嫹璇侀敓鏂ゆ嫹閿熶粙绫嶉敓鏂ゆ嫹閿燂拷
	 * @return 閿熶粙绫嶉敓鏂ゆ嫹閿熸枻鎷烽敓鑺傚嚖鎷烽敓鏂ゆ嫹0閿熸枻鎷烽敓鏂ゆ嫹閿熻妭鍑ゆ嫹閿熸枻鎷�1
	 */
	public int checkBookNum(String booknum){
		return bDao.checkBookNum(booknum);
	}
	
}
