package com.caseManage.vo;

import java.sql.Timestamp;
import java.util.List;

import com.FileManage.vo.ZqFileModel;

/**
 * ZqCaseprocess entity. @author MyEclipse Persistence Tools
 */

public class ZqCaseprocessModel implements java.io.Serializable {

	// Fields

	private String id;
	private Integer caseId;
	private Timestamp createTime;
	private String processTime;
	private String content;
	private Integer creater;
	private String isTimeRemender;
	private String remTime;
	private String remContent;
	//创建者姓名
	private String createrName;
	
	//事件附件集合
	private List<ZqFileModel> zqprocessFileList;

	// Constructors

	/** default constructor */
	public ZqCaseprocessModel() {
	}

	/** full constructor */
	public ZqCaseprocessModel(Integer caseId, Timestamp createTime, String content,
			Integer creater) {
		this.caseId = caseId;
		this.createTime = createTime;
		this.content = content;
		this.creater = creater;
	}
	/**
	 * 
	 * @param id
	 * @param caseId
	 * @param createTime
	 * @param content
	 * @param creater
	 * @param name
	 * (a.id,a.caseId,a.createTime,a.content,a.creater,b.name
	 */
	public ZqCaseprocessModel(String id,Integer caseId, Timestamp createTime, String content,
			Integer creater,String name) {
		this.id = id;
		this.caseId = caseId;
		this.createTime = createTime;
		this.content = content;
		this.creater = creater;
		this.createrName = name;
	}

	// Property accessors
	
	public String getId() {
		return this.id;
	}

	public String getIsTimeRemender() {
		return isTimeRemender;
	}

	public void setIsTimeRemender(String isTimeRemender) {
		this.isTimeRemender = isTimeRemender;
	}

	public String getRemTime() {
		return remTime;
	}

	public void setRemTime(String remTime) {
		this.remTime = remTime;
	}

	public String getRemContent() {
		return remContent;
	}

	public void setRemContent(String remContent) {
		this.remContent = remContent;
	}

	public String getProcessTime() {
		return processTime;
	}

	public void setProcessTime(String processTime) {
		this.processTime = processTime;
	}

	public List<ZqFileModel> getZqprocessFileList() {
		return zqprocessFileList;
	}

	public void setZqprocessFileList(List<ZqFileModel> zqprocessFileList) {
		this.zqprocessFileList = zqprocessFileList;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCaseId() {
		return this.caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCreater() {
		return this.creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

}