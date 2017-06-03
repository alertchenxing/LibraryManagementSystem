package service;

import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dao.BookDao;
import model.BookBean;

public class BookService {
	BookDao bDao = new BookDao();
	
	/**
	 * 閿熸枻鎷疯閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熻緝锟�
	 * @return 閿熸枻鎷烽敓鏂ゆ嫹瑁呴敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷锋伅閿熸枻鎷烽敓鍙唻鎷�
	 * @throws SQLException 
	 */
	public ArrayList<BookBean> showAll() throws SQLException{
		ArrayList<BookBean> bList = new ArrayList<>();
		bList = bDao.getAll();
		return bList;
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
	 * @param strkey 閿熸枻鎷疯閿熸枻鎷烽敓鎻鎷烽敓浠嬬睄閿熶茎鎲嬫嫹閿燂拷
	 * @return 閿熸枻鎷烽敓鎻垚鐧告嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷疯閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹鎭敓鏂ゆ嫹閿熷彨鎲嬫嫹
	 * @throws SQLException 
	 */
	public ArrayList<BookBean> findBookbynum(String strkey) throws SQLException {
		ArrayList<BookBean> bList = new ArrayList<>();
		bList = bDao.selectBynum(strkey);
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
	 * 添加图书信息
	 * @param bBean 所要添加的图书信息
	 * @return 添加成功返回true，添加失败返回false
	 * @throws SQLException
	 */
	public boolean addBook(BookBean bBean) throws SQLException {
		return bDao.doCreate(bBean);
	}
	
	/**
	 * 鍒犻敓鏂ゆ嫹閿熶粙绫�
	 * @param bnum 閿熸枻鎷疯鍒犻敓鏂ゆ嫹閿熶粙绫嶉敓鏂ゆ嫹閿燂拷
	 * @return 鍒犻敓鏂ゆ嫹閿熺即鐧告嫹閿熸枻鎷烽敓鏂ゆ嫹true閿熸枻鎷烽敓鏂ゆ嫹閿熺粸褝鎷疯妸閿熸枻鎷烽敓绲漚lse
	 * @throws SQLException 
	 */
	public boolean removeBook(String booknum) throws SQLException {
		return bDao.doDelete(booknum);
	}
	
	/**
	 * 閿熺潾闈╂嫹閿熶粙绫�
	 * @param rBean 閿熸枻鎷疯閿熺潾鏀圭鎷烽敓浠嬬睄
	 * @return 閿熺潾鏀规垚鐧告嫹閿熸枻鎷烽敓鏂ゆ嫹true閿熸枻鎷烽敓鏂ゆ嫹閿熺粸褝鎷疯妸閿熸枻鎷烽敓绲漚lse
	 * @throws SQLException 
	 */
	public boolean updateBook(BookBean bBean) throws SQLException {
		return bDao.doUpdate(bBean);	
	}
	
	/**
	 * 閿熸枻鎷疯瘉閿熶粙绫嶉敓鏂ゆ嫹閿熸枻鎷锋瑺閿熻姤涓嶉敓鏂ゆ嫹閿熸枻鎷�
	 * @param 瑕侀敓鏂ゆ嫹璇侀敓鏂ゆ嫹閿熶粙绫嶉敓鏂ゆ嫹閿燂拷
	 * @return 閿熶粙绫嶉敓鏂ゆ嫹閿熸枻鎷烽敓鑺傚嚖鎷烽敓鏂ゆ嫹0閿熸枻鎷烽敓鏂ゆ嫹閿熻妭鍑ゆ嫹閿熸枻鎷�1
	 */
	public int checkBookNum(String booknum){
		return bDao.checkBookNum(booknum);
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
}
