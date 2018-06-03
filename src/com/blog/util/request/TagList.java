package com.blog.util.request;

import java.util.List;

public class TagList {

	List<String> tagContent;

	public List<String> getTagContent() {
		return tagContent;
	}

	public void setTagContent(List<String> tagContent) {
		this.tagContent = tagContent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tagContent == null) ? 0 : tagContent.hashCode());
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
		TagList other = (TagList) obj;
		if (tagContent == null) {
			if (other.tagContent != null)
				return false;
		} else if (!tagContent.equals(other.tagContent))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TagList [tagContent=" + tagContent + "]";
	}

	public TagList(List<String> tagContent) {
		super();
		this.tagContent = tagContent;
	}
	
	public TagList() {
	}
}
