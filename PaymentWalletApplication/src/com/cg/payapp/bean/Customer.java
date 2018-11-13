package com.cg.payapp.bean;

public class Customer
{
	private String custName;
	private String custMobileNo;
	private double initialBalance;
	
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustMobileNo() {
		return custMobileNo;
	}
	public void setCustMobileNo(String custMobileNo) {
		this.custMobileNo = custMobileNo;
	}
	public double getInitialBalance() {
		return initialBalance;
	}
	public void setInitialBalance(double initialBalance) {
		this.initialBalance = initialBalance;
	}
	
	
}
