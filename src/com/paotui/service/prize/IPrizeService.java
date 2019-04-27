package com.paotui.service.prize;
import java.util.List;
import java.util.Map;
import com.paotui.model.prize.Prize;
public interface IPrizeService {
	/**
	* 通过id选取
	* @return
	*/
	public Prize selectPrizeById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Prize> selectPrizeByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountPrizeByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updatePrize(Prize prize);

	/**
	* 添加 
	* @return
	*/ 
	public int addPrize(Prize prize);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdPrize(List<Prize> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deletePrize(String id);

}

