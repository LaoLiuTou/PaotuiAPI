package com.paotui.dao.orderspt;
import java.util.List;
import java.util.Map;
import com.paotui.model.orderspt.Orderspt;
	public interface IOrdersptMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Orderspt selectordersptById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Orderspt> selectordersptByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountordersptByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updateorderspt(Orderspt orderspt);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addorderspt(Orderspt orderspt);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdorderspt(List<Orderspt> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deleteorderspt(String id);

}

