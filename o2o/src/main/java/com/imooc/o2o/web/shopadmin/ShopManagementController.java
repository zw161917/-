 package com.imooc.o2o.web.shopadmin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptions.ShopOperationException;
import com.imooc.o2o.service.AreaService;
import com.imooc.o2o.service.ShopCategoryService;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.CodeUtil;
import com.imooc.o2o.util.HttpServletRequestUtil;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PathUtil;

//实现店铺管理相关的逻辑
@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	@Autowired
	private AreaService areaService;
	
	//店铺管理相关页面开发
	@RequestMapping(value = "/getshopmanagementinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopManagementInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if(shopId <= 0) {
        	Object currentShopObj = request.getSession().getAttribute("currentShop");
        	if(currentShopObj == null) {
        		modelMap.put("redirect", true);
        		modelMap.put("url", "/o2o/shopadmin/shoplist");
        	}else {
        		Shop currentShop = (Shop)currentShopObj;
        		modelMap.put("redirect", false);
        		modelMap.put("shopId", currentShop.getShopId());
        	}
        }else {
        	Shop currentShop = new Shop();
    		currentShop.setShopId(shopId);
    		request.getSession().setAttribute("currentShop", currentShop);
    		modelMap.put("redirect", false);
        }
        return modelMap;
	}
        
	//查询店铺信息分页
		 @RequestMapping(value = "/getshoplist", method = RequestMethod.GET)
		    @ResponseBody
		    private Map<String, Object> getShopList(HttpServletRequest request) {
		        Map<String, Object> modelMap = new HashMap<String, Object>();
		        PersonInfo user = new PersonInfo();
		        user.setUserId(1L);
		        user.setName("test");
		        request.getSession().setAttribute("user", user);
		        user = (PersonInfo)request.getSession().getAttribute("user");
		        try {
		        	Shop shopCondition = new Shop();
		        	shopCondition.setOwner(user);
		        	ShopExecution se = shopService.getShoopList(shopCondition, 0, 100);
		        	modelMap.put("shopList",se.getShopList());
		        	modelMap.put("user", user);
		        	modelMap.put("success", true);
		        }catch (Exception e) {
		        	modelMap.put("success", false);
		        	modelMap.put("errMsg", e.getMessage());
		        }
		       return modelMap;
		 }
	
	//上传修改时的信息
	 @RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
	    @ResponseBody
	    private Map<String, Object> getShopById(HttpServletRequest request) {
	        Map<String, Object> modelMap = new HashMap<String, Object>();
	        Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
	        if (shopId > -1) {
	            try {
	                Shop shop = shopService.getByShopId(shopId);
	                List<Area> areaList = areaService.getAreaList();
	                modelMap.put("shop", shop);
	                modelMap.put("areaList", areaList);
	                modelMap.put("success", true);
	            } catch (Exception e) {
	                modelMap.put("success", false);
	                modelMap.put("errMsg", e.toString());
	            }

	        } else {
	            modelMap.put("success", false);
	            modelMap.put("errMsg", "empty shopId");
	        }
	        return modelMap;
	    }
	//更改店铺信息
	
	//上传商铺类型列表
	@RequestMapping(value="/getshopinitinfo",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getShopInitInfo(){
		  Map<String, Object> modelMap = new HashMap<String, Object>();
	        List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
	        List<Area> areaList = new ArrayList<Area>();
	        try {
	            //得到店铺分类的信息list
	            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
	            areaList = areaService.getAreaList();
	            //返回前端
	            modelMap.put("shopCategoryList", shopCategoryList);
	            modelMap.put("areaList", areaList);
	            modelMap.put("success", true);
	        } catch (Exception e) {
	            modelMap.put("success", false);
	            modelMap.put("errMsg", e.getMessage());
	        }
	        return modelMap;
	}
	
	//店铺注册
	@RequestMapping(value="/registershop",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> registerShop(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		//验证码判读
		if(!CodeUtil.checkVerifyCode(request)) {
			
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		//1.接受并转换相应的参数，包括店铺信息及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		}catch (Exception e) {
			// TODO: handle exception
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		//接收图片
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		
		//是否有上传的文件流
		if(commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传的图片不能为空");
			return modelMap;
		}
		//2.注册店铺
		if(shop != null && shopImg != null) {
			PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
			shop.setOwner(owner);
//			File shopImgFile = new File(PathUtil.getImgBasePath()+ImageUtil.getRandomFileName());
//			try {
//				shopImgFile.createNewFile();
//			} catch (IOException e) {
//				modelMap.put("success", false);
//				modelMap.put("errMsg", e.getMessage());
//				return modelMap;
//			}
//			try {
//				inputStreamToFile(shopImg.getInputStream(), shopImgFile);
//			} catch (IOException e) {
//				modelMap.put("success", false);
//				modelMap.put("errMsg", e.getMessage());
//				return modelMap;
//			}
			ShopExecution se;
			try {
				se = shopService.addShop(shop, shopImg.getInputStream(),shopImg.getOriginalFilename());
				if(se.getState() == ShopStateEnum.CHECK.getState()) {
					modelMap.put("success", true);
					//将用户可用得店铺列表传入session中
					@SuppressWarnings("unchecked")
					List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
					if(shopList == null || shopList.size() == 0) {
						shopList = new ArrayList<Shop>();
					}
					shopList.add(se.getShop());
					request.getSession().setAttribute("shopList", shopList);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (ShopOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			
			return modelMap;
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
		
		
	}
	//店铺信息修改
		@RequestMapping(value="/modifyshop",method = RequestMethod.POST)
		@ResponseBody
		public Map<String,Object> modifyShop(HttpServletRequest request){
			Map<String,Object> modelMap = new HashMap<String,Object>();
			//验证码判读
			if(!CodeUtil.checkVerifyCode(request)) {
				
				modelMap.put("success", false);
				modelMap.put("errMsg", "输入了错误的验证码");
				return modelMap;
			}
			//1.接受并转换相应的参数，包括店铺信息及图片信息
			//HttpServletRequestUtil.getString 主要将接收到的信息用于转化成String
	        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
	        ObjectMapper mapper = new ObjectMapper();
	        Shop shop = null;
	        try {
	            shop = mapper.readValue(shopStr, Shop.class);
	        } catch (Exception e) {
	            modelMap.put("success", false);
	            modelMap.put("errMsg", e.getMessage());
	            return modelMap;
	        }
	        //spring处理文件形式
	        CommonsMultipartFile shopImg = null;
	        //文件解析器
	        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
	        //是否是文件流
	        if (commonsMultipartResolver.isMultipart(request)) {
	            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
	            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
	        }
//	        Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
//	        System.out.println(shopId+"输入的ID"+shop.getShopId());
//	        System.out.println(shopId+"输入的ID"+shop.getShopName());
			//2.修改店铺信息
			if(shop != null && shop.getShopId() != null) {
			
				ShopExecution se;
				try {
					if(shopImg == null) {
						se = shopService.modifyShop(shop,null,null);

					}else {
						se = shopService.modifyShop(shop, shopImg.getInputStream(),shopImg.getOriginalFilename());

					}
					if(se.getState() == ShopStateEnum.SUCCESS.getState()) {
						modelMap.put("success", true);
					}else {
						modelMap.put("success", false);
						modelMap.put("errMsg", se.getStateInfo());
					}
				} catch (ShopOperationException e) {
					modelMap.put("success", false);
					modelMap.put("errMsg", e.getMessage());
				} catch (IOException e) {
					modelMap.put("success", false);
					modelMap.put("errMsg", e.getMessage());
				}
				
				return modelMap;
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "请输入店铺Id");
				return modelMap;
			}
			
			
		}

//	private static void inputStreamToFile(InputStream ins,File fiel) {
//		FileOutputStream os = null;
//		try {
//			
//		int bytesRead = 0;
//		byte[] buffer = new byte[1024];
//		while((bytesRead = ins.read(buffer))!= -1) {
//			os.write(buffer,0,bytesRead);
//			
//		}
//		}catch (Exception e) {
//			// TODO: handle exception
//			throw new RuntimeException("调用inputStreamToFile产生异常"+e.getMessage());
//		}finally {
//			try {
//				if(os != null) {
//					os.close();
//				}
//				if(ins != null) {
//					ins.close();
//				}
//			} catch (Exception e2) {
//				// TODO: handle exception
//				throw new RuntimeException("inputStreamToFile关闭io产生异常"+e2.getMessage());
//			}
//		}
//	}
	
}
