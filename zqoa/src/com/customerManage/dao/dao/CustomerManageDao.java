package com.customerManage.dao.dao;

import java.util.List;
import java.util.Map;

import com.customerManage.vo.ZqCustomerModel;
import com.util.PageBean;

/**
 * 客户管理持久层接口
 * @author peng
 * @since 2013-9-9下午07:35:29
 */
public interface CustomerManageDao {
	/**
	 * 根据客户名称查询客户信息
	 * @param cust_name
	 * @return
	 */
	public List<ZqCustomerModel> findCustByName(String cust_name);

	/**
	 * 通过客户Model新增客户
	 * @param zqCustomerModel
	 * @throws Exception 
	 */
	public void saveCustomerByModel(ZqCustomerModel zqCustomerModel) throws Exception;
	
	/**
	 * 查询客户信息
	 * @param conditionMap 
	 * @return
	 */
	public PageBean queryCustomerList(Map conditionMap);

	/**
	 * 查询客户详细信息
	 * @param cust_id
	 * @return
	 */
	public ZqCustomerModel queryCustById(Integer cust_id);
	
	/**
	 * 保存修改过后的信息
	 * @param zqCustomerModel
	 */
	public void saveModify(ZqCustomerModel zqCustomerModel);
	
	/**
	 * 删除用户信息
	 * @param zqCustomerModel
	 */
	public void deltCust(ZqCustomerModel zqCustomerModel);

	/**
	 * 统计客户未完结的合同个数
	 * @param cust_id
	 * @return
	 */
	public int countContByCust(Integer cust_id);

	/**
	 * 统计客户的合同个数
	 * @param cust_id 客户id
	 * @return
	 */
	public int queryCountContInCust(Integer cust_id);
}
