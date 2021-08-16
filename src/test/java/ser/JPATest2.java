package ser;

import static org.junit.Assert.assertNull;

import java.util.ArrayList;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.whatdo.keep.config.ApplicationContextConfig;
import com.whatdo.keep.controller.group.SpecificationGroupVO;
import com.whatdo.keep.repository.GroupVORepository;
import com.whatdo.keep.repository.SystemCommonVORepository;
import com.whatdo.keep.repository.TestVORepository;
import com.whatdo.keep.vo.GroupVO;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationContextConfig.class)
public class JPATest2 {

	@Autowired
	private TestVORepository testVORepository;

	@Autowired
	private SystemCommonVORepository systemCommonVORepository;

	private static Logger LOGGER = LoggerFactory.getLogger(JPATest2.class);


	@Autowired
	private GroupVORepository groupVORepository;
	
	@Test
	public void test() throws JsonProcessingException {

		
		Map<String,Object> condition = new HashMap();//전체 검색
		PageRequest p = PageRequest.of(0, 100);
		Page<GroupVO> page = groupVORepository.findAll(SpecificationGroupVO.withCondition(condition), p);
	      
		System.out.println("$$$$$" + page.getContent().size());		
		
		List<GroupVO> list = new ArrayList();
		list = page.getContent();
		JsonArray array = new JsonArray(list.size());
		Gson gson = new Gson();
		ObjectMapper mapper = new ObjectMapper();
		for(int i =0; i<list.size();i++) {
			
			String tem =  mapper.writeValueAsString(list.get(i));
			array.add(tem);
			System.out.println(tem);
		}
		System.out.println(array);
		
	}

}
