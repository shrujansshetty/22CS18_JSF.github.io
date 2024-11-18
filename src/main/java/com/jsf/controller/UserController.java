package com.jsf.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsf.model.UserDtls;
import com.jsf.repository.UserRepository;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@ModelAttribute
	private void userDetails(Model m,Principal p) {
		String email=p.getName();
		UserDtls user=userRepo.findByEmail(email);
		m.addAttribute("user",user);
	}
	
	@GetMapping("/")
	public String home(){
		return "user/home";
	}
	@GetMapping("/home")
	public String myhome(){
		return "user/home";
	}
	@GetMapping("/user/home")
	public String userhome(){
		return "user/home";
	}
	
	@GetMapping("/doctors")
	public String doctors(){
		return "user/doctors";
	}
	@GetMapping("/submit-appointment")
	public String submitappointment(){
		return "user/home";
	}

	@GetMapping("/booking")
	public String showBookingPage(@RequestParam("doctor") String doctor, Model model) {
		model.addAttribute("doctor", doctor);
		return "user/booking";
	}

}
