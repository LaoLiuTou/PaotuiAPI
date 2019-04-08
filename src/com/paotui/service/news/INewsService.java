package com.paotui.service.news;
import java.util.List;
import java.util.Map;
import com.paotui.model.news.News;
public interface INewsService {
	/**
	* 通过id选取
	* @return
	*/
	public News selectNewsById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<News> selectNewsByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountNewsByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateNews(News news);

	/**
	* 添加 
	* @return
	*/ 
	public int addNews(News news);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdNews(List<News> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteNews(String id);

}

