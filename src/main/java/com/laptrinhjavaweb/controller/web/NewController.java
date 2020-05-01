package com.laptrinhjavaweb.controller.web;

import java.util.ArrayList;
import java.util.List;

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
import com.laptrinhjavaweb.service.ICommentServcie;
import com.laptrinhjavaweb.service.INewService;

@Controller(value = "newControllerOfWeb")
public class NewController {
	
	@Autowired
	private INewService newService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private ICommentServcie commentService;
	
	@RequestMapping(value = "/trang-chu/bai-viet/danh-sach",method = RequestMethod.GET)
	public ModelAndView showList(@RequestParam("page") int page,@RequestParam("limit") int limit,@RequestParam(value="searchValue",required = false) String searchValue) {
		NewDTO model = new NewDTO();
		model.setPage(page);
		model.setLimit(limit);
		Pageable pageable = new PageRequest(page - 1, limit); // page - 1 do db bat dau tu vi tri 0
		model.setListResult(newService.findAll(pageable,searchValue));
		model.setTotalItem(newService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));	
		
		ModelAndView mav = new ModelAndView("web/new/list");
		
		mav.addObject("categories",categoryService.findAll());
		mav.addObject("model",model); // thay cho dung request.setAttribute trong jsp-servlet
		return  mav;
	}
	
	
	@RequestMapping(value = "/trang-chu/bai-viet/chi-tiet",method = RequestMethod.GET)
	public ModelAndView showNew(@RequestParam(value = "id") Long id) {
		ModelAndView mav = new ModelAndView("web/new/new");
		NewDTO model = new NewDTO();
		if(id != null) {
			model = newService.findById(id);
		}
		
		mav.addObject("comments",commentService.findByNewId(id));
		mav.addObject("categories",categoryService.findAll());
		mav.addObject("model",model);
	
		return mav;
	}
	
	
	@RequestMapping(value="/trang-chu/bai-viet/the-loai",method = RequestMethod.GET)
	public ModelAndView searchByCategory(@RequestParam(value="code") String code) {
		NewDTO model = new NewDTO();
		List<NewDTO> list= new ArrayList<>();
		list = newService.findByCategoryCode(code);
		model.setListResult(list);
		ModelAndView mav = new ModelAndView("web/new/search");
		mav.addObject("categories",categoryService.findAll());
		mav.addObject("model",model);
		return mav;
	}
}
