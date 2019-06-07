package com.paotui.service.wxpay;

 
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil; 
import com.paotui.dao.orders.IOrdersMapper;
import com.paotui.model.orders.Orders;
import com.paotui.utils.wxpay.WXConfigUtil;
import com.paotui.utils.wxpay.WxMD5Util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class WXserviceImpl implements WXservice {
	Logger logger = Logger.getLogger("PaotuiLogger");
    public static final String SPBILL_CREATE_IP = "222.168.10.36";
    public static final String NOTIFY_URL = "http://app.dongsheng.club:8888/wxPayNotify";
    public static final String TRADE_TYPE_APP = "APP";
    @Autowired
	private IOrdersMapper iOrdersMapper;

    /**
     * 调用官方SDK 获取预支付订单等参数
     * @param attach 额外参数
     * @param total_fee 总价
     * @return
     * @throws Exception
     */
    public Map<String, String> dounifiedOrder(String attach, String total_fee,String out_trade_no) throws Exception {
        WxMD5Util md5Util = new WxMD5Util();
        Map<String, String> returnMap = new HashMap<String, String>();
        WXConfigUtil config = new WXConfigUtil();
        WXPay wxpay = new WXPay(config);
        Map<String, String> data = new HashMap<String, String>();
        //生成商户订单号，不可重复
        //String out_trade_no = "wxpay" + System.currentTimeMillis();

        data.put("appid", config.getAppID());
        data.put("mch_id", config.getMchID());
        data.put("nonce_str", WXPayUtil.generateNonceStr());
        String body = "订单支付";
        data.put("body", body);
        data.put("out_trade_no", out_trade_no);
        data.put("total_fee", total_fee);
        //自己的服务器IP地址
        data.put("spbill_create_ip", SPBILL_CREATE_IP);
        //异步通知地址（请注意必须是外网）
        data.put("notify_url", NOTIFY_URL);
        //交易类型
        data.put("trade_type", TRADE_TYPE_APP);
        //附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
        data.put("attach", attach);
        String sign1 = md5Util.getSign(data);
        data.put("sign", sign1);

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
        WXConfigUtil config = null;
        try {
            config = new WXConfigUtil();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WXPay wxpay = new WXPay(config);
        String xmlBack = "";
        Map<String, String> notifyMap = null;
        try {
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
	                        	iOrdersMapper.updateordersBynum(orders);
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

}
