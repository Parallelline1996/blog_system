package com.blog.domain;

public class Follow {
	
	private FollowUpId followUpId;
	
	public FollowUpId getFollowUpId() {
		return followUpId;
	}
	public void setFollowUpId(FollowUpId followUpId) {
		this.followUpId = followUpId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((followUpId == null) ? 0 : followUpId.hashCode());
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
		Follow other = (Follow) obj;
		if (followUpId == null) {
			if (other.followUpId != null)
				return false;
		} else if (!followUpId.equals(other.followUpId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Follow [followUpId=" + followUpId + "]";
	}
	public Follow(FollowUpId followUpId) {
		super();
		this.followUpId = followUpId;
	}
	public Follow() {
	}
	
}
