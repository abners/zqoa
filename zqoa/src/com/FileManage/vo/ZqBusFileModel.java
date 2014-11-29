package com.FileManage.vo;

/**
 * ZqBusFile entity. @author MyEclipse Persistence Tools
 */

public class ZqBusFileModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private String busId;
	private Integer fileId;

	// Constructors

	/** default constructor */
	public ZqBusFileModel() {
	}

	/** full constructor */
	public ZqBusFileModel(String busId, Integer fileId) {
		this.busId = busId;
		this.fileId = fileId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBusId() {
		return this.busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public Integer getFileId() {
		return this.fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

}