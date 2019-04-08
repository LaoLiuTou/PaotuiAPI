package com.paotui.service.message;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.paotui.dao.message.IMessageMapper;
import com.paotui.model.message.Message;
public class MessageServiceImpl  implements IMessageService {

	@Autowired
	private IMessageMapper iMessageMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Message selectMessageById(String id){
		return iMessageMapper.selectmessageById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Message> selectMessageByParam(Map paramMap){ 
		return iMessageMapper.selectmessageByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountMessageByParam(Map paramMap){ 
		return iMessageMapper.selectCountmessageByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateMessage(Message message){
		return iMessageMapper.updatemessage(message);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addMessage(Message message){
		return iMessageMapper.addmessage(message);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdMessage(List<Message> list){
		return iMessageMapper.muladdmessage(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteMessage(String id){
		return iMessageMapper.deletemessage(id);
	}

}

