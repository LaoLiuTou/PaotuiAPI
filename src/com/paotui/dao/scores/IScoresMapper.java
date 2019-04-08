package com.paotui.dao.scores;
import java.util.List;
import java.util.Map;
import com.paotui.model.scores.Scores;
	public interface IScoresMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Scores selectscoresById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Scores> selectscoresByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountscoresByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatescores(Scores scores);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addscores(Scores scores);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdscores(List<Scores> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletescores(String id);

}

