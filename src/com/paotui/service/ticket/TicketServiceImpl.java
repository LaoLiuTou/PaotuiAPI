package com.paotui.service.ticket;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.paotui.dao.ticket.ITicketMapper;
import com.paotui.model.ticket.Ticket;
public class TicketServiceImpl  implements ITicketService {

	@Autowired
	private ITicketMapper iTicketMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Ticket selectTicketById(String id){
		return iTicketMapper.selectticketById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Ticket> selectTicketByParam(Map paramMap){ 
		return iTicketMapper.selectticketByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountTicketByParam(Map paramMap){ 
		return iTicketMapper.selectCountticketByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateTicket(Ticket ticket){
		return iTicketMapper.updateticket(ticket);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addTicket(Ticket ticket){
		int result=0;
		ticket.setState(Long.parseLong("0"));
		result=iTicketMapper.addticket(ticket);
		return result;
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdTicket(List<Ticket> list){
		return iTicketMapper.muladdticket(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteTicket(String id){
		return iTicketMapper.deleteticket(id);
	}

}

