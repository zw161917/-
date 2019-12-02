package com.imooc.o2o.entity;
//店铺的具体属性

import java.util.Date;

public class Shop {
	
	private Long shopId; //店铺的id
	private String shopName; //店铺的名称
	private String shopDesc; //店铺的描述
	private String shopAddr; //店铺的地址
	private String phone; //店铺的联系方式
	private String shopImg; //店铺的缩略图
	private Integer priority; //店铺的权重
	private Date createTime; //店铺的创建时间
	private Date lastEditTime; //店铺的更新时间
	//-1.不可用，0.审核中， 1，可用
	private Integer enableStatus; //店铺的状态
	//超级管理员给店家的提醒
	private String advice; //
	private Area area; //区域实体类表示商铺属于哪一区域
	private PersonInfo owner; //用户信息实体类表示商铺是由谁创建的
	private ShopCategory shopCategory; //标识我们的店铺属于哪一个类别的
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopDesc() {
		return shopDesc;
	}
	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}
	public String getShopAddr() {
		return shopAddr;
	}
	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getShopImg() {
		return shopImg;
	}
	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public PersonInfo getOwner() {
		return owner;
	}
	public void setOwner(PersonInfo owner) {
		this.owner = owner;
	}
	public ShopCategory getShopCategory() {
		return shopCategory;
	}
	public void setShopCategory(ShopCategory shopCategory) {
		this.shopCategory = shopCategory;
	}
	
	
	
	
	
	
	
	
	

}
