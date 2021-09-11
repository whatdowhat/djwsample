package com.whatdo.keep.config.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class CustomLogoutHandler  implements LogoutSuccessHandler{

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		if (authentication != null && authentication.getDetails() != null) {
	            try {
	            	request.getSession().setAttribute("member", null);
	            	String errorMessage = (String) request.getAttribute("errorMessage");
					System.out.println("CustomLogoutHandler errorMessageerrorMessage"+errorMessage);
					request.setAttribute("errorMessage", errorMessage);
	            	
	            	request.getSession().invalidate();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        } 
	        response.setStatus(HttpServletResponse.SC_OK);
	        response.sendRedirect("/login.do");
	}	
	
}
