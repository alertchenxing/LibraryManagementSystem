package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import model.BorrowBean;

public class BorrowDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/**
	 * ��ӽ�����Ϣ
	 * @param BorrowBean ��Ҫ��ӵĽ�����Ϣ
	 * @return ��ӳɹ�����true�����ʧ�ܷ���false
	 */
	public boolean doCreate(BorrowBean borrowBean){
		boolean flag = false;
		conn = DBConnection.getConnection();
		String sql = "insert into tb_borrow(booknum,readerid,admin,borrowdate,flag,deposit)"
				+ "values(?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, borrowBean.getBookNum());
			pstmt.setString(2, borrowBean.getReaderId());
			pstmt.setString(3, borrowBean.getAdminName());
			pstmt.setDate(4, new java.sql.Date(borrowBean.getBorrowDate().getTime()));
			pstmt.setInt(5, borrowBean.getFlag());
			pstmt.setFloat(6, borrowBean.getDeposit());
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
	 * ��ѯָ�����ߵĽ�����Ϣ
	 * @param readeridҪ��ѯ���ߵ�֤����
	 * @return ���ؽ�����Ϣ�б�
	 */
	public ArrayList<BorrowBean> doSelect(String readerid){
		ArrayList<BorrowBean> bList = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql = "select a.booknum, a.bookname ,a.booktype,"
				+ "b.admin,b.borrowdate,b.backdate,b.flag,b.fine,"
				+ "b.deposit,r.cardnum,r.name,r.maxnum from tb_bookinfo a "
				+ "inner join tb_borrow b on a.booknum = b.booknum inner "
				+ "join tb_reader r on b.readerid = r.cardnum where r.cardnum='"+readerid+"'";
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
				borrowBean.setFine(rs.getFloat("fine"));
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
	 * �޸�ָ��һ��������Ϣ
	 * @param borrowBean��װ����Ҫ�޸ĵ���Ϣ
	 * @return �޸ĳɹ�����TRUEʧ�ܷ���FALSE
	 */
	public boolean doUpdate(BorrowBean borrowBean){
		boolean flag = false;
		conn = DBConnection.getConnection();
		String sql = "update tb_borrow set backdate=?,flag=?,fine=?,"
				+ "deposit=? where readerid=? and booknum=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, new java.sql.Date(borrowBean.getBackDate().getTime()));
			pstmt.setInt(2, borrowBean.getFlag());
			pstmt.setFloat(3, borrowBean.getFine());
			pstmt.setFloat(4, borrowBean.getDeposit());
			pstmt.setString(5, borrowBean.getReaderId());
			pstmt.setString(6, borrowBean.getBookName());
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
	 * ɾ��ָ��һ��������Ϣ
	 * @param readeridҪɾ��������Ϣ�Ķ���֤����
	 * @param booknumҪɾ��������Ϣ��ͼ����
	 * @return ɾ���ɹ�����TRUEʧ�ܷ���FALSE
	 */
	public boolean doDelete(String readerid, String booknum) {
		boolean flag = false;
		conn = DBConnection.getConnection();
		String sql = "delete from tb_borrow where readerid='"+readerid+"' and booknum='"+booknum+"'";
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
	 * ��֤������Ϣ�Ƿ����
	 * @param readeridҪ��֤������Ϣ�Ķ���֤����
	 * @param booknumҪ��֤������Ϣ��ͼ����
	 * @return ���ڷ���TRUE�����ڷ���FALSE
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
}
