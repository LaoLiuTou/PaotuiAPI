package com.paotui.service.wxpay;

import java.util.Map;

public interface WXservice {
    Map<String, String> dounifiedOrder(String attach, String total_fee,String out_trade_no) throws Exception;
    String payBack(String notifyData);
}
