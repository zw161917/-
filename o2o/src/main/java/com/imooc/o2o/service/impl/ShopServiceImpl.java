package com.imooc.o2o.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.o2o.dao.ShopDao;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptions.ShopOperationException;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PageCalculator;
import com.imooc.o2o.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;
	
	//分页查询电铺
	@Override
	public ShopExecution getShoopList(Shop shopCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
		int count = shopDao.queryShopCount(shopCondition);
		ShopExecution se = new ShopExecution();
		if(shopList != null) {
			se.setShopList(shopList);
			se.setCount(count);
		}else {
			se.setState(ShopStateEnum.INNER_ERROR.getState());
		
		}
		return se;
	}
	
	@Override
	@Transactional //事物标签，表明需要事物的支持
	public ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName) {
		// TODO Auto-generated method stub
		if(shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		//添加店铺
		try {
			
			shop.setEnableStatus(0); //初始化状态，表示在审核中
			shop.setCreateTime(new Date()); 
			shop.setLastEditTime(new Date());
			//插入店铺信息
			int effectedNum = shopDao.insertShop(shop);
			
			//判断这一次插入是否有效
			if(effectedNum <= 0) {
				throw new ShopOperationException("店铺创建失败");
				
			}else {
				if(shopImgInputStream != null) {
					try {
						//存储图片
						addShopImg(shop,shopImgInputStream,fileName);
					}catch (Exception e) {
						// TODO: handle exception
						throw new ShopOperationException("addShopImg error:"+e.getMessage());
					}
					
					//更新店铺的图片地址
					effectedNum = shopDao.updateShop(shop);
					if(effectedNum <= 0) {
						throw new ShopOperationException("更新图片地址失败");
					}
					
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw new ShopOperationException("addShop error:"+e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK,shop);
	}

	private void addShopImg(Shop shop, InputStream shopImgInputStream,String fileName) {
		// TODO Auto-generated method stub
		//获取shop图片的相对值路径
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream,fileName, dest);
		shop.setShopImg(shopImgAddr);
	}

	@Override
	public Shop getByShopId(long shopId) {
		// TODO Auto-generated method stub
		return shopDao.queryByShop(shopId);
	}

	@Override
	public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName)
			throws ShopOperationException {
		
		if(shop == null || shop.getShopId() == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}else {
			//判断是否需要处理图片
			try {
			if(shopImgInputStream != null && fileName != null && "".equals(fileName)) {
				System.out.println("tupianchuli=====");
				Shop tempShop = shopDao.queryByShop(shop.getShopId());
				if(tempShop.getShopImg() != null) {
					ImageUtil.deleteFileOrPath(tempShop.getShopImg());
				}
				addShopImg(shop, shopImgInputStream, fileName);
			}
		
		//更新店铺信息
		shop.setLastEditTime(new Date());
		int effectedNum = shopDao.updateShop(shop);
		if(effectedNum <= 0) {
			return new ShopExecution(ShopStateEnum.INNER_ERROR);
		}else {
			shop = shopDao.queryByShop(shop.getShopId());
			return new ShopExecution(ShopStateEnum.SUCCESS,shop);
		}}catch (Exception e) {
			throw new ShopOperationException("modifyShop error:" + e.getMessage());
		}
		
	}
	}

	



}
