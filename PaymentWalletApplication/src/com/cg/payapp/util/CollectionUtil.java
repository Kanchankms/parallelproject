package com.cg.payapp.util;

import java.util.HashMap;
import java.util.Map;

import com.cg.payapp.bean.Customer;


public class CollectionUtil
{
	private static Map<String, Customer> customer;
	public static Map<String, Customer> addCustomer()
	{
		if(customer == null)
			customer = new HashMap<>();		
		return customer;		
	}
}
