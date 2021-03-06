package com.whatdo.keep.controller.district;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.whatdo.keep.controller.MotherController;
import com.whatdo.keep.controller.member.SpecificationMmemberVO;
import com.whatdo.keep.service.dao.AddressCodeDAO;
import com.whatdo.keep.util.FileDownload;
import com.whatdo.keep.vo.AddressCodeVO;
import com.whatdo.keep.vo.ChartDataVO;
import com.whatdo.keep.vo.GroupVO;
import com.whatdo.keep.vo.MemberVO;



@Controller
public class DistrictController extends MotherController{
	
	@Autowired
	private AddressCodeDAO dao;
	
	private static Logger LOGGER = LoggerFactory.getLogger(DistrictController.class);
	
	@Value("${config.sampleFilePath}")
	private String sampleFilePath;
	
	@Value("${config.sampleFileName}")
	private String sampleFileName;
	
	
	@RequestMapping(value = "/admin/district/chart/member.do", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView admidistrictchartmember(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session,Principal principal){
		
		
		LOGGER.debug("##admidistrictchartmember enter");
		
		Map<String,String> auth =  getAuthentics();
		boolean isAdmin = auth.get("ROLE_ADMIN") !=null ? true : false;
		boolean user = auth.get("ROLE_USER") !=null ? true : false;
		if(isAdmin) {
			Map param = new HashMap<String, Object>();
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			String endDate = dateFormat.format(new Date());
//			LocalDate date = LocalDate.now();
//			date = date.minusDays(7);
//			Date convertDate =   Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
//			String startDate = dateFormat.format(convertDate);
//			param.put("startDate", startDate);
//			LOGGER.debug("##endDate endDate"+endDate);
//			param.put("endDate", endDate);
			
			Integer total = dao.gettotaldangwon(param); //????????? ??????????????? ??????
			Integer districtTotal = dao.getdistrict_district().size(); //????????? ??????
			List<AddressCodeVO> citys = dao.getCitys();//???
			param = new HashMap<String, Object>();
			
			param.put("cityCode", citys.get(0).getCityCode());
			List<AddressCodeVO> districts =  dao.getGuns_district(param);
			param.put("districtCode", districts.get(0).getDistrictCode());
			List<AddressCodeVO> dongs =  dao.getDongs_district(param);
			
			System.out.println("dongs count:"+dongs.size());
			
			modelAndView.addObject("total", total);
			modelAndView.addObject("districtTotal", districtTotal);
			modelAndView.addObject("citys", citys);
			modelAndView.addObject("districts", districts);
			modelAndView.addObject("dongs", dongs);
			
			modelAndView.addObject("cityCode", citys.get(0).getCityCode());
			modelAndView.addObject("districCode", districts.get(0).getDistrictCode());
			modelAndView.addObject("dongCode", dongs.get(0).getDongCode());
		}else {
			MemberVO memberVO = memVoRepository.findByPhone(principal.getName());
			Map param = new HashMap<String, Object>();
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			String endDate = dateFormat.format(new Date());
//			LocalDate date = LocalDate.now();
//			date = date.minusDays(7);
//			Date convertDate =   Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
//			String startDate = dateFormat.format(convertDate);
//			param.put("startDate", startDate);
//			LOGGER.debug("##endDate endDate"+endDate);
//			param.put("endDate", endDate);
			
			Integer total = dao.gettotaldangwon(param); //????????? ??????????????? ??????
			Integer districtTotal = dao.getdistrict_district().size(); //????????? ??????
			List<AddressCodeVO> citys = dao.getCitys();//???
			param = new HashMap<String, Object>();
			
			param.put("cityCode", memberVO.getCityCode());
			List<AddressCodeVO> districts =  dao.getGuns_district(param);
			param.put("districtCode", memberVO.getDistrictCode());
			List<AddressCodeVO> dongs =  dao.getDongs_district(param);
			
			System.out.println("dongs count:"+dongs.size());
			
			modelAndView.addObject("total", total);
			modelAndView.addObject("districtTotal", districtTotal);
			modelAndView.addObject("citys", citys);
			modelAndView.addObject("districts", districts);
			modelAndView.addObject("dongs", dongs);
			
			modelAndView.addObject("cityCode", memberVO.getCityCode());
			modelAndView.addObject("districCode", memberVO.getDistrictCode());
			modelAndView.addObject("dongCode", memberVO.getDongCode());
			
		}
		
		
		
		modelAndView.setViewName("district/main");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/district/getcity", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView districtgetcity(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			,AddressCodeVO inputform	) throws InterruptedException{
		
		
		LOGGER.debug("##getcity enter");
		LOGGER.debug("## !!!!!!!!!{} ", inputform);
		List<AddressCodeVO> citys = dao.getCitys();
		
		Map<String,String> param = new HashMap();
		param.put("cityCode", inputform.getCityCode());
		List<AddressCodeVO> districts =  dao.getGuns_district(param);
		param.put("districtCode", String.valueOf(districts.get(0).getDistrictCode()));
		List<AddressCodeVO> dongs =  dao.getDongs_district(param);
		
		System.out.println(citys.size());
		System.out.println(districts.size());
		System.out.println(dongs.size());
		
		modelAndView.addObject("citys", citys);
		modelAndView.addObject("districts", districts);
		modelAndView.addObject("dongs", dongs);
		
		System.out.println("dongs::" + dongs);
		modelAndView.addObject("cityCode", inputform.getCityCode());
		modelAndView.addObject("districCode", districts.get(0).getDistrictCode());
		modelAndView.addObject("dongCode", dongs.get(0).getDongCode());
		modelAndView.setViewName("district/ajax/target");
		
		return modelAndView;
	}
	
	
	
	@RequestMapping(value = "/admin/district/getdongs", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView districtgetdongs(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			,AddressCodeVO inputform	) throws InterruptedException{
		
		
		LOGGER.debug("##getcity enter");
		LOGGER.debug("## !!!!!!!!!{} ", inputform);
		List<AddressCodeVO> citys = dao.getCitys();
		
		Map<String,String> param = new HashMap();
		param.put("cityCode", inputform.getCityCode());
		List<AddressCodeVO> districts =  dao.getGuns_district(param);
		param.put("districtCode", String.valueOf(inputform.getDistrictCode()));
		List<AddressCodeVO> dongs =  dao.getDongs_district(param);
		System.out.println(districts.size());
		
		modelAndView.addObject("citys", citys);
		modelAndView.addObject("districts", districts);
		modelAndView.addObject("dongs", dongs);
		
		
		modelAndView.addObject("cityCode", inputform.getCityCode());
		modelAndView.addObject("districCode", inputform.getDistrictCode());
		modelAndView.addObject("dongCode", dongs.get(0).getDongCode());
		modelAndView.setViewName("district/ajax/target");
		
		return modelAndView;
	}
	
//	@RequestMapping(value = "/admin/district/member.do", method = { RequestMethod.GET})
	@PostMapping(value = "/admin/district/member.do")
	public ModelAndView memberlist(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			) {		
		
		LOGGER.debug("##admindistrictlistfrommain enter");
		String cityCode = Optional.ofNullable(req.getParameter("cityCode")).orElse("");
		String districtCode = Optional.ofNullable(req.getParameter("districtCode")).orElse("");
		String dongCode = Optional.ofNullable(req.getParameter("dongCode")).orElse("");
		
		modelAndView.addObject("cityCode", cityCode);
		modelAndView.addObject("districtCode",districtCode);
		modelAndView.addObject("dongCode",dongCode);
		
		modelAndView.setViewName("district/member");
		return modelAndView;
	}
	
	
	
	@RequestMapping(value = "/admin/district/memberlist.do", method = { RequestMethod.POST})
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
		
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("cityCode", condition.get("cityCode"));
		param.put("districtCode",condition.get("districtCode"));
		param.put("dongCode", condition.get("dongCode"));
		
		Integer total =  dao.getmember_fromdistrict_count(param);
		System.out.println("condition::" + condition);
		System.out.println("param::" + param);
//		String 
//		query+=" limit " + pageable.getPageNumber() * pageable.getPageSize() + "," + pageable.getPageSize();
		
//		Page<MemberVO> page = memVoRepository.findAll(SpecificationMmemberVO.withCondition(condition), p);
		
		param.put("startP",vo.getStart());
		param.put("endP",vo.getLength());
		LOGGER.debug("##param data ::" +param.toString() );
		List<MemberVO> page =	dao.getmember_fromdistrict(param);
		
		resultMap.put("recordsFiltered",total);
		resultMap.put("recordsTotal",page.size());
		resultMap.put("data", page);
		
		return resultMap;
	}
	
	
	
	public Map<String,Object> searchConvert(Map<String, String[]> m) throws ParseException {
		
		Map<String,Object> result = new HashMap();

//		
		String[] param = m.get("vo[cityCode]");
		result.put("cityCode",param[0]);
		
		param = m.get("vo[districtCode]");
		result.put("districtCode",param[0]);
//		
		param = m.get("vo[dongCode]");
		result.put("dongCode",param[0]);
		
		System.out.println("convert search condition::"+result);
		
		return result;
	}
	
}
