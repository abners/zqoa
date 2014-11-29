package com.login.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.json.annotations.JSON;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.login.business.ebi.LoginEbi;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.util.JSONUtil;
import com.util.Log4j;

/**
 * 系统登录控制类
 * 
 * @author peng
 * @since 2013-9-1下午09:21:45
 */
public class LoginAction extends ActionSupport {
	// 登录账号
	private String account;
	// 密码
	private String password;

	// 登录结果
	private String result;
	private LoginEbi loginEbi;

	public String execute() throws Exception{
		try {
			Log4j.logMess("用户:" + account + "尝试登录...");
			if(account == null || password == null){
				throw new Exception("异常登录...");
			}
			if (loginEbi.checkLogin(account, password)) {
				result = "1";
				Log4j.logMess("用户:" + account + "登录成功...");
			} else {
				result = "0";
				Log4j.logMess("用户:" + account + "登录失败...");
			}
			
		} catch (Exception e) {
			Log4j.logMess(e.getMessage());
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@JSON(serialize = false)
	public LoginEbi getLoginEbi() {
		return loginEbi;
	}

	public void setLoginEbi(LoginEbi loginEbi) {
		this.loginEbi = loginEbi;
	}

	@JSON(serialize = false)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@JSON(name = "result")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@JSON(serialize = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
