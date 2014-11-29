package com.contManage.vo;

import java.sql.Timestamp;

/**
 * ZqContracttype entity. @author MyEclipse Persistence Tools
 */

public class ZqContracttypeModel implements java.io.Serializable {

	// Fields

	private Integer contractTypeId;
	private String contractTypeName;
	private Integer operator;
	private Timestamp operatetime;

	// Constructors

	/** default constructor */
	public ZqContracttypeModel() {
	}

	/** full constructor */
	public ZqContracttypeModel(String contractTypeName, Integer operator,
			Timestamp operatetime) {
		this.contractTypeName = contractTypeName;
		this.operator = operator;
		this.operatetime = operatetime;
	}

	// Property accessors

	public Integer getContractTypeId() {
		return this.contractTypeId;
	}

	public void setContractTypeId(Integer contractTypeId) {
		this.contractTypeId = contractTypeId;
	}

	public String getContractTypeName() {
		return this.contractTypeName;
	}

	public void setContractTypeName(String contractTypeName) {
		this.contractTypeName = contractTypeName;
	}

	public Integer getOperator() {
		return this.operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public Timestamp getOperatetime() {
		return this.operatetime;
	}

	public void setOperatetime(Timestamp operatetime) {
		this.operatetime = operatetime;
	}
}