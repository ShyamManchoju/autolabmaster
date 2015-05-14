package com.autolab.autolabmaster.interceptors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.util.StringUtils;



/**
 * @author ssathali
 *
 */

public class SessionTimeoutFilter implements Filter{

	private static Logger logger = Logger.getLogger(SessionTimeoutFilter.class);

	private String timeoutPage = "loginLayout"; 

	public void init(FilterConfig filterConfig) throws ServletException
	{ 
		//We will not process anything in init method so we can omit this part too.
	}  


	//Triggers for every faces-servlet request
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,ServletException {
		try {

			// check whether session variable is set
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			HttpSession ses = req.getSession(false);
			//  allow user to proccede if url is login.xhtml or user logged in or user is accessing any page in //public folder
			String reqURI = req.getRequestURI();
			if ( reqURI.indexOf("/loginLayout.xhtml") >= 0 || (ses != null && ses.getAttribute("username") != null)
					 || reqURI.contains("javax.faces.resource") )
				filterChain.doFilter(request, response);
			else   // user didn't log in but asking for a page that is not allowed so take user to login page
				res.sendRedirect(req.getContextPath() + "/template/layout/loginLayout.xhtml");  // Anonymous user. Redirect to login page
		}
		catch(Throwable t) {
			System.out.println( t.getMessage());
		}
	}
	/*public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,ServletException 
	{ 
		if ((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse)) 
		{ 

			HttpServletRequest httpServletRequest = (HttpServletRequest) request; 
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;

			if (logger.isDebugEnabled()) {
				logger.debug("Before isSessionControlRequiredForThisResource isSessionControlRequiredForThisResource(httpServletRequest)"+isSessionControlRequiredForThisResource(httpServletRequest));
			}
			System.out.println(" isSessionInvalid(httpServletRequest) :: "+ isSessionInvalid(httpServletRequest));
			System.out.println(" isSignIn(httpServletRequest) :: "+ isSignIn(httpServletRequest));
			System.out.println(" httpServletRequest.getContextPath() +  + getTimeoutPage() "+ httpServletRequest.getContextPath() + "/" + getTimeoutPage());
			// is session expire control required for this request? 
			if (isSessionControlRequiredForThisResource(httpServletRequest)) 
			{ 

				// is session invalid? 
				if (isSessionInvalid(httpServletRequest) && !isSignIn(httpServletRequest)) 
				{ 
					String timeoutUrl = httpServletRequest.getContextPath() + "/" + getTimeoutPage();
					if (logger.isDebugEnabled()) {
						logger.debug("Session is invalid! redirecting to timeoutpage : " + timeoutUrl); 
					}
					httpServletResponse.sendRedirect(timeoutUrl); 
					return; 

				} 

			}  
			filterChain.doFilter(request, response); 
		}
	} 
	private boolean isSessionControlRequiredForThisResource(HttpServletRequest httpServletRequest) 
	{ 

		String requestPath = httpServletRequest.getRequestURI(); 

		if(logger.isDebugEnabled()){
			logger.debug("requestPath :: " + requestPath);
			logger.debug("getTimeoutPage :: " + getTimeoutPage());
			logger.debug("!requestPath.contains(getTimeoutPage()) ::" + !requestPath.contains(getTimeoutPage()));
		}

		boolean controlRequired = !requestPath.contains(getTimeoutPage());
		//boolean controlRequired = !StringUtils.contains(requestPath, getTimeoutPage()); 

		return controlRequired; 

	} 
	//Check whether the session is  valid
	private boolean isSessionInvalid(HttpServletRequest httpServletRequest) { 

		boolean sessionInValid = (httpServletRequest.getRequestedSessionId() != null) 

				&& !httpServletRequest.isRequestedSessionIdValid(); 	

		if(logger.isDebugEnabled()){
			logger.debug("httpServletRequest.getRequestedSessionId()  :: " + httpServletRequest.getRequestedSessionId());
			logger.debug("httpServletRequest.isRequestedSessionIdValid() :: " + httpServletRequest.isRequestedSessionIdValid());
			logger.debug("sessionInValid :: " +sessionInValid);
		}
		return sessionInValid; 

	} 

	public boolean isSignIn(HttpServletRequest httpServletRequest){
		HttpSession httpSession = httpServletRequest.getSession(false);
		String user = (String )httpSession.getAttribute("username");
		if (logger.isDebugEnabled()) {
			logger.debug("Is User SignIn :: username :" + user);
		}
		return !StringUtils.isEmpty(user);
	}*/

	public void destroy() 
	{ 

	} 

	public String getTimeoutPage() 
	{  
		//Return timeout page to which we mentioned ablove    
		return timeoutPage;       
	} 

	public void setTimeoutPage(String timeoutPage) 

	{  
		//Set timeout page to which we mentioned ablove    
		this.timeoutPage = timeoutPage; 
	}  


}
