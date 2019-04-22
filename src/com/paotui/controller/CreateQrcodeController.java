package com.paotui.controller;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paotui.model.drivers.Drivers;
import com.paotui.service.drivers.IDriversService;
import com.paotui.utils.ZipUtil;
import com.paotui.utils.qrcode.CodeCreator;
import com.paotui.utils.qrcode.CodeDecoder;
import com.paotui.utils.qrcode.CodeModel;

@Controller
public class CreateQrcodeController {
	@Autowired
	private IDriversService iDriversService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("PaotuiLogger");
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/createQrcode")
	@ResponseBody
	public Map create(HttpServletRequest request){
		Map resultMap=new HashMap();
		try {
			List<String> listKey = new ArrayList<String>();
			String realPath=request.getSession().getServletContext().getRealPath("/upload/")+"/";
			System.out.println(realPath+"ic_launcher.png");
			Map paramMap=new HashMap();
			paramMap.put("orderBy","ID DESC"); 
			int totalnumber=iDriversService.selectCountDriversByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber); 
			List<Drivers> list=iDriversService.selectDriversByParam(paramMap);
			for(Drivers driver:list){
				String savePath=realPath+"/upload/qrcodes/"+driver.getDrivername()+"-"+driver.getNumber()+".jpg";
				String content="{\"id\":\""+driver.getId()+"\",\"number\":\""+driver.getNumber()+
						"\",\"driver\":\""+driver.getDrivername()+"\"}";
				encode(realPath+"logo.png",content,driver.getNumber(),savePath);
				listKey.add(savePath);
			}
		    java.io.File zipFile = new java.io.File(realPath+"/upload/出租车二维码.zip");// 最终打包的压缩包
	        ZipUtil.packageZip(zipFile,listKey);
			//encode
			resultMap.put("status", "0");
			resultMap.put("msg", "生成成功！");
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "生成失败！");
			logger.info("发送失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	
	private void encode(String image,String content,String desc,String savePath) {
		CodeCreator creator = new CodeCreator();
		CodeModel info = new CodeModel();
		info.setWidth(600);
		info.setHeight(600);
		info.setFontSize(30);
		info.setContents(content);
		info.setLogoFile(new File(image));
		info.setDesc("              "+desc);
		//info.setLogoDesc("一叶浮萍");
		creator.createCodeImage(info, savePath);
	}
	
	static public void decode(InputStream input){
		CodeDecoder decoder = new CodeDecoder();
		String result = decoder.decode(input);
		System.out.println(result);
	}
	
	public static void main(String[] args) throws Exception{
		//encode();
		//decode(new FileInputStream(new File("D:/2Dcode/dest.gif")));
	}
 
}
