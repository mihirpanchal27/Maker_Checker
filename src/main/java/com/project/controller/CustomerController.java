package com.project.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.entity.Customer;
import com.project.service.CustomerService;

@Controller
public class CustomerController {

	
	@Autowired
	CustomerService service;
	
	static final String REDIRECT_MAKER = "redirect:/maker";
	
	static final String REDIRECT_CHECKER = "redirect:/checker";
	
	@RequestMapping(value = "/get-login")
	public String getLoginForm(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String role = userDetails.getAuthorities().toString();
		if(role.equals("[ROLE_MAKER")) {
			return REDIRECT_MAKER;
		}
		if(role.equals("[ROLE_CHECKER]")) {
			return REDIRECT_CHECKER;
		}
		return "redirect:/index.jsp";
	}
	
	@RequestMapping(value = "/maker")
	public String getAdminPage(Model model) {
		Set<Customer> customerList = service.getAllCustomers();
		model.addAttribute("customerList",customerList);
		return "checker-page";
	}
	
	@RequestMapping(value = "/uploadFile")
	public String addFile(@RequestParam("file") MultipartFile file) {
		return REDIRECT_MAKER;
	}
}
