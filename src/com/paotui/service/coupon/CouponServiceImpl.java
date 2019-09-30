package com.paotui.service.coupon;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.paotui.dao.coupon.ICouponMapper;
import com.paotui.model.coupon.Coupon;
public class CouponServiceImpl  implements ICouponService {

	@Autowired
	private ICouponMapper iCouponMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Coupon selectCouponById(String id){
		return iCouponMapper.selectcouponById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Coupon> selectCouponByParam(Map paramMap){ 
		return iCouponMapper.selectcouponByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountCouponByParam(Map paramMap){ 
		return iCouponMapper.selectCountcouponByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateCoupon(Coupon coupon){
		return iCouponMapper.updatecoupon(coupon);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addCoupon(Coupon coupon){
		return iCouponMapper.addcoupon(coupon);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdCoupon(List<Coupon> list){
		return iCouponMapper.muladdcoupon(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteCoupon(String id){
		return iCouponMapper.deletecoupon(id);
	}

}

