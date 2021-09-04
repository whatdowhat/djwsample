package com.whatdo.keep.controller.manage;

import java.io.FileOutputStream;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.whatdo.keep.controller.MotherController;
import com.whatdo.keep.service.dao.AddressCodeDAO;
import com.whatdo.keep.vo.AddressCodeVO;
import com.whatdo.keep.vo.GroupVO;
import com.whatdo.keep.vo.MemberVO;



@Controller
public class ManageController extends MotherController{
	
	@Autowired
	private AddressCodeDAO dao;
	
	private static Logger LOGGER = LoggerFactory.getLogger(ManageController.class);
	
	
	@RequestMapping(value = "/admin/manage/test", method = { RequestMethod.GET,RequestMethod.POST})
	public ModelAndView test(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session){
		
		
		modelAndView.setViewName("/manage/test");
		return modelAndView;
	}
	
	public void writeFile(String imageData) throws Exception {

		
		byte[] imageByte = imageData.getBytes();
		
		try(FileOutputStream fos = new FileOutputStream("c:\\test.png")){
			System.out.println("write file");
			fos.write(imageByte);
			System.out.println("write file end");
		}
//		
		
	}
	
	@RequestMapping(value = "/public/manage/inviteMember", method = { RequestMethod.GET})
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
	
	
	@RequestMapping(value = "/public/member/commit", method = { RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public Map publicmembercommit(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			,MemberVO inputform	) throws InterruptedException{
		
		LOGGER.debug("##publicmembercommit enter");
		byte[] contentBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(inputform.getSignPad().split(",")[1]);
		inputform.setSignPad("");
		inputform.setSignData(contentBytes);
		
		
		Map<String,String> param = new HashMap();
		param.put("cityCode", inputform.getCityCode());
		param.put("gunCode", inputform.getGunCode());
		param.put("dongCode", inputform.getDongCode());
		AddressCodeVO getvo = dao.search_districtCode(param);
		
		inputform.setDistrictCode(getvo.getDistrictCode());
		inputform.setDistrictName(getvo.getDistrictName());
		
		
		//default data
		inputform.setRegDt(new Date());
		inputform.setLevel("읍면동");
		inputform.setDangwon("일반");
		inputform.setChurchRank("기타");
		inputform.setDetailAddress(inputform.getCityN()+" "+inputform.getGunN()+" "+inputform.getDongN()+" "+inputform.getDetailAddress());
		
		LOGGER.debug("##data {}",inputform);
		MemberVO vo = memVoRepository.save(inputform);
		Map<String, Object> result =  new HashMap();
		result.put("vo", vo);
		
		return result;
	}
}
