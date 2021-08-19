package com.whatdo.keep.controller.member;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.whatdo.keep.controller.MotherController;
import com.whatdo.keep.service.dao.AddressCodeDAO;
import com.whatdo.keep.vo.AddressCodeVO;
import com.whatdo.keep.vo.GroupVO;
import com.whatdo.keep.vo.MemberVO;



@Controller
public class MemberController extends MotherController{
	
	@Autowired
	private AddressCodeDAO dao;
	
	private static Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping(value = "/admin/member/page.do", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView adminmemberpage(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session){
		
		
		LOGGER.debug("##adminmemberpage enter");
		
		List<GroupVO> groups = groupVORepository.findAll();
//		
		List<AddressCodeVO> citys = dao.getCitys();
		Map<String,String> param = new HashMap();
		param.put("cityCode", citys.get(0).getCityCode());
		List<AddressCodeVO> gus = dao.getGus(param);
		
		param = new HashMap();
		param.put("cityCode", citys.get(0).getCityCode());
		param.put("gunCode", gus.get(0).getGunCode());
		List<AddressCodeVO> dongs = dao.getDongs(param);
//		
		modelAndView.addObject("groups", groups );
		modelAndView.addObject("cities", citys );
		modelAndView.addObject("gus",gus );
		modelAndView.addObject("dongs",dongs );
		modelAndView.setViewName("member/page");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/member/list.do", method = { RequestMethod.GET})
	public ModelAndView adminmemberlist(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			
			) {		
		
		
		LOGGER.debug("##adminmemberlist enter");
		
		List<GroupVO> groupList = groupVORepository.findAll();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String endDate = dateFormat.format(new Date());
		LocalDate date = LocalDate.now();
		date = date.minusDays(7);
		Date convertDate =   Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		String startDate = dateFormat.format(convertDate);
		modelAndView.addObject("groups", groupList);
		modelAndView.addObject("startDate", startDate);
		modelAndView.addObject("endDate", endDate);
		modelAndView.setViewName("member/list");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/admin/member/listtable.do", method = { RequestMethod.POST})
	@ResponseBody
	public Map adminmemberlisttable(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			, MemberVO vo ) throws JsonProcessingException, ParseException {		
		
		LOGGER.debug("##admingrouplist enter" +vo.toString() );
		
		Map resultMap = new HashMap<String, Object>();
		Pageable p = getPageable(req,  vo.getStart(), vo.getLength());
		
		Map<String, String[]> m = req.getParameterMap();
//		System.out.println(m.get("vo"));
		
//		String[] columSize = m.get("vo[groupName]");
//		System.out.println(columSize[0]);
//		columSize = m.get("vo[name]");
//		System.out.println(columSize[0]);
//		columSize = m.get("vo[startDate]");
//		System.out.println(columSize[0]);
//		System.out.println(req.getParameter("vo"));
		
		
		Map<String,Object> condition = searchConvert(m);
		
		
		Page<MemberVO> page = memVoRepository.findAll(SpecificationMmemberVO.withCondition(condition), p);
		
		resultMap.put("recordsFiltered",page.getTotalElements());
		resultMap.put("recordsTotal",page.getContent().size());
		resultMap.put("data", page.getContent());
		
		return resultMap;
	}

	@RequestMapping(value = "/admin/member/commit", method = { RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public Map admingroupcommit(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			,MemberVO inputform	) throws InterruptedException{
		
		LOGGER.debug("##admingroupcommit enter");
		LOGGER.debug("##data {}",inputform);
		inputform.setRegDt(new Date());
		inputform.setDetailAddress(inputform.getCityN()+" "+inputform.getGunN()+" "+inputform.getDongN()+" "+inputform.getDetailAddress());
		MemberVO vo = memVoRepository.save(inputform);
		Map<String, Object> result =  new HashMap();
		result.put("already", false);
		result.put("vo", vo);
		
		return result;
	}
	
	
	@RequestMapping(value = "/admin/member/getcity", method = { RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public Map getcity(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
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
		
		
		Map<String, Object> result =  new HashMap();
		result.put("cities", citys );
		result.put("gus",gus );
		result.put("dongs",dongs );
		
		return result;
	}
//	
	@RequestMapping(value = "/admin/member/getgun", method = { RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public Map getgun(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			,AddressCodeVO inputform	) throws InterruptedException{
		
		
		LOGGER.debug("##getcity enter");
		LOGGER.debug("## !!!!!!!!!{} ", inputform);
		Map<String,String> param = new HashMap();
		param.put("cityCode", inputform.getCityCode());
		param.put("gunCode", inputform.getGunCode());
		List<AddressCodeVO> dongs = dao.getDongs(param);
		Map<String, Object> result =  new HashMap();
		result.put("dongs",dongs );
		
		return result;
	}
	
	public Map<String,Object> searchConvert(Map<String, String[]> m) throws ParseException {
		
		Map<String,Object> result = new HashMap();

		String[] param = m.get("vo[groupName]");
		if(param[0].equals("0")) {
			result.put("groupName","");
		}else {
			result.put("groupName",param[0]);	
		}
		
		param = m.get("vo[phone]");
		result.put("phone",param[0]);
		
		param = m.get("vo[name]");
		result.put("name",param[0]);
		
		param = m.get("vo[dangwon]");
		if(param[0].equals("0")) {
			result.put("dangwon","");
		}else {
			result.put("dangwon",param[0]);	
		}
		param = m.get("vo[name]");
		result.put("name",param[0]);
		
		param = m.get("vo[cityN]");
		result.put("cityN",param[0]);
		
		param = m.get("vo[gunN]");
		result.put("gunN",param[0]);
		
		param = m.get("vo[dongN]");
		result.put("dongN",param[0]);
		
		param = m.get("vo[detailAddress]");
		result.put("detailAddress",param[0]);
		
		param = m.get("vo[level]");
		if(param[0].equals("0")) {
			result.put("level","");
		}else {
			result.put("level",param[0]);	
		}
		
		param = m.get("vo[recommandName]");
		result.put("recommandName",param[0]);
		
		param = m.get("vo[church]");
		result.put("church",param[0]);
		
		param = m.get("vo[churchRank]");
		if(param[0].equals("0")) {
			result.put("churchRank","");
		}else {
			result.put("churchRank",param[0]);	
		}
		param = m.get("vo[startDate]");
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		
		result.put("regDt","yes");
		
		result.put("startDate", dateformat.parse(param[0]));
		
		param = m.get("vo[endDate]");
		dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date tDate = dateformat.parse(param[0]);
		LocalDate l = tDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		l = l.plusDays(1);
		result.put("endDate",Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		System.out.println(result);
		
		return result;
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
