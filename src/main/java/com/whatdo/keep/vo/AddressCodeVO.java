package com.whatdo.keep.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class AddressCodeVO extends InputForm {

	String cityCode;
	String gunCode;
	String dongCode;
	String cityN;
	String gunN;
	String dongN;
	
	Integer cityCount;
	Integer gunCount;
	Integer dongCount;
	
}
