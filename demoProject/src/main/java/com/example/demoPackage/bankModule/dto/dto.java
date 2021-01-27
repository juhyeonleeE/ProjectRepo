package com.example.demoPackage.bankModule.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class dto {
	
	private Integer year;
	private String name;
	private Integer acctNo;
	private String brName;
	private String brCode;
	private Long sumAmt;
	
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(Integer acctNo) {
		this.acctNo = acctNo;
	}
	
	public String getBrName() {
		return brName;
	}

	public void setBrName(String brName) {
		this.brName = brName;
	}
	
	public String getBrCode() {
		return brCode;
	}

	public void setBrCode(String brCode) {
		this.brCode = brCode;
	}
	
	public Long getSumAmt() {
		return sumAmt;
	}

	public void setSumAmt(Long sumAmt) {
		this.sumAmt = sumAmt;
	}

	public dto(Integer year, String name, Integer acctNo, Long sumAmt) {
		this.year = year;
		this.name = name;
		this.acctNo = acctNo;
		this.sumAmt = sumAmt;
	}
	
	public dto(Integer year, String name, Integer acctNo) {
		this.year = year;
		this.name = name;
		this.acctNo = acctNo;
	}
	
	public dto(Integer year, String brName, String brCode, Long sumAmt) {
		this.year = year;
		this.brName = brName;
		this.brCode = brCode;
		this.sumAmt = sumAmt;
	}
	
	public dto(String brName, String brCode, Long sumAmt) {
		this.brName = brName;
		this.brCode = brCode;
		this.sumAmt = sumAmt;
	}
	
//	public dto(String name) {
//		this.name = name;
//	}

	@Override
	public String toString() {
		return "dto []";
//		return "dto [year=" + year + ", name=" + name + "]";
	}
	
}