package com.paotui.service.scores;
import java.util.List;
import java.util.Map;
import com.paotui.model.scores.Scores;
public interface IScoresService {
	/**
	* 通过id选取
	* @return
	*/
	public Scores selectScoresById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Scores> selectScoresByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountScoresByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateScores(Scores scores);

	/**
	* 添加 
	* @return
	*/ 
	public int addScores(Scores scores);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdScores(List<Scores> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteScores(String id);

}

