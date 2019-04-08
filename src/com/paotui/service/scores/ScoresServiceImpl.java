package com.paotui.service.scores;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.paotui.dao.scores.IScoresMapper;
import com.paotui.model.scores.Scores;
public class ScoresServiceImpl  implements IScoresService {

	@Autowired
	private IScoresMapper iScoresMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Scores selectScoresById(String id){
		return iScoresMapper.selectscoresById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Scores> selectScoresByParam(Map paramMap){ 
		return iScoresMapper.selectscoresByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountScoresByParam(Map paramMap){ 
		return iScoresMapper.selectCountscoresByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateScores(Scores scores){
		return iScoresMapper.updatescores(scores);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addScores(Scores scores){
		return iScoresMapper.addscores(scores);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdScores(List<Scores> list){
		return iScoresMapper.muladdscores(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteScores(String id){
		return iScoresMapper.deletescores(id);
	}

}

