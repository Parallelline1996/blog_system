package com.blog.util.response;

import java.util.Date;

public class BlogList {
	private Integer blogId;
	private String blogTitle;
	private Integer numberOfAgree;
	private Integer blogState;
	private Date postTime;
	private Integer userId;
	public BlogList() {
		
	}

	public BlogList(Integer blogId, String blogTitle,Integer numberOfAgree,
			Integer blogState, Date postTime, Integer userId) {
		super();
		this.blogId = blogId;
		this.blogTitle = blogTitle;
		this.numberOfAgree = numberOfAgree;
		this.blogState = blogState;
		this.postTime = postTime;
		this.userId = userId;
	}
	public Integer getBlogId() {
		return blogId;
	}
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public Integer getNumberOfAgree() {
		return numberOfAgree;
	}
	public void setNumberOfAgree(Integer numberOfAgree) {
		this.numberOfAgree = numberOfAgree;
	}
	public Integer getBlogState() {
		return blogState;
	}
	public void setBlogState(Integer blogState) {
		this.blogState = blogState;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}