package com.whatdo.keep.controller.message;

import java.security.Principal;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
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
import com.whatdo.keep.vo.InnerMessage;
import com.whatdo.keep.vo.MemberVO;

@Controller
public class MessageController extends MotherController{
	
	@Autowired
	private AddressCodeDAO dao;
	
	private static Logger LOGGER = LoggerFactory.getLogger(MessageController.class);
	
	@Value("${config.sampleFilePath}")
	private String sampleFilePath;
	
	@Value("${config.sampleFileName}")
	private String sampleFileName;
	
	
	@RequestMapping(value = "/admin/message/innerMessage.do", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView innerMessage(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session){
		
		LOGGER.debug("##innerMessage enter");
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
		modelAndView.setViewName("message/list");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/admin/message/listtable.do", method = { RequestMethod.POST})
	@ResponseBody
	public Map adminmemberlisttable(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			, MemberVO vo ,Principal principal) throws JsonProcessingException, ParseException {		
		
		LOGGER.debug("##admingrouplist enter" +vo.toString() );
		
		System.out.println("pagavle::"+ vo.getStart());
		System.out.println("pagavle::"+vo.getLength());
		
		Map resultMap = new HashMap<String, Object>();
		Pageable p = getPageable(req,  vo.getStart(), vo.getLength());
		
		Map<String,String> auth =  getAuthentics();
		boolean admin = auth.get("ROLE_ADMIN") !=null ? true : false;
		boolean user = auth.get("ROLE_USER") !=null ? true : false;
		
		Map<String, String[]> m = req.getParameterMap();
		Map<String,Object> condition = searchConvert(m);//		System.out.println(m.get("vo"));
		
		Map<String,Object> param =  new HashMap<String, Object>();
		if(admin) {
			param.put("isAdmin", "true");
		}else {
			MemberVO memberVO = memVoRepository.findByPhone(principal.getName());
			param.put("isAdmin", "false");
			param.put("memberseq", memberVO.getSeq());
		}
		param.put("startP",vo.getStart());
		param.put("endP",vo.getLength());
		System.out.println("######### :: "+param);
		
		List<InnerMessage>list =  dao.getMessages(param);
		Integer total =  dao.getMessages_count(param);
		System.out.println("#########");
		System.out.println(total);
		System.out.println(list.size());
		System.out.println("#########");
		
		for(int i=0; i<list.size();i++) {
			System.out.println(list.get(i).toString());
		}
		
		Page<InnerMessage> page = new PageImpl<>(list,p,total);
		System.out.println("#########");
		page = new PageImpl<>(page.getContent(),page.getPageable(),page.getTotalElements());

		for(int i=0; i<page.getContent().size();i++) {
			System.out.println(page.getContent().get(i).toString());
		}
		
		
		resultMap.put("recordsFiltered",total);
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
		String[] param = m.get("vo[frommemberName]");
		result.put("frommemberName",param[0]);
		
		param = m.get("vo[tomemberName]");
		result.put("tomemberName",param[0]);
		
		param = m.get("vo[messageTitle]");
		result.put("messageTitle",param[0]);
		
		param = m.get("vo[messageTxt]");
		result.put("messageTxt",param[0]);
		
		param = m.get("vo[regDt]");
		result.put("regDt",param[0]);
		
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
}
