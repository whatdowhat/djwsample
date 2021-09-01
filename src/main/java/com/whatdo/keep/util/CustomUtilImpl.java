package com.whatdo.keep.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CustomUtilImpl implements CustomUtil{

	@Override
	public String getStringDateFormat(Object f) {

		String format ="yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		if(f.getClass() == LocalDate.class) {
			LocalDate date = (LocalDate) f;
			return date.format(DateTimeFormatter.ofPattern(format));
		}else if(f.getClass() == Date.class) {
			Date date = (Date)f;
			return simpleDateFormat.format(date);
		}
		
		return "Not";
	}

	@Override
	public String getStringDateFormat(Object f, String format) {
		//format : yyyy-mm-dd 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		if(f.getClass() == LocalDate.class) {
			LocalDate date = (LocalDate) f;
			return date.format(DateTimeFormatter.ofPattern(format));
		}else if(f.getClass() == Date.class) {
			Date date = (Date)f;
			return simpleDateFormat.format(date);
		}
		return "Not";
	}

}
