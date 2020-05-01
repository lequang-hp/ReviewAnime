package com.laptrinhjavaweb.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.service.ICommentServcie;

@RestController(value = "commentAPIOfAdmin")
public class CommentAPI {
	
	@Autowired
	private ICommentServcie commentService;
	@DeleteMapping("/api/comment")
	public void deleteComment(@RequestBody long [] ids) {
		commentService.delete(ids);
	}
}
