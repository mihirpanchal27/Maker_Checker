package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.entity.Customer;
import com.project.service.CustomerService;

@Controller
public class CheckerController {

	private static final String REDIRECT_CHECKER = "redirect:/checker";
	
	@Autowired
	CustomerService service;
	
	@RequestMapping(value = "/checker/Approve/{customerCode}")
	public String approve(@PathVariable("customerCode") String customerCode) {
		service.approveCustomer(customerCode);
		return REDIRECT_CHECKER;
	}
	
	@RequestMapping(value = "/checker/Reject/{cusotmerCode}")
	public String reject(@PathVariable("customerCode") String customerCode) {
		service.checkerRejectCustomer(customerCode);
		return REDIRECT_CHECKER;
	}
	
	@RequestMapping(value = "/checker/customerDetails/{customerCode")
	public String customerDetails(@PathVariable("customerCode") String customerCode,Model model) {
		Customer customer = service.getCustomerByCode(customerCode);
		if(customer == null) {
			service.getCustomerFromMst(customerCode);
		}
		model.addAttribute("customer",customer);
		return "customer-details";
	}
	@RequestMapping(value = "/checker/customerDetails/getHome/{customerCode}")
	public String makerPage() {
		return REDIRECT_CHECKER;
	}
}
