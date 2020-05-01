package com.laptrinhjavaweb.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.service.IUserService;

@Controller(value = "homeControllerOfWeb")
public class HomeController {
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/trang-chu",method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("web/home");
		return mav;
		
	}
	
	@RequestMapping(value = "/dang-nhap",method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("login");
		return  mav;
	}
	
	@RequestMapping(value="/dang-ky", method = RequestMethod.GET)
	public ModelAndView doGetAddUser() {
		ModelAndView mav = new ModelAndView("sign-up");
		return mav;
	}
	
	
	@RequestMapping(value = "/dang-ky",method = RequestMethod.POST)
	public ModelAndView doPostAddUser(@RequestParam("username") String user,
			@RequestParam("fullname") String fullname,@RequestParam("password") String password) {
		ModelAndView mav = new ModelAndView("sign-up");

		if(user != "" && password != "" && fullname != "") {
			String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
			if(userService.InsertAccount(user, fullname, hash)) {
				mav.addObject("message","Đăng ký thành công");
				mav.addObject("alert","success");
			}
			else {
				mav.addObject("message","Đăng ký thất bại");
				mav.addObject("alert","danger");
			}
		}else {
			mav.addObject("message","Không được để trống !");
			mav.addObject("alert","danger");
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/thoat",method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		//Neu da dang nhap
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth); // trong logout se khoi tao session va remove key
		}
		return  new ModelAndView("redirect:/dang-nhap");
		//return  new ModelAndView("redirect:/trang-chu");
	}
	
	
	@RequestMapping(value = "/accessDenied",method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return  new ModelAndView("redirect:/dang-nhap?accessDenied");
	}
}
