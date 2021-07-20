package com.whatdo.keep.vo;

public class Customer {

	Integer seq;
	String COMPANYNAME;
	String TOCOMP;
	String ADDRESS;
	
	
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getCOMPANYNAME() {
		return COMPANYNAME;
	}
	public void setCOMPANYNAME(String cOMPANYNAME) {
		COMPANYNAME = cOMPANYNAME;
	}
	public String getTOCOMP() {
		return TOCOMP;
	}
	public void setTOCOMP(String tOCOMP) {
		TOCOMP = tOCOMP;
	}
	@Override
	public String toString() {
		return "Customer [seq=" + seq + ", COMPANYNAME=" + COMPANYNAME + ", TOCOMP=" + TOCOMP + "]";
	}
	
	
	
}
