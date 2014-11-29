package com.personalManage.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.contManage.vo.ZqContractModel;
import com.login.vo.ZqUserModel;
import com.login.vo.ZqUserinfoModel;
import com.personalManage.dao.dao.UserManageDao;
import com.personalManage.vo.ZqDepartmentModel;
import com.personalManage.vo.ZqGroupModel;
import com.util.ExtremeTablePage;
import com.util.Log4j;
import com.util.PageBean;

/**
 * 用户管理持久层实现
 * @author peng
 * @since 2013-10-31下午05:13:32
 */
public class UserManageDaoImpl extends HibernateDaoSupport implements UserManageDao {

	

	@Override
	public List<ZqGroupModel> queryAllUserGroup() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(SELECT_USERGROUP);
	}

	@Override
	public ZqGroupModel queryPowerById(Integer powerId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(ZqGroupModel.class, powerId);
	}

	@Override
	public void saveEditPower(ZqGroupModel zqGroupModel) throws HibernateException{
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			session.update(zqGroupModel);
			
			transaction.commit();
		}catch (HibernateException e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public void saveUserGroup(ZqGroupModel zqGroupModel) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			session.save(zqGroupModel);
			
			transaction.commit();
		}catch (HibernateException e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public boolean checkGroupNameExist(String groupName) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from ZqGroupModel where groupName=?";
		try{
			return ((Long)getHibernateTemplate().find(hql,groupName).iterator().next()).intValue()==0?false:true;
		}catch (Exception e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}
		return true;
	}

	@Override
	public int checkUserGroupifInUse(Integer powerId) {
		// TODO Auto-generated method stub
		return ((Long)getHibernateTemplate().find("select count(*) from ZqUserModel where groupId=?",powerId).iterator().next()).intValue();
	}

	@Override
	public void deltUserGroupById(Integer powerId) throws HibernateException{
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			session.createQuery("delete from ZqGroupModel where id=?").setInteger(0, powerId).executeUpdate();
			
			transaction.commit();
		}catch (HibernateException e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public int checkUserNameCount(String name) {
		// TODO Auto-generated method stub
		return ((Long)getHibernateTemplate().find("select count(*) from ZqUserModel where name=?",name).iterator().next()).intValue();
	}

	@Override
	public void saveUserGroup(ZqUserModel zqUserModel) throws HibernateException{
		// TODO Auto-generated method stub
		saveObject(zqUserModel);
	}

	@Override
	public PageBean queryUserList(Map conditionMap) {
		// TODO Auto-generated method stub
		HibernateTemplate dao = getHibernateTemplate();
		
		PageBean pageBean = (PageBean) conditionMap.get("pageBean");
		// 获取总记录数目
		int totalRows = pageBean.getTotalRows(dao, SELECT_USER_COUNT,
				null);

		// 每页记录数
		final int length = pageBean.getPageSize();
		@SuppressWarnings("static-access")
		final int offset = pageBean.countOffset(length, pageBean.getPage());

		@SuppressWarnings("unchecked")
		List<ZqUserModel> list = ExtremeTablePage.queryListByPage(
				offset, length, SELECT_USER, null, dao);
		pageBean.setList(list);

		return pageBean;
	}

	@Override
	public void updateUserAccountState(Integer userId, String isCurrent) throws HibernateException{
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.createQuery(
					"update ZqUserModel set isCurrent=:isCurrent where id=:id")
					.setInteger("id", userId).setString("isCurrent", isCurrent)
					.executeUpdate();
			transaction.commit();
		}catch (HibernateException e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public ZqUserModel queryUserMessById(Integer userId) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		try {
			// 用户信息
			ZqUserModel zqUserModel = (ZqUserModel) session
					.createQuery(
							"select new ZqUserModel(a.id,a.name,a.groupId,b.groupName,a.email) " +
							"		from ZqUserModel a,ZqGroupModel b where a.groupId=b.id and a.id=?")
					.setInteger(0, userId).uniqueResult();
			// 用户身份信息
			ZqUserinfoModel zqUserinfoModel = (ZqUserinfoModel) session.get(
					ZqUserinfoModel.class, userId);

			zqUserModel.setZqUserinfoModel(zqUserinfoModel);
			return zqUserModel;
		} catch (Exception e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}finally{
			session.close();
		}
		return null;
	}

	@Override
	public List<ZqDepartmentModel> queryAllDepartment() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from ZqDepartmentModel ");
	}

	@Override
	public int queryDepartByName(String depName) throws Exception{
		// TODO Auto-generated method stub
		return ((Long)getHibernateTemplate().find("select count(*) from ZqDepartmentModel where depName=?",depName).iterator().next()).intValue();
	}

	@Override
	public void saveDepart(ZqDepartmentModel zqDepartmentModel) throws HibernateException{
		// TODO Auto-generated method stub
		saveObject(zqDepartmentModel);
	}
	/**
	 * 持久化实体对象到数据库,更新或新增
	 * @param object
	 * @throws HibernateException
	 */
	private void saveObject(Object object)throws HibernateException{
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.saveOrUpdate(object);
			transaction.commit();
		}catch (HibernateException e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public void updateDepart(ZqDepartmentModel zqDepartmentModel) throws HibernateException{
		// TODO Auto-generated method stub
		saveObject(zqDepartmentModel);
	}

	@Override
	public int queryUserByDeptId(Integer deptId) {
		// TODO Auto-generated method stub
		return ((Long)getHibernateTemplate().find("select count(*) from ZqUserinfoModel where zqDepartment.deptId=?",deptId).iterator().next()).intValue();
	}

	@Override
	public void deleteDepartById(Integer deptId) throws Exception {
		// TODO Auto-generated method stub
		updateObject("delete from ZqDepartmentModel where deptId=?", new Object[]{deptId});
	}

	/**
	 * 更新记录
	 * @param hql sql
	 * @param objects 条件数组
	 * @throws Exception 
	 */
	private void updateObject(String hql,Object[] objects) throws Exception{
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			Query query = session.createQuery(hql);
			int i = 0;
			for(Object object:objects){
				if(object instanceof String){
					query.setString(i, (String)object);
				}else if(object instanceof Integer){
					query.setInteger(i, (Integer)object);
				}else {
					throw new Exception("无法转换的数据类型:"+object.getClass());
				}
				i++;
			}
			query.executeUpdate();
			//commit the transaction
			transaction.commit();
			
		}catch (HibernateException e) {
			// TODO: handle exception
			if(transaction != null)
				transaction.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public void updateUserMess(ZqUserModel zqUserModel) throws HibernateException{
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		//用户登录信息
		ZqUserModel userModel = (ZqUserModel) session.get(ZqUserModel.class, zqUserModel.getId());
		userModel.setName(zqUserModel.getName());
		userModel.setGroupId(zqUserModel.getGroupId());
		userModel.setEmail(zqUserModel.getEmail());
		try{
			transaction = session.beginTransaction();
			Integer uid = zqUserModel.getZqUserinfoModel().getUid();
			//首次对用户信息进行修改
			if(uid==null||uid==0){
				//用户详细信息
				ZqUserinfoModel zqUserinfoModel = zqUserModel.getZqUserinfoModel();
				zqUserinfoModel.setUid(zqUserModel.getId());
				session.save(zqUserinfoModel);
			}else {
				session.update(zqUserModel.getZqUserinfoModel());
			}
			
			session.update(userModel);
			
			transaction.commit();
		}catch (HibernateException e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public void updateUserPass(ZqUserModel zqUserModel) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			updateObject("update ZqUserModel set password=? where id=?", new Object[]{zqUserModel.getPassword(),zqUserModel.getId()});
		}catch (Exception e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			throw e;
		}finally{
			session.close();
		}
	}
	
}
