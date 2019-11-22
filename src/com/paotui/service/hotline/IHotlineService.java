package com.paotui.service.hotline;
import java.util.List;
import java.util.Map;
import com.paotui.model.hotline.Hotline;
public interface IHotlineService {
	/**
	* 通过id选取
	* @return
	*/
	public Hotline selectHotlineById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Hotline> selectHotlineByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountHotlineByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateHotline(Hotline hotline);

	/**
	* 添加 
	* @return
	*/ 
	public int addHotline(Hotline hotline);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdHotline(List<Hotline> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteHotline(String id);

}

