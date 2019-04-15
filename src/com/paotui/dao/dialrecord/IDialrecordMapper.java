package com.paotui.dao.dialrecord;
import java.util.List;
import java.util.Map;
import com.paotui.model.dialrecord.Dialrecord;
	public interface IDialrecordMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Dialrecord selectdialrecordById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Dialrecord> selectdialrecordByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountdialrecordByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatedialrecord(Dialrecord dialrecord);
	/**
 	* 添加 
 	* @return
 */ 
	public  int adddialrecord(Dialrecord dialrecord);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladddialrecord(List<Dialrecord> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletedialrecord(String id);

}

