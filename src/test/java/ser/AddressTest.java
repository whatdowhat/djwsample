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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.whatdo.keep.vo.AddressAPIVO;
import com.whatdo.keep.vo.MemberVO;


public class AddressTest {


	@Test
	public void test() throws JsonProcessingException {

	    
		  MemberVO data = new MemberVO();

	      data.setName( "test");
	      data.setChurch("test2");
	      data.setPhone("Test3");
	      
	    ObjectMapper mapper = new ObjectMapper();
	    JsonArray array = new JsonArray();
	    array.add(mapper.writeValueAsString(data));
	    array.add(mapper.writeValueAsString(data));
	    
	    System.out.println(array);
	    
	    
	}

}
