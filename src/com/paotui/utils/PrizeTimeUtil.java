package com.paotui.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.paotui.model.customer.Customer;
import com.paotui.service.customer.ICustomerService;
@Component 
public class PrizeTimeUtil {
	@Autowired
	private ICustomerService iCustomerService;
	Logger logger = Logger.getLogger("PaotuiLogger");
	/**
	 * 每天凌晨执行一次
	*/
	@Scheduled(cron = "0 0 0 * * ?") 
	public void updatePrizeState(){  
		Customer customer =new Customer();
		int resultUpdate=iCustomerService.updateCustomerprize(customer);
		logger.info("更新抽奖状态，数量："+resultUpdate);
	}
}
