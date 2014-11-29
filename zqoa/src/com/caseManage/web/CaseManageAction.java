package com.caseManage.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.json.annotations.JSON;

import com.caseManage.business.ebi.CaseManageEbi;
import com.caseManage.vo.ZqCaseModel;
import com.caseManage.vo.ZqCaseTypeModel;
import com.caseManage.vo.ZqCasecontactModel;
import com.caseManage.vo.ZqCaseprocessModel;
import com.caseManage.vo.ZqContactidentityModel;
import com.contManage.business.ebi.ContracterManageEbi;
import com.contManage.vo.ZqContractModel;
import com.contManage.vo.ZqContractcoscusModel;
import com.customerManage.vo.ZqCustomerModel;
import com.login.vo.ZqUserModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.regexp.internal.REUtil;
import com.util.ExtremeTablePage;
import com.util.MethodUtils;
import com.util.PageBean;
import com.util.SystemProperties;

/**
 * 案件管理控制类
 * @author peng
 * @since 2013-10-7上午11:17:39
 */
public class CaseManageAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private CaseManageEbi caseManageEbi;
	private ContracterManageEbi contracterManageEbi;
	private HttpServletRequest request;
	
	private ZqContactidentityModel zqContactidentityModel;
	//案件信息
	private ZqCaseModel zqCaseModel;
	//案件事件
	private ZqCaseprocessModel zqCaseprocessModel;
	//案件事件附件id集合(以逗号(,)进行分隔)
	private String fileids;
	//要删除的附件的id集合
	private Integer[] deltFileIds;
	//联系人身份id
	private Integer identityId;
	//案件联系人
	private ZqCasecontactModel zqCasecontactModel;
	//案件联系人id
	private Integer caseContactId;
	
	private ZqCaseTypeModel zqCaseTypeModel;
	//案件协办律师集合
	private Integer[] lawyerId;
	//案件类型id
	private Integer typeId;
	//案件事件id
	private String processId;
	
	//案件id
	private Integer caseId;
	//案件事件id
	private String caseProcessId;
	//案件名称
	private String caseName;
	private String status;
	private String info;
	
	@SuppressWarnings("unchecked")
	public String caseContactIdentity(){
		Map conditionMap = new HashMap();
		//初始化并获取limit
		conditionMap.put("_limit", ExtremeTablePage.getLimit(request, 10));
		PageBean pageBean = caseManageEbi.getCaseIdentityList(conditionMap);
		ActionContext.getContext().getValueStack().set("contactIdentityList", pageBean.getList());
		ActionContext.getContext().getValueStack().set("totalRows", pageBean.getTotalRows());

		return SUCCESS;
	}
	/**
	 * 添加案件联系人身份
	 * @return
	 */
	public String addCaseIdentity(){
		this.status = caseManageEbi.addCaseIdentity(zqContactidentityModel);
		if("0".equals(this.status)){
			this.info = "添加失败，请稍后重试!";
		}else if("1".equals(this.status)){
			this.info = "添加成功!";
		}else if("2".equals(this.status)){
			this.info = SystemProperties.getPropsValue("caseidentity_repeat");
		}
		return SUCCESS;
	}
	/**
	 * 修改案件联系人身份
	 */
	public String modifyCaseIdentity(){
		this.status = caseManageEbi.modifyCaseIdentity(zqContactidentityModel);
		if("0".equals(this.status)){
			this.info = "修改失败，请稍后重试!";
		}else if("1".equals(this.status)){
			this.info = "修改成功!";
		}else if("2".equals(this.status)){
			this.info = SystemProperties.getPropsValue("caseidentity_repeat");
		}
		return SUCCESS;
	}
	/**
	 * 删除案件联系人身份
	 */
	public String deltCaseIdentity(){
		this.status = caseManageEbi.deltCaseIdentity(identityId);
		
		if("0".equals(this.status)){
			this.info = "删除失败，请稍后重试!";
		}else if("1".equals(this.status)){
			this.info = "删除成功!";
		}else if("2".equals(this.status)){
			this.info = SystemProperties.getPropsValue("caseidentity_deltfailed");
		}
		return SUCCESS;
	}
	/**
	 * 案件类型管理
	 */
	public String listCaseType(){
		Map conditionMap = new HashMap();
		//初始化并获取limit
		conditionMap.put("_limit", ExtremeTablePage.getLimit(request, 10));
		PageBean pageBean = caseManageEbi.getCaseTypeList(conditionMap);
		ActionContext.getContext().getValueStack().set("caseTypeList", pageBean.getList());
		ActionContext.getContext().getValueStack().set("totalRows", pageBean.getTotalRows());

		return SUCCESS;
	}
	
	/**
	 * 添加案件类型
	 */
	public String addCaseType(){
		this.status = caseManageEbi.addCaseType(zqCaseTypeModel);
		if("0".equals(this.status)){
			this.info = "添加失败，请稍后重试!";
		}else if("1".equals(this.status)){
			this.info = "添加成功!";
		}else if("2".equals(this.status)){
			this.info = SystemProperties.getPropsValue("caseidentity_repeat");
		}
		return SUCCESS;
	}
	/**
	 * 修改案件类型
	 * @return
	 */
	public String modifyCaseType()throws Exception{
		this.status = caseManageEbi.modifyCaseType(zqCaseTypeModel);
		if("0".equals(this.status)){
			this.info = "修改失败，请稍后重试!";
		}else if("1".equals(this.status)){
			this.info = "修改成功!";
		}else if("2".equals(this.status)){
			this.info = SystemProperties.getPropsValue("caseidentity_repeat");
		}
		return SUCCESS;
	}
	/**
	 * 删除案件类型
	 * @return
	 */
	public String deltCaseType(){
		this.status = caseManageEbi.deltCaseType(typeId);
		
		if("0".equals(this.status)){
			this.info = "删除失败，请稍后重试!";
		}else if("1".equals(this.status)){
			this.info = "删除成功!";
		}else if("2".equals(this.status)){
			this.info = SystemProperties.getPropsValue("casetype_deltfailed");
		}
		return SUCCESS;
	}
	/**
	 * 添加案件初始化
	 * @return
	 */
	public String addCaseInit(){
		//获取所有的案件类型
		List<ZqCaseTypeModel> zqCaseTypeModels = caseManageEbi.getAllCaseType();
		ActionContext.getContext().getValueStack().set("casetype", zqCaseTypeModels);
		//获取所有案件身份
		List<ZqContactidentityModel> zqContactidentityModels = caseManageEbi.getAllCaseIdentity();
		ActionContext.getContext().getValueStack().set("caseidentity", zqContactidentityModels);
		//获取所有的当前的在职的用户
		List<ZqUserModel> zqUserModels = contracterManageEbi.getAllUser();
		ActionContext.getContext().getValueStack().set("lawyer",zqUserModels); 
		//根据当前登录用户的权限信息获取相应的合同
		List<ZqContractModel> zqContractModels = caseManageEbi.getContCreatByUser(MethodUtils.getUserInfoModel());
		ActionContext.getContext().getValueStack().set("cont",zqContractModels); 
		
		return SUCCESS;
	}

	/**
	 * 添加案件
	 * @return
	 */
	public String addCase()throws Exception{
		ZqUserModel zqUserModel = MethodUtils.getUserInfoModel();
		
		this.status = caseManageEbi.addCase(zqCaseModel,lawyerId,zqUserModel);
		return SUCCESS;
	}
	/**
	 * 获取关联案件信息
	 */
	@JSON(serialize=false)
	public String getRelativeCase(){
		List<ZqCaseModel> zqCaseModels = caseManageEbi.getCaseByName(caseName);
		ActionContext.getContext().getValueStack().set("realtivecase", zqCaseModels);
		return SUCCESS;
	}
	/**
	 * 查看案件列表
	 * @return
	 */
	public String listCase(){
		Map conditionMap = new HashMap();
		//查询条件，查询未完结案件
		conditionMap.put("isEnd", 0);
		//0 非合同前
		conditionMap.put("isBefore", 0);
		//初始化并获取limit
		conditionMap.put("_limit", ExtremeTablePage.getLimit(request, 10));

		PageBean pageBean = caseManageEbi.listCaseByUser(conditionMap);
		ActionContext.getContext().getValueStack().set("caselist", pageBean.getList());
		ActionContext.getContext().getValueStack().set("totalRows",pageBean.getTotalRows());
		return SUCCESS;
	}
	/**
	 * 查看案件详情
	 * @return
	 */
	public String caseDetail(){
		//案件详情
		ZqCaseModel zqCaseModel = caseManageEbi.getCaseById(caseId);
		ActionContext.getContext().getValueStack().set("casemess", zqCaseModel);
		if (zqCaseModel != null) {
			// 案件协办律师
			List<ZqContractcoscusModel> zqContractcoscusModels = caseManageEbi
					.getCoscusByCaseId(zqCaseModel.getId());
			ActionContext.getContext().getValueStack()
					.set("coscus", zqContractcoscusModels);
		}
		return SUCCESS;
	}
	/**
	 * 添加案件事件初始化
	 * @return
	 */
	public String addCaseProcessInit(){
		return SUCCESS;
	}
	/**
	 * 添加案件事件
	 * @return
	 */
	public String addCaseProcess()throws Exception{
		this.status = caseManageEbi.addCaseProcess(zqCaseprocessModel,fileids);
		if("1".equals(status)){
			this.info = "添加成功!";
		}else{
			this.info = "添加失败，请稍后重试!";
		}
		return SUCCESS;
	}
	///修改案件事件初始化
	public String modifyCaseProcessInit(){
		ZqCaseprocessModel zqCaseprocessModel = caseManageEbi.getCaseProcessById(processId);
		ActionContext.getContext().getValueStack().set("caseProcess", zqCaseprocessModel);
		return SUCCESS;
	}
	
	/**
	 * 修改案件事件信息
	 */
	public String modifyCaseProcess(){
		this.status = caseManageEbi.modifyCaseProcess(zqCaseprocessModel,fileids,deltFileIds);
		if("1".equals(status)){
			this.info = "修改成功!";
		}else {
			this.info = "修改失败，请稍后重试!";
		}
		return SUCCESS;
	}
	/**
	 * 删除案件事件byid
	 * @return
	 */
	public String deltCaseProcess(){
		this.status = caseManageEbi.deltCaseProcessById(caseProcessId);
		if("1".equals(status)){
			this.info = "删除成功!";
		}else{
			this.info = "删除失败，请稍后重试!";
		}
		return SUCCESS;
	}
	/**
	 * 添加案件联系人初始化
	 * @return
	 */
	public String addCaseContactInit(){
		//获取所有案件联系人身份
		List<ZqContactidentityModel> zqContactidentityModels = caseManageEbi.getAllCaseIdentity();
		
		ActionContext.getContext().getValueStack().set("identity", zqContactidentityModels);
		return SUCCESS;
		
	}
	/**
	 * 添加案件联系人
	 * @return
	 */
	public String addCaseContact(){
		this.status = caseManageEbi.addCaseContact(zqCasecontactModel);
		if("1".equals(status)){
			this.info="添加成功!";
		}else {
			this.info = "添加失败，请稍后重试!";
		}
		return SUCCESS;
	}
	/**
	 * 删除案件联系人
	 * @return
	 */
	public String deltCaseContact(){
		this.status = caseManageEbi.deltCaseContactById(caseContactId);
		if("1".equals(status)){
			this.info="删除成功!";
		}else {
			this.info = "删除失败，请稍后重试!";
		}
		return SUCCESS;
	}
	/**
	 * 修改案件联系人初始化
	 */
	public String modifyCaseContacterInit(){
		//获取所有案件联系人身份
		List<ZqContactidentityModel> zqContactidentityModels = caseManageEbi.getAllCaseIdentity();
		
		ActionContext.getContext().getValueStack().set("identity", zqContactidentityModels);
		
		ZqCasecontactModel zqCasecontactModel = caseManageEbi.getCaseContactById(caseContactId);
		ActionContext.getContext().getValueStack().set("contacter", zqCasecontactModel);
		return SUCCESS;
	}
	/**
	 * 修改案件联系人
	 */
	public String modifyCaseContact(){
		this.status = caseManageEbi.modifyCaseContact(zqCasecontactModel);
		if("1".equals(status)){
			this.info="修改成功!";
		}else {
			this.info = "修改失败，请稍后重试!";
		}
		return SUCCESS;
	}
	/**
	 * 查看完结案件
	 * @return
	 */
	public String listEndCase(){
		Map conditionMap = new HashMap();
		//查询条件，查询已完结案件
		conditionMap.put("isEnd", 1);
		//初始化并获取limit
		conditionMap.put("_limit", ExtremeTablePage.getLimit(request, 10));

		PageBean pageBean = caseManageEbi.listCaseByUser(conditionMap);
		ActionContext.getContext().getValueStack().set("caselist", pageBean.getList());
		ActionContext.getContext().getValueStack().set("totalRows",pageBean.getTotalRows());
		return SUCCESS;
	}
	/**
	 * 结案
	 * @return
	 */
	public String closeCase(){
		this.status = caseManageEbi.closeCase(caseId,1);
		return SUCCESS;
	}
	/**
	 * 出档
	 * @return
	 */
	public String openCase(){
		this.status = caseManageEbi.closeCase(caseId,0);
		return SUCCESS;
	}
	/**
	 * 删除案件
	 * @return
	 */
	public String deltCase(){
		this.status = caseManageEbi.deltCaseById(caseId);
		return SUCCESS;
	}
	/**
	 * 修改案件信息初始化
	 * @return
	 */
	public String modifyCaseMessInit(){
		//案件详情
		ZqCaseModel zqCaseModel = caseManageEbi.getCaseMessById(caseId);
		ActionContext.getContext().getValueStack().set("casemess", zqCaseModel);
		if (zqCaseModel != null) {
			// 案件协办律师
			List<ZqContractcoscusModel> zqContractcoscusModels = caseManageEbi
					.getCoscusByCaseId(zqCaseModel.getId());
			ActionContext.getContext().getValueStack()
					.set("coscus", zqContractcoscusModels);
		}
		//获取所有的案件类型
		List<ZqCaseTypeModel> zqCaseTypeModels = caseManageEbi.getAllCaseType();
		ActionContext.getContext().getValueStack().set("casetype", zqCaseTypeModels);
		//获取所有案件身份
		List<ZqContactidentityModel> zqContactidentityModels = caseManageEbi.getAllCaseIdentity();
		ActionContext.getContext().getValueStack().set("caseidentity", zqContactidentityModels);
		//获取所有的当前的在职的用户
		List<ZqUserModel> zqUserModels = contracterManageEbi.getAllUser();
		ActionContext.getContext().getValueStack().set("lawyer",zqUserModels); 
		return SUCCESS;
	}
	/**
	 * 保存修改的案件信息
	 */
	public String modifyCase(){
		ZqUserModel zqUserModel = MethodUtils.getUserInfoModel();
		
		this.status = caseManageEbi.modifyCaseMessById(zqCaseModel,lawyerId,zqUserModel);
		return SUCCESS;
	}
	/**
	 * 案件联系人信息导入初始化
	 * @return
	 */
	public String importCaseContactInit(){
		
		
		return SUCCESS;
	}
	/**
	 * 添加合同前案件初始化
	 * @return
	 */
	public String addCaseBeforeContInit(){
		//获取所有的案件类型
		List<ZqCaseTypeModel> zqCaseTypeModels = caseManageEbi.getAllCaseType();
		ActionContext.getContext().getValueStack().set("casetype", zqCaseTypeModels);
		//获取所有案件身份
		List<ZqContactidentityModel> zqContactidentityModels = caseManageEbi.getAllCaseIdentity();
		ActionContext.getContext().getValueStack().set("caseidentity", zqContactidentityModels);
		//获取所有的当前的在职的用户
		List<ZqUserModel> zqUserModels = contracterManageEbi.getAllUser();
		ActionContext.getContext().getValueStack().set("lawyer",zqUserModels); 
		//获取当前客户
		List<ZqCustomerModel> zqCustomerModels = contracterManageEbi.getAllNowCust();
		ActionContext.getContext().getValueStack().set("cust",zqCustomerModels); 
		return SUCCESS;
	}
	/**
	 * 查看合同前案件
	 * @return
	 */
	public String listCaseBeforeCont(){
		Map conditionMap = new HashMap();
		//查询条件，查询未完结的合同前案件
		conditionMap.put("isEnd", 0);
		//1：是合同前
		conditionMap.put("isBefore", 1);
		//初始化并获取limit
		conditionMap.put("_limit", ExtremeTablePage.getLimit(request, 10));

		PageBean pageBean = caseManageEbi.listCaseByUser(conditionMap);
		ActionContext.getContext().getValueStack().set("caselist", pageBean.getList());
		ActionContext.getContext().getValueStack().set("totalRows",pageBean.getTotalRows());
		return SUCCESS;
	}
	/**
	 * 合同前案件详情
	 * @return
	 */
	public String caseBeforeContDetail(){
		//案件详情
		ZqCaseModel zqCaseModel = caseManageEbi.getCaseBeforeContById(caseId);
		ActionContext.getContext().getValueStack().set("casemess", zqCaseModel);
		if (zqCaseModel != null) {
			// 案件协办律师
			List<ZqContractcoscusModel> zqContractcoscusModels = caseManageEbi
					.getCoscusByCaseId(zqCaseModel.getId());
			ActionContext.getContext().getValueStack()
					.set("coscus", zqContractcoscusModels);
		}
		return SUCCESS;
	}
	@JSON(serialize=false)
	public String getCaseProcessId() {
		return caseProcessId;
	}
	public void setCaseProcessId(String caseProcessId) {
		this.caseProcessId = caseProcessId;
	}
	@JSON(serialize=false)
	public ZqCaseprocessModel getZqCaseprocessModel() {
		return zqCaseprocessModel;
	}
	public void setZqCaseprocessModel(ZqCaseprocessModel zqCaseprocessModel) {
		this.zqCaseprocessModel = zqCaseprocessModel;
	}
	@JSON(serialize=false)
	public Integer getCaseId() {
		return caseId;
	}
	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}
	@JSON(serialize=false)
	public Integer[] getDeltFileIds() {
		return deltFileIds;
	}
	public void setDeltFileIds(Integer[] deltFileIds) {
		this.deltFileIds = deltFileIds;
	}
	@JSON(serialize=false)
	public Integer[] getLawyerId() {
		return lawyerId;
	}
	public void setLawyerId(Integer[] lawyerId) {
		this.lawyerId = lawyerId;
	}
	@JSON(serialize=false)
	public ZqCaseModel getZqCaseModel() {
		return zqCaseModel;
	}
	public void setZqCaseModel(ZqCaseModel zqCaseModel) {
		this.zqCaseModel = zqCaseModel;
	}
	@JSON(serialize=false)
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	@JSON(serialize=false)
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	@JSON(serialize=false)
	public ContracterManageEbi getContracterManageEbi() {
		return contracterManageEbi;
	}
	public void setContracterManageEbi(ContracterManageEbi contracterManageEbi) {
		this.contracterManageEbi = contracterManageEbi;
	}
	@JSON(serialize=false)
	public ZqCaseTypeModel getZqCaseTypeModel() {
		return zqCaseTypeModel;
	}
	public void setZqCaseTypeModel(ZqCaseTypeModel zqCaseTypeModel) {
		this.zqCaseTypeModel = zqCaseTypeModel;
	}
	@JSON(serialize=false)
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	@JSON(serialize=false)
	public Integer getIdentityId() {
		return identityId;
	}
	public void setIdentityId(Integer identityId) {
		this.identityId = identityId;
	}
	@JSON(serialize=false)
	public CaseManageEbi getCaseManageEbi() {
		return caseManageEbi;
	}
	public void setCaseManageEbi(CaseManageEbi caseManageEbi) {
		this.caseManageEbi = caseManageEbi;
	}
	@JSON(serialize=false)
	public ZqContactidentityModel getZqContactidentityModel() {
		return zqContactidentityModel;
	}
	public void setZqContactidentityModel(
			ZqContactidentityModel zqContactidentityModel) {
		this.zqContactidentityModel = zqContactidentityModel;
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
	public String getFileids() {
		return fileids;
	}
	public void setFileids(String fileids) {
		this.fileids = fileids;
	}
	@JSON(serialize=false)
	public ZqCasecontactModel getZqCasecontactModel() {
		return zqCasecontactModel;
	}
	public void setZqCasecontactModel(ZqCasecontactModel zqCasecontactModel) {
		this.zqCasecontactModel = zqCasecontactModel;
	}
	@JSON(serialize=false)
	public Integer getCaseContactId() {
		return caseContactId;
	}
	public void setCaseContactId(Integer caseContactId) {
		this.caseContactId = caseContactId;
	}
	

}
