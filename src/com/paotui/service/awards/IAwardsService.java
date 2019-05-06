package com.paotui.service.awards;
import java.util.List;
import java.util.Map;
import com.paotui.model.awards.Awards;
public interface IAwardsService {
	/**
	* 通过id选取
	* @return
	*/
	public Awards selectAwardsById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Awards> selectAwardsByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountAwardsByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateAwards(Awards awards);

	/**
	* 添加 
	* @return
	*/ 
	public int addAwards(Awards awards);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdAwards(List<Awards> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteAwards(String id);

}

