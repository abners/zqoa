package com.login.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.login.dao.dao.LoginDao;
import com.login.vo.ZqUserModel;
import com.personalManage.vo.ZqGroupModel;

/**
 * 登录模块持久层实现
 * 
 * @author peng
 * @since 2013-9-2下午08:05:37
 */
public class LoginDaoImpl extends HibernateDaoSupport implements LoginDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.login.dao.dao.LoginDao#queryUser(java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ZqUserModel> queryUser(String username, String password) {
		// TODO Auto-generated method stub
		// String当前用户
		return getHibernateTemplate()
				.find("from ZqUserModel where name=? and password=? and isCurrent='1' ",
						new Object[] {username,password});
	}

	@Override
	public ZqGroupModel queryUserGroup(Integer groupId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(ZqGroupModel.class, groupId);
	}
	
}
