package com.paotui.service.wxpay;

import java.util.Map;

public interface WXservice {
    Map<String, String> dounifiedOrder(String attach, String total_fee,String out_trade_no,String spbill_create_ip) throws Exception;
    String payBack(String notifyData);
}
