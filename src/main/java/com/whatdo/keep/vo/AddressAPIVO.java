package com.whatdo.keep.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AddressAPIVO extends InputForm {


	String errorMessage;
	Integer countPerPage;
	Integer totalCount;
	Integer errorCode;
	Integer currentPage;
	
	String jibunAddr;
	String roadAddrPart2;
	String detBdNmList;
	String engAddr;
	String rn;
	String zipNo;
	String emdNm;
	String emdNo;
	String sggNm;
	String siNm;
	String roadAddrPart1;
	String bdNm;
	String admCd;
	String udrtYn;
	String lnbrMnnm;
	String roadAddr;
	String lnbrSlno;
	String buldMnnm;
	String bdKdcd;
	String liNm;
	String rnMgtSn;
	String mtYn;
	String bdMgtSn;
	String buldSlno;
	
	
	
}
