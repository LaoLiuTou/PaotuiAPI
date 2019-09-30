package com.paotui.dao.couponuser;
import java.util.List;
import java.util.Map;
import com.paotui.model.couponuser.Couponuser;
	public interface ICouponuserMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Couponuser selectcouponuserById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Couponuser> selectcouponuserByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountcouponuserByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatecouponuser(Couponuser couponuser);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addcouponuser(Couponuser couponuser);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdcouponuser(List<Couponuser> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletecouponuser(String id);

}

