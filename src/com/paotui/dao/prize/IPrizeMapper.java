package com.paotui.dao.prize;
import java.util.List;
import java.util.Map;
import com.paotui.model.prize.Prize;
	public interface IPrizeMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Prize selectprizeById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Prize> selectprizeByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountprizeByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updateprize(Prize prize);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addprize(Prize prize);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdprize(List<Prize> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deleteprize(String id);

}

