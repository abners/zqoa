package com.customerManage.vo;

import java.sql.Timestamp;

/**
 * ZqCustomer entity. @author MyEclipse Persistence Tools
 */

public class ZqCustomerModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String sex;
	private String birthday;
	private String race;
	private String origo;
	private String work;
	private String pressentAddr;
	private String idCard;
	private String chairmen;
	private String orgNumber;
	private String address;
	private String postalcode;
	private String email;
	private String contracter;
	private String contacter;
	private String tel;
	private String mobile;
	private String otherTel;
	private String notes;
	private String cust_type;
	private String isnow;
	private Integer operator;
	private Timestamp operattime;

	private String cust_typeValue;

	// Constructors

	/** default constructor */
	public ZqCustomerModel() {
	}

	/*
	*//** full constructor */
	/*
	 * public ZqCustomer(String name, String sex, String race, String origo,
	 * String work, String pressentAddr, String idCard, String chairmen, String
	 * orgNumber, String address, Integer postalcode, String email, String
	 * contracter, String tel, String mobile, String otherTel, String notes) {
	 * this.name = name; this.sex = sex; this.race = race; this.origo = origo;
	 * this.work = work; this.pressentAddr = pressentAddr; this.idCard = idCard;
	 * this.chairmen = chairmen; this.orgNumber = orgNumber; this.address =
	 * address; this.postalcode = postalcode; this.email = email;
	 * this.contracter = contracter; this.tel = tel; this.mobile = mobile;
	 * this.otherTel = otherTel; this.notes = notes; }
	 */

	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public String getCust_typeValue() {
		if (cust_type.equals("4")) {
			this.cust_typeValue = "自然人";
		} else if (cust_type.equals("5")) {
			cust_typeValue = "法人";
		} else {
			cust_typeValue = "其它组织";
		}
		return cust_typeValue;
	}

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public Timestamp getOperattime() {
		return operattime;
	}

	public void setOperattime(Timestamp operattime) {
		this.operattime = operattime;
	}

	public void setCust_typeValue(String cust_typeValue) {
		this.cust_typeValue = cust_typeValue;
	}

	public String getIsnow() {
		return isnow;
	}

	public void setIsnow(String isnow) {
		this.isnow = isnow;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getContacter() {
		return contacter;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	public void setCust_type(String cust_type) {
		this.cust_type = cust_type;

	}

	public String getCust_type() {

		return cust_type;
	}

	public void setCust_Type(String cust_type) {
		this.cust_type = cust_type;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRace() {
		return this.race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getOrigo() {
		return this.origo;
	}

	public void setOrigo(String origo) {
		this.origo = origo;
	}

	public String getWork() {
		return this.work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getPressentAddr() {
		return this.pressentAddr;
	}

	public void setPressentAddr(String pressentAddr) {
		this.pressentAddr = pressentAddr;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getChairmen() {
		return this.chairmen;
	}

	public void setChairmen(String chairmen) {
		this.chairmen = chairmen;
	}

	public String getOrgNumber() {
		return this.orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContracter() {
		return this.contracter;
	}

	public void setContracter(String contracter) {
		this.contracter = contracter;
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

	public String getOtherTel() {
		return this.otherTel;
	}

	public void setOtherTel(String otherTel) {
		this.otherTel = otherTel;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String toString() {
		return "[id:" + this.id + ",name:" + this.name + ",birthday:" + this.birthday + ",sex:"
				+ this.sex + ",race:" + this.race + ",origo:" + this.origo
				+ ",work:" + this.work + ",pressentAddr" + this.pressentAddr
				+ ",idcard" + this.idCard + ",chairmen:" + this.chairmen
				+ ",address" + this.address + ",orgNumber:" + this.orgNumber
				+ ",postalcode:" + this.postalcode + ",otherTel:"
				+ this.otherTel + ",contacter:" + this.contacter
				+ ",contracter:" + this.contracter + ",mobile:" + this.mobile
				+ "]";
	}

}