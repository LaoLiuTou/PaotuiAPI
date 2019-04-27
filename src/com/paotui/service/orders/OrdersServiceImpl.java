package com.paotui.service.orders;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.paotui.dao.customer.ICustomerMapper;
import com.paotui.dao.orders.IOrdersMapper;
import com.paotui.model.customer.Customer;
import com.paotui.model.orders.Orders;
public class OrdersServiceImpl  implements IOrdersService {

	@Autowired
	private IOrdersMapper iOrdersMapper;
	@Autowired
	private ICustomerMapper iCustomerMapper;
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		int result=0;
		orders.setPay_dt(new Date());
		//orders.setStatus("0");
		orders.setState(Long.parseLong("0"));
		orders.setOrdernum(sdf.format(new Date())+((int)((Math.random()*9+1)*100000)));
		result=iOrdersMapper.addorders(orders);
		if(result>0){
			Customer customer=iCustomerMapper.selectcustomerById(orders.getCus_id()+"");
			String balance=customer.getBalance();
			Customer temp = new Customer();
			temp.setId(orders.getCus_id());
			DecimalFormat decimalFormat=new DecimalFormat(".0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
			String price=decimalFormat.format(Float.parseFloat(balance)-Float.parseFloat(orders.getPrice()));//format 返回的是字符串
			temp.setBalance(price);
			iCustomerMapper.updatecustomer(temp);
		} 
		
		return result;
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

