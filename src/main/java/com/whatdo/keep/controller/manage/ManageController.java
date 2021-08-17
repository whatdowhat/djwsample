package com.whatdo.keep.controller.manage;

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
import org.springframework.web.servlet.ModelAndView;

import com.whatdo.keep.controller.MotherController;
import com.whatdo.keep.service.dao.AddressCodeDAO;
import com.whatdo.keep.vo.AddressCodeVO;
import com.whatdo.keep.vo.GroupVO;



@Controller
public class ManageController extends MotherController{
	
	@Autowired
	private AddressCodeDAO dao;
	
	private static Logger LOGGER = LoggerFactory.getLogger(ManageController.class);
	
	@RequestMapping(value = "/admin/manage/inviteMember", method = { RequestMethod.GET})
	public ModelAndView grouppage(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session){
		
		List<GroupVO> groups = groupVORepository.findAll();
		
		String groupKey = req.getParameter("groupKey");
		if(groupKey == null) {
			modelAndView.setViewName("/error/notGroup");
			return modelAndView;
		}
		GroupVO vo = groupVORepository.findByGroupKey(groupKey);
		if(vo == null) {
			modelAndView.setViewName("/error/notGroup");
			return modelAndView;
		}		
		List<AddressCodeVO> citys = dao.getCitys();
		Map<String,String> param = new HashMap();
		param.put("cityCode", citys.get(0).getCityCode());
		List<AddressCodeVO> gus = dao.getGus(param);
		
		param = new HashMap();
		param.put("cityCode", citys.get(0).getCityCode());
		param.put("gunCode", gus.get(0).getGunCode());
		List<AddressCodeVO> dongs = dao.getDongs(param);
		
		modelAndView.addObject("vo", vo );
		modelAndView.addObject("groups", groups );
		modelAndView.addObject("cities", citys );
		modelAndView.addObject("gus",gus );
		modelAndView.addObject("dongs",dongs );
		modelAndView.setViewName("/manage/individual_page");
		return modelAndView;
	}
	
}
