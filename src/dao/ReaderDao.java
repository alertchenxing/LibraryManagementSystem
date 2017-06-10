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
	 * 添加读者信息
	 * @param rBean 所要添加的读者信息
	 * @return 添加成功返回true，添加失败返回false
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
	 * 修改读者信息
	 * @param rBean 所要修改的读者信息
	 * @return 修改成功返回true，添加失败返回false
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
	 * 删除读者信息
	 * @param cardnum 所要删除读者的证件号
	 * @return 删除成功返回true，添加失败返回false
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
	 * 查询表的所有信息
	 * @return 返回装有所有信息的列表
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
	 * 条件查询读者的信息
	 * @param strkey 所要查找的条件
	 * @return 查找成功返返回装有所有信息的列表
	 */
	public ArrayList<ReaderBean> selectBykey(String strkey){
		ArrayList<ReaderBean> readerList = this.selectByname(strkey);
		if (readerList == null || readerList.size() == 0) {
			readerList = this.selectBynum(strkey);
		}
		return readerList;
	}
	
	/**
	 * 条件查询读者的信息
	 * @param cardnum 所要查找读者的证件号
	 * @return 查找成功返返回装有所有信息的列表
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
	 * 条件查询读者的信息
	 * @param name所要查找读者的姓名
	 * @return 查找成功返返回装有所有信息的列表
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
	 * 验证证件号存不存在
	 * @param 要验证的证件号
	 * @return 用户不存在返回0，存在返回1
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
