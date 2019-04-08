package com.paotui.dao.configure;
import java.util.List;
import java.util.Map;
import com.paotui.model.configure.Configure;
	public interface IConfigureMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Configure selectconfigureById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Configure> selectconfigureByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountconfigureByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updateconfigure(Configure configure);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addconfigure(Configure configure);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdconfigure(List<Configure> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deleteconfigure(String id);

}

