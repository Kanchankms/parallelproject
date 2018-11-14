package com.cg.ctrl;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Scope("session")
@Controller
@RequestMapping(value="user")
public class UserController
{
	
	ArrayList<String> cityList;
	ArrayList<String> skillList;
	
	@RequestMapping(value="/showLogin")
	public String prepareLogin(Model model)
	{
		System.out.println("in preparelogin()");
		model.addAttribute("login", new Login());
		return "Login";
	}
	
	
	@RequestMapping(value="/checkLogin")
	public ModelAndView checkLogin(@ModelAttribute("login") Login l)
	{
		//logic to validate username and password
		
		@RequestParam("username") String username;
		@RequestParam("username") String password;
		
		model.addAttribute("username",username);
		if(username.equals("kanchan")&&password.equals("kumari"))
		{
		return new ModelAndView("loginSuccess","username",l.getUserName());
					//username here is dummyname and l.getusername is data from login page
				//(viewname,modelname,modelobj)
		}
		}
	
	@RequestMapping(value="/showRegister")
	public String prepareRegister(Model model)
	{
		cityList = new ArrayList<String>();
		cityList.add("Mumbai");
		cityList.add("Chennai");
		cityList.add("Bangalore");
		cityList.add("Pune");
		
		skillList = new ArrayList<String>();
		
		skillList.add("Java");
		skillList.add("Struts");
		skillList.add("Spring");
		skillList.add("Hibernate");
		
		model.addAttribute("cityList", cityList);
		model.addAttribute("skillList", skillList);
		
		model.addAttribute("user", new User());
		return "register";
		
	 }
	
	
	@RequestMapping(value="/checkRegister")
	public String checkRegister(@ModelAttribute("user")
			@Valid User user,BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			System.out.println("Error");
			model.addAttribute("cityList", cityList);
			model.addAttribute("skillList", skillList);
			
			return "register";
		}
		else
		{
		model.addAttribute("user",user);
		System.out.println("Valid dd");
		return "registerSuccess";
		}
	
	}
	
}	
