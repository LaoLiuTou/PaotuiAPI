package com.paotui.dao.orders;
import java.util.List;
import java.util.Map;
import com.paotui.model.orders.Orders;
	public interface IOrdersMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Orders selectordersById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Orders> selectordersByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountordersByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updateorders(Orders orders);
	public  int updateordersBynum(Orders orders);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addorders(Orders orders);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdorders(List<Orders> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deleteorders(String id);

}

