package com.paotui.dao.customer;
import java.util.List;
import java.util.Map;
import com.paotui.model.customer.Customer;
	public interface ICustomerMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Customer selectcustomerById(String id);
	/**
 	* 统计
 	* @return
	 */ 
	 @SuppressWarnings("rawtypes")
		public List<Customer> statisticcustomerByParam(Map paramMap); 
	 /**
	  * 通过查询参数获取信息
	  * @return
	  */ 
	 @SuppressWarnings("rawtypes")
	 public List<Customer> selectcustomerByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountcustomerByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatecustomer(Customer customer);
	/**
	 * 更新 
	 * @return 
	 */ 
	public  int updatecustomerprize(Customer customer);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addcustomer(Customer customer);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdcustomer(List<Customer> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletecustomer(String id);

}

