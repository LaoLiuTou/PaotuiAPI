package com.paotui.service.orderspt;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.paotui.dao.orderspt.IOrdersptMapper;
import com.paotui.model.customer.Customer;
import com.paotui.model.orderspt.Orderspt;
public class OrdersptServiceImpl  implements IOrdersptService {

	@Autowired
	private IOrdersptMapper iOrdersptMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Orderspt selectOrdersptById(String id){
		return iOrdersptMapper.selectordersptById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Orderspt> selectOrdersptByParam(Map paramMap){ 
		return iOrdersptMapper.selectordersptByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountOrdersptByParam(Map paramMap){ 
		return iOrdersptMapper.selectCountordersptByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateOrderspt(Orderspt orderspt){
		return iOrdersptMapper.updateorderspt(orderspt);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addOrderspt(Orderspt orderspt){
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int result=0;
		orderspt.setStatus("0");
		orderspt.setState(Long.parseLong("0"));
		orderspt.setOrdernum(sdf.format(new Date())+((int)((Math.random()*9+1)*100000)));
		result=iOrdersptMapper.addorderspt(orderspt);
		return result;
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdOrderspt(List<Orderspt> list){
		return iOrdersptMapper.muladdorderspt(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteOrderspt(String id){
		return iOrdersptMapper.deleteorderspt(id);
	}

}

