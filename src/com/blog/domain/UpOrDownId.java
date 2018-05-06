package com.blog.domain;

import java.io.Serializable;

public class UpOrDownId implements Serializable{

	private static final long serialVersionUID = 1L;
	private String userId;
	private String blogId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blogId == null) ? 0 : blogId.hashCode());
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
		UpOrDownId other = (UpOrDownId) obj;
		if (blogId == null) {
			if (other.blogId != null)
				return false;
		} else if (!blogId.equals(other.blogId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	public UpOrDownId(String userId, String blogId) {
		super();
		this.userId = userId;
		this.blogId = blogId;
	}
	@Override
	public String toString() {
		return "UpOrDownId [userId=" + userId + ", blogId=" + blogId + "]";
	}
	public UpOrDownId() {
	}
}
