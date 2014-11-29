package com.caseManage.vo;

/**
 * ZqContactidentity entity. @author MyEclipse Persistence Tools
 */

public class ZqContactidentityModel implements java.io.Serializable {

	// Fields

	private Integer identityId;
	private String identityName;

	// Constructors

	/** default constructor */
	public ZqContactidentityModel() {
	}

	/** full constructor */
	public ZqContactidentityModel(String identityName) {
		this.identityName = identityName;
	}

	// Property accessors

	public Integer getIdentityId() {
		return this.identityId;
	}

	public void setIdentityId(Integer identityId) {
		this.identityId = identityId;
	}

	public String getIdentityName() {
		return this.identityName;
	}

	public void setIdentityName(String identityName) {
		this.identityName = identityName;
	}

}