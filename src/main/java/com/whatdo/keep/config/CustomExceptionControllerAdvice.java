package com.whatdo.keep.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomExceptionControllerAdvice {

	public static final Logger log = LoggerFactory.getLogger(CustomExceptionControllerAdvice.class);
	
	@ExceptionHandler(HttpMessageNotReadableException.class) 
	public ResponseEntity<String> HttpMessageNotReadableException(HttpMessageNotReadableException e) { 
		
		ResponseEntity response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return response;
		
	}
	
	
	@ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class) 
	public ModelAndView SQLIntegrityConstraintViolationException(org.springframework.dao.DataIntegrityViolationException e) { 
		
		
		log.debug("@ExceptionHandler::"+e.getMessage());
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMessage", "DATA BASE 중복키 오류");
		mv.setViewName("/");
		
		return mv; 
		
	}
	
	@ExceptionHandler(Exception.class) 
	public ModelAndView custom(Exception e) { 
		
		log.debug("@ExceptionHandler::"+e.getMessage());
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMessage", "내부오류 발생 관리자에게 문의하세요.");
//		mv.setViewName("/login/login");
		mv.setViewName("/");
		
		return mv; 
		
	}
	
}
