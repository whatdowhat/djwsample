package ser;

import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.whatdo.keep.config.ApplicationContextConfig;
import com.whatdo.keep.repository.SystemCommonVORepository;
import com.whatdo.keep.repository.TestVORepository;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationContextConfig.class)
public class JPATest {

	@Autowired
	private TestVORepository testVORepository;

	@Autowired
	private SystemCommonVORepository systemCommonVORepository;

	private static Logger LOGGER = LoggerFactory.getLogger(JPATest.class);


	@Test
	public void test() {

		System.out.println(systemCommonVORepository.findAll());
		LOGGER.info("test text {}", systemCommonVORepository.findAll());
		
		List<Map<String,Object>> list = systemCommonVORepository.customQuery();
		
		
		
		Map<String,Object> my = new HashMap<String, Object>();
		
		my.put("test1", 1);
		my.put("test2", "2");
		Gson gson = new Gson();
//		String colum = "test2";
//		System.out.println(list.get(0).get(colum));
//		System.out.println(list.get(0).toString());
//		System.out.println(my);
		JsonElement jsonElement = gson.toJsonTree(list.get(0));
		System.out.println(jsonElement);
		
		int page = 0;
		int size = 5;
	    Pageable paging = PageRequest.of(page, size);
	      
	    Page<Map<String,Object>> page_result = systemCommonVORepository.customQuery2(paging);
	    System.out.println(page_result.getTotalElements());
	    System.out.println(page_result.getContent().size());
	    System.out.println(page_result.getContent().get(0).get("strParm01"));
//		
	      
	      
//		for(int i = 0; i<100;i ++) {
//		
//			SystemCommonVO entity = new SystemCommonVO();
//			entity.setStrParam01("i+" + String.valueOf(i));
//			entity.setStrParam02("i+" + String.valueOf(i));
//			entity.setStrParam03("i+" + String.valueOf(i));
//			systemCommonVORepository.save(entity);
//			
//		}
//		
		
		
		//LOGGER.trace("exiting application");
		
		
		assertNull(null);
	}

}
