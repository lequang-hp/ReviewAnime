package com.laptrinhjavaweb.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.service.ICommentServcie;

@Controller(value = "commentControllerOfWeb")
public class CommentController {
	
	
	@Autowired
	private ICommentServcie commentService;
	
	@RequestMapping(value = "/binh-luan",method = RequestMethod.POST)
	public ModelAndView showComment(@RequestParam("username") String username,@RequestParam("cmt") String cmt,@RequestParam("newID") Long newID) {
		
		commentService.InsertComment(username, cmt, newID);
		
		return new ModelAndView("redirect:/trang-chu/bai-viet/chi-tiet?id=" + newID);
	}
}
