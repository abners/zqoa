package com.customerManage.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.annotations.JSON;

import com.customerManage.business.ebi.CustomerManageEbi;
import com.customerManage.vo.ZqCustomerModel;
import com.login.vo.ZqUserModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.util.ExtremeTablePage;
import com.util.Log4j;
import com.util.MethodUtils;
import com.util.PageBean;
import com.util.SystemProperties;

/**
 * 客户管理控制类
 * @author peng
 * @since 2013-9-8下午09:10:58
 */
public class CustomerManageAction extends ActionSupport implements SessionAware,ServletRequestAware,ServletResponseAware{
	//客户对象
	private ZqCustomerModel zqCustomerModel;
	//操作状态：0操作失败，1操作成功
	private String status;
	//操作失败的信息
	private String info;
	//客户id
	private Integer cust_id;
	//当前第几页
	private int page;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private CustomerManageEbi customerManageEbi;
	//新增客户
	public String save(){
		if(zqCustomerModel!=null){
			ZqUserModel userModel = MethodUtils.getUserInfoModel();
			zqCustomerModel.setOperator(userModel.getId());
			Log4j.logMess("用户"+ userModel.getName() + "尝试添加客户:" + zqCustomerModel.getName());
			
			this.status = customerManageEbi.addCustomer(zqCustomerModel);
			//写入日志
			if (this.status.equals("1")) {
				Log4j.logMess("用户" + MethodUtils.getUserInfoModel().getName()
						+ "添加客户" + zqCustomerModel.getName() + "成功...");
				this.info = "客户添加成功!";
			} else if(this.status.equals("0")){
				Log4j.logMess("用户" + MethodUtils.getUserInfoModel().getName()
						+ "添加客户" + zqCustomerModel.getName() + "失败，失败原因重复添加...");
				this.info = "已存在同名客户，请更换客户名称后重试!";
			}else {
				this.info = "系统异常，请联系管理员!";
			}
		}else{
			Log4j.logMess("未知原因客户添加失败!");
			info = "客户添加失败,可能是网络异常，请稍后重试!";
			status = "2";
		}
		return SUCCESS;
	}
	
	/**
	 * 查询客户列表
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String listCustomer(){
		Map conditionMap = new HashMap();
		//当前客户
		conditionMap.put("isnow", "1");
		//当前第几页
		conditionMap.put("page",page);
		conditionMap.put("_limit", ExtremeTablePage.getLimit(request, 10));
		
		PageBean pageBean = customerManageEbi.searchCustomerByType(conditionMap);
		ActionContext.getContext().getValueStack().set("zqCustomerModels", pageBean.getList());
		ActionContext.getContext().getValueStack().set("totalRows", pageBean.getTotalRows());
		
		return SUCCESS;
	}
	
	/**
	 * 查看过期客户
	 */
	public String listExpiredCustomer(){
		Map conditionMap = new HashMap();
		//过期客户
		conditionMap.put("isnow", "0");
		//当前第几页
		conditionMap.put("page",page);
		conditionMap.put("_limit", ExtremeTablePage.getLimit(request, 10));
		
		PageBean pageBean = customerManageEbi.searchCustomerByType(conditionMap);
		ActionContext.getContext().getValueStack().set("zqCustomerModels", pageBean.getList());
		ActionContext.getContext().getValueStack().set("totalRows", pageBean.getTotalRows());
		
		return SUCCESS;
	}
	/**
	 * 查看客户详情
	 * @return
	 */
	public String customerDetail(){
		ZqUserModel userModel = MethodUtils.getUserInfoModel();
		ZqCustomerModel zqCustomerModel = customerManageEbi.getCustById(cust_id);
		Log4j.logMess("用户" + userModel.getName() + "尝试修改客户" + zqCustomerModel + "的信息....");
		ActionContext.getContext().getValueStack().set("cust_mess", zqCustomerModel);
		return SUCCESS;
	}
	
	/**
	 * 保存修改的用户信息
	 * @return
	 */
	public String saveModify(){
		ZqUserModel userModel = MethodUtils.getUserInfoModel();
		//操作用户id
		zqCustomerModel.setOperator(userModel.getId());
		boolean flag = customerManageEbi.modifyCustomer(zqCustomerModel);
		if(flag){
			this.status = "1";
			this.info = "更新成功!";
			Log4j.logMess("用户" + userModel.getName() + "修改客户:" + zqCustomerModel + "信息成功...");
		}else{
			this.status = "0";
			this.info = "更新失败!请稍后重试";
			Log4j.logMess("用户" + userModel.getName() + "修改客户:" + zqCustomerModel + "信息失败...");
		}
		return SUCCESS;
	}
	/**
	 * 删除客户信息
	 * @return
	 */
	public String deltCustomer(){
		try{
		String flag = customerManageEbi.deltCust(cust_id);
		if("1".equals(flag)){
			this.status = "1";
			this.info = "删除成功!";
			Log4j.logMess("用户" + MethodUtils.getUserInfoModel().getName() + "删除一位客户.....");
		}else if("2".equals(flag)){
			this.info = "该用户已有所属合同，无法删除!";
			this.status = "0";
		}else{
			this.status = "0";
		}
		}catch (Exception e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}
		return SUCCESS;
	}
	/**
	 * 将指定客户归入过期
	 * @return
	 */
	public String expiredCustomer(){
		String flag = customerManageEbi.expiredCust(cust_id);
		if(flag.equals("0")){
			//归入过期失败，客户有未完结的案件
			this.status = "0";
			this.info = SystemProperties.getPropsValue("expiredreason");
			
		}else if(flag.equals("1")){
			//归入过期成功
			this.status = "1";
			this.info = SystemProperties.getPropsValue("expiredsuccess");
		}else {
			this.status = "0";
			this.info = "归入失败，请稍后重试!";
		}
		return SUCCESS;
	}
	/**
	 * 启用指定过期客户
	 * @return
	 */
	public String enableCustomer(){
		String flag = customerManageEbi.enableCust(cust_id);
		if(flag.equals("0")){
			this.status = "0";
			this.info = "归入失败，请稍后重试!";
		}else {
			this.status = "1";
		}
		return SUCCESS;
	}
	@JSON(serialize=false)
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@JSON(serialize=false)
	public Integer getCust_id() {
		return cust_id;
	}

	public void setCust_id(Integer cust_id) {
		this.cust_id = cust_id;
	}

	@JSON(serialize=false)
	public CustomerManageEbi getCustomerManageEbi() {
		return customerManageEbi;
	}

	public void setCustomerManageEbi(CustomerManageEbi customerManageEbi) {
		this.customerManageEbi = customerManageEbi;
	}

	@JSON(name="status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@JSON(name="info")
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	@JSON(serialize=false)
	public ZqCustomerModel getZqCustomerModel() {
		return zqCustomerModel;
	}

	public void setZqCustomerModel(ZqCustomerModel zqCustomerModel) {
		this.zqCustomerModel = zqCustomerModel;
	}

	@JSON(serialize=false)
	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	
	@JSON(serialize=false)
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	@JSON(serialize=false)
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
