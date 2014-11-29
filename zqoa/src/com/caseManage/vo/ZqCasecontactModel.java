package com.caseManage.vo;

/**
 * ZqCasecontact entity. @author MyEclipse Persistence Tools
 */

public class ZqCasecontactModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer caseId;
	private String roleName;
	private Integer roleid;
	private String name;
	private String tel;
	private String mobile;
	private String email;
	private String address;

	// Constructors

	/** default constructor */
	public ZqCasecontactModel() {
	}

	/** full constructor */
	public ZqCasecontactModel(Integer caseId, String roleName, Integer roleid,
			String name, String tel, String mobile, String email, String address) {
		this.caseId = caseId;
		this.roleName = roleName;
		this.roleid = roleid;
		this.name = name;
		this.tel = tel;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
	}
	
	public ZqCasecontactModel(Integer id,String name, String email, String address, String tel, 
			String mobile, String roleName ) {
		this.id = id;
		this.roleName = roleName;
		this.name = name;
		this.tel = tel;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.roleName = roleName;
	} 
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCaseId() {
		return this.caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}