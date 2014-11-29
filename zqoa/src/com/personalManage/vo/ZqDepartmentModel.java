package com.personalManage.vo;

import java.util.HashSet;
import java.util.Set;

/**
 * ZqDepartment entity. @author MyEclipse Persistence Tools
 */

public class ZqDepartmentModel implements java.io.Serializable {

	// Fields

	private Integer deptId;
	private String depName;
	private Set zqUserinfos = new HashSet(0);

	// Constructors

	// Property accessors

	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDepName() {
		return this.depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public Set getZqUserinfos() {
		return this.zqUserinfos;
	}

	public void setZqUserinfos(Set zqUserinfos) {
		this.zqUserinfos = zqUserinfos;
	}

}