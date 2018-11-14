package com.cg.payapp.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import com.cg.payapp.bean.Customer;
import com.cg.payapp.exception.PayAppException;
import com.cg.payapp.util.JPAUtil;

public class PayAppDaoImpl implements PayAppDao
{
	EntityTransaction tran=null;
	EntityManager em=null;
	public PayAppDaoImpl()
	{
		em=JPAUtil.getEntityManager();
	}
	
	
	@Override
	public void createAccount(Customer customer)
	{
		tran=em.getTransaction();
		tran.begin();
		em.persist(customer);			//insert
		tran.commit();
		

	}

	@Override
	public double showBalance(String mobileNo)
	{
		tran=em.getTransaction();
		tran.begin();
		Customer cust =  em.find(Customer.class, mobileNo);
		double amount = cust.getInitialBalance();
		tran.commit();
		
		return amount;
		
	}

	@Override
	public void deposit(String mobileNo, double amount)
	{
		tran=em.getTransaction();
		tran.begin();

		
		Customer cust =  em.find(Customer.class, mobileNo);
		double amt = cust.getInitialBalance();
		amt += amount;
		System.out.println(amt);
		cust.setInitialBalance(amt);
		
		tran.commit();
	}

	@Override
	public void withdraw(String mobileNo, double withdrawAmount)
	{
		tran=em.getTransaction();
		tran.begin();
		boolean flag = false;
		Customer cust = em.find(Customer.class, mobileNo);
		double amount = cust.getInitialBalance();
		if(!(amount-withdrawAmount > 500)){
			System.err.println("Insufficient Balance.\nPlease try again");
			cust.setInitialBalance(amount);
		}
		else{
			amount -= withdrawAmount;
			cust.setInitialBalance(amount);
			System.out.println("Rs."+withdrawAmount+" withdrawl successfully");
			flag = true;			
		}
		tran.commit();

	}

	@Override
	public void fundTransfer(String sender, String reciever, double amount)
	{
		tran=em.getTransaction();
		tran.begin();
		boolean flag = false;
		Customer custSender = em.find(Customer.class, sender);
		Customer custreciever = em.find(Customer.class, reciever);
		
		double senderAmount = custSender.getInitialBalance();
		double recieverAmount = custreciever.getInitialBalance();
		
		if((senderAmount - amount) > 500){
			senderAmount -= amount;
			recieverAmount += amount;
			custreciever.setInitialBalance(recieverAmount);
			custSender.setInitialBalance(senderAmount);
			flag = true;
			System.out.println("Fund of Rs."+amount+" transferred successfully! from "
					+custSender.getCustName()+" to "+custreciever.getCustName());
		}else{
			
			System.err.println("Invalid amount! As transfer amount is greater than your account balance.");
		}
		
		tran.commit();
	}
			

	@Override
	public boolean validateAccount(String mobileNo) throws PayAppException
	{
		Customer customer = em.find(Customer.class, mobileNo);
		if(customer == null)
			return false;
		return true;
	}

}
