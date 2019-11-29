package com.paotui.service.wxpay;
 
import com.paotui.controller.orders.OrdersController.SendThread;
import com.paotui.dao.customer.ICustomerMapper;
import com.paotui.dao.drivers.IDriversMapper;
import com.paotui.dao.orders.IOrdersMapper;
import com.paotui.model.customer.Customer;
import com.paotui.model.drivers.Drivers;
import com.paotui.model.orders.Orders;
import com.paotui.utils.HttpRequestUtil; 
import com.paotui.utils.PayCommonUtil;
import com.paotui.utils.wxpay.IWxPayConfig;
import com.paotui.utils.wxpay.WXPay;
import com.paotui.utils.wxpay.WXPayUtil;  

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class WXserviceImpl implements WXservice {
	Logger logger = Logger.getLogger("PaotuiLogger"); 
    public static final String TRADE_TYPE_APP = "APP";
    SimpleDateFormat sdf2 = new SimpleDateFormat("MM月dd日HH时mm分");
    @Autowired
	private IOrdersMapper iOrdersMapper;
    @Autowired
    private IDriversMapper iDriversMapper;
    @Autowired
    private ICustomerMapper iCustomerMapper;

    /**
     * 调用官方SDK 获取预支付订单等参数
     * @param attach 额外参数
     * @param total_fee 总价
     * @return
     * @throws Exception
     */
    public Map<String, String> dounifiedOrder(String attach, String total_fee,String out_trade_no,
    		String spbill_create_ip) throws Exception {
        
        Map<String, String> returnMap = new HashMap<String, String>();
        String nonceStr=PayCommonUtil.getRandomString(32);
        Map<String, String> data = new HashMap<String, String>();
        //生成商户订单号，不可重复
        //String out_trade_no = "wxpay" + System.currentTimeMillis();
        IWxPayConfig iWxPayConfig = new IWxPayConfig();
        WXPay wxpay = new WXPay(iWxPayConfig); 
        data.put("appid", iWxPayConfig.getAppID());
        data.put("mch_id", iWxPayConfig.getMchID());
        data.put("nonce_str", nonceStr);
        String body = "订单支付";
        data.put("body", body);
        data.put("out_trade_no", out_trade_no);
        data.put("total_fee", total_fee);
        //自己的服务器IP地址
        data.put("spbill_create_ip", spbill_create_ip);
        //异步通知地址（请注意必须是外网）
        data.put("notify_url", iWxPayConfig.getNotify_url());
        //交易类型
        data.put("trade_type", TRADE_TYPE_APP);
        //附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
        data.put("attach", attach);
       
        String paySign = WXPayUtil.generateSignature(data, iWxPayConfig.getKey());

        data.put("sign", paySign);

        try {
            //使用官方API请求预付订单
            Map<String, String> response = wxpay.unifiedOrder(data);
            System.out.println(response);
            String returnCode = response.get("return_code");    //获取返回码
            //若返回码为SUCCESS，则会返回一个result_code,再对该result_code进行判断
            if (returnCode.equals("SUCCESS")) {//主要返回以下5个参数
                String resultCode = response.get("result_code");
                returnMap.put("appid", response.get("appid"));
                returnMap.put("mch_id", response.get("mch_id"));
                returnMap.put("nonce_str", response.get("nonce_str"));
                returnMap.put("sign", response.get("sign"));
                if ("SUCCESS".equals(resultCode)) {//resultCode 为SUCCESS，才会返回prepay_id和trade_type
                    //获取预支付交易回话标志
                    returnMap.put("trade_type", response.get("trade_type"));
                    returnMap.put("prepay_id", response.get("prepay_id"));
                    return returnMap;
                } else {
                    //此时返回没有预付订单的数据
                    return returnMap;
                }
            } else {
                return returnMap;
            }
        } catch (Exception e) {
            System.out.println(e);
            //系统等其他错误的时候
        }
        return returnMap;
    }

    /**
     *
     * @param notifyData 异步通知后的XML数据
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public String payBack(String notifyData) {
    	
        String xmlBack = "";
        Map<String, String> notifyMap = null;
        try {
        	IWxPayConfig iWxPayConfig = new IWxPayConfig();
            WXPay wxpay = new WXPay(iWxPayConfig);
            notifyMap = WXPayUtil.xmlToMap(notifyData);         // 调用官方SDK转换成map类型数据
            if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {//验证签名是否有效，有效则进一步处理

                String return_code = notifyMap.get("return_code");//状态
                String out_trade_no = notifyMap.get("out_trade_no");//商户订单号
                if (return_code.equals("SUCCESS")) {
                    if (out_trade_no != null) {
                        
                    	if(out_trade_no!=null && !out_trade_no.equals("")){
                    		Map paramMap=new HashMap();
            				paramMap.put("status","1");
            				paramMap.put("ordernum",out_trade_no);
            				int totalnumber=iOrdersMapper.selectCountordersByParam(paramMap);
            				if(totalnumber==0){
            					Orders orders = new Orders();
	                        	orders.setOrdernum(out_trade_no);
	                        	orders.setPay_dt(new Date());
	                        	orders.setStatus("1");
	                        	 
	                        	
	                        	int result=0; 
	                    		result=iOrdersMapper.updateordersBynum(orders);
	                    		if(result>0){
	                    			paramMap=new HashMap();
	                    			paramMap.put("fromPage",0);
	                    			paramMap.put("toPage",1);
	                    			paramMap.put("ordernum",out_trade_no);
	                    			List<Orders> list=iOrdersMapper.selectordersByParam(paramMap);
	                    			if(list.size()>0){
	                    				//修改优惠券金额
	                    				Orders tempOrder=list.get(0);
	                    			    Customer customer=iCustomerMapper.selectcustomerById(tempOrder.getCus_id()+"");
	                    			    if(customer!=null){
	                    			    	String balance=customer.getBalance();  
	                    					Customer temp = new Customer();
	                    					temp.setId(customer.getId());
	                    					logger.info("用户:"+customer.getId()+",余额:"+ balance+",使用:"+tempOrder.getBalance());
	                    					DecimalFormat decimalFormat=new DecimalFormat(".0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
	                    					String cus_balance=decimalFormat.format(Float.parseFloat(balance)-Float.parseFloat(tempOrder.getBalance()));//format 返回的是字符串
	                    					temp.setBalance(cus_balance);
	                    					iCustomerMapper.updatecustomer(temp);
	                    			    }
	                    			    
	                    			    
	                    			  //发送短信
	    	                        	paramMap=new HashMap(); 
	    	                        	paramMap.put("fromPage",0);
	    	            				paramMap.put("toPage",1); 
	    	            				paramMap.put("ordernum",out_trade_no);
	    	            				List<Orders> tempList=iOrdersMapper.selectordersByParam(paramMap);
	    	            				if(tempList.size()>0){ 
	    	            					tempOrder =tempList.get(0);
	    		                        	if(tempOrder.getNote()!=null&&tempOrder.getNote().equals("免单")){
	    		        						
	    		        					}
	    		        					else{
	    		        						//Customer customer=iCustomerMapper.selectcustomerById(orders.getCus_id()+"");
	    		        						SendThread sendThread = new SendThread(customer,tempOrder.getDriver()+"",tempOrder.getPrice()); 
	    		        						sendThread.start(); 
	    		        					}
	    	            				}
	    	            				
	                    				
	                    			}
	                    		} 
	                        	  
            				} 
            				
                    		
                    	}
                    	

                        System.err.println("支付成功");

                        logger.info("微信手机支付回调成功订单号:"+ out_trade_no);
                        xmlBack = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    } else {
                        logger.info("微信手机支付回调失败订单号:"+out_trade_no);
                        xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                    }
                }
                return xmlBack;
            } else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                //失败的数据要不要存储？
                logger.error("手机支付回调通知签名错误");
                xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                return xmlBack;
            }
        } catch (Exception e) {
            logger.error("手机支付回调通知失败", e);
            xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        return xmlBack;
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
	    	Drivers drivers=iDriversMapper.selectdriversById(driverId);
			if(drivers!=null){
				String mobile=drivers.getPhone();
				String content="【便民一号线】尊敬的司机"+drivers.getDrivername()+
						"，用户（"+customer.getPhone()+"）已经于"+sdf2.format(new Date())+
						"支付本次乘车费用"+price+"元。";
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
}
