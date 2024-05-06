package com.project.validations;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.project.entity.Customer;
import com.project.entity.CustomerMST;
import com.project.entity.CustomerTMP;

@Component
public class CustomerValidations implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Customer.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

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
		
		if(!checkCustomer(customer.getCustomerCode())) {
			errors.rejectValue("customerCode","customerCode.invalid","Customer code already exist");
		}
	}
	
	public boolean checkCustomer(String customerCode) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		
		Customer tmp = session.get(CustomerTMP.class, customerCode);
		Customer mst = session.get(CustomerMST.class,customerCode);
		
		if(tmp == null && mst == null){
			return true;
		}
		return false;
	}

	
}
