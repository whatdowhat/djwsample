package com.whatdo.keep.vo;

public class Historylog {

	Integer seq;
	String ip;
	String workType;
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	@Override
	public String toString() {
		return "Historylog [seq=" + seq + ", ip=" + ip + ", workType=" + workType + "]";
	}
	
	
	
	
}
