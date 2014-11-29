package com.executiveManage.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.caseManage.vo.ZqCaseModel;
import com.caseManage.vo.ZqCaseprocessModel;
import com.contManage.vo.ZqContractModel;
import com.executiveManage.dao.dao.ExecutiveDao;
import com.executiveManage.vo.IndexModel;
import com.executiveManage.vo.ZqAsrModel;
import com.executiveManage.vo.ZqNoticeModel;
import com.executiveManage.vo.ZqSerrecordModel;
import com.login.vo.ZqUserModel;
import com.personalManage.vo.ZqDepartmentModel;
import com.util.Log4j;
import com.util.MethodUtils;
/**
 *
 * @author peng
 * @since 2013-11-10上午11:37:18
 */
public class ExecutiveDaoImpl extends HibernateDaoSupport implements ExecutiveDao {

	@Override
	public List<ZqCaseprocessModel> queryProcessByUserId(Integer id) {
		// TODO Auto-generated method stub
		String sql = " select a from ZqCaseprocessModel a,ZqCaseModel b where a.caseId=b.id and a.isTimeRemender=? and a.remTime<='"+MethodUtils.getToDayDate("yyyy-MM-DD")+"' and " +
				     " (b.creater=? or b.lawyer=? or b.id in(select caseId from ZqContractcoscusModel where lawyerId=?))";
		
		return getHibernateTemplate().find(sql,new Object[]{"1",id,id,id});
		
	}

	@Override
	public IndexModel queryAllThings(ZqUserModel zqUserModel) {
		// TODO Auto-generated method stub
		String isManage = zqUserModel.getIsManage();
		Session session = this.getSession();
		String hql = "";
		IndexModel indexModel = new IndexModel();
		//
		try{
			//当前登录用户为管理员
			if("1".equals(isManage)){
				//查询当前所有未结案件，并按时间倒序排列
				hql = "select new ZqCaseModel(a.id,a.number,a.caseName,b.typeName,c.name,a.contId) from ZqCaseModel a,ZqCaseTypeModel b,ZqUserModel c " +
					  "     where a.lawyer=c.id and a.typeId=b.typeId and a.status=? order by a.createTime desc";
				List<ZqCaseModel> zqCaseModels = session.createQuery(hql).setInteger(0, 0).list();
				indexModel.setZqNoOverCaseModels(zqCaseModels);
				
				//查询当前所有未完结的合同
				hql ="select new ZqContractModel(a.id,a.number,a.contName,b.contractTypeName,c.name,d.name) " +
					 "           from ZqContractModel a,ZqContracttypeModel b,ZqCustomerModel c,ZqUserModel d " +
					 "			 where a.typeId=b.id and a.custId=c.id and d.id=a.lawyer and a.archived=? order by a.createTime desc";
				List<ZqContractModel> zqContractModels = session.createQuery(hql).setString(0, "0").list();
				indexModel.setZqNoOverContractModels(zqContractModels);
			}else{
				Integer userId = zqUserModel.getId();
				//登录用户为其它用户，获取其主办或协办的未结案件
				hql = "select new ZqCaseModel(a.id,a.number,a.caseName,b.typeName,c.name,a.contId) from ZqCaseModel a,ZqCaseTypeModel b,ZqUserModel c " +
				  "     where a.lawyer=c.id and a.typeId=b.typeId and a.status=? and (a.creater=? or a.lawyer=? or a.id in(select caseId from ZqContractcoscusModel where lawyerId=? ))  order by a.createTime desc";
				List<ZqCaseModel> zqCaseModels = session.createQuery(hql).setInteger(0, 0).setInteger(1,userId).setInteger(2, userId).setInteger(3, userId).list();
				indexModel.setZqNoOverCaseModels(zqCaseModels);
				//获取其主办或协办的未结合同
				hql ="select new ZqContractModel(a.id,a.number,a.contName,b.contractTypeName,c.name,d.name) " +
				 "           from ZqContractModel a,ZqContracttypeModel b,ZqCustomerModel c,ZqUserModel d " +
				 "			 where a.typeId=b.id and a.custId=c.id and d.id=a.lawyer and a.archived=? and (a.creater=? or a.lawyer=? or a.id in(select caseId from ZqContractcoscusModel where lawyerId=? )) order by a.createTime desc";
				List<ZqContractModel> contractModels = session.createQuery(hql).setString(0, "0").setInteger(1,userId).setInteger(2, userId).setInteger(3, userId).list();
				indexModel.setZqNoOverContractModels(contractModels);
				
			}
		}catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
			throw e;
			
		}finally{
			session.close();
		}
		return indexModel;
	}

	@Override
	public List<ZqDepartmentModel> queryAllDepartment() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from ZqDepartmentModel");
	}

	@Override
	public void saveAsr(ZqAsrModel zqAsrModel) throws HibernateException{
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.save(zqAsrModel);
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
	public List<ZqAsrModel> queryAllAsr() {
		// TODO Auto-generated method stub
		String hql = "select new ZqAsrModel(a.id,a.name,a.author,a.createTime,b.name) from ZqAsrModel a,ZqUserModel b where a.author=b.id ";
		ZqUserModel zqUserModel =MethodUtils.getUserInfoModel();
		if("1".equals(zqUserModel.getIsManage())){
			return getHibernateTemplate().find(hql);
		}else{
			return getHibernateTemplate().find(hql+" and a.author=?",zqUserModel.getId());
		}
	}

	@Override
	public ZqAsrModel queryAsrById(Integer asrId) {
		// TODO Auto-generated method stub
		Session  session = this.getSession();
		try{
			return (ZqAsrModel) session.createQuery(" select new ZqAsrModel(a.id,a.name,a.author,a.createTime,b.name,c.depName,a.content,a.deptId) " +
					                                " from ZqAsrModel a,ZqUserModel b,ZqDepartmentModel c where a.author=b.id and a.deptId=c.id and a.id=? "
					                                ).setInteger(0, asrId).uniqueResult();
		}finally{
			session.close();
		}	 
	}

	@Override
	public void updateAsr(ZqAsrModel zqAsrModel) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			session.update(zqAsrModel);
			
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
	public void deltAsrById(Integer asrId) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			session.createQuery("delete from ZqAsrModel where id=?").setInteger(0, asrId).executeUpdate();
			
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
	public void saveZqNotice(ZqNoticeModel zqNoticeModel) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			session.save(zqNoticeModel);
			
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
	public List<ZqNoticeModel> queryAllNotice() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("select new ZqNoticeModel(a.id,b.name,a.title) from ZqNoticeModel a,ZqUserModel b where a.author=b.id");
	}

	@Override
	public ZqNoticeModel queryNoticeById(Integer asrId) {
		// TODO Auto-generated method stub
		String hql = "select new ZqNoticeModel(a.id,b.name,a.title,a.time,a.content,a.author) from ZqNoticeModel a,ZqUserModel b where a.author=b.id and a.id=?";
		Session session = this.getSession();
		
		try{
			return (ZqNoticeModel) getHibernateTemplate().find(hql,asrId).iterator().next();
			//return (ZqNoticeModel) session.createQuery(hql).setInteger(0, asrId).uniqueResult();
		}finally{
			session.close();
		}
		
		
	}

	@Override
	public void deltAnnounceById(Integer asrId) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			session.createQuery("delete from ZqNoticeModel where id=?").setInteger(0, asrId).executeUpdate();
			
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
	public void updateNotice(ZqNoticeModel zqNoticeModel) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			session.update(zqNoticeModel);
			
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
	public void saveSerRecord(ZqSerrecordModel zqSerrecordModel) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			session.save(zqSerrecordModel);
			
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
	public List<ZqSerrecordModel> queryAllSerRecordByUser(
			ZqUserModel zqUserModel) {
		// TODO Auto-generated method stub
		String hql = "select new ZqSerrecordModel(a.id,b.name,a.createTime) from ZqSerrecordModel a,ZqUserModel b where a.author=b.id ";
		//登陆用户为非管理员
		if(!"1".equals(zqUserModel.getIsManage())){
			hql += " and a.author=" + zqUserModel.getId();
		}
		
		return getHibernateTemplate().find(hql);

	}

	@Override
	public ZqSerrecordModel SerRecordById(Integer asrId) {
		// TODO Auto-generated method stub
		String hql = "select new ZqSerrecordModel(a.id,b.name,a.createTime,a.content) from ZqSerrecordModel a,ZqUserModel b where a.author=b.id and a.id=?";
		Session session = this.getSession();
		
		try{
			return (ZqSerrecordModel) session.createQuery(hql).setInteger(0, asrId).uniqueResult();
		}finally{
			session.close();
		}
	
	}

	@Override
	public IndexModel queryAllUserCase_Cont(Integer userId) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		IndexModel indexModel = new IndexModel();
		
		String hql = "select new ZqCaseModel(a.id,a.number,a.caseName,b.typeName,c.name,a.contId) from ZqCaseModel a,ZqCaseTypeModel b,ZqUserModel c " +
		  			 "     where a.lawyer=c.id and a.typeId=b.typeId and a.status=? and (a.creater=? or a.lawyer=? or a.id in(select caseId from ZqContractcoscusModel where lawyerId=? ))  order by a.createTime desc";
		try {
			// 获取其主办或协办的未结案件
			List<ZqCaseModel> zqCaseModels = session.createQuery(hql)
					.setInteger(0, 0).setInteger(1, userId)
					.setInteger(2, userId).setInteger(3, userId).list();
			indexModel.setZqNoOverCaseModels(zqCaseModels);
			// 获取其主办或协办的未结案件
			zqCaseModels = session.createQuery(hql).setInteger(0, 1)
					.setInteger(1, userId).setInteger(2, userId)
					.setInteger(3, userId).list();
			indexModel.setZqOverCaseModels(zqCaseModels);

			hql = "select new ZqContractModel(a.id,a.number,a.contName,b.contractTypeName,c.name,d.name) "
					+ "           from ZqContractModel a,ZqContracttypeModel b,ZqCustomerModel c,ZqUserModel d "
					+ "			 where a.typeId=b.id and a.custId=c.id and d.id=a.lawyer and a.archived=? and (a.creater=? or a.lawyer=? or a.id in(select caseId from ZqContractcoscusModel where lawyerId=? )) order by a.createTime desc";
			// 获取其主办或协办的未结合同
			List<ZqContractModel> contractModels = session.createQuery(hql)
					.setString(0, "0").setInteger(1, userId)
					.setInteger(2, userId).setInteger(3, userId).list();
			indexModel.setZqNoOverContractModels(contractModels);
			// 获取其主办或协办的已结合同
			contractModels = session.createQuery(hql).setString(0, "1")
					.setInteger(1, userId).setInteger(2, userId)
					.setInteger(3, userId).list();
			indexModel.setZqOverContractModels(contractModels);
		} finally {
			session.close();
		}

		return indexModel;
	}

}
