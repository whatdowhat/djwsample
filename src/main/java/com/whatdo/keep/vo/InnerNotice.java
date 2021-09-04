package com.whatdo.keep.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "web_innernotice")
@Getter
@Setter
@NoArgsConstructor
//@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InnerNotice extends InputForm {

	
	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long seq;

	@Column(name = "frommemberseq")
	private Long frommemberseq;

	@Column(name = "frommemberName")
	private String frommemberName;

	@Column(name = "frommemberPhone")
	private String frommemberPhone;
	
	@Column(name = "noticeTitle")
	private String noticeTitle;
	
	@Column(name = "noticeText")
	private String noticeText;
	
	@Column(name = "endDt")
	private Date endDt;
	
	@Column(name = "regDt")
	private Date regDt;
	
	private boolean checked;
}
