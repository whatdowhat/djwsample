package com.whatdo.keep.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "web_group")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class GroupVO extends InputForm {

	
	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long seq;

	@Column(name = "groupKey")
	private String groupKey;
	@Column(name = "name")
	private String name;
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
	@Column(name = "level")
	private String level;

	@Column(name = "phone")
	private String phone;
	
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
	
	@Column(name = "totalCount")
	private Long totalCount;
	
	@Column(name = "regDt")
	private Date regDt;
	@Column(name = "editDt")
	private Date editDt;
	
	@Transient
	int length;
	
	@Transient
	int start;
	
}
