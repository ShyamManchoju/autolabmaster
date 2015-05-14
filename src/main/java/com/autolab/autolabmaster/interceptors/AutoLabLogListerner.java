package com.autolab.autolabmaster.interceptors;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.bridge.SLF4JBridgeHandler;

public class AutoLabLogListerner implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("contextDestroyed....");
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("contextInitialized....");
		 
		//remove the jsf root logger, avoid duplicated logging
		//try comment out this and see the different on the console
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
		
	}
	
	

}
