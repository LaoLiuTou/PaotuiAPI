package com.paotui.service.customer;
import java.util.List;
import java.util.Map;
import com.paotui.model.customer.Customer;
public interface ICustomerService {
	/**
	* 通过id选取
	* @return
	*/
	public Customer selectCustomerById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Customer> selectCustomerByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountCustomerByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateCustomer(Customer customer);

	/**
	* 添加 
	* @return
	*/ 
	public int addCustomer(Customer customer);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdCustomer(List<Customer> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteCustomer(String id);

}

