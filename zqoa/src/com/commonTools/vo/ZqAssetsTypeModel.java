package com.commonTools.vo;

/**
 * ZqAssetsType entity. @author MyEclipse Persistence Tools
 */

public class ZqAssetsTypeModel implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private String typeName;

	// Constructors

	/** default constructor */
	public ZqAssetsTypeModel() {
	}

	/** full constructor */
	public ZqAssetsTypeModel(String typeName) {
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