package com.personalManage.vo;

/**
 * ZqGroup entity. @author MyEclipse Persistence Tools
 */

public class ZqGroupModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private String groupName;
	private String power;

	// Constructors

	/** default constructor */
	public ZqGroupModel() {
	}

	/** full constructor */
	public ZqGroupModel(String groupName, String power) {
		this.groupName = groupName;
		this.power = power;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getPower() {
		return this.power;
	}

	public void setPower(String power) {
		this.power = power;
	}

}