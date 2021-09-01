package com.whatdo.keep.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class ChartDataVO extends InputForm {

	String selectedDate;
	Integer selectedDateCount;
	Integer selectedDateCountC;
	Integer selectedDateCountN;
	
	String age_20;
	String age_30;
	String age_40;
	String age_50;
	String age_60;
	
	
}
