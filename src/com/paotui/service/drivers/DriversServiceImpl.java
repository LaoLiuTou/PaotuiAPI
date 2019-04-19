package com.paotui.service.drivers;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.paotui.dao.drivers.IDriversMapper;
import com.paotui.model.drivers.Drivers;
public class DriversServiceImpl  implements IDriversService {

	@Autowired
	private IDriversMapper iDriversMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Drivers selectDriversById(String id){
		return iDriversMapper.selectdriversById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Drivers> selectDriversByParam(Map paramMap){ 
		return iDriversMapper.selectdriversByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountDriversByParam(Map paramMap){ 
		return iDriversMapper.selectCountdriversByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateDrivers(Drivers drivers){
		return iDriversMapper.updatedrivers(drivers);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addDrivers(Drivers drivers){
		return iDriversMapper.adddrivers(drivers);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdDrivers(List<Drivers> list){
		return iDriversMapper.muladddrivers(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteDrivers(String id){
		return iDriversMapper.deletedrivers(id);
	}

}

