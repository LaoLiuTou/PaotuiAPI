package com.paotui.service.news;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.paotui.dao.news.INewsMapper;
import com.paotui.model.news.News;
public class NewsServiceImpl  implements INewsService {

	@Autowired
	private INewsMapper iNewsMapper;
	/**
	* 通过id选取
	* @return
	*/
	public News selectNewsById(String id){
		return iNewsMapper.selectnewsById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<News> selectNewsByParam(Map paramMap){ 
		return iNewsMapper.selectnewsByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountNewsByParam(Map paramMap){ 
		return iNewsMapper.selectCountnewsByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateNews(News news){
		return iNewsMapper.updatenews(news);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addNews(News news){
		return iNewsMapper.addnews(news);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdNews(List<News> list){
		return iNewsMapper.muladdnews(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteNews(String id){
		return iNewsMapper.deletenews(id);
	}

}

