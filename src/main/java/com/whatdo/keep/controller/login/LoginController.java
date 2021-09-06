package com.whatdo.keep.controller.login;

import java.security.Principal;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.whatdo.keep.config.em.AuthorityEm;
import com.whatdo.keep.controller.MotherController;
import com.whatdo.keep.controller.member.SpecificationMmemberVO;
import com.whatdo.keep.service.dao.AddressCodeDAO;
import com.whatdo.keep.vo.AddressCodeVO;
import com.whatdo.keep.vo.MemberVO;


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
	
	@RequestMapping(value = "/admin/loginAfter.do", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView adminloginAfter(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session,Principal principal){
		
		LOGGER.debug("##login enter");
		
		Map<String,String> auth =  getAuthentics();
		boolean isAdmin = auth.get("ROLE_ADMIN") !=null ? true : false;
		boolean user = auth.get("ROLE_USER") !=null ? true : false;
		if(isAdmin) {
			
			List<AddressCodeVO> citys = dao.getCitys();
			Map<String,String> param = new HashMap();
			param.put("cityCode", citys.get(0).getCityCode());
			List<AddressCodeVO> gus = dao.getGus(param);
			
			param = new HashMap();
			param.put("cityCode", citys.get(0).getCityCode());
			param.put("gunCode", gus.get(0).getGunCode());
			List<AddressCodeVO> dongs = dao.getDongs(param);
			
			LOGGER.debug("##cities {} "+ citys);
			
			Integer total = dao.gettotaldangwon(param);
			
			Integer groupCount = (int) groupVORepository.count();
			Integer dangwonCount00 = dao.getenterdangwon00d(param);
			Integer dangwonCount01 = dao.getenterdangwon01d(param);
			
			modelAndView.addObject("total", total);
			modelAndView.addObject("groupCount", groupCount);
			modelAndView.addObject("dangwonCount00", dangwonCount00);
			modelAndView.addObject("dangwonCount01", dangwonCount01);
			
			
			modelAndView.addObject("cities", citys );
			modelAndView.addObject("cityCode", citys.get(0).getCityCode() );
			modelAndView.addObject("gus",gus );
			modelAndView.addObject("gunCode", gus.get(0).getGunCode() );
			modelAndView.addObject("dongs",dongs );
			modelAndView.addObject("dongCode", dongs.get(0).getDongCode() );
		}else {
			MemberVO memberVO = memVoRepository.findByPhone(principal.getName());
			
			List<AddressCodeVO> citys = dao.getCitys();
			Map<String,String> param = new HashMap();
			param.put("cityCode", memberVO.getCityCode());
			List<AddressCodeVO> gus = dao.getGus(param);
			
			param = new HashMap();
			param.put("cityCode", memberVO.getCityCode());
			param.put("gunCode", memberVO.getGunCode());
			List<AddressCodeVO> dongs = dao.getDongs(param);
			
			LOGGER.debug("##cities {} "+ citys);
			
			Integer total = dao.gettotaldangwon(param);
			
			Integer groupCount = (int) groupVORepository.count();
			Integer dangwonCount00 = dao.getenterdangwon00d(param);
			Integer dangwonCount01 = dao.getenterdangwon01d(param);
			
			modelAndView.addObject("total", total);
			modelAndView.addObject("groupCount", groupCount);
			modelAndView.addObject("dangwonCount00", dangwonCount00);
			modelAndView.addObject("dangwonCount01", dangwonCount01);
			
			
			modelAndView.addObject("cities", citys );
			modelAndView.addObject("cityCode", memberVO.getCityCode() );
			modelAndView.addObject("gus",gus );
			modelAndView.addObject("gunCode", memberVO.getGunCode());
			modelAndView.addObject("dongs",dongs );
			modelAndView.addObject("dongCode", memberVO.getDongCode() );
		}

		modelAndView.setViewName("main/page");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/user/loginAfter.do", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView userloginAfter(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session,Principal principal){
		
		
		MemberVO user = memVoRepository.findByPhone(principal.getName());
		
		LOGGER.debug("##login enter");
		List<AddressCodeVO> citys = dao.getCitys();
		Map<String,String> param = new HashMap();

		param.put("cityCode", user.getCityCode());
		List<AddressCodeVO> gus = dao.getGus(param);
		
		param = new HashMap();
		param.put("cityCode", user.getCityCode());
		param.put("gunCode", user.getGunCode());
		List<AddressCodeVO> dongs = dao.getDongs(param);
		LOGGER.debug("##cities {} "+ citys);
		Integer total = dao.gettotaldangwon(param);
		
		Integer groupCount = (int) groupVORepository.count();
		Integer dangwonCount00 = dao.getenterdangwon00d(param);
		Integer dangwonCount01 = dao.getenterdangwon01d(param);
		
		modelAndView.addObject("total", total);
		modelAndView.addObject("groupCount", groupCount);
		modelAndView.addObject("dangwonCount00", dangwonCount00);
		modelAndView.addObject("dangwonCount01", dangwonCount01);
		modelAndView.addObject("cities", citys );
		modelAndView.addObject("cityCode", user.getCityCode());
		modelAndView.addObject("gus",gus );
		modelAndView.addObject("gunCode", user.getGunCode());
		modelAndView.addObject("dongs",dongs );
		modelAndView.addObject("dongCode", user.getDongCode());
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
		
		modelAndView.addObject("cityCode", inputform.getCityCode());
		modelAndView.addObject("gunCode", gus.get(0).getGunCode() );
		modelAndView.addObject("dongCode", dongs.get(0).getDongCode() );
//		modelAndView.setViewName("main/page");
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

		modelAndView.addObject("cityCode", inputform.getCityCode());
		modelAndView.addObject("gunCode", inputform.getGunCode() );
		modelAndView.addObject("dongCode", dongs.get(0).getDongCode() );
		
		modelAndView.addObject("cities", citys );
		modelAndView.addObject("gus",gus );
		modelAndView.addObject("dongs",dongs );
		modelAndView.setViewName("main/ajax/target");
		
		return modelAndView;
	}
	
	
	
//	@RequestMapping(value = "/admin/main/member.do", method = { RequestMethod.POST})
	@PostMapping(value = "/admin/main/member.do")
	public ModelAndView memberlist(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session) {		
		
		LOGGER.debug("##adminmemberlistfrommain enter");
		String cityCode = Optional.ofNullable(req.getParameter("cityCode")).orElse("");
		String gunCode = Optional.ofNullable(req.getParameter("gunCode")).orElse("");
		String dongCode = Optional.ofNullable(req.getParameter("dongCode")).orElse("");
		LOGGER.debug("##param dongCode :: "+cityCode);
		LOGGER.debug("##param dongCode :: "+gunCode);
		LOGGER.debug("##param dongCode :: "+dongCode);
		
		LOGGER.debug("##adminmemberlistfrommain enter");
		
		modelAndView.addObject("cityCode", cityCode);
		modelAndView.addObject("gunCode", gunCode);
		modelAndView.addObject("dongCode", dongCode);
		
		modelAndView.setViewName("main/member");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/main/memberlist.do", method = { RequestMethod.POST})
	@ResponseBody
	public Map adminmemberlisttable(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			, MemberVO vo ) throws JsonProcessingException, ParseException {		
		
		LOGGER.debug("##admingrouplist enter" +vo.toString() );
		
		System.out.println("pagavle::"+ vo.getStart());
		System.out.println("pagavle::"+vo.getLength());
		
		Map resultMap = new HashMap<String, Object>();
		Pageable p = getPageable(req,  vo.getStart(), vo.getLength());
		
		
		Map<String, String[]> m = req.getParameterMap();
		
		Map<String,Object> condition = searchConvert(m);
		Page<MemberVO> page = memVoRepository.findAll(SpecificationMmemberVO.withCondition(condition), p);
		
		resultMap.put("recordsFiltered",page.getTotalElements());
		resultMap.put("recordsTotal",page.getContent().size());
		resultMap.put("data", page.getContent());
		
		return resultMap;
	}
	
	
	public Map<String,Object> searchConvert(Map<String, String[]> m) throws ParseException {
		
		Map<String,Object> result = new HashMap();

//		String[] param = m.get("vo[groupName]");
//		if(param[0].equals("0")) {
//			result.put("groupName","");
//		}else {
//			result.put("groupName",param[0]);	
//		}
//		
		String[] param = m.get("vo[cityCode]");
		result.put("cityCode",param[0]);
		
		param = m.get("vo[gunCode]");
		result.put("gunCode",param[0]);
		
		param = m.get("vo[dongCode]");
		result.put("dongCode",param[0]);
//		
//		param = m.get("vo[name]");
//		result.put("name",param[0]);
//		
//		param = m.get("vo[dangwon]");
//		if(param[0].equals("0")) {
//			result.put("dangwon","");
//		}else {
//			result.put("dangwon",param[0]);	
//		}
//		param = m.get("vo[name]");
//		result.put("name",param[0]);
//		
//		param = m.get("vo[cityN]");
//		result.put("cityN",param[0]);
//		
//		param = m.get("vo[gunN]");
//		result.put("gunN",param[0]);
//		
//		param = m.get("vo[dongN]");
//		result.put("dongN",param[0]);
//		
//		param = m.get("vo[detailAddress]");
//		result.put("detailAddress",param[0]);
//		
//		param = m.get("vo[level]");
//		if(param[0].equals("0")) {
//			result.put("level","");
//		}else {
//			result.put("level",param[0]);	
//		}
//		
//		param = m.get("vo[recommandName]");
//		result.put("recommandName",param[0]);
//		
//		param = m.get("vo[church]");
//		result.put("church",param[0]);
//		
//		param = m.get("vo[churchRank]");
//		if(param[0].equals("0")) {
//			result.put("churchRank","");
//		}else {
//			result.put("churchRank",param[0]);	
//		}
//		param = m.get("vo[startDate]");
//		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		
//		result.put("regDt","yes");
//		
//		result.put("startDate", dateformat.parse(param[0]));
//		
//		param = m.get("vo[endDate]");
//		dateformat = new SimpleDateFormat("yyyy-MM-dd");
//		Date tDate = dateformat.parse(param[0]);
//		
//		LocalDate l = tDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//		l = l.plusDays(1);
//		result.put("endDate",Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		System.out.println("convert search condition::"+result);
		
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
