package com.paotui.service.coupon;
import java.util.List;
import java.util.Map;
import com.paotui.model.coupon.Coupon;
public interface ICouponService {
	/**
	* 通过id选取
	* @return
	*/
	public Coupon selectCouponById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Coupon> selectCouponByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountCouponByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateCoupon(Coupon coupon);

	/**
	* 添加 
	* @return
	*/ 
	public int addCoupon(Coupon coupon);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdCoupon(List<Coupon> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteCoupon(String id);

}

