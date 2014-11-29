package com.contManage.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.FileManage.vo.ZqBusFileModel;
import com.FileManage.vo.ZqFileModel;
import com.caseManage.vo.ZqCaseModel;
import com.contManage.dao.dao.ContracterManageDao;
import com.contManage.vo.ZqContractAffairModel;
import com.contManage.vo.ZqContractModel;
import com.contManage.vo.ZqContractcharagestageModel;
import com.contManage.vo.ZqContractcoscusModel;
import com.contManage.vo.ZqContracttypeModel;
import com.customerManage.vo.ZqCustomerModel;
import com.login.vo.ZqUserModel;
import com.util.ExtremeTablePage;
import com.util.Log4j;
import com.util.MethodUtils;
import com.util.PageBean;

/**
 * 合同管理持久层实现
 * 
 * @author peng
 * @since 2013-9-15下午08:07:23
 */
public class ContracterManageDaoImpl extends HibernateDaoSupport implements
		ContracterManageDao {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.contManage.dao.dao.ContracterManageDao#queryContTypeList(java.util
	 * .Map)
	 */
	@Override
	public PageBean queryContTypeList(Map conditionMap) {
		// TODO Auto-generated method stub
		HibernateTemplate dao = getHibernateTemplate();
		PageBean pageBean = (PageBean) conditionMap.get("pageBean");
		// 获取总记录数目
		int totalRows = pageBean.getTotalRows(dao, SELECT_CONTRACTERTYPE_COUNT,
				null);

		// 每页记录数
		final int length = pageBean.getPageSize();
		@SuppressWarnings("static-access")
		final int offset = pageBean.countOffset(length, pageBean.getPage());

		@SuppressWarnings("unchecked")
		List<ZqContracttypeModel> list = ExtremeTablePage.queryListByPage(
				offset, length, SELECT_CONTRACTERTYPE, null, dao);
		pageBean.setList(list);

		return pageBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ZqContracttypeModel> queryContracttypeModelsByName(
			String contractTypeName, Integer contractTypeId) {
		// TODO Auto-generated method stub
		List<ZqContracttypeModel> list = null;
		if (contractTypeId == null) {
			list = getHibernateTemplate().find(SELECT_CONTRACTERTYPE_BYNAME,
					contractTypeName);
		} else {
			list = getHibernateTemplate().find(SELECT_CONTRACTERTYPE_BYNAMEID,
					new Object[] { contractTypeName, contractTypeId });
		}
		return list;
	}

	@Override
	public void addContractType(ZqContracttypeModel zqContracttypeModel)
			throws DataAccessException {
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(zqContracttypeModel);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ZqContractModel> queryContractByType(Integer contractTypeId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(SELECT_CONTRACT_BYTYPENAME,
				contractTypeId);
	}

	@Override
	public void deltContractType(ZqContracttypeModel zqContracttypeModel) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(zqContracttypeModel);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ZqContracttypeModel> queryAllCotracterType() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(SELECT_CONTRACTERTYPE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ZqUserModel> queryAllUsers() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(SELECT_USER);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ZqCustomerModel> queryAllNowCust() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(SELEC_CUST);
	}

	@Override
	public void saveContract(ZqContractModel zqContractModel,
			ZqContractcoscusModel[] zqContractcoscusModels,
			List<ZqContractcharagestageModel> zqContractcharagestageModels) throws HibernateException{
		// TODO Auto-generated method stub
		Session session = this.getSession();

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			//新增合同
			Integer contractId = (Integer) session.save(zqContractModel);
			if(zqContractcoscusModels!=null){
				//保存协办律师信息
				for(ZqContractcoscusModel zqContractcoscusModel:zqContractcoscusModels){
					zqContractcoscusModel.setContractId(contractId);
					session.save(zqContractcoscusModel);
				}
				
			}
			if(zqContractcharagestageModels.size()>0){
				for(ZqContractcharagestageModel zqContractcharagestageModel:zqContractcharagestageModels){
					zqContractcharagestageModel.setContractId(contractId);
					session.save(zqContractcharagestageModel);
				}
			}
			transaction.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			if(transaction!=null)
				// 回滚操作
				transaction.rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		}finally{
			session.close();
		}
		

	}

	@Override
	public PageBean queryContractList(Map conditionMap) {
		// TODO Auto-generated method stub
		HibernateTemplate dao = getHibernateTemplate();
		//获取登录用户信息
		ZqUserModel zqUserModel = MethodUtils.getUserInfoModel();
		String condition = "";
		//生成条件数组
		Object[] condIntegerss = null;
		//用户是超级管理员
		if(zqUserModel.getIsManage().equals("1")){
			condIntegerss = new Object[] {(String)conditionMap.get("archived")};
		}else{
			Integer userId = zqUserModel.getId();
			condIntegerss = new Object[] {(String)conditionMap.get("archived"),userId,userId,userId};
			//查询主办律师或合同的创建者或协办律师为当前登录用户
			condition = " and (creater=? or lawyer=? or id in(select contractId from ZqContractcoscusModel where lawyerId=? )) ";
		}
		
		PageBean pageBean = (PageBean) conditionMap.get("pageBean");
		
		// 获取总记录数目
		int totalRows = pageBean.getTotalRows(dao, SELECT_CONTRACT_COUNT + condition,
				condIntegerss);

		// 每页记录数
		final int length = pageBean.getPageSize();
		@SuppressWarnings("static-access")
		final int offset = pageBean.countOffset(length, pageBean.getPage());

		@SuppressWarnings("unchecked")
		List<ZqContractModel> list = ExtremeTablePage.queryListByPage(
				offset, length, SELECT_CONTRACT + condition, condIntegerss, dao);
		pageBean.setList(list);

		return pageBean;
	}

	@Override
	public ZqContractModel queryContractById(Integer contId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(ZqContractModel.class, contId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ZqContractcharagestageModel> queryCharageStageByContid(
			Integer contId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(SELECT_CHARGESTAGE,contId);
	}

	@Override
	public List<ZqContractcoscusModel> queryCoscusByContid(Integer contId) {
		// TODO Auto-generated method stub
		//Session session;
		return getHibernateTemplate().find(SELECT_COSCUS,contId);
	}

	@Override
	public void deltCoscusByid(ZqContractcoscusModel zqContractcoscusModel){
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(zqContractcoscusModel);
		//getHibernateTemplate().bulku
	}

	@Override
	public void updateContract(Integer[] chargeIdArray,
			ZqContractModel zqContractModel,
			ZqContractcoscusModel[] zqContractcoscusModels,
			List<ZqContractcharagestageModel> zqContractcharagestageModels)throws HibernateException{
		// TODO Auto-generated method stub
		String hql = "delete ZqContractcharagestageModel where id=:chargeId ";
		Session session = this.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (chargeIdArray != null) {
				// 删除已有付费阶段
				for (Integer chargeId : chargeIdArray) {
					// 执行更新
					session.createQuery(hql).setInteger("chargeId", chargeId)
							.executeUpdate();
				}
			}
			//添加新的协办律师
			if(zqContractcoscusModels!=null){
				for(ZqContractcoscusModel zqContractcoscusModel:zqContractcoscusModels){
					session.save(zqContractcoscusModel);
				}
			}
			//添加新的付费阶段
			for(ZqContractcharagestageModel zqContractcharagestageModel:zqContractcharagestageModels){
				session.save(zqContractcharagestageModel);
			}
			//更新合同信息
			session.update(zqContractModel);
			//提交事务
			transaction.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		}finally{
			session.close();
		}
	}

	@Override
	public void deletContById(Integer contId) throws HibernateException{
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			//删除协办律师
			session.createQuery(deltCoscus).setInteger("contId", contId).executeUpdate();
			//删除付费阶段信息
			session.createQuery(deltChargestage).setInteger("contId",contId).executeUpdate();
			//删除合同事务信息
			session.createQuery(deltContAffair).setInteger("contId", contId).executeUpdate();
			//删除合同本身信息
			session.createQuery(deltContSql).setInteger("contId", contId).executeUpdate();
			
			
			transaction.commit();
			
		}catch (HibernateException e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			throw new HibernateException(e);
		}finally{
			session.close();
		}
	}

	@Override
	public List<Map> queryContractByTypeId(Integer contTypeId) {
		// TODO Auto-generated method stub
		List<Map> contList = null;
		List list = getHibernateTemplate()
				.find(SELECT_CONTBYTYPEID, contTypeId);
		if (list != null) {
			contList = new ArrayList<Map>();
			Map map = null;
			Iterator<Object[]> iterator = list.iterator();
			while (iterator.hasNext()) {
				Object[] objects = iterator.next();
				map = new HashMap();
				map.put("contId", objects[0]);
				map.put("contName", objects[1]);
				contList.add(map);

			}
		}
		return contList;

	}

	@Override
	public void addContAffair(ZqContractAffairModel zqContractAffairModel) throws HibernateException{
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.save(zqContractAffairModel);
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
	public List queryContAffairByContId(Integer contId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(SELECT_CONTAFFAIR_BY_CONTID,contId);
	}

	@Override
	public List<ZqCaseModel> queryContTypeList(Integer contId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("select new ZqCaseModel(id,caseName) from ZqCaseModel where contId=?",contId);
	}

	@Override
	public void updateContractArchived(Integer contId,String archived) throws HibernateException{
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			session.createQuery("update ZqContractModel set archived=? where id=?").setString(0, archived).setInteger(1, contId).executeUpdate();
			
			transaction.commit();
		}catch (HibernateException e) {
			// TODO: handle exception
			if(transaction != null)
				transaction.rollback();
			throw e;
		}
	}

	@Override
	public void saveContFile(String[] fileids, ZqBusFileModel zqBusFileModel) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		String busId = zqBusFileModel.getBusId();
		try{
			transaction = session.beginTransaction();
			
			for(String fileId:fileids){
				zqBusFileModel = new ZqBusFileModel();
				zqBusFileModel.setBusId(busId);
				//业务model中加入文件id
				zqBusFileModel.setFileId(Integer.valueOf(fileId));
				session.save(zqBusFileModel);
			}
			
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
	public List<ZqFileModel> queryContFile(Integer contId) {
		// TODO Auto-generated method stub
		String hql = "select new ZqFileModel(a.id,a.ywjm,a.realFilename,a.address,a.creater,a.createTime) " +
				     "          from ZqFileModel a,ZqBusFileModel b  where a.id=b.fileId  and b.busId=?";
		return getHibernateTemplate().find(hql,contId.toString());
	}

	@Override
	public void deltContFile(Integer fileId) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			ZqFileModel zqFileModel = (ZqFileModel) session.get(ZqFileModel.class, fileId);
			transaction = session.beginTransaction();
			//删除附件信息
			session.delete(zqFileModel);
			//删除业务附件
			session.createQuery("delete ZqBusFileModel where fileId=?").setInteger(0, fileId).executeUpdate();
			transaction.commit();
			//如果磁盘文件存在，则删除
			String address = zqFileModel.getAddress();
			MethodUtils.deltFiles(new String[]{address});	
			
		}catch (Exception e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public void updateRegFees(
			ZqContractcharagestageModel[] zqContractcharagestageModels) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			for(ZqContractcharagestageModel zqContractcharagestageModel:zqContractcharagestageModels){
				session.update(zqContractcharagestageModel);
			}
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
	public boolean checkTheContNumIfExists(String number) {
		// TODO Auto-generated method stub
		int num = ((Long)getHibernateTemplate().find("select count(*) from ZqContractModel where number=?",number).iterator().next()).intValue();
		//不存在该合同编号
		if(num==0){
			return true;
		}
		return false;
	}

	@Override
	public int countCaseInCont(Integer contId) {
		// TODO Auto-generated method stub
		return ((Long)getHibernateTemplate().find("select count(*) from ZqCaseModel where contId=?",contId).iterator().next()).intValue();
	}

}
