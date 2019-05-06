package com.paotui.dao.awards;
import java.util.List;
import java.util.Map;
import com.paotui.model.awards.Awards;
	public interface IAwardsMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Awards selectawardsById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Awards> selectawardsByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountawardsByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updateawards(Awards awards);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addawards(Awards awards);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdawards(List<Awards> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deleteawards(String id);

}

