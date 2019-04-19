package com.paotui.dao.installinfo;
import java.util.List;
import java.util.Map;
import com.paotui.model.installinfo.Installinfo;
	public interface IInstallinfoMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Installinfo selectinstallinfoById(String id);
	/**
	 * 统计
	 * @return
	 */ 
	@SuppressWarnings("rawtypes")
	public List<Installinfo> statisticinstallinfoByParam(Map paramMap); 
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Installinfo> selectinstallinfoByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountinstallinfoByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updateinstallinfo(Installinfo installinfo);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addinstallinfo(Installinfo installinfo);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdinstallinfo(List<Installinfo> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deleteinstallinfo(String id);

}

