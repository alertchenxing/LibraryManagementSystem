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
	 * 验证用户名和密码
	 * @param username 用户名
	 * @param password 登陆密码
	 * @return 用户不存在返回0，密码错误返回1，登陆成功返回2
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
