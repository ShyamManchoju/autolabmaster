package com.autolab.autolabmaster.dao;

import com.autolab.autolabmaster.model.User;

public interface AuthenticationDao {
	
	public String signIn(User user);
}
