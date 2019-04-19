package com.paotui.service.drivers;
import java.util.List;
import java.util.Map;
import com.paotui.model.drivers.Drivers;
public interface IDriversService {
	/**
	* 通过id选取
	* @return
	*/
	public Drivers selectDriversById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Drivers> selectDriversByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountDriversByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateDrivers(Drivers drivers);

	/**
	* 添加 
	* @return
	*/ 
	public int addDrivers(Drivers drivers);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdDrivers(List<Drivers> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteDrivers(String id);

}

