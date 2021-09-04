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
import com.whatdo.keep.repository.MemberVORepository;
import com.whatdo.keep.repository.SystemCommonVORepository;
import com.whatdo.keep.repository.TestVORepository;
import com.whatdo.keep.vo.GroupVO;
import com.whatdo.keep.vo.MemberVO;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationContextConfig.class)
public class JPATest3 {

	@Autowired
	private TestVORepository testVORepository;

	@Autowired
	private SystemCommonVORepository systemCommonVORepository;

	private static Logger LOGGER = LoggerFactory.getLogger(JPATest3.class);


	@Autowired
	private GroupVORepository groupVORepository;
	
	@Autowired
	private MemberVORepository membervor;
	
	@Test
	public void test() throws JsonProcessingException {

		
		
		MemberVO result =  membervor.findByPhone("01033323332");
		System.out.println(result);
		
		
		
		
	}

}
