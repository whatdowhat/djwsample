package com.whatdo.keep.vo;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "web_member")
@Getter
@Setter
@NoArgsConstructor
//@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberVO extends InputForm {

	
	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long seq;

	
	@Column(name = "adminAuth") //관리권한 01 : 비허용 /02 : 허용
	private String adminAuth;
	
	
	@Column(name = "mrank") //직책 당협위원장 01, 지역장 02, 구역장 03, 단체장 04
	private String mrank;
	@Column(name = "dangwon") //일반:01/책임:02
	private String dangwon;
	
	@Column(name = "church") //교회
	private String church;
	@Column(name = "churchRank") //교회직분 // 청년 01 /성도/집사/안수집사/권사/장로/전도사/강도사/목사/사모/선교사/기타
	private String churchRank;
	
	@Column(name = "captain") //단체 대표자 captain/ null 해다없음.
	private String captain;
	
	@Column(name = "level") //등급 전국/시군구/읍면동
	private String level;
	
	
	@Column(name = "groupJikham") //단체직함
	private String groupJikham;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "signPad")
	private String signPad;
	
	
	@Column(name = "sex")
	private String sex;
	@Column(name = "recommandName")
	private String recommandName;
	@Column(name = "recommandPhone")
	private String recommandPhone;	
	
	@Column(name = "signData")
	@Lob 
	private byte[] signData;
	
	@Column(name = "groupKey")
	private String groupKey;
	@Column(name = "groupName")
	private String groupName;
	@Column(name = "name")
	private String name;
	
	@Column(name = "representiveRankName")
	private String representiveRankName;
	
	@Column(name = "representiveName")
	private String representiveName;
	@Column(name = "representiveCode")
	private String representiveCode;
	@Column(name = "yyyymmdd")
	private String yyyymmdd;
	@Column(name = "sojeji")
	private String sojeji;
	@Column(name = "detailAddress")
	private String detailAddress;


	@Column(name = "cityCode")
	private String cityCode;
	@Column(name = "gunCode")
	private String gunCode;
	@Column(name = "dongCode")
	private String dongCode;
	@Column(name = "cityN")
	private String cityN;
	@Column(name = "gunN")
	private String gunN;
	@Column(name = "dongN")
	private String dongN;
	
	@Column(name = "regDt")
	private Date regDt;
	@Column(name = "editDt")
	private Date editDt;
	
	
	@Transient
	private String endDate;
	@Transient
	private boolean endDateval;
	@Transient
	private boolean validationYn;
	@Transient
	private boolean groupKeyval;
	@Transient
	private boolean groupJikhamval;
	@Transient
	private boolean nameval;
	@Transient
	private boolean yyyymmddval;
	@Transient
	private boolean phoneval;
	@Transient
	private boolean sexval;
	@Transient
	private boolean cityCodeval;
	@Transient
	private boolean gunCodeval;
	@Transient
	private boolean dongCodeval;
	@Transient
	private boolean detailAddressval;
	@Transient
	private boolean mrankval;
	@Transient
	private boolean levelval;
	@Transient
	private boolean dangwonval;
	@Transient
	private boolean churchval;
	@Transient
	private boolean churchRankval;
	@Transient
	private boolean recommandNameval;
	@Transient
	private boolean recommandPhoneval;
	@Transient
	private boolean adminAuthval;
	@Override
	public String toString() {
		return "MemberVO [seq=" + seq + ", adminAuth=" + adminAuth + ", mrank=" + mrank + ", dangwon=" + dangwon
				+ ", church=" + church + ", churchRank=" + churchRank + ", captain=" + captain + ", level=" + level
				+ ", groupJikham=" + groupJikham + ", phone=" + phone + ", signPad=" + signPad + ", sex=" + sex
				+ ", recommandName=" + recommandName + ", recommandPhone=" + recommandPhone + ", signData="
				+ Arrays.toString(signData) + ", groupKey=" + groupKey + ", groupName=" + groupName + ", name=" + name
				+ ", representiveRankName=" + representiveRankName + ", representiveName=" + representiveName
				+ ", representiveCode=" + representiveCode + ", yyyymmdd=" + yyyymmdd + ", sojeji=" + sojeji
				+ ", detailAddress=" + detailAddress + ", cityCode=" + cityCode + ", gunCode=" + gunCode + ", dongCode="
				+ dongCode + ", cityN=" + cityN + ", gunN=" + gunN + ", dongN=" + dongN + ", regDt=" + regDt
				+ ", editDt=" + editDt + ", endDate=" + endDate + ", endDateval=" + endDateval + ", validationYn="
				+ validationYn + ", groupKeyval=" + groupKeyval + ", groupJikhamval=" + groupJikhamval + ", nameval="
				+ nameval + ", yyyymmddval=" + yyyymmddval + ", phoneval=" + phoneval + ", sexval=" + sexval
				+ ", cityCodeval=" + cityCodeval + ", gunCodeval=" + gunCodeval + ", dongCodeval=" + dongCodeval
				+ ", detailAddressval=" + detailAddressval + ", mrankval=" + mrankval + ", levelval=" + levelval
				+ ", dangwonval=" + dangwonval + ", churchval=" + churchval + ", churchRankval=" + churchRankval
				+ ", recommandNameval=" + recommandNameval + ", recommandPhoneval=" + recommandPhoneval
				+ ", adminAuthval=" + adminAuthval + ", input1=" + input1 + ", input2=" + input2 + ", list=" + list
				+ ", map=" + map + ", length=" + length + ", start=" + start + ", startDate=" + startDate + "]";
	}
	
	
	
}
