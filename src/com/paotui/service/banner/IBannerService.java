package com.paotui.service.banner;
import java.util.List;
import java.util.Map;
import com.paotui.model.banner.Banner;
public interface IBannerService {
	/**
	* 通过id选取
	* @return
	*/
	public Banner selectBannerById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Banner> selectBannerByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountBannerByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateBanner(Banner banner);

	/**
	* 添加 
	* @return
	*/ 
	public int addBanner(Banner banner);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdBanner(List<Banner> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteBanner(String id);

}

