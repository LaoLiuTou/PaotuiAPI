package com.paotui.service.couponuser;
import java.util.List;
import java.util.Map;
import com.paotui.model.couponuser.Couponuser;
public interface ICouponuserService {
	/**
	* 通过id选取
	* @return
	*/
	public Couponuser selectCouponuserById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Couponuser> selectCouponuserByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountCouponuserByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateCouponuser(Couponuser couponuser);

	/**
	* 添加 
	* @return
	*/ 
	public int addCouponuser(Couponuser couponuser);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdCouponuser(List<Couponuser> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteCouponuser(String id);

}

