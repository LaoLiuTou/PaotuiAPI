package com.paotui.dao.ticket;
import java.util.List;
import java.util.Map;
import com.paotui.model.ticket.Ticket;
	public interface ITicketMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Ticket selectticketById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Ticket> selectticketByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountticketByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updateticket(Ticket ticket);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addticket(Ticket ticket);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdticket(List<Ticket> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deleteticket(String id);

}

