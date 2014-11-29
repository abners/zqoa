package com.contManage.vo;

import java.sql.Timestamp;
import java.util.List;

import com.FileManage.vo.ZqFileModel;
import com.caseManage.vo.ZqCaseModel;

/**
 * ZqContract entity. @author MyEclipse Persistence Tools
 */

public class ZqContractModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private String number;
	private String contName;
	private Integer custId;
	private String custName;
	private Integer typeId;
	private String contTypeName;
	private String risk;
	private Integer lawyer;
	private String lawyerName;
	private String validity;
	private String executeStartTime;
	private String executeEndTime;
	private Integer payType;
	private String payables;
	private String paid = "0";
	private String relative;
	private String archived = "0";
	private String notes;
	private Integer creater;
	private Timestamp createTime;
	
	private List<ZqCaseModel> zqCaseModels;
	
	private List<ZqFileModel> zqFileModels;

	// Constructors

	/** default constructor */
	public ZqContractModel() {
	}

	/** full constructor */
	public ZqContractModel(String number, String contName, Integer custId,
			String custName, Integer typeId, String risk, Integer lawyer,
			String validity, Integer payType, String payables, String paid,
			String relative, String archived, String notes) {
		this.number = number;
		this.contName = contName;
		this.custId = custId;
		this.custName = custName;
		this.typeId = typeId;
		this.risk = risk;
		this.lawyer = lawyer;
		this.validity = validity;
		this.payType = payType;
		this.payables = payables;
		this.paid = paid;
		this.relative = relative;
		this.archived = archived;
		this.notes = notes;
	}

	public ZqContractModel(Integer id,String number,String contName,String typeName,String custName,String lawyerName){
		this.id = id;
		this.number = number;
		this.contName = contName;
		this.contTypeName = typeName;
		this.custName = custName;
		this.lawyerName = lawyerName;
	}
	// Property accessors
	
	public Integer getId() {
		return this.id;
	}

	public List<ZqFileModel> getZqFileModels() {
		return zqFileModels;
	}

	public void setZqFileModels(List<ZqFileModel> zqFileModels) {
		this.zqFileModels = zqFileModels;
	}

	public List<ZqCaseModel> getZqCaseModels() {
		return zqCaseModels;
	}

	public void setZqCaseModels(List<ZqCaseModel> zqCaseModels) {
		this.zqCaseModels = zqCaseModels;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getLawyerName() {
		return lawyerName;
	}

	public void setLawyerName(String lawyerName) {
		this.lawyerName = lawyerName;
	}

	public String getContTypeName() {
		return contTypeName;
	}

	public void setContTypeName(String contTypeName) {
		this.contTypeName = contTypeName;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getContName() {
		return this.contName;
	}

	public void setContName(String contName) {
		this.contName = contName;
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

	public String getRisk() {
		return this.risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public Integer getLawyer() {
		return this.lawyer;
	}

	public void setLawyer(Integer lawyer) {
		this.lawyer = lawyer;
	}

	public String getValidity() {
		return this.validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}
	
	
	public String getExecuteStartTime() {
		return executeStartTime;
	}

	public void setExecuteStartTime(String executeStartTime) {
		this.executeStartTime = executeStartTime;
	}

	public String getExecuteEndTime() {
		return executeEndTime;
	}

	public void setExecuteEndTime(String executeEndTime) {
		this.executeEndTime = executeEndTime;
	}

	public Integer getPayType() {
		return this.payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getPayables() {
		return this.payables;
	}

	public void setPayables(String payables) {
		this.payables = payables;
	}

	public String getPaid() {
		return this.paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}

	public String getRelative() {
		return this.relative;
	}

	public void setRelative(String relative) {
		this.relative = relative;
	}

	public String getArchived() {
		return this.archived;
	}

	public void setArchived(String archived) {
		this.archived = archived;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	

}