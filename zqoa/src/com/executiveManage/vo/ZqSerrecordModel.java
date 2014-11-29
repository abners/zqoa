package com.executiveManage.vo;

/**
 * ZqSerrecord entity. @author MyEclipse Persistence Tools
 */

public class ZqSerrecordModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private String content;
	private String createTime;
	private Integer author;
	private String authorName;

	// Constructors

	/** default constructor */
	public ZqSerrecordModel() {
	}

	/** full constructor */
	public ZqSerrecordModel(String content, String createTime, Integer author) {
		this.content = content;
		this.createTime = createTime;
		this.author = author;
	}

	public ZqSerrecordModel(Integer id,String name,String createTime){
		this.id =id;
		this.authorName = name;
		this.createTime = createTime;
	}
	public ZqSerrecordModel(Integer id,String name,String createTime,String content){
		this.id =id;
		this.authorName = name;
		this.createTime = createTime;
		this.content = content;
	}
	// Property accessors

	public Integer getId() {
		return this.id;
	}
	
	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

}