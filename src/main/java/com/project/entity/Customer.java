package com.project.entity;

import org.hibernate.annotations.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Component
@DynamicInsert
@DynamicUpdate
@Table(name = "CUSTOMER_MASTER")
@MappedSuperclass
public class Customer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "customer_id")
	@SequenceGenerator(name = "customer_id",sequenceName = "customer_id",allocationSize = 1)
	private int customerId;
	
	@Id
	@Column(name = "customer_code")
	@NotNull(message = "customer code is required")
	private String customerCode;
	
	@Column(name = "customer_name")
	@Pattern(regexp = "^[a-zA-Z0-9]+$",message = "customer name can only contain a-z,A-Z,0-9 and spaces")
	private String customerName;
	
	@Column(name = "customer_address_1")
	private String customerAddress1;
	
	@Column(name = "customer_address_2")
	private String customerAddress2;
	
	@Column(name = "pincode")
	private int pincode;
	
	@Column(name = "email_address")
	@Email(message = "Invalid email Address")
	private String emailAddress;
	
	@Column(name = "contact_number")
	private long contactNumber;
	
	@Column(name = "primary_contact_person")
	private String primaryContactPerson;
	
	@Column(name = "record_status")
	private String recordStatus;
	
	@Column(name = "flag")
	private char flag;
	
	@Column(name = "create_date")
	private LocalDate createDate;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "modified_date")
	private LocalDate modifiedDate;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "authorized_date")
	private LocalDate authorizedDate;
	
	@Column(name = "authorized_by")
	private String authorizedBy;
	
	
	
	

	
	
	
	public Customer(int customerId, String customerCode, String customerName, String customerAddress1,
			String customerAddress2, int pincode, String emailAddress, long contactNumber, String primaryContactPerson,
			String recordStatus, char flag, LocalDate createDate, String createdBy, LocalDate modifiedDate,
			String modifiedBy, LocalDate authorizedDate, String authorizedBy) {
		super();
		this.customerId = customerId;
		this.customerCode = customerCode;
		this.customerName = customerName;
		this.customerAddress1 = customerAddress1;
		this.customerAddress2 = customerAddress2;
		this.pincode = pincode;
		this.emailAddress = emailAddress;
		this.contactNumber = contactNumber;
		this.primaryContactPerson = primaryContactPerson;
		this.recordStatus = recordStatus;
		this.flag = flag;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		this.authorizedDate = authorizedDate;
		this.authorizedBy = authorizedBy;
	}

	public Customer( String customerCode, String customerName, String customerAddress1,
			String customerAddress2, int pincode, String emailAddress, long contactNumber, String primaryContactPerson, String recordStatus,
			char flag, LocalDate createDate, String createdBy) {
		super();
		this.customerCode = customerCode;
		this.customerName = customerName;
		this.customerAddress1 = customerAddress1;
		this.customerAddress2 = customerAddress2;
		this.pincode = pincode;
		this.emailAddress = emailAddress;
		this.contactNumber = contactNumber;
		this.primaryContactPerson = primaryContactPerson;
		this.recordStatus = recordStatus;
		this.flag = flag;
		this.createDate = createDate;
		this.createdBy = createdBy;
		
	}
	
	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Customer(){};

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress1() {
		return customerAddress1;
	}

	public void setCustomerAddress1(String customerAddress1) {
		this.customerAddress1 = customerAddress1;
	}

	public String getCustomerAddress2() {
		return customerAddress2;
	}

	public void setCustomerAddress2(String customerAddress2) {
		this.customerAddress2 = customerAddress2;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPrimaryContactPerson() {
		return primaryContactPerson;
	}

	public void setPrimaryContactPerson(String primaryContactPerson) {
		this.primaryContactPerson = primaryContactPerson;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public char getFlag() {
		return flag;
	}

	public void setFlag(char flag) {
		this.flag = flag;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDate getAuthorizedDate() {
		return authorizedDate;
	}

	public void setAuthorizedDate(LocalDate authorizedDate) {
		this.authorizedDate = authorizedDate;
	}

	public String getAuthorizedBy() {
		return authorizedBy;
	}

	public void setAuthorizedBy(String authorizedBy) {
		this.authorizedBy = authorizedBy;
	}
	
}


