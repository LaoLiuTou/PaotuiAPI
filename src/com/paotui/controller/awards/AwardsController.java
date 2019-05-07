package com.paotui.controller.awards;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paotui.service.awards.IAwardsService;
import com.paotui.service.customer.ICustomerService;
import com.paotui.model.awards.Awards;
import com.paotui.model.customer.Customer;
@Controller
public class AwardsController {
	@Autowired
	private IAwardsService iAwardsService;
	@Autowired
	private ICustomerService iCustomerService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("PaotuiLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addAwards")
	@ResponseBody
	public Map add(Awards awards){
		Map resultMap=new HashMap();
		try {
			iAwardsService.addAwards(awards);
			resultMap.put("status", "0");
			resultMap.put("msg", awards.getId());
			logger.info("新建成功，主键："+awards.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdAwards")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Awards awards){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Awards.class);
			List<Awards> list = (List<Awards>)objectMapper.readValue(data, javaType);
			iAwardsService.muladdAwards(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+awards.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteAwards")
	@ResponseBody
	public Map delete(Awards awards){
		Map resultMap=new HashMap();
		try {
			if(awards.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iAwardsService.deleteAwards(awards.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+awards.getId());
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "删除失败！");
			logger.info("删除失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/selectAwards")
	@ResponseBody
	public Map select(Awards awards){
		Map resultMap=new HashMap();
		try {
			if(awards.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Awards resultSelect=iAwardsService.selectAwardsById(awards.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", resultSelect);
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "查询失败！");
			logger.info("查询失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/updateAwards")
	@ResponseBody
	public Map update(Awards awards){
		Map resultMap=new HashMap();
		try {
			if(awards.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iAwardsService.updateAwards(awards);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+awards.getId());
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "更新失败！");
			logger.info("更新失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/listAwards")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Awards awards)
		throws ServletException, IOException {
		Map resultMap=new HashMap();
		try {
			String page=request.getParameter("page");
			String size=request.getParameter("size");
			if(page!=null&&size!=null){
				Map paramMap=new HashMap();
				paramMap.put("fromPage",(Integer.parseInt(page)-1)*Integer.parseInt(size));
				paramMap.put("toPage",Integer.parseInt(size)); 
				paramMap.put("orderBy","ID DESC"); 
				paramMap.put("id",awards.getId());
				paramMap.put("drawname",awards.getDrawname());
				paramMap.put("image",awards.getImage());
				paramMap.put("rate",awards.getRate());
				paramMap.put("status",awards.getStatus());
				String c_dtFrom=request.getParameter("c_dtFrom");
				String c_dtTo=request.getParameter("c_dtTo");
				if(c_dtFrom!=null&&!c_dtFrom.equals(""))
				paramMap.put("c_dtFrom", sdf.parse(c_dtFrom));
				if(c_dtTo!=null&&!c_dtTo.equals(""))
				paramMap.put("c_dtTo", sdf.parse(c_dtTo));
				String u_dtFrom=request.getParameter("u_dtFrom");
				String u_dtTo=request.getParameter("u_dtTo");
				if(u_dtFrom!=null&&!u_dtFrom.equals(""))
				paramMap.put("u_dtFrom", sdf.parse(u_dtFrom));
				if(u_dtTo!=null&&!u_dtTo.equals(""))
				paramMap.put("u_dtTo", sdf.parse(u_dtTo));
				paramMap.put("state",awards.getState());
				paramMap.put("type",awards.getType());
				List<Awards> list=iAwardsService.selectAwardsByParam(paramMap);
				int totalnumber=iAwardsService.selectCountAwardsByParam(paramMap);
				Map tempMap=new HashMap();
				resultMap.put("status", "0");
				tempMap.put("num", totalnumber);
				tempMap.put("data", list);
				resultMap.put("msg", tempMap);
			}
			else{
				resultMap.put("status", "-1");
				resultMap.put("msg", "分页参数不能为空！");
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "查询失败！");
			logger.info("查询失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getAwards")
	@ResponseBody
	public Map get(HttpServletRequest request){
		Map resultMap=new HashMap();
		try {
			String cus_id=request.getParameter("cus_id"); 
			if(cus_id!=null){
				Map paramMap=new HashMap();
				paramMap.put("orderBy","ID DESC"); 
				paramMap.put("status","0");
				int totalnumber=iAwardsService.selectCountAwardsByParam(paramMap);
				paramMap.put("fromPage",0);
				paramMap.put("toPage",totalnumber); 
				List<Awards> list=iAwardsService.selectAwardsByParam(paramMap);
				String resultId="0";
				List<String> randoms=new ArrayList<String>();
				for(Awards item:list){
					int rateValue=Integer.parseInt(item.getRate());
					List<String> tempList=new ArrayList<String>();
					for(int i=0;i<rateValue;i++){
						tempList.add(item.getId()+"");
					}
					randoms.addAll(tempList);
				}
				int index=new Random().nextInt(1000);
				if(index>(randoms.size()+1)){
					resultMap.put("status", "-1");
					resultMap.put("msg", "未中奖！");
				}
				else{
					resultId=randoms.get(index);
					Awards resultSelect=iAwardsService.selectAwardsById(resultId+"");
					resultMap.put("status", "0");
					resultMap.put("msg", resultSelect);
				}
				
				
				Customer customer= new Customer();
				customer.setId(Long.parseLong(cus_id));
				customer.setIsprize("0");
				iCustomerService.updateCustomer(customer);	
			}
			else{
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			
			
			 
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "查询失败！");
			logger.info("查询失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
}
