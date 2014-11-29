package com.executiveManage.vo;

import java.sql.Timestamp;

/**
 * ZqNotice entity. @author MyEclipse Persistence Tools
 */

public class ZqNoticeModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String content;
	private Timestamp time;
	private Integer author;
	private String authorName;

	// Constructors

	/** default constructor */
	public ZqNoticeModel() {
	}

	/** minimal constructor */
	public ZqNoticeModel(Integer author) {
		this.author = author;
	}

	/** full constructor */
	public ZqNoticeModel(Integer id,String title, String content, Timestamp time, Integer author) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.time = time;
		this.author = author;
	}

	public ZqNoticeModel(Integer id,String authorName,String title) {
		this.id = id;
		this.authorName = authorName;
		this.title = title;
	}
	public ZqNoticeModel(Integer id,String name,String title,Object time,String content,Integer author) {
		this.id = id;
		this.authorName = name;
		this.title = title;
		this.time = (Timestamp) time;
		this.content = content;
		this.author = author;
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

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getAuthor() {
		return this.author;
	}

	public void setAuthor(Integer author) {
		this.author = author;
	}

}