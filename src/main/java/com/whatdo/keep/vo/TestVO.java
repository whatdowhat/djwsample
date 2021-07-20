package com.whatdo.keep.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "testvotable")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TestVO {

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer seq;
	
	@Column(name = "id",unique=true)
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "etc")
	private String etc;
}
