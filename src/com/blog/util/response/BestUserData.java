package com.blog.util.response;

import java.sql.Blob;

public class BestUserData {
	
	private Integer userId;
	private String nickName;
	private Blob profile;
	private Integer numOfFans;
	private Integer numberOfBlogs;
	public BestUserData() {
	}
	@Override
	public String toString() {
		return "BestUserData [userId=" + userId + ", nickName=" + nickName + ", profile=" + profile + ", numOfFans="
				+ numOfFans + ", numberOfBlogs=" + numberOfBlogs + "]";
	}
	public BestUserData(Integer userId, String nickName, Blob profile, Integer numOfFans, Integer numberOfBlogs) {
		super();
		this.userId = userId;
		this.nickName = nickName;
		this.profile = profile;
		this.numOfFans = numOfFans;
		this.numberOfBlogs = numberOfBlogs;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
		result = prime * result + ((numOfFans == null) ? 0 : numOfFans.hashCode());
		result = prime * result + ((numberOfBlogs == null) ? 0 : numberOfBlogs.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
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
		BestUserData other = (BestUserData) obj;
		if (nickName == null) {
			if (other.nickName != null)
				return false;
		} else if (!nickName.equals(other.nickName))
			return false;
		if (numOfFans == null) {
			if (other.numOfFans != null)
				return false;
		} else if (!numOfFans.equals(other.numOfFans))
			return false;
		if (numberOfBlogs == null) {
			if (other.numberOfBlogs != null)
				return false;
		} else if (!numberOfBlogs.equals(other.numberOfBlogs))
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Blob getProfile() {
		return profile;
	}
	public void setProfile(Blob profile) {
		this.profile = profile;
	}
	public Integer getNumOfFans() {
		return numOfFans;
	}
	public void setNumOfFans(Integer numOfFans) {
		this.numOfFans = numOfFans;
	}
	public Integer getNumberOfBlogs() {
		return numberOfBlogs;
	}
	public void setNumberOfBlogs(Integer numberOfBlogs) {
		this.numberOfBlogs = numberOfBlogs;
	}
	
}
