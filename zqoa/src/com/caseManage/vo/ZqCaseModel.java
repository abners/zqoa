package com.caseManage.vo;

import java.sql.Timestamp;
import java.util.List;

import com.contManage.vo.ZqContractModel;

/**
 * ZqCase entity. @author MyEclipse Persistence Tools
 */

public class ZqCaseModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private String number;
	private String caseName;
	private Integer contId;
	private Integer custId;
	private String custName;
	private Integer typeId;
	private Integer status;
	private Integer litigant;
	private Integer lawyer;
	private Integer lawyers;
	private String relative;
	private String notes;
	private Integer creater;
	private Timestamp createTime;

	private String typeName;
	//主办律师名称
	private String lawyerName;
	//合同名称
	private String contName;
	//当事人身份名称
	private String identityName;
	
	//关联案件名称集合
	private List<ZqCaseModel> relativeCaseNames;
	//案件事件流程信息
	private List<ZqCaseprocessModel> zqCaseprocessModels;
	
	//案件联系人信息
	private List<ZqCasecontactModel> zqCasecontactModels;
	//主办律师名称
	private String userName;
	// Constructors
	

	/** default constructor */
	public ZqCaseModel() {
	}

	/** full constructor */
	public ZqCaseModel( String number,Integer contId, String caseName,
			Integer custId, String custName, Integer typeId, Integer status,
			Integer litigant, Integer lawyer, Integer lawyers, String relative,
			String notes, Integer creater, Timestamp createTime) {
		this.contId = contId;
		this.number = number;
		this.caseName = caseName;
		this.custId = custId;
		this.custName = custName;
		this.typeId = typeId;
		this.status = status;
		this.litigant = litigant;
		this.lawyer = lawyer;
		this.lawyers = lawyers;
		this.relative = relative;
		this.notes = notes;
		this.creater = creater;
		this.createTime = createTime;
	}
	
	public ZqCaseModel(Integer id,String number,String caseName,String typeName,String userName,Integer contId){
		this.id = id;
		this.number = number;
		this.caseName = caseName;
		this.typeName = typeName;
		this.userName = userName;
		this.contId = contId;
		
	}
	/**
	 * 案件详情查看构造方法
	 * @param id
	 * @param number
	 * @param caseName
	 * @param custName
	 * @param contName
	 * @param lawyerName
	 * @param typeName
	 * @param identityName
	 * @param relative
	 * @param notes
	 */
	public ZqCaseModel(Integer id,String number,String caseName,String custName,String contName,
			String lawyerName,String typeName,String identityName,String relative,String notes,Integer contId,Integer custId){
		this.id = id;
		this.number = number;
		this.caseName = caseName;
		this.custName = custName;
		this.contName = contName;
		this.lawyerName = lawyerName;
		this.typeName = typeName;
		this.identityName = identityName;
		this.relative = relative;
		this.notes = notes;
		this.custId = custId;
		this.contId = contId;
	}
	/**
	 * 合同前案件详情查看构造方法
	 * @param id
	 * @param number
	 * @param caseName
	 * @param custName
	 * @param contName
	 * @param lawyerName
	 * @param typeName
	 * @param identityName
	 * @param relative
	 * @param notes
	 */
	public ZqCaseModel(Integer id,String number,String caseName,String custName,
			String lawyerName,String typeName,String identityName,String relative,String notes,Integer contId,Integer custId){
		this.id = id;
		this.number = number;
		this.caseName = caseName;
		this.custName = custName;
		this.lawyerName = lawyerName;
		this.typeName = typeName;
		this.identityName = identityName;
		this.relative = relative;
		this.notes = notes;
		this.custId = custId;
		this.contId = contId;
	}
	public ZqCaseModel(Integer id,String caseName){
		this.id = id;
		this.caseName = caseName;
	}
	// Property accessors
	
	public Integer getId() {
		return this.id;
	}
	
	public List<ZqCaseprocessModel> getZqCaseprocessModels() {
		return zqCaseprocessModels;
	}
	
	public List<ZqCasecontactModel> getZqCasecontactModels() {
		return zqCasecontactModels;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setZqCasecontactModels(List<ZqCasecontactModel> zqCasecontactModels) {
		this.zqCasecontactModels = zqCasecontactModels;
	}

	public void setZqCaseprocessModels(List<ZqCaseprocessModel> zqCaseprocessModels) {
		this.zqCaseprocessModels = zqCaseprocessModels;
	}

	public List getRelativeCaseNames() {
		return relativeCaseNames;
	}

	public void setRelativeCaseNames(List relativeName) {
		this.relativeCaseNames = relativeName;
	}

	public String getContName() {
		return contName;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public Integer getContId() {
		return contId;
	}

	public void setContId(Integer contId) {
		this.contId = contId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getLawyerName() {
		return lawyerName;
	}

	public void setLawyerName(String lawyerName) {
		this.lawyerName = lawyerName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCaseName() {
		return this.caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public Integer getCustId() {
		return this.custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLitigant() {
		return this.litigant;
	}

	public void setLitigant(Integer litigant) {
		this.litigant = litigant;
	}

	public Integer getLawyer() {
		return this.lawyer;
	}

	public void setLawyer(Integer lawyer) {
		this.lawyer = lawyer;
	}

	public Integer getLawyers() {
		return this.lawyers;
	}

	public void setLawyers(Integer lawyers) {
		this.lawyers = lawyers;
	}

	public String getRelative() {
		return this.relative;
	}

	public void setRelative(String relative) {
		this.relative = relative;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getCreater() {
		return this.creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getIdentityName() {
		return identityName;
	}

	public void setIdentityName(String identityName) {
		this.identityName = identityName;
	}
	
}