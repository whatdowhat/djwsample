package com.whatdo.keep.controller.group;

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

import org.apache.catalina.util.ParameterMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.whatdo.keep.controller.MotherController;
import com.whatdo.keep.controller.member.SpecificationMmemberVO;
import com.whatdo.keep.service.dao.AddressCodeDAO;
import com.whatdo.keep.vo.AddressCodeVO;
import com.whatdo.keep.vo.GroupVO;
import com.whatdo.keep.vo.MemberVO;


@Controller
public class GroupController extends MotherController{
	
	@Autowired
	private AddressCodeDAO dao;
	
	private static Logger LOGGER = LoggerFactory.getLogger(GroupController.class);
	
	@RequestMapping(value = "/admin/group/page.do", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView grouppage(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session){
		
		
		LOGGER.debug("##grouppage enter");
		
//		List<AddressCodeVO> citys = dao.getCitys();
//		Map<String,String> param = new HashMap();
//		param.put("cityCode", citys.get(0).getCityCode());
//		List<AddressCodeVO> gus = dao.getGus(param);
//		
//		param = new HashMap();
//		param.put("cityCode", citys.get(0).getCityCode());
//		param.put("gunCode", gus.get(0).getGunCode());
//		List<AddressCodeVO> dongs = dao.getDongs(param);
//		
//		modelAndView.addObject("cities", citys );
//		modelAndView.addObject("gus",gus );
//		modelAndView.addObject("dongs",dongs );
		modelAndView.setViewName("group/page");
		return modelAndView;
	}
	

	@RequestMapping(value = "/admin/group/commit", method = { RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public Map admingroupcommit(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			,GroupVO inputform	) throws InterruptedException{
		
		LOGGER.debug("##admingroupcommit enter");
		LOGGER.debug("##data {}",inputform);
		GroupVO vo;
		vo = groupVORepository.findByGroupKey(inputform.getGroupKey());
		Map<String, Object> result =  new HashMap();
		if(vo == null) {
			result.put("already", false);
			System.out.println("MEMBER SHOW!!!2" +inputform);
			Map<String,Object> condition = new HashMap();
			condition.put("name", inputform.getRepresentiveName());
			condition.put("phone", inputform.getPhone());
			vo = groupVORepository.save(inputform);
			List<MemberVO> l = memVoRepository.findAll(SpecificationMmemberVO.withCondition(condition));
			if(l.size() == 0) {
				result.put("exist", false);
			}else {
				inputform.setRegDt(new Date());
				vo = groupVORepository.save(inputform);
				result.put("exist", true);
			}
			
		}else {
			result.put("already", true);	
		}

		

		
		
		result.put("vo", vo);
		
		return result;
	}
	
	@RequestMapping(value = "/admin/group/list.do", method = { RequestMethod.GET})
	public ModelAndView admingrouplist(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			,GroupVO vo
			) {		
		
		LOGGER.debug("##admingrouplist enter");
		
//		Map<String,Object> condition = new HashMap();//전체 검색
//		LOGGER.debug("##vo.getLength() enter"+ vo.getLength());
//		LOGGER.debug("##vo.getStart()  enter"+vo.getStart());
//		PageRequest p = PageRequest.of(vo.getStart(), vo.getLength() == 0 ? 10 : vo.getLength());
//		Page<GroupVO> page = groupVORepository.findAll(SpecificationGroupVO.withCondition(condition), p);
//		LOGGER.debug("##page  enter"+page.getContent().size());
		modelAndView.addObject("path", req.getContextPath());
		System.out.println("list.do :::" + req.getContextPath());
		modelAndView.setViewName("group/list");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/admin/group/memberlist.do", method = { RequestMethod.GET})
	public ModelAndView admingroupmemberlist(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			,GroupVO vo
			) {		
		
		LOGGER.debug("##admingroupmemberlist enter");
		String groupKey = req.getParameter("groupKey");
		LOGGER.debug("##admingroupmemberlist enter::"+groupKey);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String endDate = dateFormat.format(new Date());
		LocalDate date = LocalDate.now();
		date = date.minusDays(7);
		Date convertDate =   Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		String startDate = dateFormat.format(convertDate);
		modelAndView.addObject("startDate", startDate);
		modelAndView.addObject("endDate", endDate);
		modelAndView.addObject("groupKey", groupKey);
		
		modelAndView.setViewName("group/memberlist");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/admin/group/memberlisttable.do", method = { RequestMethod.POST})
	@ResponseBody
	public Map admingroupmemberlisttable(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			,GroupVO vo
			) throws ParseException {		
		
		LOGGER.debug("##admingroupmemberlisttable enter" +vo.toString() );
		
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
	
	
	//finall
	@RequestMapping(value = "/admin/group/listtable.do", method = { RequestMethod.POST})
	@ResponseBody
	public Map admingrouplist2(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			, GroupVO vo ) throws JsonProcessingException {		
		
		LOGGER.debug("##admingrouplist enter" +vo.toString() );
		
		Map resultMap = new HashMap<String, Object>();
		Pageable p = getPageable(req,  vo.getStart(), vo.getLength());
		Map<String,Object> condition = new HashMap();//전체 검색
		Page<GroupVO> page = groupVORepository.findAll(SpecificationGroupVO.withCondition(condition), p);
		
		resultMap.put("recordsFiltered",page.getTotalElements());
		resultMap.put("recordsTotal",page.getContent().size());
		resultMap.put("data", page.getContent());
		resultMap.put("pageNumber", p.getPageNumber());
		
		return resultMap;
	}
	
	
	@RequestMapping(value = "/admin/group/getcity", method = { RequestMethod.GET,RequestMethod.POST })
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
	@RequestMapping(value = "/admin/group/getgun", method = { RequestMethod.GET,RequestMethod.POST })
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
		
		param = m.get("vo[groupKey]");
		result.put("groupKey",param[0]);
		
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
