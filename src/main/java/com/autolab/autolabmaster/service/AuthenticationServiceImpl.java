package com.autolab.autolabmaster.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autolab.autolabmaster.dao.AuthenticationDao;
import com.autolab.autolabmaster.model.User;

@Service
public class AuthenticationServiceImpl implements AuthenticationService, Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	private AuthenticationDao authenticationDao;

	public String signIn(User user) {
		
		String result = authenticationDao.signIn(user);
		
		return result;
	}
	
}
