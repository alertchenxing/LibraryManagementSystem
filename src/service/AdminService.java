package service;

import dao.AdminDao;

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
}
