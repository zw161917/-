package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.Product;

public interface ProductDao {
	 /**
     * 查询商品列表并分页，可输入的条件：商品名（模糊），商品状态，店铺Id，商品类别
     * @param productCondition
     * @param beginIndex
     * @param pageSize
     * @return
     */
    List<Product> queryProductList(@Param("productCondition")Product productCondition,@Param("beginIndex")int beginIndex,@Param("pageSize")int pageSize);


    Product queryProductByProductId(long productId);

  
	/**
	 * 插入商品
	 * @param product
	 * @return
	 */
	int insertProduct(Product product);
	
}
