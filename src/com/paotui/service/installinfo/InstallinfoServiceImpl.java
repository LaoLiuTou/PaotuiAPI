package com.paotui.service.installinfo;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.paotui.dao.installinfo.IInstallinfoMapper;
import com.paotui.model.installinfo.Installinfo;
public class InstallinfoServiceImpl  implements IInstallinfoService {

	@Autowired
	private IInstallinfoMapper iInstallinfoMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Installinfo selectInstallinfoById(String id){
		return iInstallinfoMapper.selectinstallinfoById(id);
	}

	/**
	 * 统计
	 * @return
	 */ 
	@SuppressWarnings("rawtypes")
	public List<Installinfo> statisticInstallinfoByParam(Map paramMap){ 
		return iInstallinfoMapper.statisticinstallinfoByParam(paramMap);
	}
	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Installinfo> selectInstallinfoByParam(Map paramMap){ 
		return iInstallinfoMapper.selectinstallinfoByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountInstallinfoByParam(Map paramMap){ 
		return iInstallinfoMapper.selectCountinstallinfoByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateInstallinfo(Installinfo installinfo){
		return iInstallinfoMapper.updateinstallinfo(installinfo);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addInstallinfo(Installinfo installinfo){
		return iInstallinfoMapper.addinstallinfo(installinfo);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdInstallinfo(List<Installinfo> list){
		return iInstallinfoMapper.muladdinstallinfo(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteInstallinfo(String id){
		return iInstallinfoMapper.deleteinstallinfo(id);
	}

}

