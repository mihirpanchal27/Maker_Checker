package com.project.dao;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.project.entity.User;

@Repository
public class UserDAOImpl implements UserDAO{

	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	@Override
	public User findByUsername(String username) {
		try(Session session = sessionFactory.openSession()){
			Query query = session.createQuery("from User where username = :name");
			query.setParameter("name",username);
			User user =  (User)query.getSingleResult();
			return user;
		}catch(Exception e) {
			return null;
		}
	}

	
}
