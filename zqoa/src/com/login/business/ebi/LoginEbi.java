package com.login.business.ebi;

/**
 * 登录模块业务逻辑层
 * @author peng
 * @since 2013-9-2下午07:17:06
 */
public interface LoginEbi {
	/**
	 * 登录检测
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean checkLogin(String username,String password);
}
