package com.imooc.o2o.entity;

import java.util.Date;

//商品--详情图片
public class ProductImg {
	
	private Long productImgId; //id
	private String imgAddr; //图片的地址
	private String imgDesc; //图片的说明
	private Integer priority; //图片的权重先显示哪一个
	private Date createTime; //图片的创建时间
	private Long productId; // 归属于哪一个商品的id（多对一的关系）
	public Long getProductImgId() {
		return productImgId;
	}
	public void setProductImgId(Long productImgId) {
		this.productImgId = productImgId;
	}
	public String getImgAddr() {
		return imgAddr;
	}
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}
	public String getImgDesc() {
		return imgDesc;
	}
	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
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
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	

}
