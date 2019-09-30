package com.paotui.service.couponuser;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.paotui.dao.couponuser.ICouponuserMapper;
import com.paotui.model.couponuser.Couponuser;
public class CouponuserServiceImpl  implements ICouponuserService {

	@Autowired
	private ICouponuserMapper iCouponuserMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Couponuser selectCouponuserById(String id){
		return iCouponuserMapper.selectcouponuserById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Couponuser> selectCouponuserByParam(Map paramMap){ 
		return iCouponuserMapper.selectcouponuserByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountCouponuserByParam(Map paramMap){ 
		return iCouponuserMapper.selectCountcouponuserByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateCouponuser(Couponuser couponuser){
		return iCouponuserMapper.updatecouponuser(couponuser);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addCouponuser(Couponuser couponuser){
		return iCouponuserMapper.addcouponuser(couponuser);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdCouponuser(List<Couponuser> list){
		return iCouponuserMapper.muladdcouponuser(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteCouponuser(String id){
		return iCouponuserMapper.deletecouponuser(id);
	}

}

