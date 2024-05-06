package com.project.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.dao.CustomerDAO;
import com.project.entity.Customer;
import com.project.entity.CustomerMST;
import com.project.entity.CustomerTMP;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerDAO customerDao;
	
	private static final Logger logger = (Logger) LogManager.getLogger();

	@Override
	public void addNewCustomer(Customer c) {
		customerDao.addNewCustomer(c);
	}

	@Override
	public Set<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerDao.getAllCustomers();
	}

	@Override
	public Set<Customer> getCustomers() {
		// TODO Auto-generated method stub
		return customerDao.getCustomers();
	}

	@Override
	public void deleteCustomer(String customerCode) {

		Customer customer = customerDao.getCustomerByCode(customerCode);
		if(customer == null) {
			customer = customerDao.getCustomerFromMst(customerCode);
		}
		
		String status = customer.getRecordStatus();
		if(status.equals("N")){
			customerDao.deleteCustomer(customer);
		}else {
			customer.setRecordStatus("D");
			Customer tempCustomer = getTMP(customer);
			customerDao.addNewCustomer(tempCustomer);
		}
				
	}

	@Override
	public Customer getCustomerByCode(String customerCode) {
		// TODO Auto-generated method stub
		return customerDao.getCustomerByCode(customerCode);
	}

	@Override
	public Customer getCustomerFromMst(String customerCode) {
		// TODO Auto-generated method stub
		return customerDao.getCustomerFromMst(customerCode);
	}

	@Override
	public void approveCustomer(String customerCode) {
		Customer customer = customerDao.getCustomerByCode(customerCode);
		if(customer != null) {
			String status = customer.getRecordStatus();
			if(status.equals("D")) {
				customerDao.deleteCustomer(customer);
				Customer cust = customerDao.getCustomerFromMst(customerCode);
				if(cust != null) {
					if(cust.getRecordStatus().equals("A")) {
						customerDao.deleteCustomer(cust);
					}
				}
			}
			if(status.equals("N")) {
				Customer masterCustomer = getMST(customer);
				masterCustomer.setRecordStatus("A");
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				String username =auth.getName();
				masterCustomer.setAuthorizedBy(username);
				masterCustomer.setAuthorizedDate(LocalDate.now());
				customerDao.updateCustomer(masterCustomer);
				customerDao.deleteCustomer(customer);
				
			}
		}
	}

	@Override
	public void checkerRejectCustomer(String customerCode) {

		Customer customer = customerDao.getCustomerByCode(customerCode);
		if(customer == null) {
			customer = customerDao.getCustomerFromMst(customerCode);
		}
		if(customer.getRecordStatus() != null) {
			String status = customer.getRecordStatus();
			customer.setRecordStatus(status + "R");
			Customer tempCustomer = getTMP(customer);
			customerDao.updateCustomer(tempCustomer);
		}
	}

	@Override
	public void updateTemprory(CustomerTMP customer) {

		Customer cust = getCustomerByCode(customer.getCustomerCode());
		customer.setRecordStatus("N");
		String status = cust.getRecordStatus();
		if(status.equals("MR")||status.equals("M")||status.equals("D")) {
			customer.setRecordStatus("M");
		}
		customer.setCustomerId(cust.getCustomerId());
		customer.setCreateDate(cust.getCreateDate());
		customer.setCreatedBy(cust.getCreatedBy());
		customerDao.updateCustomer(customer);
	}

	@Override
	public void updateMaster(CustomerMST customer) {
		customer.setRecordStatus("M");
		customer.setModifiedDate(LocalDate.now());
		
		Customer cust = customerDao.getCustomerFromMst(customer.getCustomerCode());
		
		Customer tempCustomer = getTMP(customer);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		tempCustomer.setCustomerId(cust.getCustomerId());
		tempCustomer.setModifiedDate(LocalDate.now());;
		tempCustomer.setModifiedBy(username);
		tempCustomer.setCreateDate(cust.getCreateDate());
		tempCustomer.setCreatedBy(cust.getCreatedBy());
		tempCustomer.setAuthorizedBy(cust.getAuthorizedBy());
		tempCustomer.setAuthorizedDate(cust.getAuthorizedDate());
		customerDao.addNewCustomer(tempCustomer);
	}

	@Override
	public Customer getTMP(Customer customer) {
		return new CustomerTMP(customer.getCustomerId(),customer.getCustomerCode(),customer.getCustomerName(),customer.getCustomerAddress1()
				,customer.getCustomerAddress2(),customer.getPincode(),customer.getEmailAddress(),customer.getContactNumber(),
				customer.getPrimaryContactPerson(),customer.getRecordStatus(),customer.getFlag(),customer.getCreateDate(),customer.getCreatedBy(),
				customer.getModifiedDate(),customer.getModifiedBy(),customer.getAuthorizedDate(),customer.getAuthorizedBy());
	}

	@Override
	public Customer getMST(Customer customer) {
		// TODO Auto-generated method stub
		return new CustomerMST(customer.getCustomerId(),customer.getCustomerCode(),customer.getCustomerName(),customer.getCustomerAddress1()
				,customer.getCustomerAddress2(),customer.getPincode(),customer.getEmailAddress(),customer.getContactNumber(),
				customer.getPrimaryContactPerson(),customer.getRecordStatus(),customer.getFlag(),customer.getCreateDate(),customer.getCreatedBy(),
				customer.getModifiedDate(),customer.getModifiedBy(),customer.getAuthorizedDate(),customer.getAuthorizedBy());
	}

	@Override
	public void addFile(MultipartFile file) {

		String[] data;
		List<Customer> batch = new ArrayList<>();
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy",Locale.ENGLISH);
			String line;
			while((line = reader.readLine())!= null) {
				data = line.split("~");
				
			}
		}catch(FileNotFoundException e) {
			logger.error(e.getStackTrace());
		}catch(Exception e) {
			logger.error(e.getStackTrace());
		}
	}

}
 