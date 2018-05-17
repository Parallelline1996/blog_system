package com.blog.util.request;

import java.util.List;

public class BlogWithTag {

	private Integer blogId;
	private List<Integer> tagId;
	@Override
	public String toString() {
		return "BlogWithTag [blogId=" + blogId + ", tagId=" + tagId + "]";
	}
	public BlogWithTag() {
	}
	public BlogWithTag(Integer blogId, List<Integer> tagId) {
		super();
		this.blogId = blogId;
		this.tagId = tagId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blogId == null) ? 0 : blogId.hashCode());
		result = prime * result + ((tagId == null) ? 0 : tagId.hashCode());
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
		BlogWithTag other = (BlogWithTag) obj;
		if (blogId == null) {
			if (other.blogId != null)
				return false;
		} else if (!blogId.equals(other.blogId))
			return false;
		if (tagId == null) {
			if (other.tagId != null)
				return false;
		} else if (!tagId.equals(other.tagId))
			return false;
		return true;
	}
	public Integer getBlogId() {
		return blogId;
	}
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}
	public List<Integer> getTagId() {
		return tagId;
	}
	public void setTagId(List<Integer> tagId) {
		this.tagId = tagId;
	}
	
}
