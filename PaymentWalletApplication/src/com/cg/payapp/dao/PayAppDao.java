package com.cg.payapp.dao;

import com.cg.payapp.bean.Customer;
import com.cg.payapp.exception.PayAppException;

public interface PayAppDao
{
	public void createAccount(Customer customer);

	public double showBalance(String mobileNo);
	
	public void deposit(String mobileNo, double amount);
	
	public void withdraw(String mobileNo, double amount);
		
	public void fundTransfer(String sender, String reciever, double amount);
	
	public boolean validateAccount(String mobileNo) throws PayAppException;

}
