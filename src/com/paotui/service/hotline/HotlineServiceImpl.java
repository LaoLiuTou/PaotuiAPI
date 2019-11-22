package com.paotui.service.hotline;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.paotui.dao.hotline.IHotlineMapper;
import com.paotui.model.hotline.Hotline;
public class HotlineServiceImpl  implements IHotlineService {

	@Autowired
	private IHotlineMapper iHotlineMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Hotline selectHotlineById(String id){
		return iHotlineMapper.selecthotlineById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Hotline> selectHotlineByParam(Map paramMap){ 
		return iHotlineMapper.selecthotlineByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountHotlineByParam(Map paramMap){ 
		return iHotlineMapper.selectCounthotlineByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateHotline(Hotline hotline){
		return iHotlineMapper.updatehotline(hotline);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addHotline(Hotline hotline){
		return iHotlineMapper.addhotline(hotline);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdHotline(List<Hotline> list){
		return iHotlineMapper.muladdhotline(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteHotline(String id){
		return iHotlineMapper.deletehotline(id);
	}

}

