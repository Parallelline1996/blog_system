package com.blog.domain;

public class UpOrDown {

	private UpOrDownId upOrDownId;
	private Integer state;
	public UpOrDownId getUpOrDownId() {
		return upOrDownId;
	}
	public void setUpOrDownId(UpOrDownId upOrDownId) {
		this.upOrDownId = upOrDownId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((upOrDownId == null) ? 0 : upOrDownId.hashCode());
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
		UpOrDown other = (UpOrDown) obj;
		if (upOrDownId == null) {
			if (other.upOrDownId != null)
				return false;
		} else if (!upOrDownId.equals(other.upOrDownId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UpOrDown [upOrDownId=" + upOrDownId + ", state=" + state + "]";
	}
	public UpOrDown(UpOrDownId upOrDownId, Integer state) {
		super();
		this.upOrDownId = upOrDownId;
		this.state = state;
	}
	public UpOrDown() {
	}
}
