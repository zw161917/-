package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ProductCategory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)   //控制测试方法顺序执行
public class ProductCategoryDaoTest extends BaseTest {

	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Test
//	@Ignore
	public void testBQueryByShopId() throws Exception{
		long shopId = 4;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		System.out.println("该店铺自定义类型数为："+ productCategoryList.size());
	}
	
	@Test
//	@Ignore
	public void testABatchInsertProductCategory() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryName("商品类别1");
		productCategory.setPriority(1);
		productCategory.setCerateTime(new Date());
		productCategory.setShopId(4L);
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("商品类别2");
		productCategory2.setPriority(2);
		productCategory2.setCerateTime(new Date());
		productCategory2.setShopId(4L);
		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		productCategoryList.add(productCategory);
		productCategoryList.add(productCategory2);
		int sffectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
		assertEquals(2, sffectedNum);
	}
	
	@Test
	public void testCDeleteProductCategory() throws Exception {
		long shopId = 4;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		for(ProductCategory pc:productCategoryList) {
			if("商品类别1".equals(pc.getProductCategoryName()) || "商品类别2".equals(pc.getProductCategoryName())) {
				int effectedNum = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(), shopId);
				assertEquals(1, effectedNum);
			}
		}
	}
	
}
