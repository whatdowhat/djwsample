package com.whatdo.keep.controller.district;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	public ModelAndView admidistrictchartmember(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session){
		
		
		LOGGER.debug("##admidistrictchartmember enter");
		
		Map param = new HashMap<String, Object>();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		String endDate = dateFormat.format(new Date());
//		LocalDate date = LocalDate.now();
//		date = date.minusDays(7);
//		Date convertDate =   Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
//		String startDate = dateFormat.format(convertDate);
//		param.put("startDate", startDate);
//		LOGGER.debug("##endDate endDate"+endDate);
//		param.put("endDate", endDate);
		
		Integer total = dao.gettotaldangwon(param); //총당원 가입날자가 있는
		Integer districtTotal = dao.getdistrict_district().size(); //선거구 숫자
		List<AddressCodeVO> citys = dao.getCitys();//시
		param = new HashMap<String, Object>();
		param.put("cityCode", citys.get(0).getCityCode());
		List<AddressCodeVO> districts =  dao.getGuns_district(param);
		
//		Map<String,String> paramS = new HashMap();
//		modelAndView.addObject("cities", citys );
		
//		System.out.println("##adminmemberchartmember enter>>" +chartDate.size());
//		Gson gson = new Gson();
//		String gson1String = gson.toJson(chartDate);
//		modelAndView.addObject("chartDate", gson1String);
//		
//		gson1String = gson.toJson(chartDate2);
//		modelAndView.addObject("chartDate2", gson1String);
		
		modelAndView.addObject("total", total);
		modelAndView.addObject("districtTotal", districtTotal);
		modelAndView.addObject("citys", citys);
		modelAndView.addObject("districts", districts);
		
//		modelAndView.addObject("startDate", startDate);
//		modelAndView.addObject("endDate", endDate);
		
		modelAndView.setViewName("district/chart_member");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/district/chart/member/ajax.do", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView admidistrictchartmemberajax(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			,AddressCodeVO inputform	) throws InterruptedException{
		
		
		LOGGER.debug("##admidistrictchartmemberajax enter");
		LOGGER.debug("##admidistrictchartmemberajax enter inputform:: "+inputform);
		Map param = new HashMap<String, Object>();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		String endDate = dateFormat.format(new Date());
//		LocalDate date = LocalDate.now();
//		date = date.minusDays(7);
//		Date convertDate =   Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
//		String startDate = dateFormat.format(convertDate);
		if(inputform.getCityCode().equals("00")|| inputform.getCityCode().equals("0")) {
			
		}else {
			param.put("cityCode", inputform.getCityCode());	
		}
		
		param.put("startDate", inputform.getStartDate());
		param.put("endDate", inputform.getEndDate());
		List<ChartDataVO> chartDate = dao.getenterchart01(param);
		List<ChartDataVO> chartDate2 = dao.getenterchart02(param);
		
		
		List<AddressCodeVO> citys = dao.getCitysChart(param);
		Map<String,String> paramS = new HashMap();
		modelAndView.addObject("cities", citys );
		
		
		
		
		Gson gson = new Gson();
		String gson1String = gson.toJson(chartDate);
		modelAndView.addObject("chartDate", gson1String);
		
		gson1String = gson.toJson(chartDate2);
		modelAndView.addObject("chartDate2", gson1String);
		
		Integer total = dao.gettotaldangwon(param);
		Integer dangwonCount = dao.getenterdangwonAll(param);
		Integer dangwonCount00 = dao.getenterdangwon00(param);
		Integer dangwonCount01 = dao.getenterdangwon01(param);
		
		System.out.println("total::"+total);
		System.out.println("total::"+dangwonCount);
		System.out.println("total::"+dangwonCount00);
		System.out.println("total::"+dangwonCount01);
		
		modelAndView.addObject("total", total);
		modelAndView.addObject("dangwonCount", dangwonCount);
		modelAndView.addObject("dangwonCount00", dangwonCount00);
		modelAndView.addObject("dangwonCount01", dangwonCount01);
		
		modelAndView.addObject("startDate", inputform.getStartDate());
		modelAndView.addObject("endDate", inputform.getEndDate());
		
		modelAndView.setViewName("member/ajax/chart_target");
		
		return modelAndView;
	}
	
}
