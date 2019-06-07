package com.paotui.service.orders;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.paotui.dao.configure.IConfigureMapper;
import com.paotui.dao.customer.ICustomerMapper;
import com.paotui.dao.orders.IOrdersMapper;
import com.paotui.model.configure.Configure;
import com.paotui.model.customer.Customer;
import com.paotui.model.orders.Orders;
public class OrdersServiceImpl  implements IOrdersService {

	@Autowired
	private IOrdersMapper iOrdersMapper;
	@Autowired
	private ICustomerMapper iCustomerMapper;
	@Autowired
	private IConfigureMapper iConfigureMapper;
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
	@Transactional
	public  int updateordersBynum(Orders orders){
		return iOrdersMapper.updateordersBynum(orders);
	}

	/**
	* 添加 
	* @return
	*/ 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public  int addOrders(Orders orders){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		int result=0;
		orders.setPay_dt(new Date());
		//orders.setStatus("0");
		orders.setState(Long.parseLong("0"));
		orders.setPrice("5");
		
		
		Customer customer=iCustomerMapper.selectcustomerById(orders.getCus_id()+"");
		String balance=customer.getBalance();
		if(orders.getNote().equals("免单")){
			orders.setStatus("1");
		}
		else{
			String sigelPay="0";
			Map paramMap=new HashMap();
			paramMap.put("fromPage",0);
			paramMap.put("toPage",1); 
			paramMap.put("property","price");
			List<Configure> list=iConfigureMapper.selectconfigureByParam(paramMap);
			if(list.size()>0){
				sigelPay=list.get(0).getValue();
			}
			if(Float.parseFloat(balance)>=Float.parseFloat(sigelPay)){
				orders.setBalance(sigelPay);
			}
			else{
				orders.setBalance("0");
			}
		}
		
		orders.setOrdernum(sdf.format(new Date())+((int)((Math.random()*9+1)*100000)));
		result=iOrdersMapper.addorders(orders);
		if(result>0){
			 
			Customer temp = new Customer();
			temp.setId(orders.getCus_id());
			DecimalFormat decimalFormat=new DecimalFormat(".0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
			String cus_balance=decimalFormat.format(Float.parseFloat(balance)-Float.parseFloat(orders.getBalance()));//format 返回的是字符串
			temp.setBalance(cus_balance);
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

