package com.blog.util.response;

import java.util.Date;
import java.util.Set;

import com.blog.domain.Tag;
import com.blog.domain.Blog;
public class BlogList {
	private String blogId;
	private String blogTitle;
	private Integer numberOfAgree;
	private Integer blogState;
	private Date postTime;
	private String userId;
	public BlogList() {
		
	}

	public BlogList(String blogId, String blogTitle,Integer numberOfAgree,
			Integer blogState, Date postTime, String userId) {
		super();
		this.blogId = blogId;
		this.blogTitle = blogTitle;
		this.numberOfAgree = numberOfAgree;
		this.blogState = blogState;
		this.postTime = postTime;
		this.userId = userId;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}