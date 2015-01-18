package com.customerManage.business.ebo;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.customerManage.business.ebi.CustomerManageEbi;
import com.customerManage.dao.dao.CustomerManageDao;
import com.customerManage.vo.ZqCustomerModel;
import com.util.EcPageHelper;
import com.util.Log4j;
import com.util.PageBean;

/**
 * 客户管理业务处实现
 * @author peng
 * @since 2013-9-9下午07:32:37
 */
public class CustomerManageEbo extends EcPageHelper implements CustomerManageEbi {
	private CustomerManageDao customerManageDao;
	
	public CustomerManageDao getCustomerManageDao() {
		return customerManageDao;
	}

	public void setCustomerManageDao(CustomerManageDao customerManageDao) {
		this.customerManageDao = customerManageDao;
	}
	/* (non-Javadoc)
	 * @see com.customerManage.business.ebi.CustomerManageEbi#addCustomer(com.customerManage.vo.ZqCustomerModel)
	 */
	@Override
	public String addCustomer(ZqCustomerModel zqCustomerModel) {
		// TODO Auto-generated method stub
		//客户已经存在
		if (checkCustIfExist(zqCustomerModel.getName())) {
			return "0";
		}
		try {
			customerManageDao.saveCustomerByModel(zqCustomerModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "2";
			
		}
		return "1";
	}
	
	/**
	 * 检测是否有同名客户已经存在
	 * @param name
	 * @return true是 false否
	 */
	private boolean checkCustIfExist(String cust_name){
		List customerList = customerManageDao.findCustByName(cust_name);
		if(customerList == null || customerList.size()==0){
			return false;
		}
		return true;
	}
	
	@Override
	public PageBean searchCustomerByType(Map conditionMap) {
		// TODO Auto-generated method stub
		PageBean pageBean = getPageBean(conditionMap);
		conditionMap.put("pageBean", pageBean);
		return customerManageDao.queryCustomerList(conditionMap);
	}
	
	

	@Override
	public ZqCustomerModel getCustById(Integer cust_id) {
		// TODO Auto-generated method stub
		return customerManageDao.queryCustById(cust_id);
	}

	@Override
	public boolean modifyCustomer(ZqCustomerModel zqCustomerModel) {
		// TODO Auto-generated method stub
		try{
			customerManageDao.saveModify(zqCustomerModel);
		}catch (DataAccessException e) {
			// TODO: handle exception
			Log4j.errorLog(this,e);
			return false;
		}
		return true;
	}

	@Override
	public String deltCust(Integer cust_id) {
		// TODO Auto-generated method stub
		String result = "0";
		if (cust_id != null) {
			//检测客户是否已有合同
			if (!checkTheCustIfExistCont(cust_id)) {
				ZqCustomerModel zqCustomerModel = new ZqCustomerModel();
				zqCustomerModel.setId(cust_id);
				try {
					customerManageDao.deltCust(zqCustomerModel);
					result = "1";
				} catch (Exception e) {
					// TODO: handle exception
					Log4j.errorLog(this, e);
				}
			}else{
				result = "2";
			}
		}
		return result;
	}

	/**
	 * 
	 * @param cust_id
	 * @return 已存在true 否则false
	 */
	private boolean checkTheCustIfExistCont(Integer cust_id) {
		// TODO Auto-generated method stub
		return customerManageDao.queryCountContInCust(cust_id)==0?false:true;
	}

	@Override
	public String expiredCust(Integer cust_id) {
		// TODO Auto-generated method stub
		String result = "1";
		//检测用户是否存在未完结案件
		int contCount = customerManageDao.countContByCust(cust_id);
		//用户存在未完结案件
		if(contCount!=0){
			result = "0";
		} else {
			ZqCustomerModel zqCustomerModel = getCustById(cust_id);
			// 置为过期
			zqCustomerModel.setIsnow("0");
			try {
				customerManageDao.saveModify(zqCustomerModel);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				Log4j.errorLog(this,e);
				result = "2";
			}
		}
		return result;
	}

	@Override
	public String enableCust(Integer cust_id) {
		// TODO Auto-generated method stub
		String result = "0";
		ZqCustomerModel zqCustomerModel = getCustById(cust_id);
		// 置为当前1
		zqCustomerModel.setIsnow("1");
		try {
			customerManageDao.saveModify(zqCustomerModel);
			result = "1";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Log4j.errorLog(this, e);
		}

		return result;
	}

	
	

}
