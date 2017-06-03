package service;

import java.sql.SQLException;

import common.DBConnection;
import dao.AdminDao;
import model.AdminBean;

public class AdminService {
	AdminDao aDao = new AdminDao();
	
	/**
	 * 用户登陆
	 * @param username 用户名
	 * @param password 登陆密码
	 * @return 用户不存在返回0，密码错误返回1，登陆成功返回2
	 */
	public int login(String username, String password) {
		int flag = aDao.checkAdmin(username, password);
		return flag;
	}
	/**
	 * 添加用户
	 * @param aBean存放账户信息的javabean
	 * @return 添加成功返回true，添加失败返回false
	 */
	public boolean inset(AdminBean aBean) {
		return aDao.inset(aBean);
		
	}
	/**
	 * 验证用户名
	 * @param username 用户名
	 * @return 用户不存在返回false，存在返回true
	 */
	public boolean check(String username) {
		return aDao.check(username);
	}
	/**
	 * 修改用户
	 * @param username 所要修改的用户
	 * @param password 修改之后的密码
	 * @param flag1 修改之后的权限 
	 * @return 修改失败返回false，修改成功返回true
	 */
	public boolean update(String username, String password, int flag) {
		return aDao.update(username, password,flag);
	}
	/**
	 * 删除用户
	 * @param username 所要删除的用户
	 * @return 删除失败返回false，删除成功返回true
	 */
	public boolean delete(String username) {
		
		return aDao.delete(username);
	}
}
