package com.autolab.autolabmaster.interceptors;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.apache.log4j.Logger;

public class AutoLabSessionListerner {

	private static Logger logger = Logger.getLogger(AutoLabSessionListerner.class);
	public AutoLabSessionListerner() 
	{ 

	} 

	public void sessionCreated(HttpSessionEvent event) { 

		if(logger.isDebugEnabled()){
			logger.debug("Current Session created : " 
					+ event.getSession().getId()+ " at "+ new Date()); 
		}		

	} 

	public void sessionDestroyed(HttpSessionEvent event) { 

		// get the destroying session... 

		HttpSession session = event.getSession(); 

		if(logger.isDebugEnabled()){
			logger.debug("Current Session destroyed :" 
				+ session.getId()+ " Logging out user..."); 
		}

		// Only if needed 

		try { 

			prepareLogoutInfoAndLogoutActiveUser(session); 

		} 
		catch(Exception e) 
		{  
			logger.error("Error while logging out at session destroyed : "  
					+ e.getMessage()); 
		} 

	} 

	/** 
	 * Clean your logout operations. 
	 */ 

	public void prepareLogoutInfoAndLogoutActiveUser(HttpSession httpSession) 
	{  
		// Only if needed  
		
	}  
}
