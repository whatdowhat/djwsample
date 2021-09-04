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
import lombok.ToString;

@Entity
@Table(name = "web_innermessage")
@Getter
@Setter
@NoArgsConstructor
//@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class InnerMessage extends InputForm {

	
	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long seq;

	@Column(name = "frommemberseq")
	private Long frommemberseq;

	@Column(name = "tomemberseq")
	private Long tomemberseq;
	
	@Column(name = "messageTxt")
	private String messageTxt;
	
	@Column(name = "messageTitle")
	private String messageTitle;
	
	@Column(name = "tomemberName")
	private String tomemberName;
	
	@Column(name = "tomemberPhone")
	private String tomemberPhone;
	
	@Column(name = "frommemberName")
	private String frommemberName;
	
	@Column(name = "frommemberPhone")
	private String frommemberPhone;
	
	@Column(name = "readDt")
	private Date readDt;
	
	@Column(name = "regDt")
	private Date regDt;
	
}
