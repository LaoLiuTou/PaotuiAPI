package com.paotui.service.configure;
import java.util.List;
import java.util.Map;
import com.paotui.model.configure.Configure;
public interface IConfigureService {
	/**
	* 通过id选取
	* @return
	*/
	public Configure selectConfigureById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Configure> selectConfigureByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountConfigureByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateConfigure(Configure configure);

	/**
	* 添加 
	* @return
	*/ 
	public int addConfigure(Configure configure);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdConfigure(List<Configure> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteConfigure(String id);

}

