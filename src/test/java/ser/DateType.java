package ser;


import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.whatdo.keep.util.CustomUtilImpl;
import com.whatdo.keep.vo.AddressAPIVO;


public class DateType {


	@Test
	public void test() throws Exception {

		
//		String date = "2021-08-29";
//		Date d = new SimpleDateFormat("yyyy-mm-dd").parse(date);
//		
//		System.out.println(d);
//		System.out.println(new SimpleDateFormat("yyyy-mm-dd").format(d));
//		
//		LocalDate l = LocalDate.parse("2021-08-29");
//		d = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
//		System.out.println(d);
//		
//		 String anni_date = "1999-01-01";
//		 Pattern pattern =  Pattern.compile("^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$");
//		 Matcher matcher = pattern.matcher(anni_date);
//		 System.out.println(matcher.find());
//		 
		 
		 CustomUtilImpl customUtilImpl = new CustomUtilImpl();
		LocalDate date = LocalDate.now();
//		date = date.minusDays(7);
		System.out.println(customUtilImpl.getStringDateFormat(date));
		Date date2 = new Date();
		System.out.println(customUtilImpl.getStringDateFormat(date2));

	}

}
