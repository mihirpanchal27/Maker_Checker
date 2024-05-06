package com.project.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.entity.Customer;
import com.project.entity.CustomerMST;
import com.project.entity.CustomerTMP;
import com.project.service.CustomerService;
import com.project.validations.CustomerValidations;
import com.project.validations.EditCustomerValidation;

@Controller
public class MakerController {

	static final String REDIRECT_MAKER = "redirect:/maker";
	
	@Autowired
	CustomerService service;
	
	@Autowired
	CustomerValidations customerValidation;
	
	@Autowired
	EditCustomerValidation editCustomerValidation;
	
	@RequestMapping(value = "/maker/AddCustomer")
	public String addCustomerForm(Model model) {
		model.addAttribute("customer",new CustomerTMP());
		return "customer-form";
	}
	
	@RequestMapping(value = "/maker/CustomerInfo",method = RequestMethod.POST)
	public String customerInfo(@ModelAttribute("customer") CustomerTMP customer, BindingResult result , Model model) {
		customerValidation.validate(customer,result);
		if(result.hasErrors()) {
			return "customer-form";
		}
		model.addAttribute("customer",customer);
		customer.setRecordStatus("N");
		customer.setCreateDate(LocalDate.now());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		customer.setCreatedBy(username);
		service.addNewCustomer(customer);
		return REDIRECT_MAKER;
	}
	
	@RequestMapping(value = "/maker/delete/{customerCode}")
	public String delete(@PathVariable("customerCode") String customerCode , Model model) {
		Customer customer = service.getCustomerByCode(customerCode);
		
		if(customer == null) {
			customer = service.getCustomerFromMst(customerCode);
		}
		
		if(customer != null || customer.getRecordStatus()!= null) {
			String status = customer.getRecordStatus();
			if(status.equals("D")|| status.equals("M")||status.equals("MR")|| status.equals("DR")||status.equals("NR")) {
				model.addAttribute("alertMessage","Maker Cannot Delete this Record");
				return REDIRECT_MAKER;
			}
		}
		service.deleteCustomer(customerCode);
		return REDIRECT_MAKER;
				
	}
	
	@RequestMapping(value = "/maker/update/{customerCode}/CustomerInfo")
	public String customerUpdate(@ModelAttribute("customer")CustomerTMP customer,BindingResult result) {
		editCustomerValidation.validate(customer, result);
		
		if(result.hasErrors()) {
			return "customer-update";
		}
		service.updateTemprory(customer);
		return REDIRECT_MAKER;
	}
	
	@RequestMapping(value = "/maker/update/{customerCode}/MasterCustomer")
	public String masterCustomerUpdate(@ModelAttribute("customer")CustomerMST customer,BindingResult result) {
		editCustomerValidation.validate(customer, result);
		
		if(result.hasErrors()) {
			return "customer-update";
		}
		service.updateMaster(customer);
		return REDIRECT_MAKER;
	}
	
	@RequestMapping(value = "/addFile")
	public String addFile(@RequestParam("file") MultipartFile file) {
		service.addFile(file);
		return  REDIRECT_MAKER;
	}
	
	@RequestMapping(value ="/maker/customerDetails/{customerCode}")
		public String cusotmerDetails(@PathVariable("customerCode") String customerCode , Model model) {
			Customer customer = service.getCustomerByCode(customerCode);
			if(customer == null) {
				customer = service.getCustomerFromMst(customerCode);
			}
			model.addAttribute("customer",customer);
			return "customer-details";
		}
	
	@RequestMapping(value = "/maker/customerDetails/getHome/{customerCode}")
	public String makerPage() {
		return REDIRECT_MAKER;
	}
	
}
