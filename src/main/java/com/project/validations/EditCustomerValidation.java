package com.project.validations;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.project.entity.Customer;

@Component
public class EditCustomerValidation implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Customer.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Customer customer = (Customer) target;
		if(!customer.getCustomerName().matches("^[a-zA-Z0-9]+$")) {
			errors.rejectValue("customerName", "customerName.invalid","Customer Name must contain only alphabet and Numbers");	
		}
		
		if(!customer.getEmailAddress().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
			errors.rejectValue("emailAddress","emailAddress.invalid","Customer email is invalid");
			
		}
		if(!String.valueOf(customer.getPincode()).matches("^\\d{6}$")) {
			errors.rejectValue("pincode","pincode.invalid","Pincode should be a 6 - digit number");
		}
		
	}

}
