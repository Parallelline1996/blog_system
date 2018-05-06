package com.blog.domain;

import java.util.Set;

public class Tag {

	private String tagId;
	private String userId;
	private String tagContent;
	private Set<Blog> blogs;
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTagContent() {
		return tagContent;
	}
	public void setTagContent(String tagContent) {
		this.tagContent = tagContent;
	}
	public Set<Blog> getBlogs() {
		return blogs;
	}
	public void setBlogs(Set<Blog> blogs) {
		this.blogs = blogs;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blogs == null) ? 0 : blogs.hashCode());
		result = prime * result + ((tagContent == null) ? 0 : tagContent.hashCode());
		result = prime * result + ((tagId == null) ? 0 : tagId.hashCode());
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
		Tag other = (Tag) obj;
		if (blogs == null) {
			if (other.blogs != null)
				return false;
		} else if (!blogs.equals(other.blogs))
			return false;
		if (tagContent == null) {
			if (other.tagContent != null)
				return false;
		} else if (!tagContent.equals(other.tagContent))
			return false;
		if (tagId == null) {
			if (other.tagId != null)
				return false;
		} else if (!tagId.equals(other.tagId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Tag [tagId=" + tagId + ", userId=" + userId + ", tagContent=" + tagContent + ", blogs=" + blogs + "]";
	}
	public Tag(String tagId, String userId, String tagContent, Set<Blog> blogs) {
		super();
		this.tagId = tagId;
		this.userId = userId;
		this.tagContent = tagContent;
		this.blogs = blogs;
	}
	public Tag() {
	}
}
