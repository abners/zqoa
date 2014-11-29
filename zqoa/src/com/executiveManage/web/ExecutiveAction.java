package com.executiveManage.web;

import java.util.List;

import org.apache.poi.hdf.extractor.data.ListTables;
import org.apache.struts2.json.annotations.JSON;

import com.caseManage.vo.ZqCaseprocessModel;
import com.contManage.business.ebi.ContracterManageEbi;
import com.contManage.web.ContracterManageAction;
import com.executiveManage.business.ebi.ExecutiveEbi;
import com.executiveManage.vo.IndexModel;
import com.executiveManage.vo.ZqAsrModel;
import com.executiveManage.vo.ZqNoticeModel;
import com.executiveManage.vo.ZqSerrecordModel;
import com.login.vo.ZqUserModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.personalManage.vo.ZqDepartmentModel;
import com.sun.org.apache.regexp.internal.recompile;
import com.util.MethodUtils;
import com.util.SystemProperties;

/**
 * 
 * @author peng
 * @since 2013-11-10上午11:27:09
 */
public class ExecutiveAction extends ActionSupport {
	private ExecutiveEbi executiveEbi;
	
	private ContracterManageEbi contracterManageEbi;
	private ZqAsrModel zqAsrModel;
	
	private ZqNoticeModel zqNoticeModel;
	
	private String status;
	private String info;
	
	//行政事务id
	private Integer asrId;
	
	//日期
	private String toDayDate;
	
	//事物记录
	private ZqSerrecordModel zqSerrecordModel;
	
	//用户id
	private Integer userId;
	
	/**
	 * 首页数据加载
	 * @return
	 */
	public String index(){
		//近期提醒
		List<ZqCaseprocessModel> zqCaseprocessModels = executiveEbi.getCaseProcess();
		ActionContext.getContext().getSession().put("caseprocess", zqCaseprocessModels);
		//承办的未结案件、已结案件、未结合同、已结合同
		IndexModel indexModels = executiveEbi.getAllThings();
		ActionContext.getContext().getSession().put("index", indexModels);
		return SUCCESS;
	}
	/**
	 * 律师承办信息查看
	 */
	public String lawyerCase_Cont_See(){
		List<ZqUserModel> zqUserModels = contracterManageEbi.getAllUser();
		ActionContext.getContext().getValueStack().set("user", zqUserModels);
		IndexModel indexModel = executiveEbi.queryAllUserCase_Cont(userId);
		ActionContext.getContext().getValueStack().set("index", indexModel);
		return SUCCESS;
	}
	/**
	 * 行政事务登记初始化
	 * @return
	 */
	public String asrInit(){
		//获取所有机构部门
		List<ZqDepartmentModel> zqDepartmentModels = executiveEbi.getAllDepartment();
		ActionContext.getContext().getValueStack().set("zqdepart", zqDepartmentModels);
		
		return SUCCESS;
	}
	/**
	 * 保存添加的行政事务
	 * @return
	 */
	public String saveZqAsr(){
		this.status = executiveEbi.addAsr(zqAsrModel);
		if("0".equals(status)){
			this.info = SystemProperties.getPropsValue("operate_failed");
		}else {
			this.info = SystemProperties.getPropsValue("operate_success");

		}
		return SUCCESS;
	}
	
	/**
	 * 行政事务列表查询
	 * @return
	 */
	public String listAsr(){
		List<ZqAsrModel> zqAsrModels = executiveEbi.getAllAsr();
		ActionContext.getContext().getValueStack().set("zqAsrList", zqAsrModels);
		return SUCCESS;
	}
	/**
	 * 行政事务修改初始化
	 * @return
	 */
	public String modifyAsrInit(){
		//获取所有部门信息
		/*List<ZqDepartmentModel> zqDepartmentModels = executiveEbi.getAllDepartment();
		ActionContext.getContext().getValueStack().set("zqdepart", zqDepartmentModels);*/
		//根据行政事务id获取其信息
		zqAsrModel = executiveEbi.getAsrById(asrId);
		ActionContext.getContext().getValueStack().set("zqasr", zqAsrModel);
		
		return SUCCESS;
	}
	
	/**
	 * 保存修改的行政事务
	 * @return
	 */
	public String modifyZqAsr(){
		this.status = executiveEbi.modifyAsr(zqAsrModel);
		return SUCCESS;
	}
	
	/**
	 * 删除行政事务
	 * @return
	 */
	public String deltAsr(){
		this.status = executiveEbi.removeAsrById(asrId);
		
		return SUCCESS;
	}
	/**
	 * 查看行政事务详情
	 */
	public String asrDetail(){
		//根据行政事务id获取其信息
		zqAsrModel = executiveEbi.getAsrById(asrId);
		ActionContext.getContext().getValueStack().set("zqasr", zqAsrModel);
		return SUCCESS;
	}
	/**
	 * 添加公告初始化
	 * @return
	 */
	public String announceInit(){
		return  SUCCESS;
	}
	
	/**
	 * 保存公告信息
	 * @return
	 */
	public String saveZqAnnounce(){
		this.status = executiveEbi.addZqAnnounce(zqNoticeModel);
		return SUCCESS;
	}
	/**
	 * 公告列表
	 * @return
	 */
	public String announceList(){
		List<ZqNoticeModel> zqNoticeModels = executiveEbi.getAllNotice();
		ActionContext.getContext().getValueStack().set("zqAnnounceList", zqNoticeModels);
		return SUCCESS;
	}
	
	/**
	 * 公告详情
	 * @return
	 */
	public String announceDetail(){
		ZqNoticeModel zqNoticeModel = executiveEbi.getNoticeById(asrId);
		ActionContext.getContext().getValueStack().set("zqNoticeModel", zqNoticeModel);
		return SUCCESS;
	}
	/**
	 * 删除公告
	 * @return
	 */
	public String deltAnnounce(){
		this.status = executiveEbi.deltAnnounce(asrId);
		return SUCCESS;
	}
	/**
	 * 公告信息修改初始化
	 * @return
	 */
	public String modifyAnnounceInit(){
		ZqNoticeModel zqNoticeModel = executiveEbi.getNoticeById(asrId);
		ActionContext.getContext().getValueStack().set("zqNotice", zqNoticeModel);
		return SUCCESS;
	}
	/**
	 * 保存修改的公告信息
	 * @return
	 */
	public String modifyZqNotice(){
		this.status = executiveEbi.saveModifyNotice(zqNoticeModel);
		return SUCCESS;
	}
	/**
	 * 添加每日事物记录初始化
	 * @return
	 */
	public String addSerRecordInit(){
		toDayDate = MethodUtils.getToDayDate("yyyy-MM-dd");
		return SUCCESS;
	}
	/**
	 * 保存添加的每日事物信息
	 * @return
	 */
	public String saveZqSerRecord(){
		this.status = executiveEbi.addSerRecord(zqSerrecordModel);
		return SUCCESS;
	}
	/**
	 * 每日事物列表
	 * @return
	 */
	public String listSerRecord(){
		List<ZqSerrecordModel> zqSerrecordModels = executiveEbi.queryAllSerRecord();
		ActionContext.getContext().getValueStack().set("serRecList", zqSerrecordModels);
		return SUCCESS;
	}
	/**
	 * 事物详情
	 * @return
	 */
	public String serRecordDetail(){
		zqSerrecordModel = executiveEbi.getSerRecordById(asrId);
		ActionContext.getContext().getValueStack().set("serRecord", zqSerrecordModel);
		return SUCCESS;
	}
	
	/**
	 * 退出系统
	 * @return
	 */
	public  String exitSystem(){
		ActionContext.getContext().getSession().put("user",null);
		return SUCCESS;
	}
	@JSON(serialize=false)
	public ZqSerrecordModel getZqSerrecordModel() {
		return zqSerrecordModel;
	}
	public void setZqSerrecordModel(ZqSerrecordModel zqSerrecordModel) {
		this.zqSerrecordModel = zqSerrecordModel;
	}
	@JSON(serialize=false)
	public String getToDayDate() {
		return toDayDate;
	}
	
	public void setToDayDate(String toDayDate) {
		this.toDayDate = toDayDate;
	}
	@JSON(serialize=false)
	public ContracterManageEbi getContracterManageEbi() {
		return contracterManageEbi;
	}
	public void setContracterManageEbi(ContracterManageEbi contracterManageEbi) {
		this.contracterManageEbi = contracterManageEbi;
	}
	@JSON(serialize=false)
	public ZqNoticeModel getZqNoticeModel() {
		return zqNoticeModel;
	}
	public void setZqNoticeModel(ZqNoticeModel zqNoticeModel) {
		this.zqNoticeModel = zqNoticeModel;
	}
	@JSON(serialize=false)
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@JSON(serialize=false)
	public Integer getAsrId() {
		return asrId;
	}
	public void setAsrId(Integer asrId) {
		this.asrId = asrId;
	}
	@JSON(serialize=false)
	public ExecutiveEbi getExecutiveEbi() {
		return executiveEbi;
	}
	public void setExecutiveEbi(ExecutiveEbi executiveEbi) {
		this.executiveEbi = executiveEbi;
	}
	@JSON(serialize=false)
	public ZqAsrModel getZqAsrModel() {
		return zqAsrModel;
	}
	public void setZqAsrModel(ZqAsrModel zqAsrModel) {
		this.zqAsrModel = zqAsrModel;
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
	
	
}
