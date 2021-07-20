package com.whatdo.keep.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;


//public class BasicWebApplicationInitializer implements WebApplicationInitializer{
public class BasicWebApplicationInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		AnnotationConfigWebApplicationContext  applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(ApplicationContextConfig.class);
		ServletRegistration.Dynamic dynamic = servletContext.addServlet("whatdoDispatcher", new DispatcherServlet(applicationContext));
		registerCharacterEncodingFilter(servletContext);
		dynamic.setLoadOnStartup(0);
		dynamic.addMapping("/");
		
		

		/* SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	        
	        try {
	            Scheduler scheduler = schedulerFactory.getScheduler();
	            
	            JobDetail job = newJob(UserPointBat.class)
	                    .withIdentity("UserPointBat", Scheduler.DEFAULT_GROUP)
	                    .build();
	            
	            
	            Trigger trigger = newTrigger()
	                .withIdentity("UserPointBatTrigger", Scheduler.DEFAULT_GROUP)
	                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(5, 0))
	                .build();
	                        
	            scheduler.scheduleJob(job, trigger);
	            scheduler.start();
	        } catch(Exception e) {
	            e.printStackTrace();
	        }        
	        try {
	        	
	        	Scheduler scheduler2 = schedulerFactory.getScheduler();
	            
	            JobDetail job = newJob(HasItemPossibleBat.class)
	                    .withIdentity("HasItemPossibleBat", Scheduler.DEFAULT_GROUP)
	                    .build();
	            
	            
	            Trigger trigger = newTrigger()
	                .withIdentity("HasItemPossibleBatTrigger", Scheduler.DEFAULT_GROUP)
//	                .withSchedule(SimpleScheduleBuilder.repeatMinutelyForever(1))
	                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(21, 15))
	                .build();
	                        
	            scheduler2.scheduleJob(job, trigger);
	            scheduler2.start();
	        } catch(Exception e) {
	            e.printStackTrace();
	        }        
		*/
	}

    private void registerCharacterEncodingFilter(ServletContext servletContext) {
        FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter());
        characterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        characterEncodingFilter.setInitParameter("encoding", "UTF-8");
        characterEncodingFilter.setInitParameter("forceEncoding", "true");
    }

	
}
