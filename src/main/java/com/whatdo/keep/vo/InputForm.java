package com.whatdo.keep.vo;

import java.util.ArrayList;
import java.util.List;

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
//	String[] list;
	List<String> list = new ArrayList<String>();
//	Integer[] list;
}
