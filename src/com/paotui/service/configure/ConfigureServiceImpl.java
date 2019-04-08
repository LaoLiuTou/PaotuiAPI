package com.paotui.service.configure;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.paotui.dao.configure.IConfigureMapper;
import com.paotui.model.configure.Configure;
public class ConfigureServiceImpl  implements IConfigureService {

	@Autowired
	private IConfigureMapper iConfigureMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Configure selectConfigureById(String id){
		return iConfigureMapper.selectconfigureById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Configure> selectConfigureByParam(Map paramMap){ 
		return iConfigureMapper.selectconfigureByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountConfigureByParam(Map paramMap){ 
		return iConfigureMapper.selectCountconfigureByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateConfigure(Configure configure){
		return iConfigureMapper.updateconfigure(configure);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addConfigure(Configure configure){
		return iConfigureMapper.addconfigure(configure);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdConfigure(List<Configure> list){
		return iConfigureMapper.muladdconfigure(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteConfigure(String id){
		return iConfigureMapper.deleteconfigure(id);
	}

}

