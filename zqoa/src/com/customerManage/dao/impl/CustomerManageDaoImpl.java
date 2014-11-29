package com.customerManage.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.customerManage.dao.dao.CustomerManageDao;
import com.customerManage.vo.ZqCustomerModel;
import com.util.Log4j;
import com.util.PageBean;

/**
 * 客户管理持久层实现
 * 
 * @author peng
 * @since 2013-9-9下午07:37:45
 */
public class CustomerManageDaoImpl extends HibernateDaoSupport implements
		CustomerManageDao {
	/**
	 * 根据isnow的值查询对应客户的信息
	 */
	private static final String s_SELECT_CUSTOMER = " from ZqCustomerModel where isnow=? ";
	/**
	 * 查询总记录数目
	 */
	private static final String s_SELECT_CUSTOMER_COUNT = "select count(*) from ZqCustomerModel where isnow=? ";
	/**
	 * 统计客户未完结的合同个数
	 */
	private static final String s_SELECT_CONTINCUST_COUNT = "select count(*) from ZqContractModel where custId=? and archived='0' ";
	/**
	 * 统计客户拥有的合同个数
	 */
	private static final String s_SELECT_COUNT_CONTINCUST = " select count(*) from ZqContractModel where custId=? ";
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.customerManage.dao.dao.CustomerDao#findCustByName(java.lang.String)
	 */
	@Override
	public List<ZqCustomerModel> findCustByName(String cust_name) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		List zqCustomerList = null;
		try {
			Query query = session
					.createQuery("from ZqCustomerModel where name=:name");
			query.setString("name", cust_name);
			zqCustomerList = query.list();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
		return zqCustomerList;
	}

	@Override
	public void saveCustomerByModel(ZqCustomerModel zqCustomerModel)
			throws DataAccessException {
		// TODO Auto-generated method stub
			//新增客户
			getHibernateTemplate().save(zqCustomerModel);
	}

	@Override
	public PageBean queryCustomerList(Map conditionMap) {
		// TODO Auto-generated method stub
		HibernateTemplate dao = getHibernateTemplate();
		
		PageBean pageBean = (PageBean)conditionMap.get("pageBean");
		final String isnow = (String) conditionMap.get("isnow");
		//获取总记录数目
		pageBean.getTotalRows(dao, s_SELECT_CUSTOMER_COUNT, new Object[]{isnow});

		//每页记录数
		final int length = pageBean.getPageSize();
		@SuppressWarnings("static-access")
		final int offset  = pageBean.countOffset(length, pageBean.getPage());
		
		List list = dao.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)throws HibernateException,SQLException{
				Query query=session.createQuery(s_SELECT_CUSTOMER);
				query.setString(0, isnow);
				query.setFirstResult(offset);
				query.setMaxResults(length);
				List list=query.list();
				
				return list;
			}
		});
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public ZqCustomerModel queryCustById(Integer cust_id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(ZqCustomerModel.class, cust_id);
	}

	@Override
	public void saveModify(ZqCustomerModel zqCustomerModel) throws HibernateException{
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.update(zqCustomerModel);
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
	public void deltCust(ZqCustomerModel zqCustomerModel) throws DataAccessException{
		// TODO Auto-generated method stub
		Session session = this.getSession();
		getHibernateTemplate().delete(zqCustomerModel);
	}

	@Override
	public int countContByCust(Integer cust_id) {
		// TODO Auto-generated method stub
		return ((Long)getHibernateTemplate().find(s_SELECT_CONTINCUST_COUNT,cust_id).iterator().next()).intValue();
	}

	@Override
	public int queryCountContInCust(Integer cust_id) {
		// TODO Auto-generated method stub
		return ((Long)getHibernateTemplate().find(s_SELECT_COUNT_CONTINCUST,cust_id).iterator().next()).intValue();
	}

}
