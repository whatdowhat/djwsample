package ser;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.whatdo.keep.vo.AddressAPIVO;


public class AddressTest2 {


	@Test
	public void test() {

		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
	    converters.add(new FormHttpMessageConverter());
	    converters.add(new StringHttpMessageConverter());
	 
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.setMessageConverters(converters);
	 
	    // parameter 세팅
	    MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	    
	    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
	    
	    final String addressKey = "devU01TX0FVVEgyMDIxMDgxNDEzMDc1NTExMTUyMzA=";
	    
	    map.add("confmKey", addressKey);
	    map.add("currentPage", "1");
	    map.add("countPerPage", "10");
	    map.add("keyword", "광장동");
	    map.add("resultType", "json");
	    
	 
	    // REST API 호출
	    String result =  restTemplate.postForObject("https://www.juso.go.kr/addrlink/addrLinkApi.do", request, String.class);
        Gson gson = new Gson();
        Map<String,Map> rr = gson.fromJson(result, Map.class);
	    Map<String,Object> m = rr.get("results");
	    ObjectMapper mapper = new ObjectMapper();
	    AddressAPIVO vo =  mapper.convertValue(m.get("common"), AddressAPIVO.class);
	    Map<String,Object> m2 = rr.get("results");
	    List<Map<String,Object>> jusoList = (List<Map<String, Object>>) m2.get("juso");
	    List<AddressAPIVO> addressList = new ArrayList<AddressAPIVO>();
	    for(int i =0; i<jusoList.size();i++) 
	    {
	    	AddressAPIVO tem_vo =  mapper.convertValue(jusoList.get(i), AddressAPIVO.class);
	    	addressList.add(tem_vo);
	    }
	    System.out.println(addressList.size());
	    
	    for(int i =0; i<addressList.size();i++) 
	    {
	    	System.out.println(addressList.get(i));
	    }
	    
	}

}
