package com.paotui.controller.wxpay;

 
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.paotui.service.wxpay.WXserviceImpl;
import com.paotui.utils.wxpay.WxMD5Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WXController {

    @Autowired
    private WXserviceImpl wxPayService;
    Logger logger = Logger.getLogger("PaotuiLogger");
    /**
     * 统一下单
     * 官方文档:https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1
     * @param user_id
     * @param total_fee
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/wxPay")
	@ResponseBody
    public Map  wxPay(@RequestParam(value = "userId") String user_id,
                              @RequestParam(value = "totalFee") String total_fee,
                              @RequestParam(value = "out_trade_no") String out_trade_no)  {
    	Map resultMap=new HashMap();
        try {
			String attach = "{\"user_id\":\"" + user_id + "\"}";
			//请求预支付订单
			Map<String, String> result = wxPayService.dounifiedOrder(attach, total_fee,out_trade_no);
			Map temp=new HashMap();

			WxMD5Util md5Util = new WxMD5Util();
			//返回APP端的数据
			//参加调起支付的签名字段有且只能是6个，分别为appid、partnerid、prepayid、package、noncestr和timestamp，而且都必须是小写
			temp.put("appid", result.get("appid"));
			temp.put("partnerid", result.get("mch_id"));
			temp.put("prepayid", result.get("prepay_id"));
			temp.put("package", "Sign=WXPay");
			temp.put("noncestr", result.get("nonce_str"));
			temp.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));//单位为秒
			temp.put("sign", md5Util.getSign(temp));
			temp.put("extdata", attach);
			
			resultMap.put("status", "0");
			resultMap.put("msg", temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			resultMap.put("status", "-1");
			resultMap.put("msg", "请求支付失败！");
			e.printStackTrace();
		}
        return resultMap;
    }

    /**
     *   支付异步结果通知，我们在请求预支付订单时传入的地址
     *   官方文档 ：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_7&index=3
     */
    @RequestMapping("/wxPayNotify")
	@ResponseBody
    public String wxPayNotify(HttpServletRequest request, HttpServletResponse response) {
        String resXml = "";
        try {
            InputStream inputStream = request.getInputStream();
            //将InputStream转换成xmlString
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            resXml = sb.toString();
            String result = wxPayService.payBack(resXml);
            logger.info("异步通知："+result);
            return result;
        } catch (Exception e) {
            System.out.println("微信手机支付失败:" + e.getMessage());
            String result = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            return result;
        }
    }
}
