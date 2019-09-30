package com.paotui.dao.goods;
import java.util.List;
import java.util.Map;
import com.paotui.model.goods.Goods;
	public interface IGoodsMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Goods selectgoodsById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Goods> selectgoodsByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountgoodsByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updategoods(Goods goods);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addgoods(Goods goods);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdgoods(List<Goods> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletegoods(String id);

}

