package com.project.entity;

import java.time.LocalDate;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Cacheable
@Table(name="CUSTOMER_TMP")
public class CustomerTMP extends Customer{
	

	private static final long serialVersionUID = 1L;

	public CustomerTMP(int customerId, String customerCode, String customerName, String customerAddress1,
			String customerAddress2, int pincode, String emailAddress, long contactNumber, String primaryContactPerson,
			String recordStatus, char flag, LocalDate createDate, String createdBy, LocalDate modifiedDate,
			String modifiedBy, LocalDate authorizedDate, String authorizedBy) {
		super(customerId,customerCode, customerName, customerAddress1, customerAddress2, pincode, emailAddress, contactNumber, primaryContactPerson,
				 recordStatus, flag, createDate, createdBy, modifiedDate, modifiedBy, authorizedDate,authorizedBy);
	}

	public CustomerTMP( String customerCode, String customerName, String customerAddress1,
			String customerAddress2, int pincode, String emailAddress, long contactNumber, String primaryContactPerson,
			String recordStatus, char flag, LocalDate createDate, String createdBy) {
		super(customerCode, customerName, customerAddress1, customerAddress2, pincode, emailAddress, contactNumber, primaryContactPerson,
				 recordStatus, flag, createDate, createdBy);
	}
	
	public CustomerTMP() {
		
	}
}
