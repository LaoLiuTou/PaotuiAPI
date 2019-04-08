package com.paotui.service.customer;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.paotui.dao.customer.ICustomerMapper;
import com.paotui.model.customer.Customer;
public class CustomerServiceImpl  implements ICustomerService {

	@Autowired
	private ICustomerMapper iCustomerMapper;
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
	@Transactional
	public  int addCustomer(Customer customer){
		return iCustomerMapper.addcustomer(customer);
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

