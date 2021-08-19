package com.whatdo.keep.vo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "web_fileupload")
@Getter
@Setter
public class FileuploadVO {
	
	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer seq;
	
	@Column(name = "originalFileName")
	private String originalFileName;
	
	@Column(name = "storedFileName ")
	private String storedFileName;

	@Column(name = "fileSize")
	private BigDecimal fileSize;
	
	@Column(name = "fileDirRoot")
	private String fileDirRoot;
	
	@Column(name = "fileDirPath")
	private String fileDirPath;
	
	@Column(name = "memberSeq")
	private Integer memberSeq;
	
	@Column(name = "isSpecificOK")
	private String isSpecificOK;
	
	@CreatedDate
	@Column(name = "regDt")
	private Date regDt;
	
}
