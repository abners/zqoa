package com.personalManage.dao.dao;

import java.util.List;
import java.util.Map;

import com.login.vo.ZqUserModel;
import com.personalManage.vo.ZqDepartmentModel;
import com.personalManage.vo.ZqGroupModel;
import com.util.PageBean;

/**
 * 用户管理持久层接口
 * @author peng
 * @since 2013-10-31下午05:12:54
 */
public interface UserManageDao {

	public static final String SELECT_USERGROUP = "from ZqGroupModel";
	/**
	 * 统计用户数目
	 */
	public static final String SELECT_USER_COUNT = "select count(*) from ZqUserModel";
	/**
	 * 查询用户信息
	 */
	public static final String SELECT_USER = "select new ZqUserModel(a.id,a.name,a.email,a.isCurrent,b.groupName)from ZqUserModel a,ZqGroupModel b where a.groupId=b.id  ";
	/**
	 * 获取所有的用户组信息
	 * @return
	 */
	List<ZqGroupModel> queryAllUserGroup();
	/**
	 * 根据id查询用户组信息
	 * @param powerId
	 * @return
	 */
	ZqGroupModel queryPowerById(Integer powerId);
	/**
	 * 保存权限集合修改结果
	 * @param zqGroupModel
	 */
	void saveEditPower(ZqGroupModel zqGroupModel);
	/**
	 * 添加用户组
	 * @param zqGroupModel
	 */
	void saveUserGroup(ZqGroupModel zqGroupModel);
	/**
	 * 检测是否已存在同名用户组
	 * @param groupName 用户组名称
	 * @return false：不存在 true：已存在
	 */
	boolean checkGroupNameExist(String groupName);
	/**
	 * 统计使用该用户组的用户组数目
	 * @param powerId
	 * @return
	 */
	int checkUserGroupifInUse(Integer powerId);
	/**
	 * 删除用户组
	 * @param powerId
	 */
	void deltUserGroupById(Integer powerId);
	/**
	 * 统计使用该用户名的用户数目
	 * @param name 用户名
	 * @return
	 */
	int checkUserNameCount(String name);
	/**
	 * 保存用户信息
	 * @param zqUserModel
	 */
	void saveUserGroup(ZqUserModel zqUserModel);
	/**
	 * 分页查询用户信息
	 * @param conditionMap
	 * @return
	 */
	PageBean queryUserList(Map conditionMap);
	/**
	 * 更新用户账户状态
	 * @param userId
	 * @param isCurrent 是否当前0：当前 1：历史
	 */
	void updateUserAccountState(Integer userId, String isCurrent);
	/**
	 * 查询用户详细信息
	 * @param userId
	 * @return
	 */
	ZqUserModel queryUserMessById(Integer userId);
	List<ZqDepartmentModel> queryAllDepartment();
	/**
	 * 根据部门名称获取部门数量
	 * @param depName 部门名称
	 * @return
	 * @throws Exception 
	 */
	int queryDepartByName(String depName) throws Exception;
	/**
	 * 保存部门信息
	 * @param zqDepartmentModel
	 */
	void saveDepart(ZqDepartmentModel zqDepartmentModel);
	void updateDepart(ZqDepartmentModel zqDepartmentModel);
	/**
	 * 查询属于该部门的用户数目
	 * @param deptId
	 * @return
	 */
	int queryUserByDeptId(Integer deptId);
	/**
	 * 删除部门信息
	 * @param deptId
	 * @throws Exception 
	 */
	void deleteDepartById(Integer deptId) throws Exception;
	/**
	 * 更新用户信息
	 * @param zqUserModel
	 */
	void updateUserMess(ZqUserModel zqUserModel);
	/**
	 * 更新用户密码
	 * @param zqUserModel
	 * @throws Exception 
	 */
	void updateUserPass(ZqUserModel zqUserModel) throws Exception;

}
