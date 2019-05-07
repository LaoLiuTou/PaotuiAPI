package com.paotui.service.prize;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.paotui.dao.awards.IAwardsMapper;
import com.paotui.dao.customer.ICustomerMapper;
import com.paotui.dao.prize.IPrizeMapper;
import com.paotui.model.awards.Awards;
import com.paotui.model.customer.Customer;
import com.paotui.model.prize.Prize;
public class PrizeServiceImpl  implements IPrizeService {

	@Autowired
	private IPrizeMapper iPrizeMapper;
	@Autowired
	private ICustomerMapper iCustomerMapper;
	@Autowired
	private IAwardsMapper iAwardsMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Prize selectPrizeById(String id){
		return iPrizeMapper.selectprizeById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Prize> selectPrizeByParam(Map paramMap){ 
		return iPrizeMapper.selectprizeByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountPrizeByParam(Map paramMap){ 
		return iPrizeMapper.selectCountprizeByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updatePrize(Prize prize){
		return iPrizeMapper.updateprize(prize);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addPrize(Prize prize){
		int result=0;
		result=iPrizeMapper.addprize(prize);
		if(result>0&&prize.getType()!=null&&prize.getType().equals("1")){
			Customer customer=iCustomerMapper.selectcustomerById(prize.getCus_id()+"");
			Awards awards=iAwardsMapper.selectawardsById(prize.getAwards_id()+"");
			if(awards!=null&&awards.getMoney()!=null){
				String balance=customer.getBalance();
				Customer temp = new Customer();
				temp.setId(prize.getCus_id());
				DecimalFormat decimalFormat=new DecimalFormat(".0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
				String price=decimalFormat.format(Float.parseFloat(balance)+Float.parseFloat(awards.getMoney()));//format 返回的是字符串
				temp.setBalance(price);
				temp.setIsprize("0");
				iCustomerMapper.updatecustomer(temp);
			}
			
		} 
		return result;
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdPrize(List<Prize> list){
		return iPrizeMapper.muladdprize(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deletePrize(String id){
		return iPrizeMapper.deleteprize(id);
	}

}

