package com.paotui.dao.coupon;
import java.util.List;
import java.util.Map;
import com.paotui.model.coupon.Coupon;
	public interface ICouponMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Coupon selectcouponById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Coupon> selectcouponByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountcouponByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatecoupon(Coupon coupon);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addcoupon(Coupon coupon);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdcoupon(List<Coupon> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletecoupon(String id);

}

