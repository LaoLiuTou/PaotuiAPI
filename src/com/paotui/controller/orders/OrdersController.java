package com.paotui.controller.orders;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paotui.service.customer.ICustomerService;
import com.paotui.service.drivers.IDriversService;
import com.paotui.service.orders.IOrdersService;
import com.paotui.utils.HttpRequestUtil;
import com.paotui.model.customer.Customer;
import com.paotui.model.drivers.Drivers;
import com.paotui.model.orders.Orders;
@Controller
public class OrdersController {
	@Autowired
	private IOrdersService iOrdersService;
	@Autowired
	private ICustomerService iCustomerService;
	@Autowired
	private IDriversService iDriversService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("MM月dd日HH时mm分");
	Logger logger = Logger.getLogger("PaotuiLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addOrders")
	@ResponseBody
	public Map add(Orders orders){
		Map resultMap=new HashMap();
		try {
			if(orders.getCus_id()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "用户ID不能为空！");
			}
			/*else if(orders.getPrice()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "金额不能为空！");
			}*/
			else{
				Customer customer=iCustomerService.selectCustomerById(orders.getCus_id()+"");
				//String balance=customer.getBalance();
				//if(Float.parseFloat(balance)>=Float.parseFloat(orders.getPrice())){
					iOrdersService.addOrders(orders);
					resultMap.put("status", "0");
					resultMap.put("msg", orders.getId());
					logger.info("新建成功，主键："+orders.getId());
					if(orders.getNote()!=null&&orders.getNote().equals("免单")){
						
					}
					else{
						SendThread sendThread = new SendThread(customer,orders.getDriver()+"",orders.getPrice()); 
						sendThread.start(); 
					}
					     
					
				/*}
				else{
					resultMap.put("status", "-1");
					resultMap.put("msg", "您的余额已不足！");
				}*/
				
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	public class SendThread extends Thread{
		private Customer customer;
		private String driverId;
		private String price;
	    public SendThread(Customer customer,String driverId,String price){
	    	this.customer=customer;
	    	this.driverId=driverId;
	    	this.price=price;
	    }
	    public void run(){
	    	Drivers drivers=iDriversService.selectDriversById(driverId);
			if(drivers!=null){
				String mobile=drivers.getPhone();
				String content="【便民一号线】尊敬的司机"+drivers.getDrivername()+
						"，用户（"+customer.getPhone()+"）已经于"+sdf2.format(new Date())+
						"使用"+price+"元代金券支付本次乘车费用。";
				sendSms(mobile,content);
			}
	    }
	 
	}
 
	public void sendSms(String mobile,String content){
		try {
			
			String url="http://sms.37037.com/sms.aspx";
			String param="action=send&userid=8609&account=huili&password=huili123&mobile="+mobile+"&content="+content+"&sendTime=&checkcontent=1";
			//发送 POST 请求
			System.out.println(url+param);
			String result=HttpRequestUtil.sendPostRequest(url,param);
			String returnStr="Faild";
			Pattern p=Pattern.compile("<returnstatus>(\\w+)</returnstatus>");
		    Matcher m=p.matcher(result);
		    while(m.find()){
		    	returnStr=m.group(1);
		    }
		    if(returnStr.equals("Success")){
				logger.info("发送成功，号码："+mobile);
		    }
		    else{ 
				logger.info("发送失败，号码："+mobile);
		    }
		} catch (Exception e) { 
			logger.info("发送失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdOrders")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Orders orders){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Orders.class);
			List<Orders> list = (List<Orders>)objectMapper.readValue(data, javaType);
			iOrdersService.muladdOrders(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+orders.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteOrders")
	@ResponseBody
	public Map delete(Orders orders){
		Map resultMap=new HashMap();
		try {
			if(orders.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iOrdersService.deleteOrders(orders.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+orders.getId());
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "删除失败！");
			logger.info("删除失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/selectOrders")
	@ResponseBody
	public Map select(Orders orders){
		Map resultMap=new HashMap();
		try {
			if(orders.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Orders resultSelect=iOrdersService.selectOrdersById(orders.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", resultSelect);
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "查询失败！");
			logger.info("查询失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/updateOrders")
	@ResponseBody
	public Map update(Orders orders){
		Map resultMap=new HashMap();
		try {
			if(orders.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iOrdersService.updateOrders(orders);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+orders.getId());
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "更新失败！");
			logger.info("更新失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/listOrders")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Orders orders)
		throws ServletException, IOException {
		Map resultMap=new HashMap();
		try {
			String page=request.getParameter("page");
			String size=request.getParameter("size");
			if(page!=null&&size!=null){
				Map paramMap=new HashMap();
				paramMap.put("fromPage",(Integer.parseInt(page)-1)*Integer.parseInt(size));
				paramMap.put("toPage",Integer.parseInt(size)); 
				paramMap.put("orderBy","a.ID DESC"); 
				paramMap.put("id",orders.getId());
				paramMap.put("cus_id",orders.getCus_id());
				paramMap.put("price",orders.getPrice());
				paramMap.put("driver",orders.getDriver());
				paramMap.put("note",orders.getNote());
				String c_dtFrom=request.getParameter("c_dtFrom");
				String c_dtTo=request.getParameter("c_dtTo");
				if(c_dtFrom!=null&&!c_dtFrom.equals(""))
				paramMap.put("c_dtFrom", sdf.parse(c_dtFrom));
				if(c_dtTo!=null&&!c_dtTo.equals(""))
				paramMap.put("c_dtTo", sdf.parse(c_dtTo));
				String pay_dtFrom=request.getParameter("pay_dtFrom");
				String pay_dtTo=request.getParameter("pay_dtTo");
				if(pay_dtFrom!=null&&!pay_dtFrom.equals(""))
				paramMap.put("pay_dtFrom", sdf.parse(pay_dtFrom));
				if(pay_dtTo!=null&&!pay_dtTo.equals(""))
				paramMap.put("pay_dtTo", sdf.parse(pay_dtTo));
				String u_dtFrom=request.getParameter("u_dtFrom");
				String u_dtTo=request.getParameter("u_dtTo");
				if(u_dtFrom!=null&&!u_dtFrom.equals(""))
				paramMap.put("u_dtFrom", sdf.parse(u_dtFrom));
				if(u_dtTo!=null&&!u_dtTo.equals(""))
				paramMap.put("u_dtTo", sdf.parse(u_dtTo));
				paramMap.put("status",orders.getStatus());
				paramMap.put("state",orders.getState());
				paramMap.put("balance",orders.getBalance());
				paramMap.put("ordernum",orders.getOrdernum());
				String searchText=request.getParameter("searchText");
				if(searchText!=null&&!searchText.equals(""))
				paramMap.put("searchText",searchText);
				List<Orders> list=iOrdersService.selectOrdersByParam(paramMap);
				int totalnumber=iOrdersService.selectCountOrdersByParam(paramMap);
				Map tempMap=new HashMap();
				resultMap.put("status", "0");
				tempMap.put("num", totalnumber);
				tempMap.put("data", list);
				resultMap.put("msg", tempMap);
			}
			else{
				resultMap.put("status", "-1");
				resultMap.put("msg", "分页参数不能为空！");
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "查询失败！");
			logger.info("查询失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
}
