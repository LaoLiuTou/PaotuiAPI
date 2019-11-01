package com.paotui.utils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
 
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.paotui.chat.NettyChannelMap;

public class SendChatMessageUtil {

	
	/**
	 * type:T 
	 */
	static Logger logger = Logger.getLogger("PaotuiLogger");
	@SuppressWarnings("rawtypes")
	public static void send(String type,String orderNum,String note){
		
		try {
			//推送
			for (Map.Entry entry:NettyChannelMap.map.entrySet()){
				ChannelHandlerContext channelHandlerContext = (ChannelHandlerContext) entry.getValue();
				Map<String, String> contentMap = new HashMap<String, String>();
				contentMap.put("T", type);
				contentMap.put("NAME", "system");
				contentMap.put("NO", orderNum);
				contentMap.put("FI", entry.getKey().toString()); 
				contentMap.put("NOTE", note);
				ObjectMapper mapper = new ObjectMapper();
				String json = "";
				json = mapper.writeValueAsString(contentMap);
				if(channelHandlerContext!=null){
				   channelHandlerContext.writeAndFlush(new TextWebSocketFrame(json));
			    }
			    
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("推送失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}
