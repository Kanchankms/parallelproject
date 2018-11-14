package com.cg.intro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.*;


@Component//("currencyConverter")
public class CurrencyConverterImpl implements CurrencyConverter
{
	
	
	@PostConstruct
	void init()
	{
		System.out.println("In init() - called thru @PostContruct");
	}
	
	@PreDestroy
	void destroy()
	{
		System.out.println("In destroy() - called thru @PreDestroy");
	}
	
	/*public CurrencyConverterImpl()
	{
		super();
		System.out.println("CurrencyConverterImpl()");
	}*/
	
	@Autowired
	private ExchangeService exchangeService;

	@Autowired
	public CurrencyConverterImpl(ExchangeService exchangeService) {
		super();
		this.exchangeService = exchangeService;
	}
	public ExchangeService getExchangeService() {
		
		
		return exchangeService;
	}


	
	public void setExchangeService(ExchangeService exchangeService) {
		this.exchangeService = exchangeService;
	}



	public double dollarsToRupees(double dollars) {
		
		return dollars*exchangeService.getExchangeRate();
	}

}
