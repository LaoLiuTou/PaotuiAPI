package com.paotui.service.addresses;
import java.util.List;
import java.util.Map;
import com.paotui.model.addresses.Addresses;
public interface IAddressesService {
	/**
	* 通过id选取
	* @return
	*/
	public Addresses selectAddressesById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Addresses> selectAddressesByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountAddressesByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateAddresses(Addresses addresses);

	/**
	* 添加 
	* @return
	*/ 
	public int addAddresses(Addresses addresses);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdAddresses(List<Addresses> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteAddresses(String id);

}

