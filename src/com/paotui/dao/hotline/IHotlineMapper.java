package com.paotui.dao.hotline;
import java.util.List;
import java.util.Map;
import com.paotui.model.hotline.Hotline;
	public interface IHotlineMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Hotline selecthotlineById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Hotline> selecthotlineByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCounthotlineByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatehotline(Hotline hotline);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addhotline(Hotline hotline);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdhotline(List<Hotline> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletehotline(String id);

}

