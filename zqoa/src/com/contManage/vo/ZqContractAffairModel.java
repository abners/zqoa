package com.contManage.vo;

import java.sql.Timestamp;

/**
 * ZqContractAffair entity. @author MyEclipse Persistence Tools
 */

public class ZqContractAffairModel implements java.io.Serializable {

	// Fields

	private Integer affairId;
	private Integer contractId;
	private String affairContent;
	private Integer operator;
	private Timestamp operateTime;
	//合同名称
	private String contName;
	//创建者的名称
	private String creater;
	// Constructors

	/** default constructor */
	public ZqContractAffairModel() {
	}

	/** full constructor */
	public ZqContractAffairModel(Integer contractId, String affairContent,
			Integer operator, Timestamp operateTime) {
		this.contractId = contractId;
		this.affairContent = affairContent;
		this.operator = operator;
		this.operateTime = operateTime;
	}

	// Property accessors
	
	public Integer getAffairId() {
		return this.affairId;
	}

	public String getContName() {
		return contName;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public void setAffairId(Integer affairId) {
		this.affairId = affairId;
	}

	public Integer getContractId() {
		return this.contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public String getAffairContent() {
		return this.affairContent;
	}

	public void setAffairContent(String affairContent) {
		this.affairContent = affairContent;
	}

	public Integer getOperator() {
		return this.operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public Timestamp getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Timestamp operateTime) {
		this.operateTime = operateTime;
	}

}