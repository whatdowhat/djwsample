package com.whatdo.keep.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Entity
//@Table(name = "testvotable")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class InputForm {

	String input1;
	String input2;
	List<String> list = new ArrayList<String>();
	
	Map<String,Object> map = new HashMap();
	
	@Transient
	int length;
	@Transient
	int start;
	
	
	String startDate;
	String endDate;
}
