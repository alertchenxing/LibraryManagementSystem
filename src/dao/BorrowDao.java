package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import model.BorrowBean;
import model.ReaderBean;

public class BorrowDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/**
	 * 添加借阅信息
	 * @param BorrowBean 所要添加的借阅信息
	 * @return 添加成功返回true，添加失败返回false
	 */
	public boolean doCreate(BorrowBean borrowBean){
		boolean flag = false;
		conn = DBConnection.getConnection();
		String sql = "insert into tb_borrow(booknum,readerid,admin,borrowdate,flag)"
				+ "values(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, borrowBean.getBookNum());
			pstmt.setString(2, borrowBean.getReaderId());
			pstmt.setString(3, borrowBean.getAdminName());
			pstmt.setDate(4, new java.sql.Date(borrowBean.getBorrowDate().getTime()));
			pstmt.setInt(5, borrowBean.getFlag());
			if (pstmt.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		return flag;
	}
	/**
	 * 查询指定图书或读者的所有借阅信息
	 * @param strkey 所要查找的条件读者证件号或者图书编号
	 * @return 查找成功返返回装有所有信息的列表
	 */
	public ArrayList<BorrowBean> selectBykey(String strkey){
		ArrayList<BorrowBean> bList = new ArrayList<>();
		bList = this.doSelect(strkey);
		if (bList == null || bList.size() == 0) {
			bList = this.doSelectbyBooknum(strkey);
		}
		return bList;
	}
	/**
	 * 查询指定读者的借阅信息
	 * @param readerid要查询读者的证件号
	 * @return 返回借阅信息列表
	 */
	public ArrayList<BorrowBean> doSelect(String readerid){
		ArrayList<BorrowBean> bList = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql = "select a.booknum, a.bookname ,a.booktype,"
				+ "b.admin,b.borrowdate,b.backdate,b.flag,b.fine,"
				+ "b.deposit,b.bid,r.cardnum,r.name,r.maxnum from tb_bookinfo a "
				+ "inner join tb_borrow b on a.booknum = b.booknum inner "
				+ "join tb_reader r on b.readerid = r.cardnum where r.cardnum='"+readerid+"' order by bid";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				BorrowBean borrowBean = new BorrowBean();
				borrowBean.setAdminName(rs.getString("admin"));
				borrowBean.setBookName(rs.getString("bookname"));
				borrowBean.setBookNum(rs.getString("booknum"));
				borrowBean.setBooktype(rs.getString("booktype"));
				borrowBean.setReaderId(rs.getString("cardnum"));
				borrowBean.setReaderName(rs.getString("name"));
				borrowBean.setBorrowDate(rs.getDate("borrowdate"));
				borrowBean.setBackDate(rs.getDate("backdate"));
				borrowBean.setReaderbooknum(rs.getShort("maxnum"));
				borrowBean.setDeposit(rs.getFloat("deposit"));
				borrowBean.setBid(rs.getInt("bid"));
				borrowBean.setFlag(rs.getInt("flag"));
				bList.add(borrowBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(rs, pstmt, conn);
		}
		return bList;
	}
	/**
	 * 查询指定图书的借阅信息
	 * @param booknum要查询图书的证件号
	 * @return 返回借阅信息列表
	 */
	public ArrayList<BorrowBean> doSelectbyBooknum(String booknum){
		ArrayList<BorrowBean> bList = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql = "select a.booknum, a.bookname ,a.booktype,"
				+ "b.admin,b.borrowdate,b.backdate,b.flag,b.fine,"
				+ "b.deposit,b.bid,r.cardnum,r.name,r.maxnum from tb_bookinfo a "
				+ "inner join tb_borrow b on a.booknum = b.booknum inner "
				+ "join tb_reader r on b.readerid = r.cardnum where b.booknum='"+booknum+"' order by bid";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				BorrowBean borrowBean = new BorrowBean();
				borrowBean.setAdminName(rs.getString("admin"));
				borrowBean.setBookName(rs.getString("bookname"));
				borrowBean.setBookNum(rs.getString("booknum"));
				borrowBean.setBooktype(rs.getString("booktype"));
				borrowBean.setReaderId(rs.getString("cardnum"));
				borrowBean.setReaderName(rs.getString("name"));
				borrowBean.setBorrowDate(rs.getDate("borrowdate"));
				borrowBean.setBackDate(rs.getDate("backdate"));
				borrowBean.setReaderbooknum(rs.getShort("maxnum"));
				borrowBean.setDeposit(rs.getFloat("deposit"));
				borrowBean.setBid(rs.getInt("bid"));
				borrowBean.setFlag(rs.getInt("flag"));
				bList.add(borrowBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(rs, pstmt, conn);
		}
		return bList;
	}
	/**
	 * 修改指定一条借阅信息
	 * @param borrowBean封装了所要修改的信息
	 * @return 修改成功返回TRUE失败返回FALSE
	 */
	public boolean doUpdate(BorrowBean borrowBean){
		boolean flag = false;
		conn = DBConnection.getConnection();
		String sql = "update tb_borrow set backdate=?,flag=?,"
				+ "deposit=?,message=?,admin=? where bid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, new java.sql.Date(borrowBean.getBackDate().getTime()));
			pstmt.setInt(2, borrowBean.getFlag());
			pstmt.setFloat(3, borrowBean.getDeposit());
			pstmt.setInt(4, borrowBean.getMessage());
			pstmt.setString(5, borrowBean.getAdminName());
			pstmt.setInt(6, borrowBean.getBid());
			if (pstmt.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		return flag;
	}
	/**
	 * 删除指定一条借阅信息
	 * @param bid要删除借阅信息主键ID
	 * @return 删除成功返回TRUE失败返回FALSE
	 */
	public boolean doDelete(int bid) {
		boolean flag = false;
		conn = DBConnection.getConnection();
		String sql = "delete from tb_borrow where bid='"+bid+"'";
		try {
			pstmt = conn.prepareStatement(sql);
			if (pstmt.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		return flag;
	}
	/**
	 * 验证借阅信息是否存在
	 * @param readerid要验证借阅信息的读者证件号
	 * @param booknum要验证借阅信息的图书编号
	 * @return 存在返回TRUE不存在返回FALSE
	 */
	public boolean doCheck(String readerid, String booknum) {
		boolean flag = false;
		conn = DBConnection.getConnection();
		String sql = "select * from tb_borrow where readerid='"+readerid+"' and booknum='"+booknum+"'";
		try {
			pstmt = conn.prepareStatement(sql);
			if(pstmt.executeUpdate() > 0){
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
	 * 查询指定一条借阅信息
	 * @param bid要查询借阅信息的主键
	 * @return 存在返回TRUE不存在返回FALSE
	 */
	public BorrowBean selectOneBorrow(int bid){
		BorrowBean borrowBean = new BorrowBean();
		conn = DBConnection.getConnection();
		String sql = "select a.booknum, a.bookname ,a.booktype,"
				+ "b.admin,b.borrowdate,b.backdate,b.flag,b.fine,"
				+ "b.deposit,b.message,b.bid,r.cardnum,r.name,r.maxnum from tb_bookinfo a "
				+ "inner join tb_borrow b on a.booknum = b.booknum inner "
				+ "join tb_reader r on b.readerid = r.cardnum where bid='"+bid+"'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				borrowBean.setAdminName(rs.getString("admin"));
				borrowBean.setBookName(rs.getString("bookname"));
				borrowBean.setBookNum(rs.getString("booknum"));
				borrowBean.setBooktype(rs.getString("booktype"));
				borrowBean.setReaderId(rs.getString("cardnum"));
				borrowBean.setReaderName(rs.getString("name"));
				borrowBean.setBorrowDate(rs.getDate("borrowdate"));
				borrowBean.setBackDate(rs.getDate("backdate"));
				borrowBean.setReaderbooknum(rs.getShort("maxnum"));
				borrowBean.setDeposit(rs.getFloat("deposit"));
				borrowBean.setBid(rs.getInt("bid"));
				borrowBean.setFlag(rs.getInt("flag"));
				borrowBean.setMessage(rs.getInt("message"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBConnection.closeConnection(rs, pstmt, conn);
		}
		return borrowBean;
	}
	public boolean xujie(BorrowBean borrowBean) {
		boolean flag = false;
		conn = DBConnection.getConnection();
		String sql = "update tb_borrow set borrowdate=?,admin=? where bid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, new java.sql.Date(borrowBean.getBorrowDate().getTime()));
			pstmt.setString(2, borrowBean.getAdminName());
			pstmt.setInt(3, borrowBean.getBid());
			if (pstmt.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		return flag;
		
	}
}
