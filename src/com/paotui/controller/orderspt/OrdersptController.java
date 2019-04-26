package com.paotui.controller.orderspt;
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
import com.paotui.service.orderspt.IOrdersptService;
import com.paotui.model.orderspt.Orderspt;
@Controller
public class OrdersptController {
	@Autowired
	private IOrdersptService iOrdersptService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("PaotuiLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addOrderspt")
	@ResponseBody
	public Map add(Orderspt orderspt){
		Map resultMap=new HashMap();
		try {
			iOrdersptService.addOrderspt(orderspt);
			resultMap.put("status", "0");
			resultMap.put("msg", orderspt.getId());
			logger.info("新建成功，主键："+orderspt.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdOrderspt")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Orderspt orderspt){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Orderspt.class);
			List<Orderspt> list = (List<Orderspt>)objectMapper.readValue(data, javaType);
			iOrdersptService.muladdOrderspt(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+orderspt.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteOrderspt")
	@ResponseBody
	public Map delete(Orderspt orderspt){
		Map resultMap=new HashMap();
		try {
			if(orderspt.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iOrdersptService.deleteOrderspt(orderspt.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+orderspt.getId());
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
	@RequestMapping("/selectOrderspt")
	@ResponseBody
	public Map select(Orderspt orderspt){
		Map resultMap=new HashMap();
		try {
			if(orderspt.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Orderspt resultSelect=iOrdersptService.selectOrdersptById(orderspt.getId()+"");
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
	@RequestMapping("/updateOrderspt")
	@ResponseBody
	public Map update(Orderspt orderspt){
		Map resultMap=new HashMap();
		try {
			if(orderspt.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iOrdersptService.updateOrderspt(orderspt);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+orderspt.getId());
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
	@RequestMapping("/listOrderspt")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Orderspt orderspt)
		throws ServletException, IOException {
		Map resultMap=new HashMap();
		try {
			String page=request.getParameter("page");
			String size=request.getParameter("size");
			if(page!=null&&size!=null){
				Map paramMap=new HashMap();
				paramMap.put("fromPage",(Integer.parseInt(page)-1)*Integer.parseInt(size));
				paramMap.put("toPage",Integer.parseInt(size)); 
				paramMap.put("orderBy","a.ID DESC"); 
				paramMap.put("id",orderspt.getId());
				paramMap.put("cus_id",orderspt.getCus_id());
				paramMap.put("ordernum",orderspt.getOrdernum());
				paramMap.put("price",orderspt.getPrice());
				paramMap.put("fromaddress",orderspt.getFromaddress());
				paramMap.put("fromphone",orderspt.getFromphone());
				paramMap.put("toaddress",orderspt.getToaddress());
				paramMap.put("tophone",orderspt.getTophone());
				paramMap.put("note",orderspt.getNote());
				String send_dtFrom=request.getParameter("send_dtFrom");
				String send_dtTo=request.getParameter("send_dtTo");
				if(send_dtFrom!=null&&!send_dtFrom.equals(""))
				paramMap.put("send_dtFrom", sdf.parse(send_dtFrom));
				if(send_dtTo!=null&&!send_dtTo.equals(""))
				paramMap.put("send_dtTo", sdf.parse(send_dtTo));
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
				paramMap.put("status",orderspt.getStatus());
				paramMap.put("state",orderspt.getState());
				List<Orderspt> list=iOrdersptService.selectOrdersptByParam(paramMap);
				int totalnumber=iOrdersptService.selectCountOrdersptByParam(paramMap);
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
}
