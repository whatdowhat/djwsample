package ser;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.whatdo.keep.service.dao.TimeDAO;
import com.whatdo.keep.vo.Customer;
import com.whatdo.keep.vo.Historylog;

//@ComponentScan(basePackages = {"com.whatdo.keep.service.* com.whatdo.keep.vo.*"})
@RunWith(SpringJUnit4ClassRunner.class)
//@ComponentScan(basePackageClasses = [{}])
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/spring/context-datasource.xml","file:src/main/webapp/WEB-INF/config/dispatcher-servlet.xml"})
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/spring/context-datasource.xml"})

public class DaoTest {

//	@Autowired
//	private SqlSessionFactory sqlSessionFactory;
	
//	@Autowired
//	private SqlSessionTemplate template;
	
	@Autowired(required=false)
	private SqlSession sqlSession;
	
	
	@Autowired(required=false)
	private TimeDAO timeDAO;
	
	
	@Test
	public void test() {

		
		
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("seq",194 );
		Map result = sqlSession.selectOne("mapper.sample.getCustomer",param);
		System.out.println(result);
		System.out.println("###############################");
		Customer c = sqlSession.selectOne("mapper.sample.getCustomer2",param);
		System.out.println(c);
		System.out.println("###############################");
		param.put("seq", 20677);
		Historylog h = sqlSession.selectOne("mapper.sample.getHistory",param);
		System.out.println(h);
		
		System.out.println("###############################");
		
		
		
		System.out.println("(((((((((((((((((((((((((((((((((");
		System.out.println(timeDAO.getTime());
		System.out.println("(((((((((((((((((((((((((((((((((");
		
//		System.out.println(sqlSessionFactory.openSession().selectOne("mapper.sample.getTime","test").toString());
//		sqlSessionFactory.openSession().close();
//		
//		sqlSessionFact
		
//		System.out.println(sqlSession.selectOne("mapper.sample.getTime").toString());
//		System.out.println("####################");
		
	}

}
