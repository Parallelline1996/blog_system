package com.blog.domain;


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Blog {
	private Integer blogId;
	private String blogTitle;
	private String blogContent;
	private Integer numberOfAgree;
	private Integer numberOfDisagree;
	private Integer blogState;
	private Timestamp postTime;
	private Timestamp lastModifiedTime;
	private Integer userId;
	private Set<Tag> tags = new HashSet<>();
	public Blog() {
	}
	public Blog(Integer blogId, String blogTitle, String blogContent, Integer numberOfAgree, Integer numberOfDisagree,
			Integer blogState, Timestamp postTime, Timestamp lastModifiedTime, Integer userId, Set<Tag> tags) {
		super();
		this.blogId = blogId;
		this.blogTitle = blogTitle;
		this.blogContent = blogContent;
		this.numberOfAgree = numberOfAgree;
		this.numberOfDisagree = numberOfDisagree;
		this.blogState = blogState;
		this.postTime = postTime;
		this.lastModifiedTime = lastModifiedTime;
		this.userId = userId;
		this.tags = tags;
	}
	@Override
	public String toString() {
		return "Blog [blogId=" + blogId + ", blogTitle=" + blogTitle + ", blogContent=" + blogContent
				+ ", numberOfAgree=" + numberOfAgree + ", numberOfDisagree=" + numberOfDisagree + ", blogState="
				+ blogState + ", postTime=" + postTime + ", lastModifiedTime=" + lastModifiedTime + ", userId=" + userId
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blogContent == null) ? 0 : blogContent.hashCode());
		result = prime * result + ((blogId == null) ? 0 : blogId.hashCode());
		result = prime * result + ((blogState == null) ? 0 : blogState.hashCode());
		result = prime * result + ((blogTitle == null) ? 0 : blogTitle.hashCode());
		result = prime * result + ((lastModifiedTime == null) ? 0 : lastModifiedTime.hashCode());
		result = prime * result + ((numberOfAgree == null) ? 0 : numberOfAgree.hashCode());
		result = prime * result + ((numberOfDisagree == null) ? 0 : numberOfDisagree.hashCode());
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
		Blog other = (Blog) obj;
		if (blogId == null) {
			if (other.blogId != null)
				return false;
		} else if (!blogId.equals(other.blogId))
			return false;
		return true;
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
	public String getBlogContent() {
		return blogContent;
	}
	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}
	public Integer getNumberOfAgree() {
		return numberOfAgree;
	}
	public void setNumberOfAgree(Integer numberOfAgree) {
		this.numberOfAgree = numberOfAgree;
	}
	public Integer getNumberOfDisagree() {
		return numberOfDisagree;
	}
	public void setNumberOfDisagree(Integer numberOfDisagree) {
		this.numberOfDisagree = numberOfDisagree;
	}
	public Integer getBlogState() {
		return blogState;
	}
	public void setBlogState(Integer blogState) {
		this.blogState = blogState;
	}
	public Timestamp getPostTime() {
		return postTime;
	}
	public void setPostTime(Timestamp postTime) {
		this.postTime = postTime;
	}
	public Timestamp getLastModifiedTime() {
		return lastModifiedTime;
	}
	public void setLastModifiedTime(Timestamp lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
}
