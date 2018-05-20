package com.blog.util.request;

import java.util.List;

public class NewBlog {

	private Integer blogId;
	private String blogTitle;
	private String blogContent;
	private List<Integer> tags;
	public NewBlog() {
	}
	public NewBlog(Integer blogId, String blogTitle, String blogContent, List<Integer> tags) {
		super();
		this.blogId = blogId;
		this.blogTitle = blogTitle;
		this.blogContent = blogContent;
		this.tags = tags;
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
	public List<Integer> getTags() {
		return tags;
	}
	public void setTags(List<Integer> tags) {
		this.tags = tags;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blogContent == null) ? 0 : blogContent.hashCode());
		result = prime * result + ((blogId == null) ? 0 : blogId.hashCode());
		result = prime * result + ((blogTitle == null) ? 0 : blogTitle.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
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
		NewBlog other = (NewBlog) obj;
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
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NewBlog [blogId=" + blogId + ", blogTitle=" + blogTitle + ", blogContent=" + blogContent + ", tags="
				+ tags + "]";
	}
}
