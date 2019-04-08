package com.paotui.dao.message;
import java.util.List;
import java.util.Map;
import com.paotui.model.message.Message;
	public interface IMessageMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Message selectmessageById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Message> selectmessageByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountmessageByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatemessage(Message message);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addmessage(Message message);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdmessage(List<Message> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletemessage(String id);

}

