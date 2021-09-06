package com.whatdo.keep.controller.myprofile;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatdo.keep.config.CryptoOnewayPasswrod;
import com.whatdo.keep.controller.MotherController;
import com.whatdo.keep.controller.member.SpecificationMmemberVO;
import com.whatdo.keep.controller.notice.SpecificationInnerNoticeVO;
import com.whatdo.keep.service.dao.AddressCodeDAO;
import com.whatdo.keep.vo.AddressCodeVO;
import com.whatdo.keep.vo.GroupVO;
import com.whatdo.keep.vo.InnerMessage;
import com.whatdo.keep.vo.InnerNotice;
import com.whatdo.keep.vo.MemberVO;

@Controller
public class MyprofileController extends MotherController{
	
	@Autowired
	private AddressCodeDAO dao;
	
	private static Logger LOGGER = LoggerFactory.getLogger(MyprofileController.class);
	
	@Value("${config.sampleFilePath}")
	private String sampleFilePath;
	
	@Value("${config.sampleFileName}")
	private String sampleFileName;
	
	
	@RequestMapping(value = "/admin/myprofile/page.do", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView innernotice(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session,Principal principal){
		
		LOGGER.debug("##innerMessage enter");
		Map<String,String> auth =  getAuthentics();
		boolean isAdmin = auth.get("ROLE_ADMIN") !=null ? true : false;
		boolean user = auth.get("ROLE_USER") !=null ? true : false;
	    MemberVO memberVO = memVoRepository.findByPhone(principal.getName());
	    if(isAdmin) {
	    	List<GroupVO> groups = groupVORepository.findAll();
			List<AddressCodeVO> citys = dao.getCitys();
			Map<String,String> param = new HashMap();
			param.put("cityCode", citys.get(0).getCityCode());
			List<AddressCodeVO> gus = dao.getGus(param);
			
			param = new HashMap();
			param.put("cityCode", citys.get(0).getCityCode());
			param.put("gunCode", gus.get(0).getGunCode());
			List<AddressCodeVO> dongs = dao.getDongs(param);
//			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String endDate = dateFormat.format(new Date());
			modelAndView.addObject("endDate", endDate);
			modelAndView.addObject("groups", groups );
			modelAndView.addObject("cities", citys );
			modelAndView.addObject("gus",gus );
			modelAndView.addObject("dongs",dongs );
	    }else {
	    	List<GroupVO> groups = groupVORepository.findAll();
			List<AddressCodeVO> citys = dao.getCitys();
			Map<String,String> param = new HashMap();
			param.put("cityCode", memberVO.getCityCode());
			List<AddressCodeVO> gus = dao.getGus(param);
			
			param = new HashMap();
			param.put("cityCode", memberVO.getCityCode());
			param.put("gunCode", memberVO.getGunCode());
			List<AddressCodeVO> dongs = dao.getDongs(param);
//			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String endDate = dateFormat.format(new Date());
			modelAndView.addObject("endDate", endDate);
			modelAndView.addObject("groups", groups );
			modelAndView.addObject("cities", citys );
			modelAndView.addObject("gus",gus );
			modelAndView.addObject("dongs",dongs );
	    }
		
	    
	    
		
		modelAndView.setViewName("myprofile/page");
		return modelAndView;
	}
	
	
	
	@RequestMapping(value = "/admin/myprofile/pageAdmin.do", method = {RequestMethod.POST })
	public ModelAndView editprofile(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session,Principal principal){
		
		LOGGER.debug("##adminmemberlistfrommain enter");
		String memberPhone = Optional.ofNullable(req.getParameter("phone")).orElse("");
		
		
		LOGGER.debug("##innerMessage enter");
		Map<String,String> auth =  getAuthentics();
		boolean isAdmin = auth.get("ROLE_ADMIN") !=null ? true : false;
		boolean user = auth.get("ROLE_USER") !=null ? true : false;
		LOGGER.debug("##memberPhone :: enter {}",memberPhone);
		
	    if(isAdmin) {
	    	 MemberVO memberVO = memVoRepository.findByPhone(memberPhone);
	    	 LOGGER.debug("##editprofile enter");
	    	 LOGGER.debug("##editprofile enter {}",memberVO);
	    	List<GroupVO> groups = groupVORepository.findAll();
			List<AddressCodeVO> citys = dao.getCitys();
			Map<String,String> param = new HashMap();
			param.put("cityCode", memberVO.getCityCode());
			List<AddressCodeVO> gus = dao.getGus(param);
			
			param = new HashMap();
			param.put("cityCode", memberVO.getCityCode());
			param.put("gunCode", memberVO.getGunCode());
			List<AddressCodeVO> dongs = dao.getDongs(param);
//			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String endDate = dateFormat.format(new Date());
			modelAndView.addObject("endDate", endDate);
			modelAndView.addObject("groups", groups );
			modelAndView.addObject("cities", citys );
			modelAndView.addObject("gus",gus );
			modelAndView.addObject("dongs",dongs );
			modelAndView.addObject("member",memberVO);
			modelAndView.setViewName("myprofile/pageAdmin");
	    }else {
			modelAndView.setViewName("myprofile/page");
	    }
		
	    
	    
		
		
		return modelAndView;
	}
	
    @RequestMapping(value = "/admin/myprofile/edit.do", method = RequestMethod.POST)
    @ResponseBody
    public Map myprofile(Model model, HttpServletRequest req, HttpServletResponse res
    		,MemberVO inputform	) throws InterruptedException, ParseException{
		
		LOGGER.debug("##myprofile enter");
		LOGGER.debug("##data {}",inputform);
		
		
		Map<String,String> param = new HashMap();
		param.put("cityCode", inputform.getCityCode());
		param.put("gunCode", inputform.getGunCode());
		param.put("dongCode", inputform.getDongCode());
		AddressCodeVO getvo = dao.search_districtCode(param);
		
		MemberVO target =  memVoRepository.findByPhone(inputform.getPhone());
		
		
		target.setDistrictCode(getvo.getDistrictCode());
		target.setDistrictName(getvo.getDistrictName());
		if(inputform.isChecked()) {
			String password = "";
			try {
				password = CryptoOnewayPasswrod.encryptPassword(inputform.getPhonePassword() ,inputform.getPhone());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			target.setPhonePassword(password);	
		}else {
				
		}
		
		target.setCityCode(inputform.getCityCode());
		target.setCityN(inputform.getCityN());
		target.setGunCode(inputform.getGunCode());
		target.setGunN(inputform.getGunN());
		target.setDongCode(inputform.getDongCode());
		target.setDongN(inputform.getDongN());
		target.setChurch(inputform.getChurch());
		target.setChurchRank(inputform.getChurchRank());
		
		target.setDetailAddress(inputform.getCityN()+" "+inputform.getGunN()+" "+inputform.getDongN()+" "+inputform.getDetailAddress());
		target.setEditDt(new Date());
		MemberVO vo = memVoRepository.save(target);
		Map<String, Object> result =  new HashMap();
		req.getSession().setAttribute("member", vo);
		result.put("already", false);
		result.put("vo", vo);
    	    return result;
    	    
    }
    
    
	
    @RequestMapping(value = "/admin/myprofile/editAdmin.do", method = RequestMethod.POST)
    @ResponseBody
    public Map editAdmin(Model model, HttpServletRequest req, HttpServletResponse res
    		,MemberVO inputform	) throws InterruptedException, ParseException{
		
		LOGGER.debug("##myprofile enter");
		LOGGER.debug("##data {}",inputform);
		
		
		Map<String,String> param = new HashMap();
		param.put("cityCode", inputform.getCityCode());
		param.put("gunCode", inputform.getGunCode());
		param.put("dongCode", inputform.getDongCode());
		AddressCodeVO getvo = dao.search_districtCode(param);
		
		MemberVO target =  memVoRepository.findByPhone(inputform.getPhone());
		String phonePassword =  target.getPhonePassword();
		Date regDt = target.getRegDt();
		BeanUtils.copyProperties(inputform, target);
		target.setSeq(inputform.getSeq());
		target.setRegDt(regDt);
		target.setDistrictCode(getvo.getDistrictCode());
		target.setDistrictName(getvo.getDistrictName());
		if(inputform.isChecked()) {
			String password = "";
			try {
				password = CryptoOnewayPasswrod.encryptPassword(inputform.getPhonePassword() ,inputform.getPhone());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			target.setPhonePassword(password);	
		}else {
			target.setPhonePassword(phonePassword);
		}

		target.setDetailAddress(inputform.getCityN()+" "+inputform.getGunN()+" "+inputform.getDongN()+" "+inputform.getDetailAddress());
		target.setEditDt(new Date());
		MemberVO vo = memVoRepository.save(target);
		Map<String, Object> result =  new HashMap();
		result.put("already", false);
		result.put("vo", vo);
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
