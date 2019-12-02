package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.Shop;

public interface ShopDao {
	
	/**
	 * 分页查询电铺功能，可输入的条件有：店铺名（模糊），店铺状态，区域ID，owner
	 * @param shopCondition
	 * @param rowIndex 从第几行开始取数据
	 * @param pageSize 返回数据的条数
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
			@Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);
	
	/**
	 * 返回queryShopList数据的总个数
	 * @param shopCondition
	 * @return
	 */
	int queryShopCount(@Param("shopCondition") Shop shopCondition);
	
	/**
	 * 通过shop—id查询店铺
	 * @param shop
	 * @return
	 */
	Shop queryByShop(Long shopId);
	
	/**
	 * 新增店铺
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);
	
	/**
	 * 更新店铺信息
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);

}
