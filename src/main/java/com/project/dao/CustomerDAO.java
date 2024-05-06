package com.project.dao;

import java.util.List;
import java.util.Set;

import com.project.entity.Customer;

public interface CustomerDAO {

	
	void addNewCustomer(Customer customer);
	
	Set<Customer> getAllCustomers();
	
	Set<Customer> getCustomers();
	
	Customer getCustomerByCode(String customerCode);
	
	Customer getCustomerFromMst(String customer);
	
	void updateCustomer(Customer customer);
	
	void deleteCustomer(Customer customer);
	
	void addNewCustomerFromFile(List<Customer> Customer);
	
	
}
