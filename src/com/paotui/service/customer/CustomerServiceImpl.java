package com.paotui.service.customer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.paotui.dao.configure.IConfigureMapper;
import com.paotui.dao.customer.ICustomerMapper;
import com.paotui.model.configure.Configure;
import com.paotui.model.customer.Customer;
public class CustomerServiceImpl  implements ICustomerService {

	@Autowired
	private ICustomerMapper iCustomerMapper;
	@Autowired
	private IConfigureMapper iConfigureMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Customer selectCustomerById(String id){
		return iCustomerMapper.selectcustomerById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Customer> selectCustomerByParam(Map paramMap){ 
		return iCustomerMapper.selectcustomerByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountCustomerByParam(Map paramMap){ 
		return iCustomerMapper.selectCountcustomerByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateCustomer(Customer customer){
		return iCustomerMapper.updatecustomer(customer);
	}

	/**
	* 添加 
	* @return
	*/ 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public  int addCustomer(Customer customer){
		int result=0;
		String initbalance="5";
		Map paramMap=new HashMap();
		paramMap.put("fromPage",0);
		paramMap.put("toPage",1); 
		paramMap.put("property","initbalance");
		List<Configure> list=iConfigureMapper.selectconfigureByParam(paramMap);
		if(list.size()>0){
			initbalance=list.get(0).getValue();
		}
		customer.setState(Long.parseLong("0"));
		customer.setBalance(initbalance);
		result=iCustomerMapper.addcustomer(customer);
		return result;
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdCustomer(List<Customer> list){
		return iCustomerMapper.muladdcustomer(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteCustomer(String id){
		return iCustomerMapper.deletecustomer(id);
	}

}

