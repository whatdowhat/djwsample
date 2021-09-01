package ser;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

import com.google.gson.Gson;
import com.whatdo.keep.service.dao.AddressCodeDAO;
import com.whatdo.keep.service.dao.TimeDAO;
import com.whatdo.keep.util.FileDownload;
import com.whatdo.keep.vo.AddressCodeVO;
import com.whatdo.keep.vo.ChartDataVO;
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
	
	@Autowired
	private AddressCodeDAO dao;
	
	
	@Value("${config.sampleFilePath}")
	private String sampleFilePath;
	
	@Value("${config.sampleFileName}")
	private String sampleFileName;
	
	@Test
	public void test() {


		
		//yyyy-mm-dd
		Map param = new HashMap<String, Object>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String endDate = dateFormat.format(new Date());
		LocalDate date = LocalDate.now();
		date = date.minusDays(6);
		Date convertDate =   Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		String startDate = dateFormat.format(convertDate);
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		
		Integer total = dao.getenterdangwonAll(param);
		Integer dangwonCount00 = dao.getenterdangwon00(param);
		Integer dangwonCount01 = dao.getenterdangwon01(param);
		List<ChartDataVO> chartDate = dao.getenterchart01(param);
		Gson gson = new Gson();
				Gson gson1 = new Gson();
				String gson1String = gson1.toJson(chartDate);
				System.out.println(gson1String);
		
		System.out.println("##adminmemberchartmember enter>>" +chartDate.size());
//		modelAndView.addObject("chartDate", chartDate);
		
	}

}
