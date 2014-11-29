package com.login.vo;

import com.personalManage.vo.ZqDepartmentModel;

/**
 * ZqUserinfo entity. @author MyEclipse Persistence Tools
 */

public class ZqUserinfoModel implements java.io.Serializable {

	// Fields

	private Integer uid;
	private Integer typeId;
	private String sex;
	private ZqDepartmentModel zqDepartment;
	private String birthday;
	private String origo;
	private String mobile;
	private String education;
	private String tel;

	// Property accessors
	
	public Integer getUid() {
		return this.uid;
	}

	public ZqDepartmentModel getZqDepartment() {
		return zqDepartment;
	}
	
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public void setZqDepartment(ZqDepartmentModel zqDepartmentModel) {
		this.zqDepartment = zqDepartmentModel;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getOrigo() {
		return this.origo;
	}

	public void setOrigo(String origo) {
		this.origo = origo;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}