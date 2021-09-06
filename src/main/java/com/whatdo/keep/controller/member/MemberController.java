package com.whatdo.keep.controller.member;

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
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.whatdo.keep.config.CryptoOnewayPasswrod;
import com.whatdo.keep.controller.MotherController;
import com.whatdo.keep.service.dao.AddressCodeDAO;
import com.whatdo.keep.util.FileDownload;
import com.whatdo.keep.vo.AddressCodeVO;
import com.whatdo.keep.vo.ChartDataVO;
import com.whatdo.keep.vo.FileuploadVO;
import com.whatdo.keep.vo.GroupVO;
import com.whatdo.keep.vo.InnerMessage;
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
		modelAndView.setViewName("member/page");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/member/chart/member.do", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView adminmemberchartmember(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session){
		
		
		LOGGER.debug("##adminmemberchartmember enter");
		
		Map param = new HashMap<String, Object>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String endDate = dateFormat.format(new Date());
		LocalDate date = LocalDate.now();
		date = date.minusDays(7);
		Date convertDate =   Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		String startDate = dateFormat.format(convertDate);
		param.put("startDate", startDate);
		LOGGER.debug("##endDate endDate"+endDate);
		param.put("endDate", endDate);
		
		Integer total = dao.gettotaldangwon(param);
		Integer dangwonCount = dao.getenterdangwonAll(param);
		Integer dangwonCount00 = dao.getenterdangwon00(param);
		Integer dangwonCount01 = dao.getenterdangwon01(param);
		
		
		List<ChartDataVO> chartDate = dao.getenterchart01(param);
		List<ChartDataVO> chartDate2 = dao.getenterchart02(param);
		
		LOGGER.debug("##endDate adminmemberchartmember "+param);
		List<AddressCodeVO> citys = dao.getCitysChart(param);
		Map<String,String> paramS = new HashMap();
		modelAndView.addObject("cities", citys );
		
		System.out.println("##adminmemberchartmember enter>>" +chartDate.size());
		Gson gson = new Gson();
		String gson1String = gson.toJson(chartDate);
		modelAndView.addObject("chartDate", gson1String);
		
		gson1String = gson.toJson(chartDate2);
		modelAndView.addObject("chartDate2", gson1String);
		
		modelAndView.addObject("total", total);
		modelAndView.addObject("dangwonCount", dangwonCount);
		modelAndView.addObject("dangwonCount00", dangwonCount00);
		modelAndView.addObject("dangwonCount01", dangwonCount01);
		
		modelAndView.addObject("startDate", startDate);
		modelAndView.addObject("endDate", endDate);
		
		modelAndView.setViewName("member/chart_member");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/member/chart/member/ajax.do", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView adminmemberchartmemberajax(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			,AddressCodeVO inputform	) throws InterruptedException{
		
		
		LOGGER.debug("##adminmemberchartmemberajax enter");
		LOGGER.debug("##adminmemberchartmemberajax enter inputform:: "+inputform);
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
	
	@RequestMapping(value = "/admin/member/excelPage.do", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView adminmemberexcelPage(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session){
		
		
		LOGGER.debug("##adminmemberexcelPage enter");
		LOGGER.debug("##login enter");
		
		List<AddressCodeVO> citys = dao.getCitys();
		
		Map<String,String> param = new HashMap();
		param.put("cityCode", citys.get(0).getCityCode());
		List<AddressCodeVO> gus = dao.getGus(param);
		
		param = new HashMap();
		param.put("cityCode", citys.get(0).getCityCode());
		param.put("gunCode", gus.get(0).getGunCode());
		List<AddressCodeVO> dongs = dao.getDongs(param);
		
		LOGGER.debug("##cities {} "+ citys);
		
		modelAndView.addObject("cities", citys );
		modelAndView.addObject("gus",gus );
		modelAndView.addObject("dongs",dongs );
		modelAndView.setViewName("main/page");
		
		
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
    	    		String password = "";
    	    				try {
    	    					password = CryptoOnewayPasswrod.encryptPassword(insertList.get(i).getPhone(),insertList.get(i).getPhone());
    	    				} catch (Exception e) {
    	    					// TODO Auto-generated catch block
    	    					e.printStackTrace();
    	    				}
    	    		insertList.get(i).setPhonePassword(password);
    	    		
    	    		memVoRepository.save(insertList.get(i));
    	    		outcount = outcount++;
    	    	}
    	    	
    	    }
    	    
    	    Map<String,Object> result = new HashMap<String, Object>();
    	    result.put("incomcount", incomcount);
    	    result.put("outcount", outcount);
    	    return result;
    	    
    }
    
    
    @RequestMapping(value = "/admin/send/message.do", method = RequestMethod.POST)
    @ResponseBody
    public Map sendmessagetxt(Model model, HttpServletRequest req, HttpServletResponse res,
			HttpSession session,@RequestParam Map<Object, String> param,Principal principal) throws Exception {

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
    	    
    	    
    	    int innerC = insertList.size();
    	    int outC = 0;
    	    
    		Map<String,String> auth =  getAuthentics();
    		boolean isAdmin = auth.get("ROLE_ADMIN") !=null ? true : false;
    		boolean user = auth.get("ROLE_USER") !=null ? true : false;
    	    MemberVO memberVO = memVoRepository.findByPhone(principal.getName());
    	    for(int i=0; i<insertList.size();i++) {
    	    	
    	    	InnerMessage vo = new InnerMessage();
    	    	if(isAdmin) {
    	    		vo.setFrommemberseq(new Long(0));
    	    		vo.setFrommemberPhone("00000000000");
    	    		vo.setFrommemberName("ADMIN");
    	    	}else {
    	    		vo.setFrommemberseq(memberVO.getSeq());
    	    		vo.setFrommemberPhone(memberVO.getPhone());
    	    		vo.setFrommemberName(memberVO.getName());
    	    	}
    	    	
    	    	
    	    	vo.setTomemberName(insertList.get(i).getName());
    	    	vo.setTomemberPhone(insertList.get(i).getPhone());
    	    	vo.setTomemberseq(insertList.get(i).getSeq());
    	    	vo.setMessageTxt(insertList.get(i).getMessageTxt());
    	    	vo.setMessageTitle(insertList.get(i).getMessageTitle());
    	    	
    	    	vo.setRegDt(new Date());
    	    	vo = innerMessageVORepository.save(vo);
    	    	if(vo!=null) outC++;
    	    }
    	    
    	   
    	    Map<String,Object> result = new HashMap<String, Object>();
    	    result.put("result", innerC == outC);
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
					data.setEndDate(row.getCell(0).getStringCellValue());
				} catch (Exception e) {
					data.setEndDate("");
				}
				try {
					data.setName(row.getCell(1).getStringCellValue());
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
					data.setSex(row.getCell(2).getStringCellValue());
				} catch (Exception e) {
					data.setSex("");
				}
				try {
					data.setCityN(row.getCell(5).getStringCellValue());
				} catch (Exception e) {
					data.setCityN("");
				}
				try {
					data.setGunN(row.getCell(6).getStringCellValue());
				} catch (Exception e) {
					data.setGunN("");
				}
				try {
					data.setDongN(row.getCell(7).getStringCellValue());
				} catch (Exception e) {
					data.setDongN("");
				}
				try {
					data.setDetailAddress(row.getCell(8).getStringCellValue());
				} catch (Exception e) {
					data.setDetailAddress("");
				}
				try {
					
					data.setMrank(row.getCell(9).getStringCellValue());
				} catch (Exception e) {
					data.setMrank("");
				}
				try {
					data.setLevel(row.getCell(10).getStringCellValue());
				} catch (Exception e) {
					data.setLevel("");
				}
				try {
					data.setDangwon(row.getCell(11).getStringCellValue());
				} catch (Exception e) {
					data.setDangwon("");
				}
				try {
					data.setChurch(row.getCell(12).getStringCellValue());
				} catch (Exception e) {
					data.setChurch("");
				}
				try {
					data.setChurchRank(row.getCell(13).getStringCellValue());
				} catch (Exception e) {
					data.setChurchRank("");
				}
				try {
					data.setGroupName(row.getCell(14).getStringCellValue());
				} catch (Exception e) {
					data.setGroupName("");
				}
				try {
					data.setGroupJikham(row.getCell(15).getStringCellValue());
				} catch (Exception e) {
					data.setGroupJikham("");
				}
				try {
					data.setRecommandName(row.getCell(16).getStringCellValue());
				} catch (Exception e) {
					data.setRecommandName("");
				}
				try {
					data.setRecommandPhone(row.getCell(17).getStringCellValue());
				} catch (Exception e) {
					data.setRecommandPhone("");
				}
				try {
					data.setAdminAuth(row.getCell(18).getStringCellValue());
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
		
		System.out.println("pagavle::"+ vo.getStart());
		System.out.println("pagavle::"+vo.getLength());
		
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
			,MemberVO inputform	) throws InterruptedException, ParseException{
		
		LOGGER.debug("##admingroupcommit enter");
		LOGGER.debug("##data {}",inputform);
		
		
		LocalDate l = LocalDate.parse(inputform.getEndDate());
		Date regd = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
		inputform.setRegDt(regd);
		
		Map<String,String> param = new HashMap();
		param.put("cityCode", inputform.getCityCode());
		param.put("gunCode", inputform.getGunCode());
		param.put("dongCode", inputform.getDongCode());
		AddressCodeVO getvo = dao.search_districtCode(param);
		
		inputform.setDistrictCode(getvo.getDistrictCode());
		inputform.setDistrictName(getvo.getDistrictName());
		String password = "";
				try {
					password = CryptoOnewayPasswrod.encryptPassword(inputform.getPhone(),inputform.getPhone());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		inputform.setPhonePassword(password);
		
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
		
		System.out.println("convert search condition::"+result);
		
		return result;
	}
	
	public List<MemberVO> afterValidationList(List<MemberVO> dataList){
		
		//19개 항목 validation
		for(int i=0; i<dataList.size();i++) {
			
			if(dataList.get(i).getEndDate() == null ||dataList.get(i).getGroupJikham().equals("")) {
				
			}else {
				Pattern pattern =  Pattern.compile("^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$");
				Matcher matcher = pattern.matcher(dataList.get(i).getEndDate());
				if(matcher.find()) {
					dataList.get(i).setEndDateval(true);	
				}
				
			}
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
					&& dataList.get(i).isEndDateval()
			) {
				dataList.get(i).setValidationYn(true);
			}
		}
		
		return dataList;
	}
	
}
