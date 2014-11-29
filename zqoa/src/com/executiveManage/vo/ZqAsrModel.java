package com.executiveManage.vo;

/**
 * ZqAsr entity. @author MyEclipse Persistence Tools
 */

public class ZqAsrModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer deptId;
	private String name;
	private String content;
	private Integer author;
	private String createTime;
	private String authorName;
	private String deptName;

	// Constructors

	/** default constructor */
	public ZqAsrModel() {
	}

	/** full constructor */
	public ZqAsrModel(Integer deptId, String name, String content, Integer author,
			String createTime) {
		this.deptId = deptId;
		this.name = name;
		this.content = content;
		this.author = author;
		this.createTime = createTime;
	}

	public ZqAsrModel(Integer id,String name, Integer author,
			String createTime,String authorName) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.createTime = createTime;
		this.authorName = authorName;
	}
	
	public ZqAsrModel(Integer id,String name, Integer author,
			String createTime,String authorName,String deptName,String content,Integer deptId) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.createTime = createTime;
		this.authorName = authorName;
		this.deptName = deptName;
		this.content = content;
		this.deptId = deptId;
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

	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getAuthor() {
		return this.author;
	}

	public void setAuthor(Integer author) {
		this.author = author;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	

}