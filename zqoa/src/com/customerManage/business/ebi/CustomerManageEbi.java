package com.customerManage.business.ebi;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.customerManage.vo.ZqCustomerModel;
import com.util.PageBean;

/**
 * 客户管理业务处接口
 * @author peng
 * @since 2013-9-9下午07:30:45
 */
public interface CustomerManageEbi {
	/**
	 * 添加客户
	 * @param zqCustomerModel
	 * @return 0：该客户已存在, 1：添加成功 2:系统异常
	 */
	public String addCustomer(ZqCustomerModel zqCustomerModel);
	
	/**
	 * 根据客户类型获取客户信息
	 * @param conditionMap 客户条件
	 * @return
	 */
	public PageBean searchCustomerByType(Map conditionMap);
	/**
	 * 根据用户id获取用户详情信息
	 * @param cust_id
	 * @return
	 */
	public ZqCustomerModel getCustById(Integer cust_id);

	/**
	 * 保存客户修改过后的信息
	 * @param zqCustomerModel
	 * @return
	 */
	public boolean modifyCustomer(ZqCustomerModel zqCustomerModel);
	/**
	 * 删除用户信息
	 * @param cust_id 用户id
	 * @return 成功1 失败 0 已有合同2
	 */
	public String deltCust(Integer cust_id);

	/**
	 * 将客户归入过期
	 * @param cust_id 0：用户存在未完结案件 1：归入成功 2:数据库异常
	 * @return
	 */
	public String expiredCust(Integer cust_id);

	/**
	 * 启用客户
	 * @param cust_id
	 * @return 0：启用失败 1：启用成功
	 */
	public String enableCust(Integer cust_id);
}
