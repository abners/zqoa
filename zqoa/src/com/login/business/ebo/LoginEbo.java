package com.login.business.ebo;

import java.util.List;

import com.login.business.ebi.LoginEbi;
import com.login.dao.dao.LoginDao;
import com.login.vo.ZqUserModel;
import com.opensymphony.xwork2.ActionContext;
import com.personalManage.vo.ZqGroupModel;
import com.util.MD5Encryption;

/**
 * 登录模块业务逻辑层实现
 * @author peng
 * @since 2013-9-2下午07:19:10
 */
public class LoginEbo implements LoginEbi {
	private LoginDao loginDao;
	/* (non-Javadoc)
	 * @see com.login.business.ebi.LoginEbi#checkLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean checkLogin(String username, String password) {
		// TODO Auto-generated method stub
		//对获取的密码进行32位加密
		String passMd5 = MD5Encryption.getMD5(password);
		//获取dao层查询结果
		List<ZqUserModel> userModels = loginDao.queryUser(username, passMd5);
		//用户名或密码有误
		if(userModels==null || userModels.size()==0){
			return false;
		}else{
			ZqUserModel userModel = userModels.get(0);
			//获取用户权限信息
			ZqGroupModel zqGroupModel = loginDao.queryUserGroup(userModel.getGroupId());
			//将用户权限集合放入用户信息中
			userModel.setPowers(zqGroupModel.getPower());
			//将用户信息放入session中
			ActionContext.getContext().getSession().put("user",userModel);
		}
		return true;
	}
	public LoginDao getLoginDao() {
		return loginDao;
	}
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	
}
