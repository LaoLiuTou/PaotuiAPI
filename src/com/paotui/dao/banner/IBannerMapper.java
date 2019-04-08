package com.paotui.dao.banner;
import java.util.List;
import java.util.Map;
import com.paotui.model.banner.Banner;
	public interface IBannerMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Banner selectbannerById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Banner> selectbannerByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountbannerByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatebanner(Banner banner);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addbanner(Banner banner);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdbanner(List<Banner> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletebanner(String id);

}

