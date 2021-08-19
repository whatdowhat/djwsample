package ser;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;


public class DateTest {


	@Test
	public void test() throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = dateFormat.format(new Date());
		LocalDate date = LocalDate.now();
		date = date.minusDays(7);
		Date convertDate =   Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		String endDate = dateFormat.format(convertDate);
		
		System.out.println(startDate);
		System.out.println(endDate);
		
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(dateFormat2.parse("2021-08-19"));
		date = date.plusDays(1);
		
		
	}

}
