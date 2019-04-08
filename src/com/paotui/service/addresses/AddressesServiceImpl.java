package com.paotui.service.addresses;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.paotui.dao.addresses.IAddressesMapper;
import com.paotui.model.addresses.Addresses;
public class AddressesServiceImpl  implements IAddressesService {

	@Autowired
	private IAddressesMapper iAddressesMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Addresses selectAddressesById(String id){
		return iAddressesMapper.selectaddressesById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Addresses> selectAddressesByParam(Map paramMap){ 
		return iAddressesMapper.selectaddressesByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountAddressesByParam(Map paramMap){ 
		return iAddressesMapper.selectCountaddressesByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateAddresses(Addresses addresses){
		return iAddressesMapper.updateaddresses(addresses);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addAddresses(Addresses addresses){
		return iAddressesMapper.addaddresses(addresses);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdAddresses(List<Addresses> list){
		return iAddressesMapper.muladdaddresses(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteAddresses(String id){
		return iAddressesMapper.deleteaddresses(id);
	}

}

