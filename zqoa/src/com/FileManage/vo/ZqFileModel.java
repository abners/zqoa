package com.FileManage.vo;

import java.sql.Timestamp;



/**
 * ZqFile entity. @author MyEclipse Persistence Tools
 */

public class ZqFileModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private String ywjm;
	private String realFilename;
	private String address;
	private Integer creater;
	private Timestamp createTime;

	//业务表id
	private Integer busId;
	//业务id
	private String caseprocessId;
	
	// Constructors

	/** default constructor */
	public ZqFileModel() {
	}

	/** full constructor */
	public ZqFileModel(String ywjm, String realFilename, String address,
			Integer creater, Timestamp createTime) {
		this.ywjm = ywjm;
		this.realFilename = realFilename;
		this.address = address;
		this.creater = creater;
		this.createTime = createTime;
	}
	
	/**
	 * 
	 */
	/**
	 * 
	 * @param id
	 * @param ywjm
	 * @param realFilename
	 * @param address
	 * @param creater
	 * @param createTime
	 * @param caseprocessId
	 * @param busId
	 * 
	 */
	public ZqFileModel(Integer id,String ywjm, String realFilename, String address,
			Integer creater, Object createTime,String caseprocessId,Integer busId) {
		this.id = id;
		this.ywjm =  ywjm;
		this.realFilename = realFilename;
		this.address = address;
		this.creater = creater;
		this.createTime = (Timestamp) createTime;
		this.caseprocessId =  caseprocessId;
		this.busId = busId;
	}
	public ZqFileModel(Integer id,String ywjm, String realFilename, String address,
			Integer creater, Object createTime) {
		this.id = id;
		this.ywjm =  ywjm;
		this.realFilename = realFilename;
		this.address = address;
		this.creater = creater;
		this.createTime = (Timestamp) createTime;
	}
	// Property accessors
	
	public Integer getId() {
		return this.id;
	}

	public Integer getBusId() {
		return busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	public String getCaseprocessId() {
		return caseprocessId;
	}

	public void setCaseprocessId(String caseprocessId) {
		this.caseprocessId = caseprocessId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYwjm() {
		return this.ywjm;
	}

	public void setYwjm(String ywjm) {
		this.ywjm = ywjm;
	}

	public String getRealFilename() {
		return this.realFilename;
	}

	public void setRealFilename(String realFilename) {
		this.realFilename = realFilename;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCreater() {
		return this.creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}