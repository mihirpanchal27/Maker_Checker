package com.project.service;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.project.entity.Customer;
import com.project.entity.CustomerMST;
import com.project.entity.CustomerTMP;

public interface CustomerService {

	
	void addNewCustomer(Customer c);
	
	Set<Customer> getAllCustomers();
	
	Set<Customer> getCustomers();
	
	public void deleteCustomer(String customerCode);
	
	Customer getCustomerByCode(String customerCode);
	
	Customer getCustomerFromMst(String customerCode);
	
	void approveCustomer(String customerCode);
	
	void checkerRejectCustomer(String customerCode);
	
	void updateTemprory(CustomerTMP customer);
	
	void updateMaster(CustomerMST customer);
	
	Customer getTMP(Customer customer);
	
	Customer getMST(Customer customer);
	
	public void addFile(MultipartFile file);
}
