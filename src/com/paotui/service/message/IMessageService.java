package com.paotui.service.message;
import java.util.List;
import java.util.Map;
import com.paotui.model.message.Message;
public interface IMessageService {
	/**
	* 通过id选取
	* @return
	*/
	public Message selectMessageById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Message> selectMessageByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountMessageByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateMessage(Message message);

	/**
	* 添加 
	* @return
	*/ 
	public int addMessage(Message message);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdMessage(List<Message> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteMessage(String id);

}

