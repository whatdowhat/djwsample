package ser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.whatdo.keep.service.dao.AddressCodeDAO;
import com.whatdo.keep.service.dao.TimeDAO;
import com.whatdo.keep.vo.AddressCodeVO;

//@ComponentScan(basePackages = {"com.whatdo.keep.service.* com.whatdo.keep.vo.*"})
@RunWith(SpringJUnit4ClassRunner.class)
//@ComponentScan(basePackageClasses = [{}])
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/spring/context-datasource.xml","file:src/main/webapp/WEB-INF/config/dispatcher-servlet.xml"})
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/spring/context-datasource.xml"})

public class DaoAddress {

//	@Autowired
//	private SqlSessionFactory sqlSessionFactory;
	
//	@Autowired
//	private SqlSessionTemplate template;
	
	@Autowired(required=false)
	private SqlSession sqlSession;
	
	
	@Autowired(required=false)
	private AddressCodeDAO dao;
	
	
	
	
	@Test
	public void test() {

		
//		List<AddressCodeVO> l = dao.getCitys();
//		System.out.println("###################");
//		System.out.println(l.size());
//		System.out.println(l);
//		
//		Map<String,String> param = new HashMap();
//		param.put("cityCode", "11");
//		l = dao.getGus(param);
//		System.out.println("###################");
//		System.out.println(l.size());
//		System.out.println(l);
//		
//		param = new HashMap();
//		param.put("cityCode", "11");
//		param.put("gunCode", "11010");
//		l = dao.getDongs(param);
//		System.out.println("###################");
//		System.out.println(l.size());
//		System.out.println(l);
	
		List<AddressCodeVO> l = sqlSession.selectList("mapper.address.getCitys");
		System.out.println(l);
		
	
	}

}
