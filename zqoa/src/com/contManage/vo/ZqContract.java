package com.contManage.vo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * ZqContract entity. @author MyEclipse Persistence Tools
 */
public class ZqContract implements java.io.Serializable {

	// Fields

	private Integer id;
	private String number;
	private String contName;
	private Integer custId;
	private String custName;
	private Integer typeId;
	private String contTypeName;
	private String risk;
	private String lawyerName;
	private Integer lawyer;
	private String executeEndTime;
	private String executeStartTime;
	private String validity;
	private Integer payType;
	private String payables;
	private String paid;
	private String relative;
	private String archived;
	private String notes;
	private Integer creater;
	private Timestamp createtime;
	private Set zqCases = new HashSet(0);

	// Constructors

	/** default constructor */
	public ZqContract() {
	}

	/** full constructor */
	public ZqContract(String number, String contName, Integer custId,
			String custName, Integer typeId, String contTypeName, String risk,
			String lawyerName, Integer lawyer, String executeEndTime,
			String executeStartTime, String validity, Integer payType,
			String payables, String paid, String relative, String archived,
			String notes, Integer creater, Timestamp createtime, Set zqCases) {
		this.number = number;
		this.contName = contName;
		this.custId = custId;
		this.custName = custName;
		this.typeId = typeId;
		this.contTypeName = contTypeName;
		this.risk = risk;
		this.lawyerName = lawyerName;
		this.lawyer = lawyer;
		this.executeEndTime = executeEndTime;
		this.executeStartTime = executeStartTime;
		this.validity = validity;
		this.payType = payType;
		this.payables = payables;
		this.paid = paid;
		this.relative = relative;
		this.archived = archived;
		this.notes = notes;
		this.creater = creater;
		this.createtime = createtime;
		this.zqCases = zqCases;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
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

	public String getContTypeName() {
		return this.contTypeName;
	}

	public void setContTypeName(String contTypeName) {
		this.contTypeName = contTypeName;
	}

	public String getRisk() {
		return this.risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getLawyerName() {
		return this.lawyerName;
	}

	public void setLawyerName(String lawyerName) {
		this.lawyerName = lawyerName;
	}

	public Integer getLawyer() {
		return this.lawyer;
	}

	public void setLawyer(Integer lawyer) {
		this.lawyer = lawyer;
	}

	public String getExecuteEndTime() {
		return this.executeEndTime;
	}

	public void setExecuteEndTime(String executeEndTime) {
		this.executeEndTime = executeEndTime;
	}

	public String getExecuteStartTime() {
		return this.executeStartTime;
	}

	public void setExecuteStartTime(String executeStartTime) {
		this.executeStartTime = executeStartTime;
	}

	public String getValidity() {
		return this.validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
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
		return this.creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Set getZqCases() {
		return this.zqCases;
	}

	public void setZqCases(Set zqCases) {
		this.zqCases = zqCases;
	}

}