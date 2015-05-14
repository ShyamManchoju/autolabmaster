package com.autolab.autolabmaster.controllers;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.autolab.autolabmaster.model.User;
import com.autolab.autolabmaster.service.AuthenticationService;

@Component("loginBean")
public class LoginController implements Serializable{

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(LoginController.class);

	private User user = new User(); 

	@Autowired
	private AuthenticationService authenticationService;

	public String signIn(){

		// logs debug
		if (logger.isDebugEnabled()) {
			logger.debug("LoginController.signIn()");
		}

		String result = authenticationService.signIn(user);
		if(result.equals("homeAutoLabLayout")){
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("username", user.getUsername());
			logger.debug("User signIn success ");
		}else{
			FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(" Invalid Credentials. Please try with vaild Username & Password "));
		}
		
		return result;
	}

	public String logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);		
		session.invalidate();		
		logger.debug("User Logged out");
		return "loginLayout";
	}

	public String showLogin(){
		logger.debug("Inside Show Login ");
		return "loginLayout";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
