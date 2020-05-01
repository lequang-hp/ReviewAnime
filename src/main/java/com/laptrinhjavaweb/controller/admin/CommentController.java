package com.laptrinhjavaweb.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.CommentDTO;
import com.laptrinhjavaweb.service.ICommentServcie;
import com.laptrinhjavaweb.util.MessageUtils;

@Controller(value = "commentControllerOfAdmin")
public class CommentController {
	@Autowired
	private ICommentServcie commentService;
	@Autowired
	private MessageUtils messageUtils;
	
	@RequestMapping(value = "/quan-tri/bai-viet/binh-luan",method = RequestMethod.GET)
	public ModelAndView showComment(@RequestParam("page") int page,@RequestParam("limit") int limit,HttpServletRequest request) {
		CommentDTO model = new CommentDTO();
		model.setPage(page);
		model.setLimit(limit);
		Pageable pageable = new PageRequest(page - 1, limit); // page - 1 do db bat dau tu vi tri 0
		model.setListResult(commentService.findAll(pageable));
		model.setTotalItem(commentService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));	
		
		ModelAndView mav = new ModelAndView("admin/new/comment");
		if(request.getParameter("message") != null) {
			Map<String,String> message = messageUtils.getMessage(request.getParameter("message"));
			mav.addObject("message",message.get("message"));
			mav.addObject("alert",message.get("alert"));
		}
		mav.addObject("model",model); // thay cho dung request.setAttribute trong jsp-servlet
		return  mav;
	}
}
