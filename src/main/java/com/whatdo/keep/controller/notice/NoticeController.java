package com.whatdo.keep.controller.notice;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatdo.keep.controller.MotherController;
import com.whatdo.keep.service.dao.AddressCodeDAO;
import com.whatdo.keep.vo.GroupVO;
import com.whatdo.keep.vo.InnerMessage;
import com.whatdo.keep.vo.InnerNotice;
import com.whatdo.keep.vo.MemberVO;

@Controller
public class NoticeController extends MotherController{
	
	@Autowired
	private AddressCodeDAO dao;
	
	private static Logger LOGGER = LoggerFactory.getLogger(NoticeController.class);
	
	@Value("${config.sampleFilePath}")
	private String sampleFilePath;
	
	@Value("${config.sampleFileName}")
	private String sampleFileName;
	
	
	@RequestMapping(value = "/admin/notice/innernotice.do", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView innernotice(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session){
		
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
		modelAndView.setViewName("notice/list");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/admin/notice/listtable.do", method = { RequestMethod.POST})
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
		
		Page<InnerNotice> page = innerNoticeVORepository.findAll(SpecificationInnerNoticeVO.withCondition(condition),p);
		
		
		resultMap.put("recordsFiltered",page.getTotalElements());
		resultMap.put("recordsTotal",page.getContent().size());
		resultMap.put("data", page.getContent());
		
		return resultMap;
	}
	
    
    @RequestMapping(value = "/admin/send/notice.do", method = RequestMethod.POST)
    @ResponseBody
    public Map sendmessagetxt(Model model, HttpServletRequest req, HttpServletResponse res,
			HttpSession session,@RequestParam Map<Object, String> param,Principal principal) throws Exception {

    	 String json = param.get("list").toString();
    	    ObjectMapper mapper = new ObjectMapper();
    	    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);	// 없는 필드로 인한 오류 무시
    	    
    	    List<InnerNotice> insertList = null;
    	    try{
    	    	insertList = mapper.readValue(json, new TypeReference<ArrayList<InnerNotice>>(){});
    	    }catch(Exception e){
    	    	e.printStackTrace();
    	    }
    	    
    	    Map<String,Object> condition = new HashMap<String, Object>();
    	    System.out.println(insertList);
    	    System.out.println("insertList :: "+insertList);
    	    
    	    
    	    int innerC = insertList.size();
    	    int outC = 0;
    	    
    		Map<String,String> auth =  getAuthentics();
    		boolean isAdmin = auth.get("ROLE_ADMIN") !=null ? true : false;
    		boolean user = auth.get("ROLE_USER") !=null ? true : false;
    	    MemberVO memberVO = memVoRepository.findByPhone(principal.getName());
    	    for(int i=0; i<insertList.size();i++) {
    	    	
    	    	InnerNotice vo = new InnerNotice();
    	    	if(isAdmin) {
    	    		
    	    		if(principal.getName().equals("administrator")) {
    	    			vo.setFrommemberseq(new Long(0));
        	    		vo.setFrommemberPhone("00000000000");
        	    		vo.setFrommemberName("ADMIN");	
    	    		}else {
    	    			vo.setFrommemberseq(memberVO.getSeq());
        	    		vo.setFrommemberPhone(memberVO.getPhone());
        	    		vo.setFrommemberName(memberVO.getName());
    	    		}
    	    		
    	    	}else {
    	    		vo.setFrommemberseq(memberVO.getSeq());
    	    		vo.setFrommemberPhone(memberVO.getPhone());
    	    		vo.setFrommemberName(memberVO.getName());
    	    	}
    	    	
    	    	
    	    	vo.setNoticeText(insertList.get(i).getNoticeText());
    	    	vo.setNoticeTitle(insertList.get(i).getNoticeTitle());
    	    	
    	    	vo.setRegDt(new Date());
    	    	vo = innerNoticeVORepository.save(vo);
    	    	if(vo!=null) outC++;
    	    }
    	    
    	   
    	    Map<String,Object> result = new HashMap<String, Object>();
    	    result.put("result", innerC == outC);
    	    return result;
    	    
    }

	
	public Map<String,Object> searchConvert(Map<String, String[]> m) throws ParseException {
		
		Map<String,Object> result = new HashMap();

		
		String[] param = m.get("vo[frommemberName]");
		result.put("frommemberName",param[0]);
		
		param = m.get("vo[noticeText]");
		result.put("messageTitle",param[0]);
		
		param = m.get("vo[noticeTitle]");
		result.put("messageTxt",param[0]);
		
		param = m.get("vo[regDt]");
		result.put("regDt",param[0]);
		System.out.println("convert search condition::"+result);
		
		return result;
	}
}
