package com.paotui.model.prize;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author LT
 */
public class Prize {

	/**  */
	private  Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/**  */
	private  Long cus_id;
	public Long getCus_id() {
		return cus_id;
	}
	public void setCus_id(Long cus_id) {
		this.cus_id = cus_id;
	}
	/** 奖品类型 */
	private  String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	/** 详情 */
	private  String details;
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
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

	private String cusname; 
	public String getCusname() {
		return cusname;
	}
	public void setCusname(String cusname) {
		this.cusname = cusname;
	}
	private String phone; 
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	private Long awards_id;
	public Long getAwards_id() {
		return awards_id;
	}
	public void setAwards_id(Long awards_id) {
		this.awards_id = awards_id;
	}

	/**  */
	private  String drawname;
	public String getDrawname() {
		return drawname;
	}
	public void setDrawname(String drawname) {
		this.drawname = drawname;
	}
	/**  */
	private  String image;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	private String money;
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	
}
