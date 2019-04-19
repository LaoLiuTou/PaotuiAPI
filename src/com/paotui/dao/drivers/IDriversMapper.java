package com.paotui.dao.drivers;
import java.util.List;
import java.util.Map;
import com.paotui.model.drivers.Drivers;
	public interface IDriversMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Drivers selectdriversById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Drivers> selectdriversByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountdriversByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatedrivers(Drivers drivers);
	/**
 	* 添加 
 	* @return
 */ 
	public  int adddrivers(Drivers drivers);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladddrivers(List<Drivers> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletedrivers(String id);

}

