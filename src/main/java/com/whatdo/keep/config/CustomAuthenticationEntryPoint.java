package com.whatdo.keep.config;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{

	
	static final Logger log = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);
	private String redirectUrl;
	public CustomAuthenticationEntryPoint(){
		
		log.debug("CustomAuthenticationEntryPoint created");
	}
	public CustomAuthenticationEntryPoint(String redirectUrl) {
		log.debug("CustomAuthenticationEntryPoint(String redirectUrl) created");
		this.redirectUrl = redirectUrl;
		
	}
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.debug("enter =================>{}","CustomAuthenticationEntryPoint");
		String errorMessage ="";
		if(authException instanceof BadCredentialsException || authException instanceof InternalAuthenticationServiceException) {
			errorMessage = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해 주십시오.";
		}
		else if(authException instanceof DisabledException) {
			errorMessage = "계정이 비활성화 되었습니다. 관리자에게 문의하세요.";
		}
		else if(authException instanceof CredentialsExpiredException) {
			errorMessage = "비밀번호 유효기간이 만료 되었습니다. 관리자에게 문의하세요.";
		}
		else{
			errorMessage = "권한이 없는 접근입니다. 관리자에게 문의하세요.";
		}
		
		log.debug("errorMessage =================>{}",errorMessage);
		
		request.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = request.getRequestDispatcher("/");
		rd.forward(request, response); 
	}

}
