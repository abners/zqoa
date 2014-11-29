package com.caseManage.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.FileManage.vo.ZqBusFileModel;
import com.FileManage.vo.ZqFileModel;
import com.caseManage.dao.dao.CaseManageDao;
import com.caseManage.vo.ZqCaseModel;
import com.caseManage.vo.ZqCaseTypeModel;
import com.caseManage.vo.ZqCasecontactModel;
import com.caseManage.vo.ZqCaseprocessModel;
import com.caseManage.vo.ZqContactidentityModel;
import com.contManage.vo.ZqContractModel;
import com.contManage.vo.ZqContractcoscusModel;
import com.contManage.vo.ZqContracttypeModel;
import com.login.vo.ZqUserModel;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.util.ExtremeTablePage;
import com.util.Log4j;
import com.util.MethodUtils;
import com.util.PageBean;

/**
 * 案件管理持久层实现
 * 
 * @author peng
 * @since 2013-10-7下午02:34:36
 */
public class CaseManageDaoImpl extends HibernateDaoSupport implements
		CaseManageDao {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.caseManage.dao.dao.CaseManageDao#queryContactIdentityList(java.util
	 * .Map)
	 */
	@Override
	public PageBean queryContactIdentityList(Map conditionMap) {
		// TODO Auto-generated method stub
		HibernateTemplate dao = getHibernateTemplate();
		PageBean pageBean = (PageBean) conditionMap.get("pageBean");
		// 获取总记录数目
		int totalRows = pageBean.getTotalRows(dao, SELECT_COUNT_CASEIDENTITY,
				null);

		// 每页记录数
		final int length = pageBean.getPageSize();
		@SuppressWarnings("static-access")
		final int offset = pageBean.countOffset(length, pageBean.getPage());

		@SuppressWarnings("unchecked")
		List<ZqContracttypeModel> list = ExtremeTablePage.queryListByPage(
				offset, length, SELECT_CASEIDENTITY, null, dao);
		pageBean.setList(list);

		return pageBean;
	}

	@Override
	public void saveCaseIdentity(ZqContactidentityModel zqContactidentityModel)
			throws HibernateException {
		// TODO Auto-generated method stub
		Session session = this.getSession();

		Transaction transaction = null;
		try {
			// beging transaction
			transaction = session.beginTransaction();
			// do transaction
			session.save(zqContactidentityModel);
			// commit transaction
			transaction.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();

			throw new HibernateException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public List<ZqContactidentityModel> queryCaseIdentityByName(
			String identityName) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(SELECT_CASEIDENTITY_BY_NAME,
				identityName);
	}

	@Override
	public void updateCaseIdentity(ZqContactidentityModel zqContactidentityModel)
			throws HibernateException {
		// TODO Auto-generated method stub
		updateObject(zqContactidentityModel);
	}

	@Override
	public int countIdentityUsedByCaseContact(Integer identityId) {
		// TODO Auto-generated method stub
		try {
			return ((Long) getHibernateTemplate()
					.find(SELECT_COUNT_CASEIDENTITY_USEDBY_CASECONTACT,
							identityId).iterator().next()).intValue();
		} catch (Exception e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);

			return 0;
		}
	}

	@Override
	public void deltCaseIdentityById(Integer identityId)
			throws HibernateException {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.createQuery(DELETE_CASEIDENTITY)
					.setInteger("identityId", identityId).executeUpdate();
			transaction.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();

			throw new HibernateException(e);
		} finally {
			session.close();
		}

	}

	@Override
	public PageBean queryContactTypeList(Map conditionMap) {
		// TODO Auto-generated method stub
		HibernateTemplate dao = getHibernateTemplate();
		PageBean pageBean = (PageBean) conditionMap.get("pageBean");
		// 获取总记录数目
		int totalRows = pageBean.getTotalRows(dao, SELECT_COUNT_CASETYPE, null);

		// 每页记录数
		final int length = pageBean.getPageSize();
		@SuppressWarnings("static-access")
		final int offset = pageBean.countOffset(length, pageBean.getPage());

		@SuppressWarnings("unchecked")
		List<ZqContracttypeModel> list = ExtremeTablePage.queryListByPage(
				offset, length, SELECT_CASETYPE, null, dao);
		pageBean.setList(list);

		return pageBean;
	}

	@Override
	public int countCaseTypeByName(String typeName) {
		// TODO Auto-generated method stub
		return ((Long) getHibernateTemplate()
				.find(SELECT_COUNT_CASETYPE_BY_NAME, typeName).iterator()
				.next()).intValue();
	}

	@Override
	public void saveCaseType(ZqCaseTypeModel zqCaseTypeModel)
			throws HibernateException {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			session.save(zqCaseTypeModel);

			transaction.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			if (transaction != null) {
				transaction.rollback();
			}

			throw new HibernateException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public void updateCaseType(ZqCaseTypeModel zqCaseTypeModel)
			throws HibernateException {
		// TODO Auto-generated method stub
		updateObject(zqCaseTypeModel);
	}

	@Override
	public int countTypeUsedByCase(Integer typeId) {
		// TODO Auto-generated method stub
		return ((Long) getHibernateTemplate()
				.find(SELECT_COUNT_TYPE_USERBY_CASE, typeId).iterator().next())
				.intValue();
	}

	@Override
	public void deltCaseTypeById(Integer typeId) throws HibernateException {
		// TODO Auto-generated method stub
		updateObject(DELETE_CASETYPE, new Object[] { typeId });
	}

	/**
	 * 持久化实例对象到数据库(只适用于单例更新)
	 * 
	 * @param object
	 *            实例信息
	 * @throws HibernateException
	 */
	private void updateObject(Object object) throws HibernateException {
		Session session = this.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			session.update(object);

			transaction.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			if (transaction != null) {
				transaction.rollback();
			}
			throw new HibernateException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public List<ZqCaseTypeModel> queryAllCaseType() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(SELECT_CASETYPE);
	}

	@Override
	public List<ZqContactidentityModel> queryAllCaseIdentity() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(SELECT_CASEIDENTITY);
	}

	@Override
	public List<ZqContractModel> queryAllContract() {
		// TODO Auto-generated method stub
		// 查询未归档合同
		List list = getHibernateTemplate().find(SELECT_CONTRACT, "0");

		return getContractModels(list);
	}

	@Deprecated
	@Override
	public List<ZqContractModel> queryContractCreatedByUser(Integer userId) {
		// TODO Auto-generated method stub
		List list = getHibernateTemplate().find(
				SELECT_CONTRACT + " and creater=?",
				new Object[] { "1", userId });

		return getContractModels(list);
	}

	private List<ZqContractModel> getContractModels(List list) {
		List<ZqContractModel> zqContractModels = null;
		if (list != null) {
			zqContractModels = new ArrayList<ZqContractModel>();
			Iterator<Object[]> iterator = list.iterator();
			ZqContractModel zqContractModel = null;
			while (iterator.hasNext()) {
				Object[] objects = iterator.next();
				zqContractModel = new ZqContractModel();
				zqContractModel.setId((Integer) objects[0]);
				zqContractModel.setContName((String) objects[1]);
				zqContractModel.setCustId((Integer) objects[2]);
				zqContractModel.setCustName((String) objects[3]);

				zqContractModels.add(zqContractModel);
			}
		}
		return zqContractModels;
	}

	@Override
	public List queryCaseByName(String caseName) {
		// TODO Auto-generated method stub
		String condition = "";
		if (caseName != null && !caseName.trim().equals("")) {
			condition = " and caseName like ? ";
			caseName = "%" + caseName + "%";
			return getHibernateTemplate().find(SELECT_CASE + condition,
					caseName);
		} else {
			return getHibernateTemplate().find(SELECT_CASE);
		}

	}

	@Override
	public void saveCaseIdentity(ZqCaseModel zqCaseModel,
			ZqContractcoscusModel[] zqContractcoscusModels)
			throws HibernateException {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			// 案件id，保存案件信息
			Integer caseId = (Integer) session.save(zqCaseModel);
			if (zqContractcoscusModels != null) {
				for (ZqContractcoscusModel zqContractcoscusModel : zqContractcoscusModels) {
					zqContractcoscusModel.setCaseId(caseId);
					// 保存协办律师信息
					session.save(zqContractcoscusModel);
				}
			}
			transaction.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public PageBean queryCaseByPage(Map conditionMap) {
		// TODO Auto-generated method stub
		HibernateTemplate dao = getHibernateTemplate();
		PageBean pageBean = (PageBean) conditionMap.get("pageBean");
		// 获取登录用户信息
		ZqUserModel zqUserModel = MethodUtils.getUserInfoModel();
		// 获取总记录数目
		int totalRows = 0;
		//条件sql
		String whereSql1 = "";
		String whereSql2 = "";
		if(conditionMap.get("isBefore")!=null&&((Integer)conditionMap.get("isBefore"))==1){
			whereSql1 = " and contId is null";
			whereSql2 = " and a.contId is null";
		}else if(conditionMap.get("isBefore")!=null){
			whereSql1 = " and contId is not null";
			whereSql2 = " and a.contId is not null";
		}
		// 当前登录用户为管理员
		if ("1".equals(zqUserModel.getIsManage())) {
			Object[] condition = { (Integer) conditionMap.get("isEnd") };
			
			// 统计所有未完结案件
			totalRows = pageBean
					.getTotalRows(dao, SELECT_COUNT_CASE + whereSql1, condition);

			// 每页记录数
			final int length = pageBean.getPageSize();
			@SuppressWarnings("static-access")
			final int offset = pageBean.countOffset(length, pageBean.getPage());

			@SuppressWarnings("unchecked")
			List list = ExtremeTablePage.queryListByPage(offset, length,
					SELECT_NOW_CASE + whereSql2 + " order by createTime desc", condition,
					dao);
			pageBean.setList(list);
		} else {
			Integer userId = zqUserModel.getId();
			// 生成条件数组
			Object[] condIntegers = { (Integer) conditionMap.get("isEnd"),
					userId, userId, userId };
			// 查询主办律师或案件的创建者或协办律师为当前登录用户
			String condition = " and (creater=? or lawyer=? or id in(select caseId from ZqContractcoscusModel where lawyerId=? )) ";
			// 统计所有未完结案件
			totalRows = pageBean.getTotalRows(dao, SELECT_COUNT_CASE
					+ condition + whereSql1 , condIntegers);

			// 每页记录数
			final int length = pageBean.getPageSize();
			@SuppressWarnings("static-access")
			final int offset = pageBean.countOffset(length, pageBean.getPage());

			@SuppressWarnings("unchecked")
			List list = ExtremeTablePage
					.queryListByPage(
							offset,
							length,
							SELECT_NOW_CASE
									+ whereSql2 + " and (creater=? or lawyer=? or a.id in("
									+ "    select caseId from ZqContractcoscusModel where lawyerId=?))"
									+ "        order by a.createTime desc",
							condIntegers, dao);
			pageBean.setList(list);
		}

		return pageBean;
	}

	@Override
	public ZqCaseModel queryCaseById(Integer caseId) {
		// TODO Auto-generated method stub
		ZqCaseModel zqCaseModel = null;
		Session session = this.getSession();

		try {
			/*
			 * Criteria crit = session.createCriteria(ZqCaseModel.class);
			 * crit.add(Restrictions.eq("id", caseId)); zqCaseModel =
			 * (ZqCaseModel)crit.uniqueResult();
			 */
			zqCaseModel = (ZqCaseModel) session.createQuery(SELECT_CASE_BY_ID)
					.setInteger("id", caseId).uniqueResult();
		} catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		} finally {
			session.close();
		}
		return zqCaseModel;
	}

	@Override
	public List<ZqContractcoscusModel> queryCoscusByCaseId(Integer caseId) {
		// TODO Auto-generated method stub
		String hql = " select new ZqContractcoscusModel(a.id,a.lawyerId,b.name,a.contractId,a.caseId) from ZqContractcoscusModel a,ZqUserModel b where a.lawyerId=b.id and caseId=?";
		return getHibernateTemplate().find(hql, caseId);
	}

	@Override
	public List queryCaseNameArray(String[] relativeIds) {

		// TODO Auto-generated method stub
		int length = relativeIds.length;
		// 创建关联案件id数组
		Integer[] caseIds = new Integer[length];
		String condition = "";
		for (int i = 0; i < length - 1; i++) {
			caseIds[i] = Integer.valueOf(relativeIds[i]);
			
			condition += " or id=?";
		}
		caseIds[length - 1] = Integer.valueOf(relativeIds[length - 1]);

		List<ZqCaseModel> list = getHibernateTemplate().find(
				"select new ZqCaseModel(id,caseName) from ZqCaseModel where id=? " + condition,
				caseIds);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ZqCaseprocessModel> queryCaseProcessByCaseId(Integer caseId) {
		// TODO Auto-generated method stub
		HibernateTemplate DAO = getHibernateTemplate();
		List list = DAO.find(SELECT_CASEPROCESS, caseId);
		List<ZqCaseprocessModel> zqCaseprocessModels = null;
		if (list != null) {
			zqCaseprocessModels = new ArrayList<ZqCaseprocessModel>();
			Iterator<Object[]> iterator = list.iterator();
			while (iterator.hasNext()) {
				Object[] objects = iterator.next();
				ZqCaseprocessModel zqCaseprocessModel = (ZqCaseprocessModel) objects[0];
				// 获取该案件事件的所有附件
				List<ZqFileModel> zqFileModels = DAO.find(
						SELECT_CASEPROCESS_FILE, zqCaseprocessModel.getId());
				zqCaseprocessModel.setZqprocessFileList(zqFileModels);

				// 创建者姓名
				zqCaseprocessModel.setCreaterName((String) objects[1]);

				zqCaseprocessModels.add(zqCaseprocessModel);
			}
		}
		return zqCaseprocessModels;
	}

	@Override
	public void addCaseProcess(ZqCaseprocessModel zqCaseprocessModel,
			ZqBusFileModel[] zqBusFileModels) throws HibernateException {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			// 保存案件事件
			session.save(zqCaseprocessModel);
			// 保存关联附件
			if (zqBusFileModels != null) {
				for (ZqBusFileModel zqBusFileModel : zqBusFileModels) {
					session.save(zqBusFileModel);
				}
			}
			transaction.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();
			Log4j.errorLog(this, e);
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void deltAllFileByids(String[] fileid_array) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			// 附件地址信息集合
			String[] address_array = new String[fileid_array.length];
			int i = 0;
			for (String fileId : fileid_array) {
				ZqFileModel zqFileModel = (ZqFileModel) session.get(
						ZqFileModel.class, Integer.valueOf(fileId));
				address_array[i] = zqFileModel.getAddress();
				i++;
				// 删除记录
				session.delete(zqFileModel);

			}
			// 删除磁盘实际的文件
			MethodUtils.deltFiles(address_array);
			transaction.commit();

		} catch (Exception e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();
			throw e;

		} finally {
			session.close();
		}
	}

	@Override
	public void deltCaseProcessById(String caseProcessId) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		// 查询所有附件的id
		List<Integer> fileIds = session
				.createQuery(
						"select fileId from ZqBusFileModel where busId=:processId")
				.setString("processId", caseProcessId).list();

		try {
			transaction = session.beginTransaction();
			// 该事件存在附件
			if (fileIds != null && fileIds.size() > 0) {
				// 附件地址信息集合
				String[] address_array = new String[fileIds.size()];
				int i = 0;
				for (Integer fileId : fileIds) {
					ZqFileModel zqFileModel = (ZqFileModel) session.get(
							ZqFileModel.class, fileId);
					address_array[i] = zqFileModel.getAddress();
					i++;
					// 删除附件信息
					session.delete(zqFileModel);
				}
				// 删除磁盘文件
				MethodUtils.deltFiles(address_array);
			}
			// 删除业务附件信息
			session.createQuery(
					"delete from ZqBusFileModel where busId=:processId")
					.setString("processId", caseProcessId).executeUpdate();

			// 删除案件事件信息
			session.createQuery("delete from ZqCaseprocessModel where id=:id")
					.setString("id", caseProcessId).executeUpdate();

			transaction.commit();

		} catch (Exception e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();
			Log4j.errorLog(this, e);
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void saveCaseContact(ZqCasecontactModel zqCasecontactModel)
			throws HibernateException {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			session.save(zqCasecontactModel);

			transaction.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();
			Log4j.errorLog(this, e);
			throw e;
		} finally {
			session.close();
		}

	}

	@Override
	public List<ZqCasecontactModel> queryContactByCaseId(Integer caseId) {
		// TODO Auto-generated method stub
		String hql = "select new ZqCasecontactModel(a.id,a.name,a.email,a.address,a.tel,a.mobile,b.identityName)"
				+ "  from ZqCasecontactModel a,ZqContactidentityModel b where a.roleid=b.identityId and a.caseId=? ";
		return getHibernateTemplate().find(hql, caseId);
	}

	@Override
	public void deltCaseContactById(Integer caseContactId)
			throws HibernateException {
		// TODO Auto-generated method stub
		updateObject("delete from ZqCasecontactModel where id=?",
				new Object[] { caseContactId });
	}

	@Override
	public void updateCaseStatusById(Integer caseId, Integer status)
			throws HibernateException {
		// TODO Auto-generated method stub
		updateObject("update ZqCaseModel set status=? where id=? ",
				new Object[] { status, caseId });
	}

	/**
	 * 更新实体信息
	 * 
	 * @param hql
	 *            更新sql
	 * @param objects
	 *            条件数组
	 * @throws HibernateException
	 *             异常由调用者处理
	 */
	private void updateObject(String hql, Object[] objects)
			throws HibernateException {
		Session session = this.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			Query query = session.createQuery(hql);
			if (objects != null) {
				int i = 0;
				for (Object condition : objects) {
					if (condition instanceof String) {
						query.setString(i, (String) objects[i]);
					} else if (condition instanceof Integer) {
						query.setInteger(i, (Integer) objects[i]);
					}
					i++;
				}
			}

			query.executeUpdate();

			transaction.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();
			 Log4j.errorLog(this, e);
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void deltCaseById(Integer caseId) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		// 查询所有附件的id
		List<Integer> fileIds = session
				.createQuery(
						"select a.fileId from ZqBusFileModel a,ZqCaseprocessModel b where a.busId=b.id and b.caseId=:processId")
				.setInteger("processId", caseId).list();
		try {
			transaction = session.beginTransaction();
			// 删除该案件的所有事件及其关联附件
			// 该事件存在附件
			if (fileIds != null && fileIds.size() > 0) {
				// 附件地址信息集合
				String[] address_array = new String[fileIds.size()];
				int i = 0;
				for (Integer fileId : fileIds) {
					ZqFileModel zqFileModel = (ZqFileModel) session.get(
							ZqFileModel.class, fileId);
					address_array[i] = zqFileModel.getAddress();
					i++;
					// 删除附件信息
					session.delete(zqFileModel);
				}
				// 删除磁盘文件
				MethodUtils.deltFiles(address_array);
			}
			// 查询案件事件id
			List<String> processIdList = session
					.createQuery(
							"select id from ZqCaseprocessModel where caseId=?")
					.setInteger(0, caseId).list();
			if (processIdList != null) {
				for (String processId : processIdList) {
					// 删除业务附件信息
					session.createQuery(
							"delete from ZqBusFileModel where busId=:processId")
							.setString("processId", processId).executeUpdate();
				}
			}
			// 删除案件事件信息
			session.createQuery(
					"delete from ZqCaseprocessModel where caseId=:id")
					.setInteger("id", caseId).executeUpdate();
			// 删除案件联系人信息
			session.createQuery(
					"delete from ZqCasecontactModel where caseId=:caseId")
					.setInteger("caseId", caseId).executeUpdate();
			// 删除案件信息
			session.createQuery("delete from ZqCaseModel where id=:caseId")
					.setInteger("caseId", caseId).executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public ZqCaseModel queryCaseMessById(Integer caseId) {
		// TODO Auto-generated method stub

		ZqCaseModel zqCaseModel = null;
		Session session = this.getSession();

		try {
			/*
			 * Criteria crit = session.createCriteria(ZqCaseModel.class);
			 * crit.add(Restrictions.eq("id", caseId)); zqCaseModel =
			 * (ZqCaseModel)crit.uniqueResult();
			 */
			zqCaseModel = (ZqCaseModel) session
					.createQuery("from ZqCaseModel where id=:id ")
					.setInteger("id", caseId).uniqueResult();
		} catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		} finally {
			session.close();
		}
		return zqCaseModel;
	}

	@Override
	public void updateCaseAndIdentity(ZqCaseModel zqCaseModel,
			ZqContractcoscusModel[] zqContractcoscusModels) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			// 更新案件信息
			session.update(zqCaseModel);
			if (zqContractcoscusModels != null) {
				//获取案件id
				Integer caseId = zqCaseModel.getId();
				for (ZqContractcoscusModel zqContractcoscusModel : zqContractcoscusModels) {
					zqContractcoscusModel.setCaseId(caseId);
					// 保存协办律师信息
					session.save(zqContractcoscusModel);
				}
			}
			transaction.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public ZqCaseModel queryCaseBeforeContById(Integer caseId) {
		// TODO Auto-generated method stub
		ZqCaseModel zqCaseModel = null;
		Session session = this.getSession();

		try {
			/*
			 * Criteria crit = session.createCriteria(ZqCaseModel.class);
			 * crit.add(Restrictions.eq("id", caseId)); zqCaseModel =
			 * (ZqCaseModel)crit.uniqueResult();
			 */
			zqCaseModel = (ZqCaseModel) session.createQuery(SELECT_CASEBEFORECONT_BY_ID)
					.setInteger("id", caseId).uniqueResult();
		} catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		} finally {
			session.close();
		}
		return zqCaseModel;
	}

	@Override
	public ZqCaseprocessModel queryCaseProcessById(String processId) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		
		ZqCaseprocessModel zqCaseprocessModel = null;
		try{
			zqCaseprocessModel = (ZqCaseprocessModel) session.get(ZqCaseprocessModel.class, processId);
			//所有附件
			List<ZqFileModel> zqFileModels = session.createQuery("select a from ZqFileModel a,ZqBusFileModel b where a.id=b.fileId and b.busId=? ").setString(0, processId).list();
			
			zqCaseprocessModel.setZqprocessFileList(zqFileModels);
		}finally{
			session.close();
		}
		
		return zqCaseprocessModel;
	}

	@Override
	public void modifyCaseProcess(ZqCaseprocessModel zqCaseprocessModel,
			ZqBusFileModel[] zqBusFileModel_Array, Integer[] deltFileIds) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if(zqBusFileModel_Array!=null){
				for(ZqBusFileModel zqBusFileModel:zqBusFileModel_Array)
					//保存业务附件
					session.save(zqBusFileModel);
			}
			session.update(zqCaseprocessModel);
			if(deltFileIds!=null){
				//要删除的附件的位置
				String file_Address[] = new String[deltFileIds.length];
				int i=0;
				for (Integer fileId:deltFileIds) {
					//获取附件信息
					ZqFileModel zqFileModel = (ZqFileModel) session.get(ZqFileModel.class, fileId);
					//获取文件地址
					file_Address[i] = zqFileModel.getAddress();
					i++;
					
					//删除附件信息
					session.createQuery("delete from ZqFileModel where id=?").setInteger(0, fileId).executeUpdate();
					//删除业务附件
					session.createQuery("delete from ZqBusFileModel where busId=? and fileId=?")
						.setString(0, zqCaseprocessModel.getId())
						.setInteger(1, fileId).executeUpdate();
				}
				/**
				 * 删除磁盘文件
				 */
				MethodUtils.deltFiles(file_Address);			
			}
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public ZqCasecontactModel queryCasecontactById(Integer caseContactId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(ZqCasecontactModel.class, caseContactId);
	}

	@Override
	public void updateCaseContact(ZqCasecontactModel zqCasecontactModel) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.update(zqCasecontactModel);
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

}
