package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@Controller
public class HelloController{
	
	@Autowired
	private SimpleJdbcDao dao;
	
	/*@RequestMapping("/")
	protected ModelAndView index() {
		// TODO Auto-generated method stub
		ModelAndView modelAndView=new ModelAndView("hello");		
		return modelAndView;
	}*/
	
	public SimpleJdbcDao getDao() {
		return dao;
	}

	public void setDao(SimpleJdbcDao dao) {
		this.dao = dao;
	}

	@RequestMapping("/")
	protected ModelAndView index() {
		// TODO Auto-generated method stub
		ModelAndView modelAndView=new ModelAndView("display");
		List<User> users=getDao().getUsers();
		modelAndView.addObject("users", users);
		return modelAndView;
	}
	
	@RequestMapping("/hi")
	protected ModelAndView handleRequestInternal()  {
		// TODO Auto-generated method stub
		ModelAndView modelAndView=new ModelAndView("hi");
		modelAndView.addObject("msg", "Welcome, Spring MVC Page!!!");
		return modelAndView;
	}
	
	@RequestMapping(value="/welcome",method=RequestMethod.POST)
	protected ModelAndView welcome(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView=new ModelAndView("welcome");
		User user=new User();
		user.setName(request.getParameter("name"));
		user.setMobile(request.getParameter("mobile"));
		user.setEmail(request.getParameter("email"));
		modelAndView.addObject("user",user);
		return modelAndView;		
	}
	
	@RequestMapping("insert")
	protected String insertForm() {
		return "insert";
	}
	
	@RequestMapping("submit")
	public String submit(HttpServletRequest request,HttpServletResponse response) {
		String name=request.getParameter("name");
		String mobile=request.getParameter("mobile");
		int count=getDao().insert(name, mobile);
		if(count>0)
			return "redirect:display";
		else 
			return "insert";
	}
	
	@RequestMapping("display")
	public ModelAndView display() {
		ModelAndView modelAndView=new ModelAndView("display");
		List<User> users=getDao().getUsers();
		modelAndView.addObject("users", users);
		return modelAndView;
	}

}
