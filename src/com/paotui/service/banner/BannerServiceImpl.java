package com.paotui.service.banner;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.paotui.dao.banner.IBannerMapper;
import com.paotui.model.banner.Banner;
public class BannerServiceImpl  implements IBannerService {

	@Autowired
	private IBannerMapper iBannerMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Banner selectBannerById(String id){
		return iBannerMapper.selectbannerById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Banner> selectBannerByParam(Map paramMap){ 
		return iBannerMapper.selectbannerByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountBannerByParam(Map paramMap){ 
		return iBannerMapper.selectCountbannerByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateBanner(Banner banner){
		return iBannerMapper.updatebanner(banner);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addBanner(Banner banner){
		return iBannerMapper.addbanner(banner);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdBanner(List<Banner> list){
		return iBannerMapper.muladdbanner(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteBanner(String id){
		return iBannerMapper.deletebanner(id);
	}

}

