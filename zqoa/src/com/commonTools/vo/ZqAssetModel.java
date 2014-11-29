package com.commonTools.vo;

import java.util.Date;

/**
 * ZqAsset entity. @author MyEclipse Persistence Tools
 */

public class ZqAssetModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private String assetName;
	private Integer type;
	private Integer status;
	private String sn;
	private Float value;
	private Date inTime;
	private Date outTime;
	private String typeName;

	// Constructors

	/** default constructor */
	public ZqAssetModel() {
	}

	/** full constructor */
	public ZqAssetModel(String assetName, Integer type, Integer status, Float value,
			Date inTime, Date outTime) {
		this.assetName = assetName;
		this.type = type;
		this.status = status;
		this.value = value;
		this.inTime = inTime;
		this.outTime = outTime;
	}
	
	public ZqAssetModel(Integer id,String sn,String assetName,String typeName,Date inTime){
		this.id = id;
		this.sn = sn;
		this.assetName = assetName;
		this.typeName = typeName;
		this.inTime = inTime;
	}
	public ZqAssetModel(Integer id,String sn,String assetName,Integer type,String typeName,Date inTime,Date outTime,Float value){
		this.id = id;
		this.sn = sn;
		this.type = type;
		this.assetName = assetName;
		this.typeName = typeName;
		this.inTime = inTime;
		this.outTime = outTime;
		this.value = value;
	}
	// Property accessors
	public Integer getId() {
		return this.id;
	}
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAssetName() {
		return this.assetName;
	}
	
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Float getValue() {
		return this.value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public Date getInTime() {
		return this.inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Date getOutTime() {
		return this.outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

}