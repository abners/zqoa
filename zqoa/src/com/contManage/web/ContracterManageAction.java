package com.contManage.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.json.annotations.JSON;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.FileManage.vo.ZqBusFileModel;
import com.FileManage.vo.ZqFileModel;
import com.contManage.business.ebi.ContracterManageEbi;
import com.contManage.vo.ZqContractAffairModel;
import com.contManage.vo.ZqContractModel;
import com.contManage.vo.ZqContractcharagestageModel;
import com.contManage.vo.ZqContractcoscusModel;
import com.contManage.vo.ZqContracttypeModel;
import com.customerManage.vo.ZqCustomerModel;
import com.login.vo.ZqUserModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.util.ExtremeTablePage;
import com.util.JSONUtil;
import com.util.Log4j;
import com.util.MethodUtils;
import com.util.PageBean;
import com.util.SystemProperties;

/**
 * 合同信息管理控制类
 * @author peng
 * @since 2013-9-15下午07:52:58
 */
/**
 *
 * @author peng
 * @since 2013-12-10上午12:11:38
 */
public class ContracterManageAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private ContracterManageEbi contracterManageEbi;
	private HttpServletRequest request;
	private ZqContracttypeModel zqContracttypeModel;
	private ZqContractModel zqContractModel;
	private ZqContractAffairModel zqContractAffairModel;
	private ZqBusFileModel zqBusFileModel; 
	
	//协办律师集合
	private Integer[] lawyerId;
	private String[] lawyerName;
	//付费情况集合
	private Integer[] pay;
	private String[] payTime;
	//实际付费集合
	private Integer[] payMoney;
	//应付费情况结合
	private Integer[] chargeMoney;
	private String[] chargeTime;
	
	private String status;
	private String info;
	//合同id
	private Integer contId;
	//协办律师记录id
	private Integer coscusid;
	
	//付费阶段id集合
	private Integer[] chargeId;
	//合同类别id
	private Integer contTypeId;
	//
	private String jsonString;
	//附件id集合
	private String fileids;
	//合同附件id
	private Integer fileId;
	
	/**
	 * 分页获取合同类型
	 * @return
	 */
	public String listContractType(){
		Map conditionMap = new HashMap();
		//初始化并获取limit
		conditionMap.put("_limit", ExtremeTablePage.getLimit(request, 10));
		PageBean pageBean = contracterManageEbi.getCotracterTypeList(conditionMap);
		ActionContext.getContext().getValueStack().set("contType_Mess", pageBean.getList());
		ActionContext.getContext().getValueStack().set("totalRows", pageBean.getTotalRows());
		
		return SUCCESS;
	}
	/**
	 * 添加合同类型
	 * @return
	 */
	public String addContractType() {
		//操作人id
		zqContracttypeModel.setOperator(MethodUtils.getUserInfoModel().getId());
		String reslut = contracterManageEbi.addContractType(zqContracttypeModel);
		if(reslut.equals("1")){
			this.status = "1";
			this.info = SystemProperties.getPropsValue("conttypesuccess");
		}else if (reslut.equals("0")) {
			this.status = "0";
			this.info ="添加失败，请稍后重试!";
		}else {
			this.status = "0";
			//重复添加
			this.info = SystemProperties.getPropsValue("conttype_failedreason");
		}
		return SUCCESS;
	}
	/**
	 * 修改合同类型信息
	 * @return
	 * 
	 */
	public String modifyContractType(){
		//操作人id
		zqContracttypeModel.setOperator(MethodUtils.getUserInfoModel().getId());
		String reslut = contracterManageEbi.addContractType(zqContracttypeModel);
		if(reslut.equals("1")){
			this.status = "1";
			this.info = SystemProperties.getPropsValue("conttype_successmodify");
		}else if (reslut.equals("0")) {
			this.status = "0";
			this.info ="修改失败，请稍后重试!";
		}else {
			this.status = "0";
			//合同类型名称重复
			this.info = SystemProperties.getPropsValue("conttype_failedmodify");
		}
		return SUCCESS;
	}
	/**
	 * 删除合同类型信息
	 * @return
	 * 
	 */
	public String deltContractType(){
		String reslut = contracterManageEbi.deltContractType(zqContracttypeModel);
		if(reslut.equals("1")){
			this.status = "1";
			this.info = "删除成功!";
		}else if (reslut.equals("0")) {
			this.status = "0";
			this.info ="删除失败，请稍后重试!";
		}else {
			this.status = "0";
			//合同类型已经存在关联的合同
			this.info = SystemProperties.getPropsValue("conttype_failedmdelt");
		}
		
		return SUCCESS;
	}
	/**
	 *添加合同初始化
	 * @return
	 */
	public String addContractInit(){
		//获取所有的合同类型
		List<ZqContracttypeModel> zqContracttypeModels = contracterManageEbi.getAllCotracterType();
		ActionContext.getContext().getValueStack().set("conttype", zqContracttypeModels);
		//获取所有的律师
		List<ZqUserModel> zqUserModels = contracterManageEbi.getAllUser();
		ActionContext.getContext().getValueStack().set("users",zqUserModels);
		
		//获取所有的当前客户
		List<ZqCustomerModel> zqCustomerModels = contracterManageEbi.getAllNowCust();
		ActionContext.getContext().getValueStack().set("cust", zqCustomerModels);
		return SUCCESS;
	}
	/**
	 * 获取协办律师
	 * @return
	 */
	@JSON(serialize=false)
	public String getCo_counsel(){
		//获取所有的律师
		List<ZqUserModel> zqUserModels = contracterManageEbi.getAllUser();
		ActionContext.getContext().getValueStack().set("users",zqUserModels);
		return SUCCESS;
	}
	
	/**
	 * 保存添加的合同信息
	 * @return
	 */
	public String addContract()throws Exception{
		//System.out.println(zqContractModel);
		//操作人id
		zqContractModel.setCreater(MethodUtils.getUserInfoModel().getId());
		
		String result = contracterManageEbi.addContract(zqContractModel,lawyerId,lawyerName,pay,payTime);
		this.status = result;
		return SUCCESS;
	}
	
	/**
	 * 分页获取合同信息
	 * @return
	 */
	public String listContract(){
		Map conditionMap = new HashMap();
		//未归档的合同
		conditionMap.put("archived","0");
		//初始化并获取limit
		conditionMap.put("_limit", ExtremeTablePage.getLimit(request, 10));
		conditionMap.put("USER_CODE", MethodUtils.getUserInfoModel().getId());
		PageBean pageBean = contracterManageEbi.getCotracterList(conditionMap);
		ActionContext.getContext().getValueStack().set("contlist", pageBean.getList());
		ActionContext.getContext().getValueStack().set("totalRows", pageBean.getTotalRows());
		return SUCCESS;
	}
	/**
	 * 查看合同详情
	 * @return
	 */
	public String contractDetail()throws Exception{
		//获取合同详细信息
		ZqContractModel zqContractModel = contracterManageEbi.getContractById(contId);
		ActionContext.getContext().getValueStack().set("contMess", zqContractModel);
		
		//获取收费信息
		List<ZqContractcharagestageModel> zqContractcharagestageModels = contracterManageEbi.getCharageStageByContid(contId);
		ActionContext.getContext().getValueStack().set("chargetStage", zqContractcharagestageModels);
		
		//获取该合同的协办律师信息
		List<ZqContractcoscusModel> zqContractcoscusModels = contracterManageEbi.getCoscusByContid(contId);
		ActionContext.getContext().getValueStack().set("coscus", zqContractcoscusModels);
		
		return SUCCESS;
	}
	/**
	 * 修改合同信息初始化
	 * @return
	 */
	public String modifyContInit(){
		//获取合同详细信息
		ZqContractModel zqContractModel = contracterManageEbi.getContractById(contId);
		ActionContext.getContext().getValueStack().set("contMess", zqContractModel);
		
		//获取所有的合同类型
		List<ZqContracttypeModel> zqContracttypeModels = contracterManageEbi.getAllCotracterType();
		ActionContext.getContext().getValueStack().set("conttype", zqContracttypeModels);
		//获取所有的律师
		List<ZqUserModel> zqUserModels = contracterManageEbi.getAllUser();
		ActionContext.getContext().getValueStack().set("users",zqUserModels);
		
		//获取所有的当前客户
		List<ZqCustomerModel> zqCustomerModels = contracterManageEbi.getAllNowCust();
		ActionContext.getContext().getValueStack().set("cust", zqCustomerModels);
		
		//获取该合同的协办律师信息
		List<ZqContractcoscusModel> zqContractcoscusModels = contracterManageEbi.getCoscusByContid(contId);
		ActionContext.getContext().getValueStack().set("coscus", zqContractcoscusModels);
		
		//获取合同的收费信息
		List<ZqContractcharagestageModel> zqContractcharagestageModels = contracterManageEbi.getCharageStageByContid(contId);
		ActionContext.getContext().getValueStack().set("chargestage", zqContractcharagestageModels);
		
		return SUCCESS;
	}
	/**
	 * 删除合同的某个协办律师
	 * @return
	 */
	public String deltCoscus(){
		this.status = contracterManageEbi.deltCoscusById(coscusid);
		return SUCCESS;
	}
	/**
	 * 更新合同信息
	 * @return
	 */
	public String updateContract()throws Exception{
		this.status = contracterManageEbi.updateContract(chargeId,zqContractModel,lawyerId,lawyerName,pay,payTime);
		return SUCCESS;
	}
	/**
	 * 删除合同信息
	 * @return
	 */
	public String deltContract(){
		String result = contracterManageEbi.deltContractById(contId);
		this.status = result;
		if(result.equals("0")){
			this.info = SystemProperties.getPropsValue("cont_faileddelt");
		}else if(result.equals("2")){
			this.info = SystemProperties.getPropsValue("cont_faileddelt_forcase");
		}
		return SUCCESS;
	}
	/**
	 * 合同事务登记初始化
	 * @return
	 */
	public String contAffairRegInit(){
		//获取所有合同类型
		List<ZqContracttypeModel> contracttypeModels = contracterManageEbi.getAllCotracterType();
		ActionContext.getContext().getValueStack().set("contType" , contracttypeModels);
		ActionContext.getContext().getValueStack().set("todaydate" , MethodUtils.getToDayDate("yyyy-MM-dd"));
		return SUCCESS;
	}
	/**
	 * 添加合同事务
	 * @return
	 */
	public String addContAffair(){
		//创建人
		zqContractAffairModel.setOperator(MethodUtils.getUserInfoModel().getId());
		
		this.status = contracterManageEbi.addContAffair(zqContractAffairModel);
		return SUCCESS;
	}
	/**
	 * 根据合同类型获取合同信息
	 * @return
	 * @throws IOException 
	 * @throws JsonGenerationException 
	 * @throws JsonMappingException 
	 */
	@JSON(serialize=false)
	public String getContractByTypeId() throws Exception{
		List<Map> contractModels = contracterManageEbi.getContractByTypeId(contTypeId);
		jsonString = JSONUtil.toJson(contractModels, false);
		return SUCCESS;
	}
	/**
	 * 获取某一合同下的所有事务
	 * @return
	 */
	public String contAffairView()throws Exception{
		List<ZqContractAffairModel> zqContractAffairModels = contracterManageEbi.viewAffairByContId(contId);
		ActionContext.getContext().getValueStack().set("contaffair", zqContractAffairModels);
		return SUCCESS;
	}
	/**
	 * 合同归档
	 * @return
	 */
	public String arachiveCont(){
		this.status = contracterManageEbi.arachiveCont(contId,"1");
		if("1".equals(status)){
			this.info = SystemProperties.getPropsValue("operate_success");
		}else {
			this.info = SystemProperties.getPropsValue("operate_failed");
		}
		return SUCCESS;
	}
	
	/**
	 * 档案列表
	 * @return
	 */
	public String listArchives(){
		Map conditionMap = new HashMap();
		//初始化并获取limit
		conditionMap.put("_limit", ExtremeTablePage.getLimit(request, 10));
		conditionMap.put("USER_CODE", MethodUtils.getUserInfoModel().getId());
		//已归档的合同
		conditionMap.put("archived","1");
		PageBean pageBean = contracterManageEbi.getCotracterList(conditionMap);
		ActionContext.getContext().getValueStack().set("contlist", pageBean.getList());
		ActionContext.getContext().getValueStack().set("totalRows", pageBean.getTotalRows());
		return SUCCESS;
	}
	/**
	 * 添加档案初始化
	 */
	public String addArchiveInit(){
		//获取所有的合同类型
		List<ZqContracttypeModel> zqContracttypeModels = contracterManageEbi.getAllCotracterType();
		ActionContext.getContext().getValueStack().set("conttype", zqContracttypeModels);
		//获取所有的律师
		List<ZqUserModel> zqUserModels = contracterManageEbi.getAllUser();
		ActionContext.getContext().getValueStack().set("users",zqUserModels);
		
		//获取所有的当前客户
		List<ZqCustomerModel> zqCustomerModels = contracterManageEbi.getAllNowCust();
		ActionContext.getContext().getValueStack().set("cust", zqCustomerModels);
		return SUCCESS;
	}
	/**
	 * 保存添加的档案
	 */
	public String addArchive(){
		
		//操作人id
		zqContractModel.setCreater(MethodUtils.getUserInfoModel().getId());
		
		String result = contracterManageEbi.addArchive(zqContractModel,lawyerId,lawyerName,pay,payTime);
		this.status = result;
		return SUCCESS;
	}
	/**
	 * 档案出档
	 * @return
	 */
	public String contArchive(){
		this.status = contracterManageEbi.arachiveCont(contId,"0");
		if("1".equals(status)){
			this.info = SystemProperties.getPropsValue("operate_success");
		}else {
			this.info = SystemProperties.getPropsValue("operate_failed");
		}
		return SUCCESS;
	}
	/**
	 * 添加合同附件业务信息
	 * @return
	 */
	public String addContFile(){
		this.status = contracterManageEbi.addContFile(fileids,zqBusFileModel);
		if("1".equals(status)){
			this.info = SystemProperties.getPropsValue("operate_success");
		}else {
			this.info = SystemProperties.getPropsValue("operate_failed");
		}
		return SUCCESS;
	}
	/**
	 * 删除合同附件信息
	 * @return
	 */
	public String deltContFile(){
		this.status = contracterManageEbi.deltContFileById(fileId);
		return SUCCESS;
	}
	/**
	 * 收费登记初始化
	 * @return
	 */
	public String regFeesInit(){
		List<ZqContractcharagestageModel> zqContractcharagestageModels = contracterManageEbi.getCharageStageByContid(contId);
		ActionContext.getContext().getValueStack().set("chargestage", zqContractcharagestageModels);
		return SUCCESS;
	}
	/**
	 * 保存收费登记结果
	 * @return
	 */
	public String regFees()throws Exception{
		try{
		this.status = contracterManageEbi.updateRegFees(contId,chargeId,payMoney,payTime,chargeTime,chargeMoney);
		}catch (Exception e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}
		return SUCCESS;
	}
	/**
	 * 律师承办查看，获取律师
	 * @return
	 * 
	 */
	public String lawyerSelect(){
		List<ZqUserModel> zqUserModels = contracterManageEbi.getAllUser();
		ActionContext.getContext().getValueStack().set("user", zqUserModels);
		return SUCCESS;
	}
	@JSON(serialize=false) 
	public Integer[] getChargeMoney() {
		return chargeMoney;
	}
	public void setChargeMoney(Integer[] chargeMoney) {
		this.chargeMoney = chargeMoney;
	}
	@JSON(serialize=false)
	public String[] getChargeTime() {
		return chargeTime;
	}
	public void setChargeTime(String[] chargeTime) {
		this.chargeTime = chargeTime;
	}
	@JSON(serialize=false)
	public Integer[] getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Integer[] payMoney) {
		this.payMoney = payMoney;
	}
	@JSON(serialize=false)
	public String getFileids() {
		return fileids;
	}
	
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public void setFileids(String fileids) {
		this.fileids = fileids;
	}
	@JSON(serialize=false)
	public ZqBusFileModel getZqBusFileModel() {
		return zqBusFileModel;
	}
	public void setZqBusFileModel(ZqBusFileModel zqBusFileModel) {
		this.zqBusFileModel = zqBusFileModel;
	}
	@JSON(serialize=false)
	public ZqContractAffairModel getZqContractAffairModel() {
		return zqContractAffairModel;
	}
	public void setZqContractAffairModel(ZqContractAffairModel zqContractAffairModel) {
		this.zqContractAffairModel = zqContractAffairModel;
	}
	@JSON(name="item")
	public String getJsonString() {
		return jsonString;
	}
	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
	@JSON(serialize=false)
	public Integer getContTypeId() {
		return contTypeId;
	}
	public void setContTypeId(Integer contTypeId) {
		this.contTypeId = contTypeId;
	}
	@JSON(serialize=false)
	public Integer[] getChargeId() {
		return chargeId;
	}
	public void setChargeId(Integer[] chargeId) {
		this.chargeId = chargeId;
	}
	@JSON(serialize=false)
	public Integer getCoscusid() {
		return coscusid;
	}
	public void setCoscusid(Integer coscusid) {
		this.coscusid = coscusid;
	}
	@JSON(serialize=false)
	public Integer getContId() {
		return contId;
	}
	public void setContId(Integer contId) {
		this.contId = contId;
	}
	@JSON(serialize=false)
	public Integer[] getPay() {
		return pay;
	}
	public void setPay(Integer[] pay) {
		this.pay = pay;
	}
	@JSON(serialize=false)
	public String[] getPayTime() {
		return payTime;
	}
	public void setPayTime(String[] payTime) {
		this.payTime = payTime;
	}
	@JSON(serialize=false)
	public Integer[] getLawyerId() {
		return lawyerId;
	}
	public void setLawyerId(Integer[] lawyerId) {
		this.lawyerId = lawyerId;
	}
	@JSON(serialize=false)
	public String[] getLawyerName() {
		return lawyerName;
	}
	public void setLawyerName(String[] lawyerName) {
		this.lawyerName = lawyerName;
	}
	@JSON(serialize=false)
	public ZqContractModel getZqContractModel() {
		return zqContractModel;
	}
	public void setZqContractModel(ZqContractModel zqContractModel) {
		this.zqContractModel = zqContractModel;
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
	public ContracterManageEbi getContracterManageEbi() {
		return contracterManageEbi;
	}
	public void setContracterManageEbi(ContracterManageEbi contracterManageEbi) {
		this.contracterManageEbi = contracterManageEbi;
	}
	@JSON(serialize=false)
	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	@JSON(serialize=false)
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	@JSON(serialize=false)
	public ZqContracttypeModel getZqContracttypeModel() {
		return zqContracttypeModel;
	}
	public void setZqContracttypeModel(ZqContracttypeModel zqContracttypeModel) {
		this.zqContracttypeModel = zqContracttypeModel;
	}
	
	

}
