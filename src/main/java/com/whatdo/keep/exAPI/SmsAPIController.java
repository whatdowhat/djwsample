package com.whatdo.keep.exAPI;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.NameClassPair;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.whatdo.keep.controller.MotherController;
import com.whatdo.keep.service.dao.AddressCodeDAO;
import com.whatdo.keep.vo.AddressCodeVO;
import com.whatdo.keep.vo.GroupVO;
import com.whatdo.keep.vo.MemberVO;

import java.io.InputStreamReader;                           
import java.io.BufferedReader;                              
import org.apache.http.HttpResponse;                        
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.HttpPost;             
import org.apache.http.impl.client.HttpClients;             
import org.apache.http.client.HttpClient;                   
import org.apache.http.message.BasicNameValuePair;          
import java.util.ArrayList;                                 
import org.apache.http.NameValuePair;                       


@Controller
public class SmsAPIController extends MotherController{
	
	@Autowired
	private AddressCodeDAO dao;
	
	private static Logger LOGGER = LoggerFactory.getLogger(SmsAPIController.class);
	
	@Value("${config.sampleFilePath}")
	private String sampleFilePath;
	
	@Value("${config.sampleFileName}")
	private String sampleFileName;
	
	@Value("${config.smsRealSend}")
	private String smsRealSend;
	
	
	
	@RequestMapping(value = "/admin/sms/send.do", method = { RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public Map sendmessagesms(Model model, HttpServletRequest req, HttpServletResponse response,
			HttpSession session,@RequestParam Map<Object, String> param,Principal principal) throws InterruptedException{
		
		 String json = param.get("list").toString();
 	    ObjectMapper mapper = new ObjectMapper();
 	    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);	// ?????? ????????? ?????? ?????? ??????
 	    
 	    List<MemberVO> insertList = null;
 	    try{
 	    	insertList = mapper.readValue(json, new TypeReference<ArrayList<MemberVO>>(){});
 	    }catch(Exception e){
 	    	e.printStackTrace();
 	    }
 	   int innerC = insertList.size();
	   int outC = 0;
		
		try{
			
			final String encodingType = "utf-8";
			final String boundary = "____boundary____";
		
			/**************** ?????????????????? ?????? ******************/
			/* "result_code":????????????,"message":????????????, */
			/* "msg_id":?????????ID,"error_cnt":????????????,"success_cnt":???????????? */
			/* ???????????? > ????????? ?????????.  
			/******************** ???????????? ********************/
			String sms_url = "https://apis.aligo.in/send/"; // ???????????? URL
			
			
			for(int k=0; k<insertList.size();k++) {
				
//		    	insertList.get(k).getMessageTxt();
//		    	insertList.get(k).getMessageTitle();
				
		    	Map<String, String> sms = new HashMap<String, String>();
				
				sms.put("user_id", "koreacrp"); // SMS ?????????
				sms.put("sender", ""); // ????????????
				sms.put("key", "n6p1fwvzj1l54ehteruj5x607842bpom"); //?????????
				sms.put("receiver", insertList.get(k).getPhone()); // ????????????
				/******************** ???????????? ********************/
				sms.put("msg", insertList.get(k).getMessageTxt()); // ????????? ??????
				/******************** ???????????? ********************/
				
				
//				sms.put("destination", "01111111111|?????????,01111111112|?????????"); // ????????? %?????????% ??????
				
				sms.put("rdate", ""); // ???????????? - 20161004 : 2016-10-04?????????
				sms.put("rtime", ""); // ???????????? - 1930 : ?????? 7???30???
				if(smsRealSend.equals("true")) {
					sms.put("testmode_yn", ""); // Y ????????? ???????????? ??????X , ????????????(??????) ??????
				}else {
					sms.put("testmode_yn", "Y"); // Y ????????? ???????????? ??????X , ????????????(??????) ??????
				}
				
				sms.put("title", insertList.get(k).getMessageTitle()); //  LMS, MMS ?????? (???????????? ????????? 44Byte ?????? ?????? ????????? ?????????)
				
				
				String image = "";
				//image = "/tmp/pic_57f358af08cf7_sms_.jpg"; // MMS ????????? ?????? ??????
				
				/******************** ???????????? ********************/
				
				MultipartEntityBuilder builder = MultipartEntityBuilder.create();
				
				builder.setBoundary(boundary);
				builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
				builder.setCharset(Charset.forName(encodingType));
				
				for(Iterator<String> i = sms.keySet().iterator(); i.hasNext();){
					String key = i.next();
					builder.addTextBody(key, sms.get(key)
							, ContentType.create("Multipart/related", encodingType));
				}
				
				File imageFile = new File(image);
				if(image!=null && image.length()>0 && imageFile.exists()){
			
					builder.addPart("image",
							new FileBody(imageFile, ContentType.create("application/octet-stream"),
									URLEncoder.encode(imageFile.getName(), encodingType)));
				}
				HttpEntity entity= builder.build();
				
				HttpClient client = HttpClients.createDefault();
				HttpPost post = new HttpPost(sms_url);
				post.setEntity(entity);
				
				HttpResponse res = client.execute(post);
				
				String result = "";
				if(res != null){
					BufferedReader in = new BufferedReader(new InputStreamReader(res.getEntity().getContent(), encodingType));
					String buffer = null;
					while((buffer = in.readLine())!=null){
						result += buffer;
					}
					in.close();
				}
//				System.out.println("sms sned!!!" + insertList.get(k).getPhone());
				
				GsonBuilder gsonbuilder = new GsonBuilder();
		        gsonbuilder.setPrettyPrinting();
		        Gson gson = gsonbuilder.create();
		        Map<String, Object> resultjson = gson.fromJson(result, Map.class);
		        if(Integer.valueOf(String.valueOf(resultjson.get("result_code"))) == 1) {
		        	outC  = outC+1;
		        }
			}
			
			
			
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("result", innerC == outC);
		return result;
	}
	
	
	@RequestMapping(value = "/admin/message/sms.do", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView innerMessage(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response,HttpSession session){
		
		LOGGER.debug("##innerMessage enter");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String endDate = dateFormat.format(new Date());
		LocalDate date = LocalDate.now();
		date = date.minusDays(7);
		Date convertDate =   Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		String startDate = dateFormat.format(convertDate);
		
		
		try{

			final String encodingType = "utf-8";
			
			/**************** ?????? ?????? ?????? ******************/
			/* "result_code":????????????,"message":????????????, */
			/** list : ????????? ?????? ?????? ***/
			/******************** ???????????? ********************/
			String sms_url = "https://apis.aligo.in/list/"; // ???????????? URL
			
			List<NameValuePair> sms = new ArrayList<NameValuePair>();
			
			sms.add(new BasicNameValuePair("user_id", "koreacrp"));// SMS ?????????1
			sms.add(new BasicNameValuePair("key", "n6p1fwvzj1l54ehteruj5x607842bpom")); //?????????
			
			/******************** ???????????? ********************/
			
			sms.add(new BasicNameValuePair("page", "1")); //?????? ????????????1
			sms.add(new BasicNameValuePair("page_size", "10000")); //?????? ??????
			sms.add(new BasicNameValuePair("start_date", startDate.replaceAll("-",""))); //????????? ??????
			sms.add(new BasicNameValuePair("end_date", endDate.replaceAll("-",""))); //????????? ??????
			sms.add(new BasicNameValuePair("limit_day", "8")); //????????????
		
			HttpClient client = HttpClients.createDefault();
			HttpPost post = new HttpPost(sms_url);
			post.setEntity(new UrlEncodedFormEntity(sms, encodingType));
			
			HttpResponse res = client.execute(post);
			
			String str = "";
			if(res != null){
				BufferedReader in = new BufferedReader(new InputStreamReader(res.getEntity().getContent(), encodingType));
				String buffer = null;
				while((buffer = in.readLine())!=null){
					str += buffer;
				}
				in.close();
			}
//			GsonBuilder builder = new GsonBuilder();
//	        builder.setPrettyPrinting();
//	        Gson gson = builder.create();
//	        Map<String, Object> result = gson.fromJson(str, Map.class);
			modelAndView.addObject("list", str);
			
			
		}catch(Exception e){
//			out.print(e.getMessage());
		}
		
		
		modelAndView.addObject("startDate", startDate);
		modelAndView.addObject("endDate", endDate);
		modelAndView.setViewName("message/smslist");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/message/smslist.do", method = { RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public Map smslist(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse response,HttpSession session
			,MemberVO inputform	) throws Exception{
		
		LOGGER.debug("##innerMessage enter");
		 Map<String, Object> result = new HashMap<String, Object>();
		try{

			final String encodingType = "utf-8";
			
			/**************** ?????? ?????? ?????? ******************/
			/* "result_code":????????????,"message":????????????, */
			/** list : ????????? ?????? ?????? ***/
			/******************** ???????????? ********************/
			String sms_url = "https://apis.aligo.in/list/"; // ???????????? URL
			
			List<NameValuePair> sms = new ArrayList<NameValuePair>();
			
			sms.add(new BasicNameValuePair("user_id", "koreacrp"));// SMS ?????????1
			sms.add(new BasicNameValuePair("key", "n6p1fwvzj1l54ehteruj5x607842bpom")); //?????????
			
			/******************** ???????????? ********************/
			
			sms.add(new BasicNameValuePair("page", "1")); //?????? ????????????1
			sms.add(new BasicNameValuePair("page_size", "10000")); //?????? ??????
			sms.add(new BasicNameValuePair("start_date", inputform.getSdate().replaceAll("-",""))); //????????? ??????
			sms.add(new BasicNameValuePair("end_date", inputform.getEdate().replaceAll("-",""))); //????????? ??????
			sms.add(new BasicNameValuePair("limit_day", "8")); //????????????
		
			HttpClient client = HttpClients.createDefault();
			HttpPost post = new HttpPost(sms_url);
			post.setEntity(new UrlEncodedFormEntity(sms, encodingType));
			
			HttpResponse res = client.execute(post);
			
			String str = "";
			if(res != null){
				BufferedReader in = new BufferedReader(new InputStreamReader(res.getEntity().getContent(), encodingType));
				String buffer = null;
				while((buffer = in.readLine())!=null){
					str += buffer;
				}
				in.close();
			}
			GsonBuilder builder = new GsonBuilder();
	        builder.setPrettyPrinting();
	        Gson gson = builder.create();
	        result = gson.fromJson(str, Map.class);
			modelAndView.addObject("list", str);
			
		}catch(Exception e){
//			out.print(e.getMessage());
		}
		return result;
		
	}
	
}
