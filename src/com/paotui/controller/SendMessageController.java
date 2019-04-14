package com.paotui.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paotui.utils.HttpRequestUtil;

@Controller
public class SendMessageController {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("PaotuiLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/sendMessage")
	@ResponseBody
	public Map send(HttpServletRequest request){
		Map resultMap=new HashMap();
		try {
			String mobile=request.getParameter("mobile");
			String code=request.getParameter("code");
			String content="【便民一号线】您的验证码是"+code+"，在5分钟内有效。如非本人操作请忽略本短信。";
			String url="http://sms.37037.com/sms.aspx";
			String param="action=send&userid=8609&account=huili&password=huili123&mobile="+mobile+"&content="+content+"&sendTime=&checkcontent=1";
			//http://sms.37037.com/sms.aspx?action=send&userid=8609&account=huili&password=huili123&mobile=13321419193&content=测试&sendTime=&checkcontent=1
			//发送 POST 请求
			String result=HttpRequestUtil.sendPostRequest(url,param);
			String returnStr="Faild";
			Pattern p=Pattern.compile("<returnstatus>(\\w+)</returnstatus>");
		    Matcher m=p.matcher(result);
		    while(m.find()){
		    	returnStr=m.group(1);
		    }
		    if(returnStr.equals("Success")){
		    	resultMap.put("status", "0");
				resultMap.put("msg", "发送成功！");
				logger.info("发送成功，号码："+mobile);
		    }
		    else{
		    	resultMap.put("status", "-1");
				resultMap.put("msg", "发送失败！");
				logger.info("发送失败，号码："+mobile);
		    }
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "发送失败！");
			logger.info("发送失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	
	public static void main(String[] args){
	    String str = "abc<icon>def</icon>deftfh<icon>a</icon>";
	 
	    Pattern p=Pattern.compile("<icon>(\\w+)</icon>");
	    Matcher m=p.matcher(str);
	    while(m.find()){
	        System.out.println(m.group(1));
	        
	    }
	     
	 
	}
}
