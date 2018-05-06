package com.blog.domain;

import com.mysql.jdbc.Blob;

public class User {

	private String userId;
	private String nickName;
	private String password;
	private String eMail;
	private String phoneNumber;
	//private Blob profile;
	private Integer status;
	private Integer numOfFans;
	private Integer NumOfAttention;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/*
	public Blob getProfile() {
		return profile;
	}
	public void setProfile(Blob profile) {
		this.profile = profile;
	}*/
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getNumOfFans() {
		return numOfFans;
	}
	public void setNumOfFans(Integer numOfFans) {
		this.numOfFans = numOfFans;
	}
	public Integer getNumOfAttention() {
		return NumOfAttention;
	}
	public void setNumOfAttention(Integer numOfAttention) {
		NumOfAttention = numOfAttention;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NumOfAttention == null) ? 0 : NumOfAttention.hashCode());
		result = prime * result + ((eMail == null) ? 0 : eMail.hashCode());
		result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
		result = prime * result + ((numOfFans == null) ? 0 : numOfFans.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		//result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", nickName=" + nickName + ", password=" + password + ", eMail=" + eMail
				+ ", phoneNumber=" + phoneNumber + ", profile=" + ", status=" + status + ", numOfFans="
				+ numOfFans + ", NumOfAttention=" + NumOfAttention + "]";
	}
	public User(String userId, String nickName, String password, String eMail, String phoneNumber, Blob profile,
			Integer status, Integer numOfFans, Integer numOfAttention) {
		super();
		this.userId = userId;
		this.nickName = nickName;
		this.password = password;
		this.eMail = eMail;
		this.phoneNumber = phoneNumber;
		//this.profile = profile;
		this.status = status;
		this.numOfFans = numOfFans;
		NumOfAttention = numOfAttention;
	}
	public User() {
	}
}
