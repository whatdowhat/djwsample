package com.whatdo.keep.vo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.whatdo.keep.util.ExcelColumn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "SystemCommonVO")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SystemCommonVO {

	@Id
	@Column(name = "seq")
	@ExcelColumn(headerName="seq",order=1)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigDecimal seq;
//	private BigDecimal seq;
	
	@ExcelColumn(headerName="name1",order=1)
	@Column(name = "strParam01")
	private String strParam01;
	
	@ExcelColumn(headerName="name2",order=2)
	@Column(name = "strParam02")
	private String strParam02;
	
	@ExcelColumn(headerName="name3",order=3)
	@Column(name = "strParam03")
	private String strParam03;

	@ExcelColumn(headerName="name4",order=4)
	@Column(name = "intParam01")
	private Integer intParam01;
	
	@ExcelColumn(headerName="name5",order=5)
	@Column(name = "intParam02")
	private Integer intParam02;
	
	@ExcelColumn(headerName="name6",order=6)
	@Column(name = "intParam03")
	private Integer intParam03;
}
