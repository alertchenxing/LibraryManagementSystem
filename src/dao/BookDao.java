package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.DBConnection;
import model.BookBean;

public class BookDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/**
	 * ���ͼ����Ϣ
	 * @param bBean ��Ҫ��ӵ�ͼ����Ϣ
	 * @return ��ӳɹ�����true�����ʧ�ܷ���false
	 * @throws SQLException
	 */
	public boolean doCreate(BookBean bBean) throws SQLException{
		boolean flag = false;
		conn = DBConnection.getConnection();
		String sql = "insert into tb_bookinfo(bookName,bookNum,writer,translator,bdate,publisher,bookType,price,num,"+
		"photo��b_num)values(?,?,?,?,?,?,?,?,?,?,?)";
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bBean.getBookName());
			pstmt.setString(2, bBean.getBookNumm());
			pstmt.setString(3, bBean.getBookWriter());
			pstmt.setString(4, bBean.getBookTrans());
			pstmt.setString(5, bBean.getBookDate());
			pstmt.setString(6, bBean.getBookPublishr());
			pstmt.setString(7, bBean.getBookType());
			pstmt.setFloat(8, bBean.getBookPrice());
			pstmt.setInt(9, bBean.getNumb());
			pstmt.setString(10, bBean.getBookphoto());
			pstmt.setInt(11, bBean.getB_numb());
			if(pstmt.executeUpdate()>0){
			flag = true;
			}
			DBConnection.closeConnection(pstmt, conn);
		return flag;
	}
	
	/**
	 * �޸Ķ�����Ϣ
	 * @param bBean ��Ҫ�޸ĵĶ�����Ϣ
	 * @return �޸ĳɹ�����true�����ʧ�ܷ���false
	 * @throws SQLException 
	 */
	public boolean doUpdate(BookBean bBean) throws SQLException{
		boolean flag = false;
		conn = DBConnection.getConnection();
		String sql = "update tb_bookinfo set bookname=?,writer=?,translator=?,bdate=?,"+
		"publisher?,booktype=?,price=?,num=?,photo=? where booknum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bBean.getBookName());
			pstmt.setString(2, bBean.getBookNumm());
			pstmt.setString(3, bBean.getBookWriter());
			pstmt.setString(4, bBean.getBookTrans());
			pstmt.setString(5, bBean.getBookDate());
			pstmt.setString(6, bBean.getBookPublishr());
			pstmt.setString(7, bBean.getBookType());
			pstmt.setFloat(8, bBean.getBookPrice());
			pstmt.setInt(9, bBean.getNumb());
			pstmt.setString(10, bBean.getBookphoto());
			pstmt.setInt(11, bBean.getB_numb());
			if(pstmt.executeUpdate()>0){
				flag = true;
			}
			DBConnection.closeConnection(pstmt, conn);
		return flag;
	}
	
	/**
	 * ɾ��������Ϣ
	 * @param bnum��Ҫɾ����ͼ��ı��
	 * @return �޸ĳɹ�����true�����ʧ�ܷ���false
	 * @throws SQLException 
	 */
	public boolean doDelete(String bnum) throws SQLException{
		boolean flag = false;
		conn = DBConnection.getConnection();
		String sql = "delete from tb_bookinfo where bookunm=����+booknum+����";
			pstmt = conn.prepareStatement(sql);
			if(pstmt.executeUpdate()>0){
				flag = true;
			}
			DBConnection.closeConnection(pstmt, conn);
		return  flag;
	}
	
	/**
	 * ��ѯ���������Ϣ
	 * @return ����װ��������Ϣ���б�
	 * @throws SQLException 
	 */
	public ArrayList<BookBean>getAll() throws SQLException{
		ArrayList<BookBean> bookList = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql = "select * from tb_bookinfo order by nlssort(bookname,'NLS_SORT=SCHINESE_PINYIN_M')";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				BookBean bBean = new BookBean();
				bBean.setBookName(rs.getString("bookname"));
				bBean.setBookNumm(rs.getString("booknum"));
				bBean.setBookWriter(rs.getString("writer"));
				bBean.setBookPublishr(rs.getString("translator"));
				bBean.setBookDate(rs.getString("bdate"));
				bBean.setBookPublishr(rs.getString("publisher"));
				bBean.setBookType(rs.getString("booktype"));
				bBean.setBookPrice(rs.getFloat("price"));
				bBean.setNumb(rs.getInt("num"));
				bBean.setBookphoto(rs.getString("photo"));
				bBean.setB_numb(rs.getInt("b_num"));
				bookList.add(bBean);
			}
			DBConnection.closeConnection(pstmt, conn);
		return bookList;
	}
	
	/**
	 * ������ѯ���ߵ���Ϣ
	 * @param strkey ��Ҫ���ҵ�����
	 * @return ���ҳɹ�������װ��������Ϣ���б�
	 */
	public ArrayList<BookBean> selectBykey(String strkey){
		ArrayList<BookBean> bookList = this.selectByname(strkey);
		if (bookList == null || bookList.size() == 0) {
			try {
				bookList = this.selectBynum(strkey);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bookList;
	}
	
	/**
	 * ������ѯͼ�����Ϣ
	 * @param bnum ��Ҫ����ͼ��ı��
	 * @return ���ҳɹ�������װ��������Ϣ���б�
	 * @throws SQLException 
	 */
	public ArrayList<BookBean> selectBynum(String booknum) throws SQLException{
		ArrayList<BookBean> bookList = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql= "select * from tb_bookinfo where booknum='"+booknum+"'";
			pstmt = conn.prepareStatement(sql);
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BookBean bBean = new BookBean();
					bBean.setBookName(rs.getString("bookname"));
					bBean.setBookNumm(rs.getString("booknum"));
					bBean.setBookWriter(rs.getString("writer"));
					bBean.setBookPublishr(rs.getString("translator"));
					bBean.setBookDate(rs.getString("bdate"));
					bBean.setBookPublishr(rs.getString("publisher"));
					bBean.setBookType(rs.getString("booktype"));
					bBean.setBookPrice(rs.getFloat("price"));
					bBean.setNumb(rs.getInt("num"));
					bBean.setBookphoto(rs.getString("photo"));
					bBean.setB_numb(rs.getInt("b_num"));
					bookList.add(bBean);
				}
			}
			DBConnection.closeConnection(pstmt, conn);
		return bookList;
	}
	/**
	 * ������ѯͼ�����Ϣ
	 * @param name��Ҫ����ͼ�������
	 * @return ���ҳɹ�������װ��������Ϣ���б�
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
					bBean.setBookPublishr(rs.getString("translator"));
					bBean.setBookDate(rs.getString("bdate"));
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
	 * ��֤֤���Ŵ治����
	 * @param Ҫ��֤��֤����
	 * @return �û������ڷ���0�����ڷ���1
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
}

