package com.autolab.autolabmaster.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.autolab.autolabmaster.model.User;

@Repository
@Transactional
public class AuthenticationDaoImpl implements AuthenticationDao, Serializable{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AuthenticationDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	public String signIn(User user){
		
		String query = "select u from User as u where u.username=:username and u.password=:password";
		List<User>  users = (List<User>) sessionFactory.getCurrentSession().createQuery(query).setParameter("username", user.getUsername()).setParameter("password",user.getPassword()).list();
				
		if(users.size() == 1){
			if(logger.isDebugEnabled()){
				logger.debug("User logged in Successfully  :: " + users.size());
			}
			return "homeAutoLabLayout";
		}else {
			if(logger.isDebugEnabled()){
				logger.debug("User login Failure :: " + users.size());
			}
			return "loginLayout";
		}
	}

}
