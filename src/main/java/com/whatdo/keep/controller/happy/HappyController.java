package com.whatdo.keep.controller.happy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.whatdo.keep.controller.MotherController;
import com.whatdo.keep.service.dao.AddressCodeDAO;
import com.whatdo.keep.util.JsonUtil;
import com.whatdo.keep.vo.GroupVO;
import com.whatdo.keep.vo.MemberVO;



@Controller
public class HappyController extends MotherController{
	
	@Autowired
	private AddressCodeDAO dao;
	
	private static Logger LOGGER = LoggerFactory.getLogger(HappyController.class);
	
	@Value("${config.sampleFilePath}")
	private String sampleFilePath;
	
	@Value("${config.sampleFileName}")
	private String sampleFileName;

	@Value("${config.happyAPI}")
	private String happyAPI;
	
	@Value("${config.happyAPICode}")
	private String happyAPICode;
	
	

    private static String readUrl(String urlString) throws Exception {

           BufferedReader reader = null;

           try {
                   URL url = new URL(urlString);
                   reader = new BufferedReader(new InputStreamReader(url.openStream()));
                   StringBuffer buffer = new StringBuffer();
                   int read = -1;
                   char[] chars = new char[1024];
                   while ((read = reader.read(chars)) != -1) {
                          buffer.append(chars, 0, read);

                   }
                   return buffer.toString();
           } finally {

                   if (reader != null) {

                          reader.close();

                   }

           }

    }


	@RequestMapping(value = "/admin/happy/list.do", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView happyList(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session,Principal principal){
		
		LOGGER.debug("##happyList enter");
		List<GroupVO> groupList = groupVORepository.findAll();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String endDate = dateFormat.format(new Date());
		LocalDate date = LocalDate.now();
		date = date.minusDays(7);
		Date convertDate =   Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		String startDate = dateFormat.format(convertDate);
		modelAndView.addObject("startDate", startDate);
		modelAndView.addObject("endDate", endDate);
		modelAndView.addObject("happyAPI", happyAPI);
		modelAndView.addObject("happyAPICode", happyAPICode);
		modelAndView.setViewName("happy/list");
		return modelAndView;
	
	}
	
	@RequestMapping(value = "/admin/happy/listdata.do", method = { RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public Map happyList(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse response,HttpSession session
			,MemberVO inputform	) throws Exception{
		
		LOGGER.debug("##happyList enter");
		String json = readUrl(inputform.getHappyurl());
		GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Map<String, Object> result = gson.fromJson(json, Map.class);
		
		return result;
	}
}
