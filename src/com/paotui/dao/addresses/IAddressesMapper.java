package com.paotui.dao.addresses;
import java.util.List;
import java.util.Map;
import com.paotui.model.addresses.Addresses;
	public interface IAddressesMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Addresses selectaddressesById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Addresses> selectaddressesByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountaddressesByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updateaddresses(Addresses addresses);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addaddresses(Addresses addresses);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdaddresses(List<Addresses> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deleteaddresses(String id);

}

