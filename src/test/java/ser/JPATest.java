package ser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.whatdo.keep.config.ApplicationContextConfig;
import com.whatdo.keep.repository.TestVORepository;
import com.whatdo.keep.vo.TestVO;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationContextConfig.class)
public class JPATest {

	@Autowired
	private TestVORepository testVORepository;

	@Test
	public void test() {
		System.out.println("(((((((((((((((((((((((((((((((((");
		System.out.println(testVORepository == null);
		TestVO vo = testVORepository.findBySeq(1);
		System.out.println("(((((((((((((((((((((((((((((((((");
		System.out.println("(((((((((((((((((((((((((((((((((");
		System.out.println(vo.getId());
		System.out.println(vo.toString());
		System.out.println("(((((((((((((((((((((((((((((((((");
		System.out.println("(((((((((((((((((((((((((((((((((");
		
	}

}
