package com.imooc.o2o.entity;

import java.util.Date;
//商品类别
public class ProductCategory {

	private Long productCategoryId; //id
	private Long shopId; //店铺id
	private String productCategoryName; //商品类别的名称
	private Integer priority; //商品类别的权重
	private Date cerateTime; //商品类别创建的时间
	public Long getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getCerateTime() {
		return cerateTime;
	}
	public void setCerateTime(Date cerateTime) {
		this.cerateTime = cerateTime;
	}
	
	
	
	
}
