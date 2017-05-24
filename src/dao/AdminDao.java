package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.DBConnection;

public class AdminDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/**
	 * ��֤�û���������
	 * @param username �û���
	 * @param password ��½����
	 * @return �û������ڷ���0��������󷵻�1����½�ɹ�����2
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
						flag = 2;
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
}
