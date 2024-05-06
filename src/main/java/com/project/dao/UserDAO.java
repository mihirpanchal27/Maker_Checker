package com.project.dao;

import com.project.entity.User;

public interface UserDAO {

	
	User findByUsername(String username);
}
