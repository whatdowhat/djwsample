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

import com.whatdo.keep.config.CryptoOnewayPasswrod;
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
	
//	@RequestMapping(value = "/public/manage/inviteMember", method = { RequestMethod.GET})
	@RequestMapping(value = "/public", method = { RequestMethod.GET})
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
		
	    NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();
	    
	    String sSiteCode = "BV284";			// NICE로부터 부여받은 사이트 코드
	    String sSitePassword = "ElInK3g8bwwu";		// NICE로부터 부여받은 사이트 패스워드
	    String sRequestNumber = "REQ0000000001";        	// 요청 번호, 이는 성공/실패후에 같은 값으로 되돌려주게 되므로 
	                                                    	// 업체에서 적절하게 변경하여 쓰거나, 아래와 같이 생성한다.
	    sRequestNumber = niceCheck.getRequestNO(sSiteCode);
	  	//session.setAttribute("REQ_SEQ" , sRequestNumber);	// 해킹등의 방지를 위하여 세션을 쓴다면, 세션에 요청번호를 넣는다.
	   	String sAuthType = "M";      	// 없으면 기본 선택화면, M(휴대폰), X(인증서공통), U(공동인증서), F(금융인증서), S(PASS인증서), C(신용카드)
		String customize 	= "";		//없으면 기본 웹페이지 / Mobile : 모바일페이지
		
	    // CheckPlus(본인인증) 처리 후, 결과 데이타를 리턴 받기위해 다음예제와 같이 http부터 입력합니다.
		//리턴url은 인증 전 인증페이지를 호출하기 전 url과 동일해야 합니다. ex) 인증 전 url : http://www.~ 리턴 url : http://www.~
	    String sReturnUrl = "http://localhost:8080//public/authReturnS.do";      // 성공시 이동될 URL
	    String sErrorUrl = "http://localhost:8080/public/authReturnF.do";            // 실패시 이동될 URL

	    // 입력될 plain 데이타를 만든다.
	    String sPlainData = "7:REQ_SEQ" + sRequestNumber.getBytes().length + ":" + sRequestNumber +
	                        "8:SITECODE" + sSiteCode.getBytes().length + ":" + sSiteCode +
	                        "9:AUTH_TYPE" + sAuthType.getBytes().length + ":" + sAuthType +
	                        "7:RTN_URL" + sReturnUrl.getBytes().length + ":" + sReturnUrl +
	                        "7:ERR_URL" + sErrorUrl.getBytes().length + ":" + sErrorUrl +
	                        "9:CUSTOMIZE" + customize.getBytes().length + ":" + customize;
	    
	    String sMessage = "";
	    String sEncData = "";
	    
	    int iReturn = niceCheck.fnEncode(sSiteCode, sSitePassword, sPlainData);
	    if( iReturn == 0 )
	    {
	        sEncData = niceCheck.getCipherData();
	    }
	    else if( iReturn == -1)
	    {
	        sMessage = "암호화 시스템 에러입니다.";
	    }    
	    else if( iReturn == -2)
	    {
	        sMessage = "암호화 처리오류입니다.";
	    }    
	    else if( iReturn == -3)
	    {
	        sMessage = "암호화 데이터 오류입니다.";
	    }    
	    else if( iReturn == -9)
	    {
	        sMessage = "입력 데이터 오류입니다.";
	    }    
	    else
	    {
	        sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
	    }
		
	    modelAndView.addObject("sEncData", sEncData);
		
		modelAndView.setViewName("/manage/individual_page");
		return modelAndView;
	}
	
	@RequestMapping(value = "/public/authReturnS.do", method = { RequestMethod.GET})
	public ModelAndView authenticS(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse res,HttpSession session){
		
	    NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();

	    String sEncodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");

	    String sSiteCode = "BV284";			// NICE로부터 부여받은 사이트 코드
	    String sSitePassword = "ElInK3g8bwwu";		// NICE로부터 부여받은 사이트 패스워드

	    String sCipherTime = "";			// 복호화한 시간
	    String sRequestNumber = "";			// 요청 번호
	    String sResponseNumber = "";		// 인증 고유번호
	    String sAuthType = "";				// 인증 수단
	    String sName = "";					// 성명
	    String sDupInfo = "";				// 중복가입 확인값 (DI_64 byte)
	    String sConnInfo = "";				// 연계정보 확인값 (CI_88 byte)
	    String sBirthDate = "";				// 생년월일(YYYYMMDD)
	    String sGender = "";				// 성별
	    String sNationalInfo = "";			// 내/외국인정보 (개발가이드 참조)
		String sMobileNo = "";				// 휴대폰번호
		String sMobileCo = "";				// 통신사
	    String sMessage = "";
	    String sPlainData = "";
	    
	    int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);

	    if( iReturn == 0 )
	    {
	        sPlainData = niceCheck.getPlainData();
	        sCipherTime = niceCheck.getCipherDateTime();
	        
	        // 데이타를 추출합니다.
	        java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);
	        
	        sRequestNumber  = (String)mapresult.get("REQ_SEQ");
	        sResponseNumber = (String)mapresult.get("RES_SEQ");
	        sAuthType		= (String)mapresult.get("AUTH_TYPE");
	        sName			= (String)mapresult.get("NAME");
//			sName			= (String)mapresult.get("UTF8_NAME"); //charset utf8 사용시 주석 해제 후 사용
	        sBirthDate		= (String)mapresult.get("BIRTHDATE");
	        sGender			= (String)mapresult.get("GENDER");
	        sNationalInfo  	= (String)mapresult.get("NATIONALINFO");
	        sDupInfo		= (String)mapresult.get("DI");
	        sConnInfo		= (String)mapresult.get("CI");
	        sMobileNo		= (String)mapresult.get("MOBILE_NO");
	        sMobileCo		= (String)mapresult.get("MOBILE_CO");
	        
	        String session_sRequestNumber = (String)session.getAttribute("REQ_SEQ");
	        if(!sRequestNumber.equals(session_sRequestNumber))
	        {
	            sMessage = "세션값 불일치 오류입니다.";
	            sResponseNumber = "";
	            sAuthType = "";
	        }
	    }
	    else if( iReturn == -1)
	    {
	        sMessage = "복호화 시스템 오류입니다.";
	    }    
	    else if( iReturn == -4)
	    {
	        sMessage = "복호화 처리 오류입니다.";
	    }    
	    else if( iReturn == -5)
	    {
	        sMessage = "복호화 해쉬 오류입니다.";
	    }    
	    else if( iReturn == -6)
	    {
	        sMessage = "복호화 데이터 오류입니다.";
	    }    
	    else if( iReturn == -9)
	    {
	        sMessage = "입력 데이터 오류입니다.";
	    }    
	    else if( iReturn == -12)
	    {
	        sMessage = "사이트 패스워드 오류입니다.";
	    }    
	    else
	    {
	        sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
	    }
	    LOGGER.debug("##mapresult :"+sMobileNo);
	    modelAndView.addObject("sMobileNo", sMobileNo);
	    modelAndView.addObject("sName", sName);
	    modelAndView.addObject("sBirthDate", sBirthDate);
	    modelAndView.addObject("sGender", sGender);
	    
	    modelAndView.setViewName("/manage/checkplus_success");
		return modelAndView;
	}
	
	@RequestMapping(value = "/public/authReturnF.do", method = { RequestMethod.GET})
	public ModelAndView authenticF(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse res,HttpSession session){
		
		 NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();

		    String sEncodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");

		    String sSiteCode = "BV284";			// NICE로부터 부여받은 사이트 코드
		    String sSitePassword = "ElInK3g8bwwu";		// NICE로부터 부여받은 사이트 패스워드

		    String sCipherTime = "";			// 복호화한 시간
		    String sRequestNumber = "";			// 요청 번호
		    String sErrorCode = "";				// 인증 결과코드
		    String sAuthType = "";				// 인증 수단
		    String sMessage = "";
		    String sPlainData = "";
		    
		    int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);

		    if( iReturn == 0 )
		    {
		        sPlainData = niceCheck.getPlainData();
		        sCipherTime = niceCheck.getCipherDateTime();
		        
		        // 데이타를 추출합니다.
		        java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);
		        
		        sRequestNumber 	= (String)mapresult.get("REQ_SEQ");
		        sErrorCode 		= (String)mapresult.get("ERR_CODE");
		        sAuthType 		= (String)mapresult.get("AUTH_TYPE");
		    }
		    else if( iReturn == -1)
		    {
		        sMessage = "복호화 시스템 에러입니다.";
		    }    
		    else if( iReturn == -4)
		    {
		        sMessage = "복호화 처리오류입니다.";
		    }    
		    else if( iReturn == -5)
		    {
		        sMessage = "복호화 해쉬 오류입니다.";
		    }    
		    else if( iReturn == -6)
		    {
		        sMessage = "복호화 데이터 오류입니다.";
		    }    
		    else if( iReturn == -9)
		    {
		        sMessage = "입력 데이터 오류입니다.";
		    }    
		    else if( iReturn == -12)
		    {
		        sMessage = "사이트 패스워드 오류입니다.";
		    }    
		    else
		    {
		        sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
		    }
		    
		    LOGGER.debug("##mapresult :"+sMessage);
		    modelAndView.addObject("sMessage", sMessage);
		    modelAndView.setViewName("/manage/checkplus_fail");
//		    modelAndView.setViewName("/manage/individual_page");
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
		
		String password = "";
		try {
//			password = CryptoOnewayPasswrod.encryptPassword(insertList.get(i).getPhone(),insertList.get(i).getPhone());
			password = CryptoOnewayPasswrod.encryptPassword(inputform.getYyyymmdd(),inputform.getPhone());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		inputform.setPhonePassword(password);
		inputform.setAdminAuth("01"); //관리 비허용
		
		LOGGER.debug("##data {}",inputform);
		MemberVO user = memVoRepository.findByPhone(inputform.getPhone());
		Map<String, Object> result =  new HashMap();
		if(user !=null) {
			result.put("already",true);
		}else {
			MemberVO vo = memVoRepository.save(inputform);
			result.put("vo", vo);	
		}
		return result;
	}
	
	public String requestReplace (String paramValue, String gubun) {

        String result = "";
        
        if (paramValue != null) {
        	
        	paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

        	paramValue = paramValue.replaceAll("\\*", "");
        	paramValue = paramValue.replaceAll("\\?", "");
        	paramValue = paramValue.replaceAll("\\[", "");
        	paramValue = paramValue.replaceAll("\\{", "");
        	paramValue = paramValue.replaceAll("\\(", "");
        	paramValue = paramValue.replaceAll("\\)", "");
        	paramValue = paramValue.replaceAll("\\^", "");
        	paramValue = paramValue.replaceAll("\\$", "");
        	paramValue = paramValue.replaceAll("'", "");
        	paramValue = paramValue.replaceAll("@", "");
        	paramValue = paramValue.replaceAll("%", "");
        	paramValue = paramValue.replaceAll(";", "");
        	paramValue = paramValue.replaceAll(":", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll("#", "");
        	paramValue = paramValue.replaceAll("--", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll(",", "");
        	
        	if(gubun != "encodeData"){
        		paramValue = paramValue.replaceAll("\\+", "");
        		paramValue = paramValue.replaceAll("/", "");
            paramValue = paramValue.replaceAll("=", "");
        	}
        	
        	result = paramValue;
            
        }
        return result;
  }
}
