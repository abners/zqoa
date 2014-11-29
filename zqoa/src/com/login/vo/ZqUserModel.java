package com.login.vo;

/**
 * AbstractZqUser entity provides the base persistence definition of the ZqUser
 * entity. @author MyEclipse Persistence Tools
 */

public class ZqUserModel implements
		java.io.Serializable {

	// Fields
	private Integer id;
	private String name;
	private String password;
	private String email;
	private String status;
	private Integer groupId;
	private String isCurrent;
	private String acceptIp;
	private String isManage;

	private String powers;
		
	private String currentMess; //是否当前
	
	private ZqUserinfoModel zqUserinfoModel;
	
	private String groupName;
	//default constructer
	public ZqUserModel(){
	}
	
	public ZqUserModel(Integer id,String name,Integer groupId,String groupName,String email){
		this.id = id;
		
		this.groupId = groupId;
		this.email = email;
		this.name = name;
		this.groupName = groupName;
	}
	
	public ZqUserModel(Integer id,String name,String email,String isCurrent,String groupName){
		this.id = id;
		this.name = name;
		this.email = email;
		this.isCurrent = isCurrent;
		this.groupName = groupName;
		setCurrentMess(isCurrent);
	}
	// Property accessors
	
	public Integer getId() {
		return this.id;
	}
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public ZqUserinfoModel getZqUserinfoModel() {
		return zqUserinfoModel;
	}

	public void setZqUserinfoModel(ZqUserinfoModel zqUserinfoModel) {
		this.zqUserinfoModel = zqUserinfoModel;
	}

	public String getCurrentMess() {
		if("1".equals(this.isCurrent)){
			return "当前";
		}else {
			return "历史";
		}
	}

	public void setCurrentMess(String currentMess) {
		this.currentMess = currentMess;
	}

	public String getPowers() {
		return powers;
	}

	public void setPowers(String powers) {
		this.powers = powers;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getIsManage() {
		return isManage;
	}

	public void setIsManage(String isManage) {
		this.isManage = isManage;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getIsCurrent() {
		return this.isCurrent;
	}

	public void setIsCurrent(String isCurrent) {
		this.isCurrent = isCurrent;
	}

	public String getAcceptIp() {
		return this.acceptIp;
	}

	public void setAcceptIp(String acceptIp) {
		this.acceptIp = acceptIp;
	}

}