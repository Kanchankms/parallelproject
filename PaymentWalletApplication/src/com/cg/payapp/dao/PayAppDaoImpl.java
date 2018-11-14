package com.cg.payapp.dao;

import java.util.Map;

import com.cg.payapp.bean.Customer;
import com.cg.payapp.exception.PayAppException;
import com.cg.payapp.util.CollectionUtil;

public class PayAppDaoImpl implements PayAppDao
{
	Map<String,Customer> custMap=CollectionUtil.addCustomer();
	@Override
	public void createAccount(Customer customer)
	{
		custMap.put(customer.getCustMobileNo(),customer);

	}

	@Override
	public double showBalance(String mobileNo)
	{
		Customer custShowBalance = custMap.get(mobileNo);
		double amount = custShowBalance.getInitialBalance();
		return amount;
	}

	@Override
	public void deposit(String mobileNo, double amount)
	{
		Customer customer = custMap.get(mobileNo);
		if(customer != null){
			double currentUpdatedAmount = customer.getInitialBalance();
			currentUpdatedAmount += amount;
			String name = customer.getCustName();
			String MobNo = customer.getCustMobileNo();
						
			customer.setInitialBalance(currentUpdatedAmount);
			customer.setCustName(name);
			customer.setCustMobileNo(MobNo);
			
			custMap.put(MobNo, customer);
			System.out.println("Amount deposited");
			System.out.println(" Current Updated Amount is:"+currentUpdatedAmount);
		}
		else{
			System.err.println("Sorry Mobile number not found, please enter valid mobile number!");
		}
	
	}

	@Override
	public void withdraw(String mobileNo, double amount)
	{
		Customer customer = custMap.get(mobileNo);
		if(customer != null){
			double currentUpdatedAmount = customer.getInitialBalance();
			currentUpdatedAmount = currentUpdatedAmount - amount;
			String name = customer.getCustName();
			String MobNo = customer.getCustMobileNo();
						
			customer.setInitialBalance(currentUpdatedAmount);
			customer.setCustName(name);
			customer.setCustMobileNo(MobNo);
			
			custMap.put(MobNo, customer);
			System.out.println("Amount withdrawl successful");
			System.out.println(" Current Updated Amount is:"+currentUpdatedAmount);
		}
		else{
			System.err.println("Sorry Mobile number not found, please enter valid mobile number!");
		}

	}

	@Override
	public void fundTransfer(String sender, String reciever, double amount)
	{
		String name, MobNo;
		double amountTrans;
		
		Customer custSender =  custMap.get(sender);
		Customer custReciever = custMap.get(reciever);
		
		double recieverAmount = custReciever.getInitialBalance();
		double senderAmount = custSender.getInitialBalance();
		if(!(senderAmount - amount > 500)){
			System.err.println("Sorry you don't have enough balance to make a fund transfer!!");
		}
		else{
			recieverAmount += amount;
			senderAmount -= amount;
			System.out.println("Fund successfully Transferred ");
		
		name = custSender.getCustName();
		MobNo = custSender.getCustMobileNo();
		amountTrans = senderAmount;
		
		custSender.setInitialBalance(senderAmount);
		custSender.setCustMobileNo(MobNo);
		custSender.setCustName(name);
		
		custMap.put(MobNo, custSender);
		
		name = custReciever.getCustName();
		MobNo = custReciever.getCustMobileNo();
		amountTrans = recieverAmount;
		
		
		custReciever.setInitialBalance(recieverAmount);
		custReciever.setCustMobileNo(MobNo);
		custReciever.setCustName(name);
		
		custMap.put(MobNo, custReciever);
		}

	}
			

	@Override
	public boolean validateAccount(String mobileNo) throws PayAppException
	{
		Customer customer = custMap.get(mobileNo);
		if(customer == null)
			return false;
		return true;
	}

}
