package com.whatdo.keep.controller.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.whatdo.keep.controller.MotherController;
import com.whatdo.keep.service.dao.AddressCodeDAO;
import com.whatdo.keep.vo.AddressCodeVO;
import com.whatdo.keep.vo.ChildTestVO;
import com.whatdo.keep.vo.SystemCommonVO;


@Controller
public class LoginController extends MotherController{
	
	@Autowired
	private AddressCodeDAO dao;
	
	private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/index.do", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView index(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session){
		
		
		LOGGER.debug("##index enter");
		String data = req.getContextPath() + "/login.do";
		LOGGER.debug("controll data {} ", data);
		modelAndView.addObject("data",data );
		modelAndView.setViewName("index");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/login2.do", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView login2(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session){
		
		
		LOGGER.debug("##login enter");
		String data = "testCssLoading";
		modelAndView.addObject("data",data );
		modelAndView.setViewName("main/page2");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/login.do", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView login(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session){
		
		
		LOGGER.debug("##login enter");
		
		List<AddressCodeVO> citys = dao.getCitys();
		
		Map<String,String> param = new HashMap();
		param.put("cityCode", citys.get(0).getCityCode());
		List<AddressCodeVO> gus = dao.getGus(param);
		
		param = new HashMap();
		param.put("cityCode", citys.get(0).getCityCode());
		param.put("gunCode", gus.get(0).getGunCode());
		List<AddressCodeVO> dongs = dao.getDongs(param);
		
		LOGGER.debug("##cities {} "+ citys);
		
		modelAndView.addObject("cities", citys );
		modelAndView.addObject("gus",gus );
		modelAndView.addObject("dongs",dongs );
		modelAndView.setViewName("main/page");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/getcity", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView getcity(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			,AddressCodeVO inputform	) throws InterruptedException{
		
		
		LOGGER.debug("##getcity enter");
		LOGGER.debug("## !!!!!!!!!{} ", inputform);
		List<AddressCodeVO> citys = dao.getCitys();
		
		Map<String,String> param = new HashMap();
		param.put("cityCode", inputform.getCityCode());
		List<AddressCodeVO> gus = dao.getGus(param);
		
		param = new HashMap();
		param.put("cityCode", gus.get(0).getCityCode());
		param.put("gunCode", gus.get(0).getGunCode());
		List<AddressCodeVO> dongs = dao.getDongs(param);
		
		modelAndView.addObject("cities", citys );
		modelAndView.addObject("gus",gus );
		modelAndView.addObject("dongs",dongs );
		modelAndView.setViewName("main/ajax/target");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/getgun", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView getgun(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			,AddressCodeVO inputform	) throws InterruptedException{
		
		
		LOGGER.debug("##getcity enter");
		LOGGER.debug("## !!!!!!!!!{} ", inputform);
		List<AddressCodeVO> citys = dao.getCitys();
		
		Map<String,String> param = new HashMap();
		param.put("cityCode", inputform.getCityCode());
		List<AddressCodeVO> gus = dao.getGus(param);
		
		param = new HashMap();
		param.put("cityCode", inputform.getCityCode());
		param.put("gunCode", inputform.getGunCode());
		List<AddressCodeVO> dongs = dao.getDongs(param);
		
		modelAndView.addObject("cities", citys );
		modelAndView.addObject("gus",gus );
		modelAndView.addObject("dongs",dongs );
		modelAndView.setViewName("main/ajax/target");
		
		return modelAndView;
	}
	
//	@RequestMapping(value = "/admin/getcity", method = { RequestMethod.GET,RequestMethod.POST })
//	@ResponseBody
//	public Map getcity(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
//			,AddressCodeVO inputform	) throws InterruptedException{
//		
//		List<AddressCodeVO> citys = dao.getCitys();
//		LOGGER.debug("##getcity enter");
//		LOGGER.debug("## !!!!!!!!!{} ", inputform);
//		Map<String,String> param = new HashMap();
//		param.put("cityCode", inputform.getCityCode());
//		List<AddressCodeVO> gus = dao.getGus(param);
//		param = new HashMap();
//		param.put("cityCode", gus.get(0).getCityCode());
//		param.put("gunCode", gus.get(0).getGunCode());
//		List<AddressCodeVO> dongs = dao.getDongs(param);
//		
//		Map<String, Object> result =  new HashMap();
//		result.put("cities", citys );
//		result.put("gus",gus );
//		result.put("dongs",dongs );
//		return result;		
//	}
	
}
