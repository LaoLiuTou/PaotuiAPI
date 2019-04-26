package com.paotui.service.orderspt;
import java.util.List;
import java.util.Map;
import com.paotui.model.orderspt.Orderspt;
public interface IOrdersptService {
	/**
	* 通过id选取
	* @return
	*/
	public Orderspt selectOrdersptById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Orderspt> selectOrdersptByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountOrdersptByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateOrderspt(Orderspt orderspt);

	/**
	* 添加 
	* @return
	*/ 
	public int addOrderspt(Orderspt orderspt);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdOrderspt(List<Orderspt> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteOrderspt(String id);

}

