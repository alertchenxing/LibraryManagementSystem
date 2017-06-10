package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.omg.CORBA.PUBLIC_MEMBER;

import common.DBConnection;
import model.BookBean;

public class BookDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/**
	 * 添加图书信息
	 * @param bBean 所要添加的图书信息
	 * @return 添加成功返回true，添加失败返回false
	 */
	public boolean doCreate(BookBean bBean){
		boolean flag = false;
		conn = DBConnection.getConnection();
		if (bBean.getBookDate() == null) {
			String sql = "insert into tb_bookinfo(bookname,writer,translator,"
					+ "publisher,booktype,price,num,photo,b_num) values(?,?,?,?,?,?,?,?,?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bBean.getBookName());
				pstmt.setString(2, bBean.getBookWriter());
				pstmt.setString(3, bBean.getBookTrans());
				pstmt.setString(4, bBean.getBookPublishr());
				pstmt.setString(5, bBean.getBookType());
				pstmt.setFloat(6, bBean.getBookPrice());
				pstmt.setInt(7, bBean.getNumb());
				pstmt.setString(8, bBean.getBookphoto());
				pstmt.setInt(9, bBean.getB_numb());
				if(pstmt.executeUpdate()>0){
				flag = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.closeConnection(pstmt, conn);
			}
		}else{
			String sql = "insert into tb_bookinfo(bookname,writer,translator,"
					+ "bdate,publisher,booktype,price,num,photo,b_num) values(?,?,?,?,?,?,?,?,?,?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bBean.getBookName());
				pstmt.setString(2, bBean.getBookWriter());
				pstmt.setString(3, bBean.getBookTrans());
				pstmt.setDate(4, new java.sql.Date(bBean.getBookDate().getTime()));
				pstmt.setString(5, bBean.getBookPublishr());
				pstmt.setString(6, bBean.getBookType());
				pstmt.setFloat(7, bBean.getBookPrice());
				pstmt.setInt(8, bBean.getNumb());
				pstmt.setString(9, bBean.getBookphoto());
				pstmt.setInt(10, bBean.getB_numb());
				if(pstmt.executeUpdate()>0){
				flag = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.closeConnection(pstmt, conn);
			}
		}
		
		return flag;
	}
	
	/**
	 * 修改图书信息
	 * @param bBean 所要修改的读者信息
	 * @return 修改成功返回true，添加失败返回false
	 */
	public boolean doUpdate(BookBean bBean){
		boolean flag = false;
		conn = DBConnection.getConnection();
		if (bBean.getBookDate() != null) {
			String sql = "update tb_bookinfo set bookname=?,writer=?,translator=?,bdate=?,"+
					"publisher=?,booktype=?,price=?,num=?,photo=? where booknum=?";
					try {
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, bBean.getBookName());
						pstmt.setString(2, bBean.getBookWriter());
						pstmt.setString(3, bBean.getBookTrans());
						pstmt.setDate(4, new java.sql.Date(bBean.getBookDate().getTime()));
						pstmt.setString(5, bBean.getBookPublishr());
						pstmt.setString(6, bBean.getBookType());
						pstmt.setFloat(7, bBean.getBookPrice());
						pstmt.setInt(8, bBean.getNumb());
						pstmt.setString(9, bBean.getBookphoto());
						pstmt.setString(10, bBean.getBookNumm());
						if(pstmt.executeUpdate()>0){
							flag = true;
						}	
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						DBConnection.closeConnection(pstmt, conn);
					}
		} else {
			String sql = "update tb_bookinfo set bookname=?,writer=?,translator=?,"+
					"publisher=?,booktype=?,price=?,num=?,photo=?,bdate=null where booknum=?";
					try {
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, bBean.getBookName());
						pstmt.setString(2, bBean.getBookWriter());
						pstmt.setString(3, bBean.getBookTrans());
						pstmt.setString(4, bBean.getBookPublishr());
						pstmt.setString(5, bBean.getBookType());
						pstmt.setFloat(6, bBean.getBookPrice());
						pstmt.setInt(7, bBean.getNumb());
						pstmt.setString(8, bBean.getBookphoto());
						pstmt.setString(9, bBean.getBookNumm());
						if(pstmt.executeUpdate() > 0){
							flag = true;
						}	
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						DBConnection.closeConnection(pstmt, conn);
					}
		}
		
		return flag;
	}
	
	/**
	 * 删除图书信息
	 * @param booknum需要删除的图书的编号
	 * @return 修改成功返回true，添加失败返回false
	 */
	public boolean doDelete(String booknum){
		boolean flag = false;
		conn = DBConnection.getConnection();
		String sql = "delete from tb_bookinfo where booknum='"+booknum+"'";
		try {
			pstmt = conn.prepareStatement(sql);
			if(pstmt.executeUpdate()>0){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		return  flag;
	}
	
	/**
	 * 查询表的所有信息
	 * @return 返回装有所有信息的列表
	 */
	public ArrayList<BookBean> getAll(){
		ArrayList<BookBean> bookList = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql = "select * from tb_bookinfo order by nlssort(bookname,'NLS_SORT=SCHINESE_PINYIN_M')";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				BookBean bBean = new BookBean();
				bBean.setBookName(rs.getString("bookname"));
				bBean.setBookNumm(rs.getString("booknum"));
				bBean.setBookWriter(rs.getString("writer"));
				bBean.setBookTrans(rs.getString("translator"));
				bBean.setBookDate(rs.getDate("bdate"));
				bBean.setBookPublishr(rs.getString("publisher"));
				bBean.setBookType(rs.getString("booktype"));
				bBean.setBookPrice(rs.getFloat("price"));
				bBean.setNumb(rs.getInt("num"));
				bBean.setBookphoto(rs.getString("photo"));
				bBean.setB_numb(rs.getInt("b_num"));
				bookList.add(bBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(rs, pstmt, conn);
		}
		return bookList;
	}
	
	/**
	 * 条件查询读者的信息
	 * @param strkey 所要查找的条件
	 */
	public ArrayList<BookBean> selectBykey(String strkey){
		ArrayList<BookBean> bookList = this.selectByname(strkey);
		return bookList;
	}
	
	/**
	 * 查询指定图书
	 * @param booknum 所要查找图书的编号
	 * @return 查找成功返存有指定信息的javabean
	 */
	public BookBean selectBynum(String booknum){
		BookBean bBean = new BookBean();
		conn = DBConnection.getConnection();
		String sql= "select * from tb_bookinfo where booknum='"+booknum+"'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bBean.setBookName(rs.getString("bookname"));
				bBean.setBookNumm(rs.getString("booknum"));
				bBean.setBookWriter(rs.getString("writer"));
				bBean.setBookTrans(rs.getString("translator"));
				bBean.setBookDate(rs.getDate("bdate"));
				bBean.setBookPublishr(rs.getString("publisher"));
				bBean.setBookType(rs.getString("booktype"));
				bBean.setBookPrice(rs.getFloat("price"));
				bBean.setNumb(rs.getInt("num"));
				bBean.setBookphoto(rs.getString("photo"));
				bBean.setB_numb(rs.getInt("b_num"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(rs, pstmt, conn);
		}
		return bBean;
	}
	/**
	 * 条件查询图书的信息
	 * @param name所要查找图书的名称
	 * @return 查找成功返返回装有所有信息的列表
	 */
	public ArrayList<BookBean> selectByname(String bookname){
		ArrayList<BookBean> bookList = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql= "select * from tb_bookinfo where bookname='"+bookname+"'";
		try {
			pstmt = conn.prepareStatement(sql);
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BookBean bBean = new BookBean();
					bBean.setBookName(rs.getString("bookname"));
					bBean.setBookNumm(rs.getString("booknum"));
					bBean.setBookWriter(rs.getString("writer"));
					bBean.setBookTrans(rs.getString("translator"));
					bBean.setBookDate(rs.getDate("bdate"));
					bBean.setBookPublishr(rs.getString("publisher"));
					bBean.setBookType(rs.getString("booktype"));
					bBean.setBookPrice(rs.getFloat("price"));
					bBean.setNumb(rs.getInt("num"));
					bBean.setBookphoto(rs.getString("photo"));
					bBean.setB_numb(rs.getInt("b_num"));
					bookList.add(bBean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(rs, pstmt, conn);
		}
		return bookList;
	}
	
	/**
	 * 验证证件号存不存在
	 * @param 要验证的证件号
	 * @return 用户不存在返回0，存在返回1
	 */
	public int checkBookNum(String booknum){
		conn = DBConnection.getConnection();
		int flag = 0;
		String sql = "select * from tb_reader where booknum='"+booknum+"'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(pstmt.executeUpdate() > 0){
				flag = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		return flag;
	}
	/**
	 * 验证图书是否可借
	 * @param 要验证的图书编号
	 * @return 可借返回大于1的值，不可借返回0
	 * */
	public int checkBorrow(String booknum){
		int num = 0;
		conn = DBConnection.getConnection();
		String sql = "select num - b_num from tb_bookinfo where booknum='"+booknum+"'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(rs, pstmt, conn);
		}
		return num;
	}
	/**
	 * 借阅之后修改图书信息中的可借图书数量
	 * @param 所要修改的图书编号
	 * @return 失败返回false，成功返回true
	 * **/
	public boolean updateBnum(String booknum) {
		boolean flag = false;
		conn = DBConnection.getConnection();
		String sql = "update tb_bookinfo set b_num = "
				+ "(select count(*) from tb_borrow where "
				+ "booknum='"+booknum+"' and flag = '0') where booknum='"+booknum+"'";
		try {
			pstmt = conn.prepareStatement(sql);
			if (pstmt.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(rs, pstmt, conn);
		}
		return flag;
	}
	/**
	 * 按所有字段检索图书
	 * @param strkey特征字段
	 * @return 返回装有查询结果的列表
	 * **/
	public ArrayList<BookBean> selectByallkey(String strkey) {
		ArrayList<BookBean> bookList = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql= "select * from tb_bookinfo where bookname "
				+ "like '%"+strkey+"%' or writer like '%"+strkey+"%' "
				+ "or publisher like '%"+strkey+"%' or booknum"
				+ " like '%"+strkey+"%' or translator like '%"+strkey+"%' or booktype like '%"+strkey+"%'";
		try {
			pstmt = conn.prepareStatement(sql);
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BookBean bBean = new BookBean();
					bBean.setBookName(rs.getString("bookname"));
					bBean.setBookNumm(rs.getString("booknum"));
					bBean.setBookWriter(rs.getString("writer"));
					bBean.setBookTrans(rs.getString("translator"));
					bBean.setBookDate(rs.getDate("bdate"));
					bBean.setBookPublishr(rs.getString("publisher"));
					bBean.setBookType(rs.getString("booktype"));
					bBean.setBookPrice(rs.getFloat("price"));
					bBean.setNumb(rs.getInt("num"));
					bBean.setBookphoto(rs.getString("photo"));
					bBean.setB_numb(rs.getInt("b_num"));
					bookList.add(bBean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(rs, pstmt, conn);
		}
		return bookList;
	}
	/**
	 * 按所有书名检索图书
	 * @param strkey书名部分字段
	 * @return 返回装有查询结果的列表
	 * **/
	public ArrayList<BookBean> selectBynamekey(String strkey) {
		ArrayList<BookBean> bookList = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql= "select * from tb_bookinfo where bookname like '%"+strkey+"%'";
		try {
			pstmt = conn.prepareStatement(sql);
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BookBean bBean = new BookBean();
					bBean.setBookName(rs.getString("bookname"));
					bBean.setBookNumm(rs.getString("booknum"));
					bBean.setBookWriter(rs.getString("writer"));
					bBean.setBookTrans(rs.getString("translator"));
					bBean.setBookDate(rs.getDate("bdate"));
					bBean.setBookPublishr(rs.getString("publisher"));
					bBean.setBookType(rs.getString("booktype"));
					bBean.setBookPrice(rs.getFloat("price"));
					bBean.setNumb(rs.getInt("num"));
					bBean.setBookphoto(rs.getString("photo"));
					bBean.setB_numb(rs.getInt("b_num"));
					bookList.add(bBean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(rs, pstmt, conn);
		}
		return bookList;
	}
	/**
	 * 按作者检索图书
	 * @param strkey作者部分字段
	 * @return 返回装有查询结果的列表
	 * **/
	public ArrayList<BookBean> selectBywriterkey(String strkey) {
		ArrayList<BookBean> bookList = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql= "select * from tb_bookinfo where writer like '%"+strkey+"%'";
		try {
			pstmt = conn.prepareStatement(sql);
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BookBean bBean = new BookBean();
					bBean.setBookName(rs.getString("bookname"));
					bBean.setBookNumm(rs.getString("booknum"));
					bBean.setBookWriter(rs.getString("writer"));
					bBean.setBookTrans(rs.getString("translator"));
					bBean.setBookDate(rs.getDate("bdate"));
					bBean.setBookPublishr(rs.getString("publisher"));
					bBean.setBookType(rs.getString("booktype"));
					bBean.setBookPrice(rs.getFloat("price"));
					bBean.setNumb(rs.getInt("num"));
					bBean.setBookphoto(rs.getString("photo"));
					bBean.setB_numb(rs.getInt("b_num"));
					bookList.add(bBean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(rs, pstmt, conn);
		}
		return bookList;
	}
	/**
	 * 按译者检索图书
	 * @param strkey译者部分字段
	 * @return 返回装有查询结果的列表
	 * **/
	public ArrayList<BookBean> selectBytranslatorkey(String strkey) {
		ArrayList<BookBean> bookList = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql= "select * from tb_bookinfo where translator like '%"+strkey+"%'";
		try {
			pstmt = conn.prepareStatement(sql);
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BookBean bBean = new BookBean();
					bBean.setBookName(rs.getString("bookname"));
					bBean.setBookNumm(rs.getString("booknum"));
					bBean.setBookWriter(rs.getString("writer"));
					bBean.setBookTrans(rs.getString("translator"));
					bBean.setBookDate(rs.getDate("bdate"));
					bBean.setBookPublishr(rs.getString("publisher"));
					bBean.setBookType(rs.getString("booktype"));
					bBean.setBookPrice(rs.getFloat("price"));
					bBean.setNumb(rs.getInt("num"));
					bBean.setBookphoto(rs.getString("photo"));
					bBean.setB_numb(rs.getInt("b_num"));
					bookList.add(bBean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(rs, pstmt, conn);
		}
		return bookList;
	}
	
	/**
	 * 按出版社检索图书
	 * @param strkey出版社部分字段
	 * @return 返回装有查询结果的列表
	 * **/
	public ArrayList<BookBean> selectBypublisherkey(String strkey) {
		ArrayList<BookBean> bookList = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql= "select * from tb_bookinfo where publisher like '%"+strkey+"%'";
		try {
			pstmt = conn.prepareStatement(sql);
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BookBean bBean = new BookBean();
					bBean.setBookName(rs.getString("bookname"));
					bBean.setBookNumm(rs.getString("booknum"));
					bBean.setBookWriter(rs.getString("writer"));
					bBean.setBookTrans(rs.getString("translator"));
					bBean.setBookDate(rs.getDate("bdate"));
					bBean.setBookPublishr(rs.getString("publisher"));
					bBean.setBookType(rs.getString("booktype"));
					bBean.setBookPrice(rs.getFloat("price"));
					bBean.setNumb(rs.getInt("num"));
					bBean.setBookphoto(rs.getString("photo"));
					bBean.setB_numb(rs.getInt("b_num"));
					bookList.add(bBean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(rs, pstmt, conn);
		}
		return bookList;
	}
	/**
	 * 按图书编号检索图书
	 * @param strkey图书编号部分字段
	 * @return 返回装有查询结果的列表
	 * **/
	public ArrayList<BookBean> selectBybooknumkey(String strkey) {
		ArrayList<BookBean> bookList = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql= "select * from tb_bookinfo where booknum like '%"+strkey+"%'";
		try {
			pstmt = conn.prepareStatement(sql);
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BookBean bBean = new BookBean();
					bBean.setBookName(rs.getString("bookname"));
					bBean.setBookNumm(rs.getString("booknum"));
					bBean.setBookWriter(rs.getString("writer"));
					bBean.setBookTrans(rs.getString("translator"));
					bBean.setBookDate(rs.getDate("bdate"));
					bBean.setBookPublishr(rs.getString("publisher"));
					bBean.setBookType(rs.getString("booktype"));
					bBean.setBookPrice(rs.getFloat("price"));
					bBean.setNumb(rs.getInt("num"));
					bBean.setBookphoto(rs.getString("photo"));
					bBean.setB_numb(rs.getInt("b_num"));
					bookList.add(bBean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(rs, pstmt, conn);
		}
		return bookList;
	}
	/**
	 * 按图书类别检索图书
	 * @param strkey图书类别部分字段
	 * @return 返回装有查询结果的列表
	 * **/
	public ArrayList<BookBean> selectBybooktypekey(String strkey) {
		ArrayList<BookBean> bookList = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql= "select * from tb_bookinfo where booktype like '%"+strkey+"%'";
		try {
			pstmt = conn.prepareStatement(sql);
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BookBean bBean = new BookBean();
					bBean.setBookName(rs.getString("bookname"));
					bBean.setBookNumm(rs.getString("booknum"));
					bBean.setBookWriter(rs.getString("writer"));
					bBean.setBookTrans(rs.getString("translator"));
					bBean.setBookDate(rs.getDate("bdate"));
					bBean.setBookPublishr(rs.getString("publisher"));
					bBean.setBookType(rs.getString("booktype"));
					bBean.setBookPrice(rs.getFloat("price"));
					bBean.setNumb(rs.getInt("num"));
					bBean.setBookphoto(rs.getString("photo"));
					bBean.setB_numb(rs.getInt("b_num"));
					bookList.add(bBean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(rs, pstmt, conn);
		}
		return bookList;
	}
}

