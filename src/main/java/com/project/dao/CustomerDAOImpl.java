package com.project.dao;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.entity.Customer;
import com.project.entity.CustomerMST;
import com.project.entity.CustomerTMP;

@Repository
@Transactional
public class CustomerDAOImpl implements CustomerDAO{
	
	private static final Logger logger = (Logger) LogManager.getLogger();
	
	
	SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	@Override
	public void addNewCustomer(Customer customer) {
		
		try(Session session = sessionFactory.openSession()){
			Transaction transaction = session.beginTransaction();
			BigDecimal nextValue = (BigDecimal) session.createNativeQuery("SELECT customer_id_seq.nextval FROM dual").uniqueResult();
			int id = nextValue.intValue();
			customer.setCustomerId(id);
			session.save(customer);
			transaction.commit();
			
		}catch(Exception e) {
			logger.error(e.getStackTrace());
			logger.error(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Customer> getAllCustomers() {
		
		List<Customer> uniqueCustomers = new ArrayList<>();
		
		try(Session session = sessionFactory.openSession()){
			Query query = session.createQuery("from CustomerTMP");
			Query query1 = session.createQuery("From CustomerMST");
			
			uniqueCustomers = query.getResultList();
			uniqueCustomers.addAll(query1.getResultList());
			
		}catch(Exception e) {
			logger.error(e.getStackTrace());
		}
		return new HashSet<>(uniqueCustomers);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Customer> getCustomers() {
		List<Customer> uniqueCustomers = new ArrayList<>();
		
		try(Session session = sessionFactory.openSession()){
			Query query = session.createQuery("from CustomerTMP where recordStatus in ('M','D','N')");
			Query query1 = session.createQuery("From CustomerMST where recordStatus in ('M','D','N')");
			
			uniqueCustomers = query.getResultList();
			uniqueCustomers.addAll(query1.getResultList());
			
		}catch(Exception e) {
			logger.error(e.getStackTrace());
		}
		return new HashSet<>(uniqueCustomers);
	}

	@Override
	public Customer getCustomerByCode(String customerCode) {
		Customer customer = null;
		try(Session session = sessionFactory.openSession()){
			customer = session.get(CustomerTMP.class, customerCode);
			
		}catch(Exception e) {
			logger.error(e.getStackTrace());
		}
		return customer;
	}

	@Override
	public Customer getCustomerFromMst(String customerCode) {
		Customer customer = null;
		try(Session session = sessionFactory.openSession()){
			customer = session.get(CustomerMST.class, customerCode);
			
		}catch(Exception e) {
			logger.error(e.getStackTrace());
		}
		return customer;
	}

	@Override
	@Transactional
	public void updateCustomer(Customer customer) {
		Session session = sessionFactory.openSession();
		session.update(customer);
		Transaction transaction = session.beginTransaction();
		transaction.commit();
		session.close();
	}

	@Override
	@Transactional
	public void deleteCustomer(Customer customer) {
		Session session = sessionFactory.openSession();
		session.delete(customer);
		Transaction transaction = session.beginTransaction();
		transaction.commit();
		session.close();
	}

	@Override
	public void addNewCustomerFromFile(List<Customer> customers) {
		
		try(Session session = sessionFactory.getCurrentSession()){
			Transaction transaction = session.beginTransaction();
			for(Customer customer : customers) {
				BigDecimal nextValue = (BigDecimal) session.createNativeQuery("SELECT customer_id_seq.nextval FROM dual").uniqueResult();
				int id = nextValue.intValue();
				customer.setCustomerId(id);
				session.saveOrUpdate(customer);
			}
			transaction.commit();
		}catch(Exception e){
			logger.error(e.getStackTrace());
		}
	}

}
