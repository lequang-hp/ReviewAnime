package com.laptrinhjavaweb.dto;

public class CommentDTO extends AbstractDTO<CommentDTO> {
	private Long user_id;
	private String content;
	private Long newID;
	private String username;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getNewID() {
		return newID;
	}
	public void setNewID(Long newID) {
		this.newID = newID;
	}		
}
