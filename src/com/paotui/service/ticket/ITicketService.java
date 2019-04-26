package com.paotui.service.ticket;
import java.util.List;
import java.util.Map;
import com.paotui.model.ticket.Ticket;
public interface ITicketService {
	/**
	* 通过id选取
	* @return
	*/
	public Ticket selectTicketById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Ticket> selectTicketByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountTicketByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateTicket(Ticket ticket);

	/**
	* 添加 
	* @return
	*/ 
	public int addTicket(Ticket ticket);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdTicket(List<Ticket> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteTicket(String id);

}

