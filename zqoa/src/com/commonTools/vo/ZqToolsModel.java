package com.commonTools.vo;

/**
 * ZqTools entity. @author MyEclipse Persistence Tools
 */

public class ZqToolsModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer typeId;
	private String createTime;
	private Integer author;
	private String address;
	private Integer fileId;
	private String notes;
	private String ywjm;
	private String typeName;

	// Constructors

	/** default constructor */
	public ZqToolsModel() {
	}

	/** full constructor */
	public ZqToolsModel(String name, Integer typeId, String createTime,
			Integer author, String address, Integer fileId, String notes) {
		this.name = name;
		this.typeId = typeId;
		this.createTime = createTime;
		this.author = author;
		this.address = address;
		this.fileId = fileId;
		this.notes = notes;
	}

	// Property accessors
	public ZqToolsModel(Integer id,String name,String address,String typeName,String ywjm,Integer fileId){
		this.id = id;
		this.address = address;
		this.name = name;
		this.typeName = typeName;
		this.ywjm = ywjm;
		this.fileId = fileId;
	}
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getId() {
		return this.id;
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

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getAuthor() {
		return this.author;
	}

	public void setAuthor(Integer author) {
		this.author = author;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getFileId() {
		return this.fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getNotes() {
		return this.notes;
	}
	
	public String getYwjm() {
		return ywjm;
	}

	public void setYwjm(String ywjm) {
		this.ywjm = ywjm;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}