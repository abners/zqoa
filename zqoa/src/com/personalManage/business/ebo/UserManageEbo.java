package com.personalManage.business.ebo;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.login.vo.ZqUserModel;
import com.personalManage.business.ebi.UserManageEbi;
import com.personalManage.dao.dao.UserManageDao;
import com.personalManage.vo.ZqDepartmentModel;
import com.personalManage.vo.ZqGroupModel;
import com.util.EcPageHelper;
import com.util.Log4j;
import com.util.MD5Encryption;
import com.util.MethodUtils;
import com.util.PageBean;

/**
 * 用户管理业务层实现
 * 
 * @author peng
 * @since 2013-10-31下午05:12:12
 */
public class UserManageEbo extends EcPageHelper implements UserManageEbi {
	private UserManageDao userManageDao;

	@Override
	public List<ZqGroupModel> getAllUserGroup() {
		// TODO Auto-generated method stub
		return userManageDao.queryAllUserGroup();
	}

	public UserManageDao getUserManageDao() {
		return userManageDao;
	}

	public void setUserManageDao(UserManageDao userManageDao) {
		this.userManageDao = userManageDao;
	}

	@Override
	public ZqGroupModel getGroupPowersById(Integer powerId) {
		// TODO Auto-generated method stub
		return userManageDao.queryPowerById(powerId);
	}

	@Override
	public String saveEditPowers(ZqGroupModel zqGroupModel) {
		// TODO Auto-generated method stub
		if (zqGroupModel.getPower() != null) {
			// 去除权限集合中间和首尾的空格
			zqGroupModel.setPower(zqGroupModel.getPower().replaceAll(" ", "")
					.trim());
		}

		try {
			userManageDao.saveEditPower(zqGroupModel);
		} catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
			return "0";
		}

		return "1";
	}

	@Override
	public String addUserGroup(ZqGroupModel zqGroupModel) {
		// TODO Auto-generated method stub
		if (zqGroupModel.getGroupName() != null) {
			//检测是否已存在相同名称的用户组
			if (!userManageDao.checkGroupNameExist(zqGroupModel.getGroupName())) {
				try {
					userManageDao.saveUserGroup(zqGroupModel);
				} catch (HibernateException e) {
					// TODO: handle exception
					Log4j.errorLog(this, e);
					return "0";
				}
			}else{
				//存在同名用户组
				return "2";
			}
		}

		return "1";
	}

	@Override
	public String deltUserGroupById(Integer powerId) {
		// TODO Auto-generated method stub
		String result = "0";
		if (userManageDao.checkUserGroupifInUse(powerId) == 0) {
			try {
				userManageDao.deltUserGroupById(powerId);
				result = "1";
			} catch (HibernateException e) {
				// TODO: handle exception
				Log4j.errorLog(this, e);
			}
		}else {
			result = "2";
		}
		return result;
	}

	@Override
	public String addUser(ZqUserModel zqUserModel) {
		// TODO Auto-generated method stub
		String result = "0";
		//用户是当前
		zqUserModel.setIsCurrent("1");
		//是否为超级管理员
		if(zqUserModel.getGroupId()==1){
			zqUserModel.setIsManage("1");
		}else {
			zqUserModel.setIsManage("0");
		}
		try{
			if(userManageDao.checkUserNameCount(zqUserModel.getName())==0){
				//对用户的密码进行MD5 32位加密
				zqUserModel.setPassword(MD5Encryption.getMD5(zqUserModel.getPassword()));
				
				userManageDao.saveUserGroup(zqUserModel);
				result = "1";
			}else {
				result = "2";
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}
		return result;
	}

	@Override
	public PageBean getUserList(Map conditionMap) {
		// TODO Auto-generated method stub
		PageBean pageBean = getPageBean(conditionMap);
		
		conditionMap.put("pageBean", pageBean);
		
		return userManageDao.queryUserList(conditionMap);
	}

	@Override
	public String closeAccountById(Integer userId) {
		// TODO Auto-generated method stub
		String result = "0";
		try{
			if (userId != null) {
				// 将isCurrent置为0： 历史
				userManageDao.updateUserAccountState(userId, "0");
				result = "1";
			}
		}catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
			
		}
		return result;
	}

	@Override
	public String openAccountById(Integer userId) {
		// TODO Auto-generated method stub
		String result = "0";
		try{
			if (userId != null) {
				// 将isCurrent置为0： 历史
				userManageDao.updateUserAccountState(userId, "1");
				result = "1";
			}
		}catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
			
		}
		return result;
	}

	@Override
	public ZqUserModel getUserDetailById(Integer userId) {
		// TODO Auto-generated method stub
		return userManageDao.queryUserMessById(userId);
	}

	@Override
	public List<ZqDepartmentModel> getAllDepartment() {
		// TODO Auto-generated method stub
		return userManageDao.queryAllDepartment();
	}

	@Override
	public String addDepart(ZqDepartmentModel zqDepartmentModel) {
		// TODO Auto-generated method stub
		String result = "0";
		try{
			if(userManageDao.queryDepartByName(zqDepartmentModel.getDepName())==0){
				userManageDao.saveDepart(zqDepartmentModel);
				result = "1";
			}else {
				result = "2";
			}
		}catch (Exception e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}
		return result;
	}

	@Override
	public String modifyDepart(ZqDepartmentModel zqDepartmentModel) {
		// TODO Auto-generated method stub
		String result = "0";
		try{
			if(userManageDao.queryDepartByName(zqDepartmentModel.getDepName())==0){
				userManageDao.updateDepart(zqDepartmentModel);
				result = "1";
			}else {
				result = "2";
			}
		}catch (Exception e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}
		return result;
	}

	@Override
	public String deltDepartById(Integer deptId) {
		// TODO Auto-generated method stub
		String result = "0";
		try{
			if(userManageDao.queryUserByDeptId(deptId)==0){
				userManageDao.deleteDepartById(deptId);
				result = "1";
			}else {
				result = "2";
			}
		}catch (Exception e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}
		return result;
	}

	@Override
	public String modifyUserMess(ZqUserModel zqUserModel) {
		// TODO Auto-generated method stub
		String result = "0";
		try{
			
			userManageDao.updateUserMess(zqUserModel);
			result = "1";
		}catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}
		return result;
	}

	@Override
	public String updateUserPass(ZqUserModel zqUserModel) {
		// TODO Auto-generated method stub
		String result = "0";
		try{
			userManageDao.updateUserPass(zqUserModel);
			result = "1";
		}catch (Exception e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}
		return result;
	}
	
	

}
