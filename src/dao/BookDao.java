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
	 * ���ͼ����Ϣ
	 * @param bBean ��Ҫ��ӵ�ͼ����Ϣ
	 * @return ��ӳɹ�����true�����ʧ�ܷ���false
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
	 * �޸�ͼ����Ϣ
	 * @param bBean ��Ҫ�޸ĵĶ�����Ϣ
	 * @return �޸ĳɹ�����true�����ʧ�ܷ���false
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
	 * ɾ��ͼ����Ϣ
	 * @param booknum��Ҫɾ����ͼ��ı��
	 * @return �޸ĳɹ�����true�����ʧ�ܷ���false
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
	 * ��ѯ���������Ϣ
	 * @return ����װ��������Ϣ���б�
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
	 * ������ѯ���ߵ���Ϣ
	 * @param strkey ��Ҫ���ҵ�����
	 */
	public ArrayList<BookBean> selectBykey(String strkey){
		ArrayList<BookBean> bookList = this.selectByname(strkey);
		return bookList;
	}
	
	/**
	 * ��ѯָ��ͼ��
	 * @param booknum ��Ҫ����ͼ��ı��
	 * @return ���ҳɹ�������ָ����Ϣ��javabean
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
	/**
	 * ��֤ͼ���Ƿ�ɽ�
	 * @param Ҫ��֤��ͼ����
	 * @return �ɽ践�ش���1��ֵ�����ɽ践��0
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
	 * ����֮���޸�ͼ����Ϣ�еĿɽ�ͼ������
	 * @param ��Ҫ�޸ĵ�ͼ����
	 * @return ʧ�ܷ���false���ɹ�����true
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
	 * �������ֶμ���ͼ��
	 * @param strkey�����ֶ�
	 * @return ����װ�в�ѯ������б�
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
	 * ��������������ͼ��
	 * @param strkey���������ֶ�
	 * @return ����װ�в�ѯ������б�
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
	 * �����߼���ͼ��
	 * @param strkey���߲����ֶ�
	 * @return ����װ�в�ѯ������б�
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
	 * �����߼���ͼ��
	 * @param strkey���߲����ֶ�
	 * @return ����װ�в�ѯ������б�
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
	 * �����������ͼ��
	 * @param strkey�����粿���ֶ�
	 * @return ����װ�в�ѯ������б�
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
	 * ��ͼ���ż���ͼ��
	 * @param strkeyͼ���Ų����ֶ�
	 * @return ����װ�в�ѯ������б�
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
	 * ��ͼ��������ͼ��
	 * @param strkeyͼ����𲿷��ֶ�
	 * @return ����װ�в�ѯ������б�
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

