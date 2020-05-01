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

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.util.MessageUtils;

@Controller(value = "newControllerOfAdmin")
public class NewController {
	@Autowired
	private INewService newService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private MessageUtils messageUtils;
	
	@RequestMapping(value = "/quan-tri/bai-viet/danh-sach",method = RequestMethod.GET)
	public ModelAndView showList(@RequestParam("page") int page,@RequestParam("limit") int limit,HttpServletRequest request) {
		NewDTO model = new NewDTO();
		model.setPage(page);
		model.setLimit(limit);
		Pageable pageable = new PageRequest(page - 1, limit); // page - 1 do db bat dau tu vi tri 0
		model.setListResult(newService.findAll(pageable));
		model.setTotalItem(newService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));	
		
		ModelAndView mav = new ModelAndView("admin/new/list");
		if(request.getParameter("message") != null) {
			Map<String,String> message = messageUtils.getMessage(request.getParameter("message"));
			mav.addObject("message",message.get("message"));
			mav.addObject("alert",message.get("alert"));
		}
		mav.addObject("model",model); // thay cho dung request.setAttribute trong jsp-servlet
		return  mav;
	}
	
	
	/*
	 * Chinh sua co 2 chuc nang: create + update cung su dung chung url
	 * update: phai co id bai viet
	 * @RequestParam(value = "id",required = false): Neu co id tren url thi RequestParam
	 */
	@RequestMapping(value = "/quan-tri/bai-viet/chinh-sua",method = RequestMethod.GET)
	public ModelAndView editNew(@RequestParam(value = "id",required = false) Long id,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/new/edit");
		NewDTO model = new NewDTO();
		if(id != null) {
			model = newService.findById(id);
		}
		if(request.getParameter("message") != null) {
			Map<String,String> message = messageUtils.getMessage(request.getParameter("message"));
			mav.addObject("message",message.get("message"));
			mav.addObject("alert",message.get("alert"));
		}
		
		mav.addObject("categories",categoryService.findAll());
		mav.addObject("model",model);
		return  mav;
	}
}
