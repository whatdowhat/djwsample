package ser;

import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.whatdo.keep.config.ApplicationContextConfig;
import com.whatdo.keep.repository.SystemCommonVORepository;
import com.whatdo.keep.repository.TestVORepository;
import com.whatdo.keep.vo.SystemCommonVO;
import com.whatdo.keep.vo.TestVO;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationContextConfig.class)
public class JPATest {

	@Autowired
	private TestVORepository testVORepository;

	@Autowired
	private SystemCommonVORepository systemCommonVORepository;

	
	@Test
	public void test() {
//		System.out.println("(((((((((((((((((((((((((((((((((");
//		System.out.println(testVORepository == null);
//		TestVO vo = testVORepository.findBySeq(1);
//		System.out.println("(((((((((((((((((((((((((((((((((");
//		System.out.println("(((((((((((((((((((((((((((((((((");
//		System.out.println(vo.getId());
//		System.out.println(vo.toString());
//		System.out.println("(((((((((((((((((((((((((((((((((");
//		System.out.println("(((((((((((((((((((((((((((((((((");
//		
//		
//		
//		SystemCommonVO t = systemCommonVORepository.findBySeq(new BigDecimal(1));
//		System.out.println(t);
		
//		for(int tt=0 ; tt<1000; tt++) {
			SystemCommonVO commonVO = new SystemCommonVO();
			commonVO.setStrParam01("value1");
			commonVO.setStrParam02("value2");
			commonVO.setStrParam03("value3");
			commonVO.setIntParam01(1);
			commonVO.setIntParam02(2);
			commonVO.setIntParam02(3);
			System.out.println(systemCommonVORepository.save(commonVO));	
//		}
		
		
		assertNull(null);
	}

}
