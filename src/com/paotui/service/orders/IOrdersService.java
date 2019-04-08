package com.paotui.service.orders;
import java.util.List;
import java.util.Map;
import com.paotui.model.orders.Orders;
public interface IOrdersService {
	/**
	* 通过id选取
	* @return
	*/
	public Orders selectOrdersById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Orders> selectOrdersByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountOrdersByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateOrders(Orders orders);

	/**
	* 添加 
	* @return
	*/ 
	public int addOrders(Orders orders);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdOrders(List<Orders> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteOrders(String id);

}

