package com.whatdo.keep.controller.test;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.log.Log;
import com.whatdo.keep.repository.TestVORepository;
import com.whatdo.keep.service.dao.TimeDAO;
import com.whatdo.keep.vo.Customer;
import com.whatdo.keep.vo.InputForm;
import com.whatdo.keep.vo.TestVO;


@Controller
public class TestController {
	
	@Autowired
	TestVORepository re;
	
	@Autowired
	TimeDAO dao;
//	
	@RequestMapping(value = "/test/qrenter.do", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView qrenter(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			){
//		log.debug("/test/qrenter.do enter");
		
		TestVO vo = re.findBySeq(1);
		modelAndView.addObject("jpaObject", vo);
		
		Map<String,Object> m = new HashMap();
		m.put("seq", 194);
		Customer resultVo =  dao.getCustomer2(m);
		modelAndView.addObject("daoObject", resultVo);
		
		modelAndView.setViewName("/test");
		return modelAndView;
		
	}
	
	
	@RequestMapping(value = "/test/form1", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView form1(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			,InputForm inputform ){
		
		Map m = returnParamMap(req);
		modelAndView.setViewName("/test");
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/test/form2", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView form2(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			,Map<String,Object> inputform	){
		
		Map m = returnParamMap(req);
		
		
		modelAndView.setViewName("/test");
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/test/form3", method = { RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public Map form3(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			,InputForm inputform	){

//		Map m = returnParamMap(req);
		System.out.println(inputform.toString());
		System.out.println(inputform.getList().size());
		System.out.println(inputform.getList().get(0));
		return new HashMap<String, String>();
		
	}
	
	public Map<String,String> returnParamMap(HttpServletRequest req){
		
		
		System.out.println("[ENTER]\t:"+req.getRequestURI());
		Map<String,String> result = new HashMap<String, String>();
		Enumeration<String> m =  req.getParameterNames();
		
		while(m.hasMoreElements()) {
			String name = m.nextElement();
			String value = req.getParameter(name);
//			System.out.println(name + ":"+result);
			result.put(name, value);
		}
		System.out.println(result);
		return result;
		
	}
	
//	@RequestMapping(value = "/test/form2", method = { RequestMethod.GET,RequestMethod.POST })
//	public ModelAndView form2(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
//			,Map<String,Object> inputform	){
//		
//		req.getParameterNames()
//		System.out.println("/test/form2");
//		System.out.println(req.getParameterMap().get("input1")[0]);
//		
////		System.out.println( req.getAttribute("inputform"));
//		System.out.println(req.getParameter("inputform"));
////		Map<String, Object> rr =  (Map<String, Object>) req.getParameter("inputform");
////		System.out.println( rr);
//		
//		System.out.println(inputform.toString());
//		modelAndView.setViewName("/test");
//		return modelAndView;
//		
//	}
	
}
