package com.paotui.service.installinfo;
import java.util.List;
import java.util.Map;
import com.paotui.model.installinfo.Installinfo;
public interface IInstallinfoService {
	/**
	* 通过id选取
	* @return
	*/
	public Installinfo selectInstallinfoById(String id);

	/**
	 * 统计
	 * @return
	 */ 
	@SuppressWarnings("rawtypes")
	public List<Installinfo> statisticInstallinfoByParam(Map paramMap); 
	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Installinfo> selectInstallinfoByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountInstallinfoByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateInstallinfo(Installinfo installinfo);

	/**
	* 添加 
	* @return
	*/ 
	public int addInstallinfo(Installinfo installinfo);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdInstallinfo(List<Installinfo> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteInstallinfo(String id);

}

