package com.jsf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jsf.model.UserDtls;
import com.jsf.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/signin")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/doctors")
	public String doctors() {
		return "doctors";
	}
	
	@PostMapping("/createUser")
	public String createuser(@ModelAttribute UserDtls user,HttpSession session) {
		//System.out.println("User Details: " + user.getFullName() + ", " + user.getEmail() + ", " + user.getAddress() + ", " + user.getQualification());
		//System.out.println(user);
		
		boolean flag=userService.checkEmail(user.getEmail());
		if(flag) {
			session.setAttribute("msg", "Email aldready Exists");
		} else {
			UserDtls userDtls =userService.createUser(user);
			if(userDtls!=null) {
				session.setAttribute("msg", "Registered Successfully");
			} else {
				session.setAttribute("msg", "Some error in Server");
			}
		}
		
		return "redirect:/register"; //to prevent same data submit to db
	}
}
