package common;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:DBDAO";
	private static final String USERNAME = "LMS";
	private static final String PASSWORD = "LMS";
	
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Oracle数据库驱动加载失败！");
		}
	}
	/**
     * 获取Connection
     * @return 返回数据库连接
     * @throws SQLException
     */
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Oracle数据库连接失败！");
		}
		return conn;
	}
	/**
     * 关闭ResultSet
     * @param rs
     */
	public static void closeResultSet(ResultSet rs){
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	/**
     * 关闭Statement
     * @param stmt
     */
	public static void closeStatement(Statement stmt){
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	/**
     * 关闭ResultSet、Statement
     * @param rs
     * @param stmt
     */
	public static void closeStatement(ResultSet rs, Statement stmt){
		closeResultSet(rs);
		closeStatement(stmt);
	}
	/**
    * 关闭PreparedStatement
    * @param pstmt
    */
	public static void closePreparedStatement(PreparedStatement pstmt){
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	/**
     * 关闭ResultSet、PreparedStatement
     * @param rs
     * @param pstmt
     */
	public static void closePreparedStatement(PreparedStatement pstmt, ResultSet rs){
		closeResultSet(rs);
		closePreparedStatement(pstmt);
	}
	/**
     * 关闭Connection
     * @param conn
     */
	public static void closeConnection(Connection conn){
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	/**
    * 关闭Statement、Connection
    * @param stmt
    * @param conn
    */
	public static void closeConnection(Statement stmt, Connection conn){
		closeConnection(conn);
		closeStatement(stmt);
	}
	/**
	    * 关闭PreparedStatement、Connection
	    * @param pstmt
	    * @param conn
	    */
	public static void closeConnection(PreparedStatement pstmt, Connection conn){
		closePreparedStatement(pstmt);
		closeConnection(conn);
	}
	/**
     * 关闭ResultSet、Statement、Connection
     * @param rs
     * @param stmt
     * @param conn
     */
	public static void closeConnection(ResultSet rs, Statement stmt, Connection conn){
		closeResultSet(rs);
		closeConnection(conn);
		closeStatement(stmt);
	}
	/**
     * 关闭ResultSet、PreparedStatement、Connection
     * @param rs
     * @param pstmt
     * @param conn
     */
	public static void closeConnection(ResultSet rs, PreparedStatement pstmt, Connection conn){
		closeResultSet(rs);
		closePreparedStatement(pstmt);
		closeConnection(conn);
	}
}
