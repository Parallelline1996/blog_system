package com.blog.domain;

import java.io.Serializable;

public class FollowUpId implements Serializable {

	private static final long serialVersionUID = 1L;
	private String fansId;
	private String bloggerId;
	public String getFansId() {
		return fansId;
	}
	public void setFansId(String fansId) {
		this.fansId = fansId;
	}
	public String getBloggerId() {
		return bloggerId;
	}
	public void setBloggerId(String bloggerId) {
		this.bloggerId = bloggerId;
	}
	@Override
	public String toString() {
		return "FollowUpId [fansId=" + fansId + ", bloggerId=" + bloggerId + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bloggerId == null) ? 0 : bloggerId.hashCode());
		result = prime * result + ((fansId == null) ? 0 : fansId.hashCode());
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
		FollowUpId other = (FollowUpId) obj;
		if (bloggerId == null) {
			if (other.bloggerId != null)
				return false;
		} else if (!bloggerId.equals(other.bloggerId))
			return false;
		if (fansId == null) {
			if (other.fansId != null)
				return false;
		} else if (!fansId.equals(other.fansId))
			return false;
		return true;
	}
	public FollowUpId(String fansId, String bloggerId) {
		super();
		this.fansId = fansId;
		this.bloggerId = bloggerId;
	}
	public FollowUpId() {
	}
	
}
