package com.whatdo.keep.controller.member;

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
import org.springframework.http.ResponseEntity;
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
import com.google.gson.JsonArray;
import com.whatdo.keep.controller.MotherController;
import com.whatdo.keep.service.dao.AddressCodeDAO;
import com.whatdo.keep.util.FileDownload;
import com.whatdo.keep.vo.AddressCodeVO;
import com.whatdo.keep.vo.FileuploadVO;
import com.whatdo.keep.vo.GroupVO;
import com.whatdo.keep.vo.MemberVO;



@Controller
public class MemberController extends MotherController{
	
	@Autowired
	private AddressCodeDAO dao;
	
	private static Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
	
	@Value("${config.sampleFilePath}")
	private String sampleFilePath;
	
	@Value("${config.sampleFileName}")
	private String sampleFileName;
	
	
	@RequestMapping(value = "/admin/member/page.do", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView adminmemberpage(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session){
		
		
		LOGGER.debug("##adminmemberpage enter");
		
		List<GroupVO> groups = groupVORepository.findAll();
//		
		List<AddressCodeVO> citys = dao.getCitys();
		Map<String,String> param = new HashMap();
		param.put("cityCode", citys.get(0).getCityCode());
		List<AddressCodeVO> gus = dao.getGus(param);
		
		param = new HashMap();
		param.put("cityCode", citys.get(0).getCityCode());
		param.put("gunCode", gus.get(0).getGunCode());
		List<AddressCodeVO> dongs = dao.getDongs(param);
//		
		modelAndView.addObject("groups", groups );
		modelAndView.addObject("cities", citys );
		modelAndView.addObject("gus",gus );
		modelAndView.addObject("dongs",dongs );
		modelAndView.setViewName("member/page");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/member/excelPage.do", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView adminmemberexcelPage(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session){
		
		
		LOGGER.debug("##adminmemberexcelPage enter");
		
		
		modelAndView.setViewName("member/excelPage");
		return modelAndView;
	}
	
    @RequestMapping(value = "/admin/member/excelcommit.do", method = RequestMethod.POST)
    @ResponseBody
    public Map sampleDownload(Model model, HttpServletRequest req, HttpServletResponse res,
			HttpSession session,@RequestParam Map<Object, String> param) throws Exception {

    	 String json = param.get("list").toString();
    	    ObjectMapper mapper = new ObjectMapper();
    	    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);	// 없는 필드로 인한 오류 무시
    	    
    	    List<MemberVO> insertList = null;
    	    try{
    	    	insertList = mapper.readValue(json, new TypeReference<ArrayList<MemberVO>>(){});
    	    }catch(Exception e){
    	    	e.printStackTrace();
    	    }
    	    
    	    Map<String,Object> condition = new HashMap<String, Object>();
    	    System.out.println(insertList);
    	    System.out.println("insertList :: "+insertList);
    	    int incomcount = insertList.size();
    	    int outcount = 0;
    	    for(int i=0; i<insertList.size();i++) {
    	    	condition.put("name", insertList.get(i).getName());
    	    	condition.put("phone", insertList.get(i).getPhone());
    	    	
    	    	List<MemberVO> checkList = memVoRepository.findAll(SpecificationMmemberVO.withCondition(condition));
    	    	System.out.println("checklist :: "+checkList.size());
    	    	if(checkList.size()>0) {//기존에 이미 가입.
    	    		
    	    	}else {
    	    		insertList.get(i).setRegDt(new Date());
    	    		memVoRepository.save(insertList.get(i));
    	    		outcount = outcount++;
    	    	}
    	    	
    	    }
    	    
    	    Map<String,Object> result = new HashMap<String, Object>();
    	    result.put("incomcount", incomcount);
    	    result.put("outcount", outcount);
    	    return result;
    	    
    }
	
    @RequestMapping(value = "/admin/member/example/filedownload.do", method = RequestMethod.GET)
    @ResponseBody
    public View excelcommit(Model model, HttpServletRequest req, HttpServletResponse res,
			HttpSession session) throws Exception {
    	
    	//for linux
//        String path = sampleFilePath;
        URL url = getClass().getResource("/sample.xlsx");
        String path = url.getPath();

//    	String path = "C:\\eGovFrameDev-3.10.0-64bit\\eclipse\\sampleFile\\sample.xlsx";
        File file = new File(path);
        System.out.println("path:::"+path);
        model.addAttribute("downloadFile", file);
        model.addAttribute("fileName", sampleFileName);
        model.addAttribute("contentType", "application/download;charset=UTF-8");
        return new FileDownload();
    }
    
	@RequestMapping(value="/admin/member/excel/upload.do", method = {RequestMethod.POST})
	@ResponseBody
	//public ResponseEntity<String> userwaituploaduploadAjax(MultipartFile file, String fromJSP) throws Exception {
	public Map userwaituploaduploadAjax(MultipartFile file, String fromJSP) throws Exception {
	
		LOGGER.debug("##adminmemberexcelPage enter");
		
		
		  List<MemberVO> dataList = new ArrayList<>();

		    String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 3

		    if (!extension.equals("xlsx") && !extension.equals("xls")) {
		      throw new IOException("엑셀파일만 업로드 해주세요.");
		    }

		    Workbook workbook = null;

		    if (extension.equals("xlsx")) {
		      workbook = new XSSFWorkbook(file.getInputStream());
		    } else if (extension.equals("xls")) {
		      workbook = new HSSFWorkbook(file.getInputStream());
		    }
		    Sheet worksheet = workbook.getSheetAt(0);
		    System.out.println("worksheet.getPhysicalNumberOfRows() :: "+worksheet.getPhysicalNumberOfRows());
		    for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4
		    	
		      ObjectMapper mapper = new ObjectMapper();
		      Row row = worksheet.getRow(i);

		      MemberVO data = new MemberVO();

				try {
					data.setGroupName(row.getCell(0).getStringCellValue());
				} catch (Exception e) {
					data.setGroupName("");
				}
				try {
					data.setGroupJikham(row.getCell(1).getStringCellValue());
				} catch (Exception e) {
					data.setGroupJikham("");
				}
				try {
					data.setName(row.getCell(2).getStringCellValue());
				} catch (Exception e) {
					data.setName("");
				}
				try {
					data.setYyyymmdd(row.getCell(3).getStringCellValue());
				} catch (Exception e) {
					data.setYyyymmdd("");
				}
				try {
					data.setPhone(row.getCell(4).getStringCellValue());
				} catch (Exception e) {
					data.setPhone("");
				}
				try {
					data.setSex(row.getCell(5).getStringCellValue());
				} catch (Exception e) {
					data.setSex("");
				}
				try {
					data.setCityN(row.getCell(6).getStringCellValue());
				} catch (Exception e) {
					data.setCityN("");
				}
				try {
					data.setGunN(row.getCell(7).getStringCellValue());
				} catch (Exception e) {
					data.setGunN("");
				}
				try {
					data.setDongN(row.getCell(8).getStringCellValue());
				} catch (Exception e) {
					data.setDongN("");
				}
				try {
					data.setDetailAddress(row.getCell(9).getStringCellValue());
				} catch (Exception e) {
					data.setDetailAddress("");
				}
				try {
					data.setMrank(row.getCell(10).getStringCellValue());
				} catch (Exception e) {
					data.setMrank("");
				}
				try {
					data.setLevel(row.getCell(11).getStringCellValue());
				} catch (Exception e) {
					data.setLevel("");
				}
				try {
					data.setDangwon(row.getCell(12).getStringCellValue());
				} catch (Exception e) {
					data.setDangwon("");
				}
				try {
					data.setChurch(row.getCell(13).getStringCellValue());
				} catch (Exception e) {
					data.setChurch("");
				}
				try {
					data.setChurchRank(row.getCell(14).getStringCellValue());
				} catch (Exception e) {
					data.setChurchRank("");
				}
				try {
					data.setRecommandName(row.getCell(15).getStringCellValue());
				} catch (Exception e) {
					data.setRecommandName("");
				}
				try {
					data.setRecommandPhone(row.getCell(16).getStringCellValue());
				} catch (Exception e) {
					data.setRecommandPhone("");
				}
				try {
					data.setAdminAuth(row.getCell(17).getStringCellValue());
				} catch (Exception e) {
					data.setAdminAuth("");
				}
		      
		     
			/*
			 * data.setName(row.getCell(2).getStringCellValue());
			 * data.setYyyymmdd(row.getCell(3).getStringCellValue());
			 * data.setPhone(row.getCell(4).getStringCellValue());
			 * data.setSex(row.getCell(5).getStringCellValue());
			 * data.setCityN(row.getCell(6).getStringCellValue());
			 * data.setGunN(row.getCell(7).getStringCellValue());
			 * data.setDongN(row.getCell(8).getStringCellValue());
			 * data.setDetailAddress(row.getCell(9).getStringCellValue());
			 * data.setMrank(row.getCell(10).getStringCellValue());
			 * data.setLevel(row.getCell(11).getStringCellValue());
			 * data.setDangwon(row.getCell(12).getStringCellValue());
			 * data.setChurch(row.getCell(13).getStringCellValue());
			 * data.setChurchRank(row.getCell(14).getStringCellValue());
			 * data.setRecommandName(row.getCell(15).getStringCellValue());
			 * data.setRecommandPhone(row.getCell(16).getStringCellValue());
			 * data.setAdminAuth(row.getCell(17).getStringCellValue());
			 */

		      dataList.add(data);
		    }
		    
		    dataList = afterValidationList(dataList);
		    System.out.println("datalist size : :"+dataList.size());
		    System.out.println("datalist size : :"+dataList);
		    Map resultMap = new HashMap<String, Object>();
			resultMap.put("data",dataList);
			
			
		    return resultMap;
	}
	
	@RequestMapping(value = "/admin/member/list.do", method = { RequestMethod.GET})
	public ModelAndView adminmemberlist(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			
			) {		
		
		
		LOGGER.debug("##adminmemberlist enter");
		
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
		modelAndView.setViewName("member/list");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/admin/member/listtable.do", method = { RequestMethod.POST})
	@ResponseBody
	public Map adminmemberlisttable(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			, MemberVO vo ) throws JsonProcessingException, ParseException {		
		
		LOGGER.debug("##admingrouplist enter" +vo.toString() );
		
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

	
	@RequestMapping(value = "/admin/member/commit", method = { RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public Map admingroupcommit(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			,MemberVO inputform	) throws InterruptedException{
		
		LOGGER.debug("##admingroupcommit enter");
		LOGGER.debug("##data {}",inputform);
		inputform.setRegDt(new Date());
		inputform.setDetailAddress(inputform.getCityN()+" "+inputform.getGunN()+" "+inputform.getDongN()+" "+inputform.getDetailAddress());
		MemberVO vo = memVoRepository.save(inputform);
		Map<String, Object> result =  new HashMap();
		result.put("already", false);
		result.put("vo", vo);
		
		return result;
	}
	
	
	@RequestMapping(value = "/admin/member/getcity", method = { RequestMethod.GET,RequestMethod.POST })
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
	@RequestMapping(value = "/admin/member/getgun", method = { RequestMethod.GET,RequestMethod.POST })
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
	
	public List<MemberVO> afterValidationList(List<MemberVO> dataList){
		
		//18개 항목 validation
		for(int i=0; i<dataList.size();i++) {
			
			
			
			
			GroupVO group =  groupVORepository.findByName(dataList.get(i).getGroupName());
			if(group!=null) {
				dataList.get(i).setGroupKeyval(true);
				dataList.get(i).setGroupKey(dataList.get(i).getGroupKey());
				
			}
			
			if(dataList.get(i).getGroupJikham() == null ||dataList.get(i).getGroupJikham().equals("")) {
				
			}else {
				dataList.get(i).setGroupJikhamval(true);
			}
			
			
			if(dataList.get(i).getYyyymmdd() == null ||dataList.get(i).getYyyymmdd().equals("")) {
				
			}else {
				dataList.get(i).setYyyymmddval(true);
			}
			
			if(dataList.get(i).getName() == null ||dataList.get(i).getName().equals("")) {
				
			}else {
				dataList.get(i).setNameval(true);
			}
			
			if(dataList.get(i).getPhone() == null ||dataList.get(i).getPhone().equals("")) {
				
			}else {
				dataList.get(i).setPhoneval(true);
			}

			//기존회원 체크. 이름/연락처
			Map<String,Object> condition = new HashMap<String, Object>();
	    	
			condition.put("name", dataList.get(i).getName());
	    	condition.put("phone", dataList.get(i).getPhone());
			List<MemberVO> checkList = memVoRepository.findAll(SpecificationMmemberVO.withCondition(condition));
			System.out.println("afterValidationListafterValidationList!!! "+checkList.size());
			if(checkList.size() >0 ) {
				dataList.get(i).setNameval(false);
				dataList.get(i).setPhoneval(false);
			}
			
			
			if(dataList.get(i).getSex() == null ||dataList.get(i).getSex().equals("")) {
				
			}else {
				dataList.get(i).setSexval(true);
			}
			
			if(dataList.get(i).getCityN() == null ||dataList.get(i).getCityN().equals("")) {
				
			}else {
				Map<String,String> param = new HashMap<String, String>();
				param.put("cityN", dataList.get(i).getCityN());
				String strresult = dao.cityVal(param);
				if(!strresult.equals("0")) {
					dataList.get(i).setCityCodeval(true);
					dataList.get(i).setCityCode(strresult);
				}
				
			}
			
			if(dataList.get(i).getGunN() == null ||dataList.get(i).getGunN().equals("")) {
				
			}else {
				Map<String,String> param = new HashMap<String, String>();
				param.put("gunN", dataList.get(i).getGunN());
				String strresult = dao.gunVal(param);
				if(!strresult.equals("0")) {
					dataList.get(i).setGunCodeval(true);
					dataList.get(i).setGunCode(strresult);
				}
				
			}
			
			if(dataList.get(i).getDongN() == null ||dataList.get(i).getDongN().equals("")) {
				
			}else {
				Map<String,String> param = new HashMap<String, String>();
				param.put("dongN", dataList.get(i).getDongN());
				String strresult = dao.dongVal(param);
				if(!strresult.equals("0")) {
					dataList.get(i).setDongCodeval(true);
					dataList.get(i).setDongCode(strresult);
				}
				
			}
			
			if(dataList.get(i).getDetailAddress() == null ||dataList.get(i).getDetailAddress().equals("")) {
				
			}else {
				dataList.get(i).setDetailAddressval(true);
			}
			
			if(dataList.get(i).getMrank() == null ||dataList.get(i).getMrank().equals("")) {
				
			}else {
				dataList.get(i).setMrankval(true);
			}
			
			if(dataList.get(i).getLevel() == null ||dataList.get(i).getLevel().equals("")) {
				
			}else {
				dataList.get(i).setLevelval(true);
			}
			
			if(dataList.get(i).getDangwon() == null ||dataList.get(i).getDangwon().equals("")) {
				
			}else {
				
				dataList.get(i).setDangwonval(true);
			}
			
			if(dataList.get(i).getChurch() == null ||dataList.get(i).getChurch().equals("")) {
				
			}else {
				
				dataList.get(i).setChurchval(true);
			}
			
			if(dataList.get(i).getChurchRank() == null ||dataList.get(i).getChurchRank().equals("")) {
				
			}else {
				
				dataList.get(i).setChurchRankval(true);
			}
			
			if(dataList.get(i).getRecommandName() == null ||dataList.get(i).getRecommandName().equals("")) {
				
			}else {
				
				dataList.get(i).setRecommandNameval(true);
			}
			
			if(dataList.get(i).getRecommandPhone() == null ||dataList.get(i).getRecommandPhone().equals("")) {
				
			}else {
				
				dataList.get(i).setRecommandPhoneval(true);
			}
			
			if(dataList.get(i).getAdminAuth() == null ||dataList.get(i).getAdminAuth().equals("")) {
				
			}else {
				
				if(dataList.get(i).getAdminAuth().equals("허용")) {
					dataList.get(i).setAdminAuth("01");
				}else {
					dataList.get(i).setAdminAuth("02");
				}
				
				dataList.get(i).setAdminAuthval(true);
			}
			if(
					dataList.get(i).isGroupKeyval() 
					&& dataList.get(i).isGroupJikhamval()
					&& dataList.get(i).isNameval()
					&& dataList.get(i).isYyyymmddval()
					&& dataList.get(i).isPhoneval()
					&& dataList.get(i).isSexval()
					&& dataList.get(i).isCityCodeval()
					&& dataList.get(i).isGunCodeval()
					&& dataList.get(i).isDongCodeval()
					&& dataList.get(i).isDetailAddressval()
					&& dataList.get(i).isGroupJikhamval()
					&& dataList.get(i).isLevelval()
					&& dataList.get(i).isDangwonval()
					&& dataList.get(i).isChurchval()
					&& dataList.get(i).isChurchRankval()
					&& dataList.get(i).isRecommandNameval()
					&& dataList.get(i).isRecommandPhoneval()
					&& dataList.get(i).isAdminAuthval()
			) {
				dataList.get(i).setValidationYn(true);
			}
		}
		
		return dataList;
	}
	
}
