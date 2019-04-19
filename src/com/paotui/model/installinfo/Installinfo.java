package com.paotui.model.installinfo;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author LT
 */
public class Installinfo {

	/**  */
	private  Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/** 厂商 */
	private  String devicebrand;
	public String getDevicebrand() {
		return devicebrand;
	}
	public void setDevicebrand(String devicebrand) {
		this.devicebrand = devicebrand;
	}
	/** 型号 */
	private  String systemmodel;
	public String getSystemmodel() {
		return systemmodel;
	}
	public void setSystemmodel(String systemmodel) {
		this.systemmodel = systemmodel;
	}
	/** 系统版本 */
	private  String systemversion;
	public String getSystemversion() {
		return systemversion;
	}
	public void setSystemversion(String systemversion) {
		this.systemversion = systemversion;
	}
	/** 序列号 */
	private  String imei;
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	/**  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date c_dt;
	public Date getC_dt() {
		return c_dt;
	}
	public void setC_dt(Date c_dt) {
		this.c_dt = c_dt;
	}
	/**  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date u_dt;
	public Date getU_dt() {
		return u_dt;
	}
	public void setU_dt(Date u_dt) {
		this.u_dt = u_dt;
	}
	/**  */
	private  Long state;
	public Long getState() {
		return state;
	}
	public void setState(Long state) {
		this.state = state;
	}

	private String count;
	private String days;
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}


}
