package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.DBConnection;
import model.AdminBean;

public class AdminDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/**
	 * ��֤�û���������
	 * @param username �û���
	 * @param password ��½����
	 * @return �û������ڷ���0��������󷵻�1����½�ɹ����ش���1������2��ʾΪ��ͨ�û���1��ʾ����Ա�û�
	 */
	public int checkAdmin(String username, String password){
		conn = DBConnection.getConnection();
		int flag = 0;
		String sql = "select * from tb_admin where username='"+username+"'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (username.equals(rs.getString("username"))) {
					flag = 1;
					if (password.equals(rs.getString("password"))) {
						int flag1 = rs.getInt("flag");
						if (flag1 == 0) {
							flag = 2;
						}else if (flag1 == 1) {
							flag = 3;
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(rs, pstmt, conn);
		}
		return flag;
	}

	/**
	 * ����û�
	 * @param aBean����˻���Ϣ��javabean
	 * @return ��ӳɹ�����true�����ʧ�ܷ���false
	 */
	public boolean inset(AdminBean aBean) {
		boolean flag = false;
		conn = DBConnection.getConnection();
		String sql = "insert into tb_admin(username,password,flag) values(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aBean.getUsername());
			pstmt.setString(2, aBean.getPassword());
			pstmt.setInt(3, aBean.getFlag());
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
	 * ��֤�û���
	 * @param username �û���
	 * @return �û������ڷ���false�����ڷ���true
	 */
	public boolean check(String username) {
		boolean flag = false;
		conn = DBConnection.getConnection();
		String sql = "select * from tb_admin where username='"+username+"'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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
	 * �޸��û�
	 * @param username ��Ҫ�޸ĵ��û�
	 * @param password �޸�֮�������
	 * @param flag1 �޸�֮���Ȩ�� 
	 * @return �޸�ʧ�ܷ���false���޸ĳɹ�����true
	 */
	public boolean update(String username, String password,int flag1) {
		boolean flag = false;
		conn = DBConnection.getConnection();
		String sql = "update tb_admin set password='"+password+"',flag='"+flag1+"' where username='"+username+"'";
		System.out.println(username+password);
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
	 * ɾ���û�
	 * @param username ��Ҫɾ�����û�
	 * @return ɾ��ʧ�ܷ���false��ɾ���ɹ�����true
	 */
	public boolean delete(String username) {
		boolean flag = false;
		conn = DBConnection.getConnection();
		String sql = "delete from tb_admin where username='"+username+"'";
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
}
