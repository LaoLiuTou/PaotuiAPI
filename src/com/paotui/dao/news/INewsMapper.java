package com.paotui.dao.news;
import java.util.List;
import java.util.Map;
import com.paotui.model.news.News;
	public interface INewsMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public News selectnewsById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<News> selectnewsByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountnewsByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatenews(News news);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addnews(News news);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdnews(List<News> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletenews(String id);

}

