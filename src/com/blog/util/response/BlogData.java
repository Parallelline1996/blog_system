package com.blog.util.response;

import java.util.Date;
import java.util.List;

public class BlogData {
	
	private Integer blogId;
	private String blogTitle;
	private String blogContent;
	private Integer numberOfAgree;
	private Integer numberOfDisagree;
	private Integer blogState;
	private Date postTime;
	private Date lastModifiedTime;
	private Integer userId;
	private List<String> tags;
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
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
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
		BlogData other = (BlogData) obj;
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
		if (blogState == null) {
			if (other.blogState != null)
				return false;
		} else if (!blogState.equals(other.blogState))
			return false;
		if (blogTitle == null) {
			if (other.blogTitle != null)
				return false;
		} else if (!blogTitle.equals(other.blogTitle))
			return false;
		if (lastModifiedTime == null) {
			if (other.lastModifiedTime != null)
				return false;
		} else if (!lastModifiedTime.equals(other.lastModifiedTime))
			return false;
		if (numberOfAgree == null) {
			if (other.numberOfAgree != null)
				return false;
		} else if (!numberOfAgree.equals(other.numberOfAgree))
			return false;
		if (numberOfDisagree == null) {
			if (other.numberOfDisagree != null)
				return false;
		} else if (!numberOfDisagree.equals(other.numberOfDisagree))
			return false;
		if (postTime == null) {
			if (other.postTime != null)
				return false;
		} else if (!postTime.equals(other.postTime))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	public BlogData(Integer blogId, String blogTitle, String blogContent, Integer numberOfAgree,
			Integer numberOfDisagree, Integer blogState, Date postTime, Date lastModifiedTime, Integer userId,
			List<String> tags) {
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
	public BlogData() {
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
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}
	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	
}
