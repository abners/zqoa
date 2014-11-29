package com.login.dao.dao;

import java.util.List;

import com.login.vo.ZqUserModel;
import com.personalManage.vo.ZqGroupModel;

/**
 * 登录模块持久层实现
 * @author peng
 * @since 2013-9-2下午07:20:02
 */
public interface LoginDao {
	public List<ZqUserModel> queryUser(String username,String password);

	/**
	 * 查询用户权限信息
	 * @param groupId 用户组id
	 * @return
	 */
	public ZqGroupModel queryUserGroup(Integer groupId);
}
