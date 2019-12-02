package com.imooc.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//对shopoperation.html文件做转发

@Controller
@RequestMapping(value="shopadmin",method={RequestMethod.GET})
public class ShopAdminCaontroller {
	//实现转发
	@RequestMapping(value="/shopoperation")
	public String shopOperation() {
		return "shop/shopoperation";
	}
	
	@RequestMapping(value="/shoplist")
	public String shoplist() {
		return "shop/shoplist";
	}
	
	@RequestMapping(value="/shopnamagement")
	public String shopNamagement() {
		return "shop/shopnamagement";
	}
	
	@RequestMapping(value="/productcategorymanagement",method = RequestMethod.GET)
	public String productCategoryManage() {
		return "shop/productcategorymanagement";
	}
}
