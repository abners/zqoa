package com.personalManage.business.ebi;

import java.util.List;
import java.util.Map;

import com.login.vo.ZqUserModel;
import com.personalManage.vo.ZqDepartmentModel;
import com.personalManage.vo.ZqGroupModel;
import com.util.PageBean;

/**
 * 用户管理业务层接口
 * @author peng
 * @since 2013-10-31下午05:11:23
 */
public interface UserManageEbi {

	/**
	 * 获取所有用户组，超级管理员除外
	 * @return
	 */
	List<ZqGroupModel> getAllUserGroup();

	/**
	 * 根据id获取权限集合
	 * @param powerId
	 * @return
	 */
	ZqGroupModel getGroupPowersById(Integer powerId);

	/**
	 * 保存权限集合修改结果
	 * @param zqGroupModel
	 * @return
	 */
	String saveEditPowers(ZqGroupModel zqGroupModel);

	/**
	 * 添加用户组
	 * @param zqGroupModel
	 * @return 0:添加失败 1：添加成功 2：存在同名用户组
	 */
	String addUserGroup(ZqGroupModel zqGroupModel);

	/** 
	 * 删除用户组
	 * @param powerId
	 * @return 0:删除失败 1：删除成功 2：用户组已被使用
	 */
	String deltUserGroupById(Integer powerId);

	/**
	 * 添加用户
	 * @param zqUserModel
	 * @return 1:添加成功 0：添加失败 2：存在同名用户
	 */
	String addUser(ZqUserModel zqUserModel);

	/**
	 * 分页获取用户信息
	 * @param conditionMap
	 * @return
	 */
	PageBean getUserList(Map conditionMap);

	/**
	 * 关闭账户
	 * @param userId
	 * @return
	 */
	String closeAccountById(Integer userId);

	/**
	 * 启用账户
	 * @param userId
	 * @return
	 */
	String openAccountById(Integer userId);

	/**
	 * 获取用户详细信息
	 * @param userId
	 * @return
	 */
	ZqUserModel getUserDetailById(Integer userId);

	List<ZqDepartmentModel> getAllDepartment();

	/**
	 * 添加部门
	 * @param zqDepartmentModel
	 * @return 1成功 0失败 2部门名称重复
	 */
	String addDepart(ZqDepartmentModel zqDepartmentModel);

	/**
	 * 修改部门信息
	 * @param zqDepartmentModel
	 * @return
	 */
	String modifyDepart(ZqDepartmentModel zqDepartmentModel);

	/**
	 * 删除部门
	 * @param deptId 部门id
	 * @return 0:失败 1：成功 2：部门已被使用
	 */
	String deltDepartById(Integer deptId);

	/**
	 * 保存用户信息修改
	 * @param zqUserModel
	 * @return
	 */
	String modifyUserMess(ZqUserModel zqUserModel);

	/**
	 * 保存修改的密码
	 * @param zqUserModel
	 * @return
	 */
	String updateUserPass(ZqUserModel zqUserModel);

}
