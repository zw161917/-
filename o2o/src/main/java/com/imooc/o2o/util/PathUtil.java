package com.imooc.o2o.util;

public class PathUtil {
	
	private static String seperstor = System.getProperty("file.separator");
	//返回项目图片的根路径
	public static String getImgBasePath() {
		
		String os = System.getProperty("os.name");
		String basePath = "";
		if(os.toLowerCase().startsWith("win")) {
			basePath = "F:/eclipsetupian/image/";
			
		}else {
			basePath = "/home/xiangze/image/";
		}
		basePath = basePath.replace("/", seperstor);
		 return basePath;
	}
	//依旧不同的项目需求返回项目图片的子路径
	public static String getShopImagePath(long shopId) {
		String imagePath = "upload/item/shop/"+shopId+"/";
		return imagePath.replace("/", seperstor);
		
	}

}
