package com.paotui.controller.customer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.paotui.service.customer.ICustomerService;
import com.paotui.utils.MD5Encryption;
import com.paotui.utils.ShareCodeUtil;
import com.paotui.model.customer.Customer; 
@Controller
public class CustomerController {
	@Autowired
	private ICustomerService iCustomerService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("PaotuiLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addCustomer")
	@ResponseBody
	public Map add(Customer customer){
		Map resultMap=new HashMap();
		try {
			iCustomerService.addCustomer(customer);
			resultMap.put("status", "0");
			resultMap.put("msg", customer.getId());
			logger.info("新建成功，主键："+customer.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdCustomer")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Customer customer){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Customer.class);
			List<Customer> list = (List<Customer>)objectMapper.readValue(data, javaType);
			iCustomerService.muladdCustomer(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+customer.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteCustomer")
	@ResponseBody
	public Map delete(Customer customer){
		Map resultMap=new HashMap();
		try {
			if(customer.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iCustomerService.deleteCustomer(customer.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+customer.getId());
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
	@RequestMapping("/selectCustomer")
	@ResponseBody
	public Map select(Customer customer){
		Map resultMap=new HashMap();
		try {
			if(customer.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Customer resultSelect=iCustomerService.selectCustomerById(customer.getId()+"");
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
	@RequestMapping("/updateCustomer")
	@ResponseBody
	public Map update(Customer customer){
		Map resultMap=new HashMap();
		try {
			if(customer.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iCustomerService.updateCustomer(customer);
				Customer resultSelect=iCustomerService.selectCustomerById(customer.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", resultSelect);
				logger.info("更新成功，主键："+customer.getId());
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
	@RequestMapping("/listCustomer")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Customer customer)
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
				paramMap.put("id",customer.getId());
				paramMap.put("nickname",customer.getNickname());
				paramMap.put("phone",customer.getPhone());
				paramMap.put("password",customer.getPassword());
				paramMap.put("header",customer.getHeader());
				paramMap.put("wechat",customer.getWechat());
				paramMap.put("inviter",customer.getInviter());
				paramMap.put("invitecode",customer.getInvitecode());
				paramMap.put("isnew",customer.getIsnew());
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
				paramMap.put("state",customer.getState());
				String searchText=request.getParameter("searchText");
				if(searchText!=null&&!searchText.equals(""))
				paramMap.put("searchText",searchText);
				List<Customer> list=iCustomerService.selectCustomerByParam(paramMap);
				int totalnumber=iCustomerService.selectCountCustomerByParam(paramMap);
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
	@RequestMapping("/loginCustomer")
	@ResponseBody
	public Map login(HttpServletRequest request,Customer customer){
		Map resultMap=new HashMap();
		try {
			if(customer.getPhone()!=null&&customer.getPassword()!=null){
				
				Map paramMap=new HashMap();
				paramMap.put("fromPage",0);
				paramMap.put("toPage",1); 
				paramMap.put("phone",customer.getPhone());
				//paramMap.put("audit_status","已审核");
				List<Customer> list=iCustomerService.selectCustomerByParam(paramMap);
				if(list.size()>0){
					
					String localpwdStr=list.get(0).getPassword();
					if(localpwdStr.equals(customer.getPassword().toLowerCase())){
						
						if(list.get(0).getState()!=null&&list.get(0).getState()==0){
							resultMap.put("status", "0");
							resultMap.put("msg", list.get(0));
							logger.info("用户登录："+list.get(0).getPhone());
						}
						else if(list.get(0).getState()!=null&&list.get(0).getState()==1){
							resultMap.put("status", "-1");
							resultMap.put("msg", "该账号正在审核！");
							
						}
						 
						
					}
					else{
						resultMap.put("status", "-1");
						resultMap.put("msg", "密码错误！");
					}
				}
				else{
					resultMap.put("status", "-1");
					resultMap.put("msg", "用户名不存在！");
				}
			}
			else{
				resultMap.put("status", "-1");
				resultMap.put("msg", "用户名或密码不能为空！");
			}
			 
			 
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "登录失败！");
			logger.info("登录失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/registerCustomer")
	@ResponseBody
	public Map register(Customer customer){
		Map resultMap=new HashMap();
		try {
			if(customer.getPhone()!=null&&customer.getPassword()!=null){
				
				Map paramMap=new HashMap();
				paramMap.put("fromPage",0);
				paramMap.put("toPage",1); 
				paramMap.put("phone",customer.getPhone());
				//paramMap.put("audit_status","已审核");
				List<Customer> list=iCustomerService.selectCustomerByParam(paramMap);
				if(list.size()>0){
					resultMap.put("status", "-1");
					resultMap.put("msg", "用户名已存在！");
				}
				else{
					if(customer.getInvitecode()!=null&&!customer.getInvitecode().equals("")){
						String cus_id=ShareCodeUtil.getUidByCode(customer.getInvitecode())+"";
						Customer temp=iCustomerService.selectCustomerById(cus_id);
						if(temp!=null){
							customer.setInviter(temp.getId());
						}
						else{
							resultMap.put("status", "-1");
							resultMap.put("msg", "该邀请码不存在！");
							return resultMap;
						}
					}
					iCustomerService.addCustomer(customer);
					resultMap.put("status", "0");
					resultMap.put("msg", customer);
					logger.info("新建成功，主键："+customer.getId());
				}
			}
			else{
				resultMap.put("status", "-1");
				resultMap.put("msg", "用户名或密码不能为空！");
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/statisticCustomer")
	@ResponseBody
	public Map statistic(HttpServletRequest request, HttpServletResponse response,Customer customer)
		throws ServletException, IOException {
		Map resultMap=new HashMap();
		try {
			Map paramMap=new HashMap();
			String stype=request.getParameter("stype");
			if(stype!=null&&!stype.equals(""))
				paramMap.put("stype",stype);
			paramMap.put("id",customer.getId());
			paramMap.put("nickname",customer.getNickname());
			paramMap.put("phone",customer.getPhone());
			paramMap.put("password",customer.getPassword());
			paramMap.put("header",customer.getHeader());
			paramMap.put("wechat",customer.getWechat());
			paramMap.put("inviter",customer.getInviter());
			paramMap.put("invitecode",customer.getInvitecode());
			paramMap.put("isnew",customer.getIsnew());
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
			paramMap.put("state",customer.getState());
			List<Customer> list=iCustomerService.statisticCustomerByParam(paramMap);
			resultMap.put("status", "0");
			resultMap.put("msg", list);
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "查询失败！");
			logger.info("查询失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
}
