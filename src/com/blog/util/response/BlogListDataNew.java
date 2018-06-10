package com.blog.util.response;

import java.util.Date;

public class BlogListDataNew {

	private Integer blogId;
	private String blogTitle;
	private Integer numberOfAgree;
	private Date postTime;
	private Integer userId;
	private Integer numberOfComments;
	private String blogContent;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blogContent == null) ? 0 : blogContent.hashCode());
		result = prime * result + ((blogId == null) ? 0 : blogId.hashCode());
		result = prime * result + ((blogTitle == null) ? 0 : blogTitle.hashCode());
		result = prime * result + ((numberOfAgree == null) ? 0 : numberOfAgree.hashCode());
		result = prime * result + ((numberOfComments == null) ? 0 : numberOfComments.hashCode());
		result = prime * result + ((postTime == null) ? 0 : postTime.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlogListDataNew other = (BlogListDataNew) obj;
		if (blogContent == null) {
			if (other.blogContent != null)
				return false;
		} else if (!blogContent.equals(other.blogContent))
			return false;
		if (blogId == null) {
			if (other.blogId != null)
				return false;
		} else if (!blogId.equals(other.blogId))
			return false;
		if (blogTitle == null) {
			if (other.blogTitle != null)
				return false;
		} else if (!blogTitle.equals(other.blogTitle))
			return false;
		if (numberOfAgree == null) {
			if (other.numberOfAgree != null)
				return false;
		} else if (!numberOfAgree.equals(other.numberOfAgree))
			return false;
		if (numberOfComments == null) {
			if (other.numberOfComments != null)
				return false;
		} else if (!numberOfComments.equals(other.numberOfComments))
			return false;
		if (postTime == null) {
			if (other.postTime != null)
				return false;
		} else if (!postTime.equals(other.postTime))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	public BlogListDataNew(Integer blogId, String blogTitle, Integer numberOfAgree, Date postTime, Integer userId,
			Integer numberOfComments, String blogContent) {
		super();
		this.blogId = blogId;
		this.blogTitle = blogTitle;
		this.numberOfAgree = numberOfAgree;
		this.postTime = postTime;
		this.userId = userId;
		this.numberOfComments = numberOfComments;
		this.blogContent = blogContent;
	}
	@Override
	public String toString() {
		return "BlogListDataNew [blogId=" + blogId + ", blogTitle=" + blogTitle + ", numberOfAgree=" + numberOfAgree
				+ ", postTime=" + postTime + ", userId=" + userId + ", numberOfComments=" + numberOfComments
				+ ", blogContent=" + blogContent + "]";
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
	public Integer getNumberOfComments() {
		return numberOfComments;
	}
	public void setNumberOfComments(Integer numberOfComments) {
		this.numberOfComments = numberOfComments;
	}
	public String getBlogContent() {
		return blogContent;
	}
	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}
	
}
