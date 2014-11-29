package com.caseManage.vo;

/**
 * ZqCaseType entity. @author MyEclipse Persistence Tools
 */

public class ZqCaseTypeModel implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private String typeName;

	// Constructors

	/** default constructor */
	public ZqCaseTypeModel() {
	}

	/** full constructor */
	public ZqCaseTypeModel(String typeName) {
		this.typeName = typeName;
	}

	// Property accessors

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}