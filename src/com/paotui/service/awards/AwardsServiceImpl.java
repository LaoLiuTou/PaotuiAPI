package com.paotui.service.awards;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.paotui.dao.awards.IAwardsMapper;
import com.paotui.model.awards.Awards;
public class AwardsServiceImpl  implements IAwardsService {

	@Autowired
	private IAwardsMapper iAwardsMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Awards selectAwardsById(String id){
		return iAwardsMapper.selectawardsById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Awards> selectAwardsByParam(Map paramMap){ 
		return iAwardsMapper.selectawardsByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountAwardsByParam(Map paramMap){ 
		return iAwardsMapper.selectCountawardsByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateAwards(Awards awards){
		return iAwardsMapper.updateawards(awards);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addAwards(Awards awards){
		return iAwardsMapper.addawards(awards);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdAwards(List<Awards> list){
		return iAwardsMapper.muladdawards(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteAwards(String id){
		return iAwardsMapper.deleteawards(id);
	}

}

