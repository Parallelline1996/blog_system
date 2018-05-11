package com.blog.util.response;

import java.sql.Blob;
import java.util.Date;

public class UserSimpleData {
	private String userId;
	private String nickName;
	private Blob profile;
	private Integer numOfFans;
	private Integer NumOfAttention;
	public UserSimpleData() {
		// TODO Auto-generated constructor stub
	}
	public String getUserId() {
		return userId;
	}
	public UserSimpleData(String userId, String nickName,Blob  profile,
			Integer numOfFans,Integer NumOfAttention) {
		super();
		this.userId = userId;
		this.nickName = nickName;
		this.profile =  profile;
		this.numOfFans = numOfFans;
		this.NumOfAttention = NumOfAttention;

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
	public Integer getNumOfAttention() {
		return NumOfAttention;
	}
	public void setNumOfAttention(Integer numOfAttention) {
		NumOfAttention = numOfAttention;
	}
}