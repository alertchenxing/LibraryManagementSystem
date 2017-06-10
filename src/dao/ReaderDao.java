package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import model.ReaderBean;

public class ReaderDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/**
	 * ��Ӷ�����Ϣ
	 * @param rBean ��Ҫ��ӵĶ�����Ϣ
	 * @return ��ӳɹ�����true�����ʧ�ܷ���false
	 */
	public boolean doCreate(ReaderBean rBean) {
		boolean flag = false;
		conn = DBConnection.getConnection();
		String sql="insert into tb_reader(name,sex,age,tel,cardtype,cardnum,maxnum,money,"+
		"photopath)values(?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rBean.getReaderName());
			pstmt.setString(2, rBean.getReaderSex());
			pstmt.setInt(3, rBean.getReaderAge());
			pstmt.setString(4, rBean.getReaderTel());
			pstmt.setString(5, rBean.getCardType());
			pstmt.setString(6, rBean.getCardNum());
			pstmt.setInt(7, rBean.getMaxNum());
			pstmt.setFloat(8, rBean.getMoney());
			pstmt.setString(9, rBean.getPhotoPath());
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
	 * �޸Ķ�����Ϣ
	 * @param rBean ��Ҫ�޸ĵĶ�����Ϣ
	 * @return �޸ĳɹ�����true�����ʧ�ܷ���false
	 */
	public boolean doUpdate(ReaderBean rBean){
		boolean flag = false;
		if (rBean.getPhotoPath() != null) {
			conn = DBConnection.getConnection();
			String sql = "update tb_reader set name=?,sex=?,age=?,tel=?,"+
			"cardtype=?,maxnum=?,money=?,photopath=? where cardnum=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, rBean.getReaderName());
				pstmt.setString(2, rBean.getReaderSex());
				pstmt.setInt(3, rBean.getReaderAge());
				pstmt.setString(4, rBean.getReaderTel());
				pstmt.setString(5, rBean.getCardType());
				pstmt.setInt(6, rBean.getMaxNum());
				pstmt.setFloat(7, rBean.getMoney());
				pstmt.setString(8, rBean.getPhotoPath());
				pstmt.setString(9, rBean.getCardNum());
				if (pstmt.executeUpdate() > 0) {
					flag = true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.closeConnection(pstmt, conn);
			}
		}else if(rBean.getPhotoPath() == null){
			conn = DBConnection.getConnection();
			String sql = "update tb_reader set name=?,sex=?,age=?,tel=?,"+
			"cardtype=?,maxnum=?,money=? where cardnum=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, rBean.getReaderName());
				pstmt.setString(2, rBean.getReaderSex());
				pstmt.setInt(3, rBean.getReaderAge());
				pstmt.setString(4, rBean.getReaderTel());
				pstmt.setString(5, rBean.getCardType());
				pstmt.setInt(6, rBean.getMaxNum());
				pstmt.setFloat(7, rBean.getMoney());
				pstmt.setString(8, rBean.getCardNum());
				if (pstmt.executeUpdate() > 0) {
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
	 * ɾ��������Ϣ
	 * @param cardnum ��Ҫɾ�����ߵ�֤����
	 * @return ɾ���ɹ�����true�����ʧ�ܷ���false
	 */
	public boolean doDelete(String cardnum){
		boolean flag = false;
		conn = DBConnection.getConnection();
		String sql = "delete from tb_reader where cardnum='"+cardnum+"'";
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
	 * ��ѯ���������Ϣ
	 * @return ����װ��������Ϣ���б�
	 */
	public ArrayList<ReaderBean> getAll(){
		ArrayList<ReaderBean> readerList = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql = "select * from tb_reader order by nlssort(name,'NLS_SORT=SCHINESE_PINYIN_M')";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReaderBean rBean = new ReaderBean();
				rBean.setReaderName(rs.getString("name"));
				rBean.setReaderSex(rs.getString("sex"));
				rBean.setReaderAge(rs.getInt("age"));
				rBean.setReaderTel(rs.getString("tel"));
				rBean.setCardType(rs.getString("cardtype"));
				rBean.setCardNum(rs.getString("cardnum"));
				rBean.setMaxNum(rs.getInt("maxnum"));
				rBean.setMoney(rs.getFloat("money"));
				rBean.setPhotoPath(rs.getString("photopath"));
				readerList.add(rBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(rs, pstmt, conn);
		}
		return readerList;
	}
	
	/**
	 * ������ѯ���ߵ���Ϣ
	 * @param strkey ��Ҫ���ҵ�����
	 * @return ���ҳɹ�������װ��������Ϣ���б�
	 */
	public ArrayList<ReaderBean> selectBykey(String strkey){
		ArrayList<ReaderBean> readerList = this.selectByname(strkey);
		if (readerList == null || readerList.size() == 0) {
			readerList = this.selectBynum(strkey);
		}
		return readerList;
	}
	
	/**
	 * ������ѯ���ߵ���Ϣ
	 * @param cardnum ��Ҫ���Ҷ��ߵ�֤����
	 * @return ���ҳɹ�������װ��������Ϣ���б�
	 */
	public ArrayList<ReaderBean> selectBynum(String cardnum){
		ArrayList<ReaderBean> readerList = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql= "select * from tb_reader where cardnum='"+cardnum+"'";
		try {
			pstmt = conn.prepareStatement(sql);
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ReaderBean rBean = new ReaderBean();
					rBean.setReaderName(rs.getString("name"));
					rBean.setReaderSex(rs.getString("sex"));
					rBean.setReaderAge(rs.getInt("age"));
					rBean.setReaderTel(rs.getString("tel"));
					rBean.setCardType(rs.getString("cardtype"));
					rBean.setCardNum(rs.getString("cardnum"));
					rBean.setMaxNum(rs.getInt("maxnum"));
					rBean.setMoney(rs.getFloat("money"));
					rBean.setPhotoPath(rs.getString("photopath"));
					readerList.add(rBean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(rs, pstmt, conn);
		}
		return readerList;
	}
	/**
	 * ������ѯ���ߵ���Ϣ
	 * @param name��Ҫ���Ҷ��ߵ�����
	 * @return ���ҳɹ�������װ��������Ϣ���б�
	 */
	public ArrayList<ReaderBean> selectByname(String name){
		ArrayList<ReaderBean> readerList = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql= "select * from tb_reader where name='"+name+"'";
		try {
			pstmt = conn.prepareStatement(sql);
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ReaderBean rBean = new ReaderBean();
					rBean.setReaderName(rs.getString("name"));
					rBean.setReaderSex(rs.getString("sex"));
					rBean.setReaderAge(rs.getInt("age"));
					rBean.setReaderTel(rs.getString("tel"));
					rBean.setCardType(rs.getString("cardtype"));
					rBean.setCardNum(rs.getString("cardnum"));
					rBean.setMaxNum(rs.getInt("maxnum"));
					rBean.setMoney(rs.getFloat("money"));
					rBean.setPhotoPath(rs.getString("photopath"));
					readerList.add(rBean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(rs, pstmt, conn);
		}
		return readerList;
	}
	/**
	 * ��֤֤���Ŵ治����
	 * @param Ҫ��֤��֤����
	 * @return �û������ڷ���0�����ڷ���1
	 */
	public int checkCardNum(String cardnum){
		conn = DBConnection.getConnection();
		int flag = 0;
		String sql = "select * from tb_reader where cardnum='"+cardnum+"'";
		try {
			pstmt = conn.prepareStatement(sql);
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
