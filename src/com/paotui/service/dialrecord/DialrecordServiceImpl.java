package com.paotui.service.dialrecord;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.paotui.dao.dialrecord.IDialrecordMapper;
import com.paotui.model.dialrecord.Dialrecord;
public class DialrecordServiceImpl  implements IDialrecordService {

	@Autowired
	private IDialrecordMapper iDialrecordMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Dialrecord selectDialrecordById(String id){
		return iDialrecordMapper.selectdialrecordById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Dialrecord> selectDialrecordByParam(Map paramMap){ 
		return iDialrecordMapper.selectdialrecordByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountDialrecordByParam(Map paramMap){ 
		return iDialrecordMapper.selectCountdialrecordByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateDialrecord(Dialrecord dialrecord){
		return iDialrecordMapper.updatedialrecord(dialrecord);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addDialrecord(Dialrecord dialrecord){
		return iDialrecordMapper.adddialrecord(dialrecord);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdDialrecord(List<Dialrecord> list){
		return iDialrecordMapper.muladddialrecord(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteDialrecord(String id){
		return iDialrecordMapper.deletedialrecord(id);
	}

}

