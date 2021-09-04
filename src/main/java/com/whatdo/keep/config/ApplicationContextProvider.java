package com.whatdo.keep.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware {

	  public static ApplicationContext applicationContext;
	    
	    @Override
	    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
	        applicationContext = ctx;
	    }
	    
	    public static ApplicationContext getApplicationContext() {
	        return applicationContext;
	    }



}
