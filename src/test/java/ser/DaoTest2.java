package ser;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.whatdo.keep.service.dao.TimeDAO;
import com.whatdo.keep.util.FileDownload;
import com.whatdo.keep.vo.AddressCodeVO;
import com.whatdo.keep.vo.Customer;
import com.whatdo.keep.vo.Historylog;

//@ComponentScan(basePackages = {"com.whatdo.keep.service.* com.whatdo.keep.vo.*"})
@RunWith(SpringJUnit4ClassRunner.class)
//@ComponentScan(basePackageClasses = [{}])
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/spring/context-datasource.xml","file:src/main/webapp/WEB-INF/config/dispatcher-servlet.xml"})
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/spring/context-datasource.xml"})

public class DaoTest2 {

//	@Autowired
//	private SqlSessionFactory sqlSessionFactory;
	
//	@Autowired
//	private SqlSessionTemplate template;
	
	@Autowired(required=false)
	private SqlSession sqlSession;
	
	
	@Autowired(required=false)
	private TimeDAO timeDAO;
	
	
	@Autowired(required=false)
	private AddressCodeVO dao;
	
	
	@Value("${config.sampleFilePath}")
	private String sampleFilePath;
	
	@Value("${config.sampleFileName}")
	private String sampleFileName;
	
	@Test
	public void test() {


		Map<String, Object> param = new HashMap<String, Object>();
		//param.put("cityCode",11 );

		//param.put("cityCode",11 );
		param.put("dongN","광장동도" );
		
		
//		Integer resultCount = sqlSession.selectOne("mapper.address.dongVal",param);
//		System.out.println(resultCount);
		
        String path = File.separator + sampleFilePath + File.separator + sampleFileName; 
        File file = new File(path);
        System.out.println("path:::"+path);
        
        
		
//		System.out.println(sqlSessionFactory.openSession().selectOne("mapper.sample.getTime","test").toString());
//		sqlSessionFactory.openSession().close();
//		
//		sqlSessionFact
		
//		System.out.println(sqlSession.selectOne("mapper.sample.getTime").toString());
//		System.out.println("####################");
		
	}

}
