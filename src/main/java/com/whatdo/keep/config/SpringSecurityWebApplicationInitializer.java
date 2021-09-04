package com.whatdo.keep.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SpringSecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer{

	 public SpringSecurityWebApplicationInitializer() {
	        super(WebSecurityConfig.class);
	    }

}
