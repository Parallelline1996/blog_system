package com.blog.domain;

import java.util.Date;

public class Comment {

	private String commentId;
	private String commentObjectId;
	private String content;
	private Integer status;
	private Date sendTime;
	private Integer objectOption;
	private String userId;
	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getCommentObjectId() {
		return commentObjectId;
	}
	public void setCommentObjectId(String commentObjectId) {
		this.commentObjectId = commentObjectId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getObjectOption() {
		return objectOption;
	}
	public void setObjectOption(Integer objectOption) {
		this.objectOption = objectOption;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentObjectId=" + commentObjectId + ", content=" + content
				+ ", status=" + status + ", sendTime=" + sendTime + ", objectOption=" + objectOption + ", userId="
				+ userId + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentId == null) ? 0 : commentId.hashCode());
		result = prime * result + ((commentObjectId == null) ? 0 : commentObjectId.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((objectOption == null) ? 0 : objectOption.hashCode());
		result = prime * result + ((sendTime == null) ? 0 : sendTime.hashCode());
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
		Comment other = (Comment) obj;
		if (commentId == null) {
			if (other.commentId != null)
				return false;
		}
		return true;
	}
	public Comment(String commentId, String commentObjectId, String content, Integer status, Date sendTime,
			Integer objectOption, String userId) {
		super();
		this.commentId = commentId;
		this.commentObjectId = commentObjectId;
		this.content = content;
		this.status = status;
		this.sendTime = sendTime;
		this.objectOption = objectOption;
		this.userId = userId;
	}
	public Comment() {
	}
}
