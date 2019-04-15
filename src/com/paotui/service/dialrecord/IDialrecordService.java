package com.paotui.service.dialrecord;
import java.util.List;
import java.util.Map;
import com.paotui.model.dialrecord.Dialrecord;
public interface IDialrecordService {
	/**
	* 通过id选取
	* @return
	*/
	public Dialrecord selectDialrecordById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Dialrecord> selectDialrecordByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountDialrecordByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateDialrecord(Dialrecord dialrecord);

	/**
	* 添加 
	* @return
	*/ 
	public int addDialrecord(Dialrecord dialrecord);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdDialrecord(List<Dialrecord> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteDialrecord(String id);

}

