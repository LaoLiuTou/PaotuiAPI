package com.paotui.service.orders;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.paotui.dao.orders.IOrdersMapper;
import com.paotui.model.orders.Orders;
public class OrdersServiceImpl  implements IOrdersService {

	@Autowired
	private IOrdersMapper iOrdersMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Orders selectOrdersById(String id){
		return iOrdersMapper.selectordersById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Orders> selectOrdersByParam(Map paramMap){ 
		return iOrdersMapper.selectordersByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountOrdersByParam(Map paramMap){ 
		return iOrdersMapper.selectCountordersByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateOrders(Orders orders){
		return iOrdersMapper.updateorders(orders);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addOrders(Orders orders){
		return iOrdersMapper.addorders(orders);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdOrders(List<Orders> list){
		return iOrdersMapper.muladdorders(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteOrders(String id){
		return iOrdersMapper.deleteorders(id);
	}

}

