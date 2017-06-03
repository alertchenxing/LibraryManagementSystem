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
	 * 验证用户名和密码
	 * @param username 用户名
	 * @param password 登陆密码
	 * @return 用户不存在返回0，密码错误返回1，登陆成功返回大于1的数，2表示为普通用户，1表示管理员用户
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
	 * 添加用户
	 * @param aBean存放账户信息的javabean
	 * @return 添加成功返回true，添加失败返回false
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
	 * 验证用户名
	 * @param username 用户名
	 * @return 用户不存在返回false，存在返回true
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
	 * 修改用户
	 * @param username 所要修改的用户
	 * @param password 修改之后的密码
	 * @param flag1 修改之后的权限 
	 * @return 修改失败返回false，修改成功返回true
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
	 * 删除用户
	 * @param username 所要删除的用户
	 * @return 删除失败返回false，删除成功返回true
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
