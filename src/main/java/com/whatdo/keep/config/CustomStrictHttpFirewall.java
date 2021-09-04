package com.whatdo.keep.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.web.firewall.FirewalledRequest;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomStrictHttpFirewall implements HttpFirewall {

	@Override
	public FirewalledRequest getFirewalledRequest(HttpServletRequest request) throws RequestRejectedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpServletResponse getFirewalledResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
								     
}
