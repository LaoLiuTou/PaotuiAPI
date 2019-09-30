package com.paotui.service.goods;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.paotui.dao.goods.IGoodsMapper;
import com.paotui.model.goods.Goods;
public class GoodsServiceImpl  implements IGoodsService {

	@Autowired
	private IGoodsMapper iGoodsMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Goods selectGoodsById(String id){
		return iGoodsMapper.selectgoodsById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Goods> selectGoodsByParam(Map paramMap){ 
		return iGoodsMapper.selectgoodsByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountGoodsByParam(Map paramMap){ 
		return iGoodsMapper.selectCountgoodsByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateGoods(Goods goods){
		return iGoodsMapper.updategoods(goods);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addGoods(Goods goods){
		return iGoodsMapper.addgoods(goods);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdGoods(List<Goods> list){
		return iGoodsMapper.muladdgoods(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteGoods(String id){
		return iGoodsMapper.deletegoods(id);
	}

}

