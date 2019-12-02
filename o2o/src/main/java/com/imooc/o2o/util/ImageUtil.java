package com.imooc.o2o.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	//获取绝对路径
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath(); 
	
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	
	//生成随机数
	private static final Random r = new Random();
	
	private static Logger logger = LoggerFactory.getLogger(ImageUtil.class); 
	
	/**
	 * 将CommonsMultipartFile转换成File类
	 * @param cFile
	 * @return
	 */
	public static File transferCommonsMultipartFileTOFile(CommonsMultipartFile cFile) {
		File newFile = new File(cFile.getOriginalFilename());
		try {
			cFile.transferTo(newFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			e.printStackTrace();
		}
		return newFile;
	}
	
	/**
	 * 处理用户传过来的数据创建缩略图
	 * 并返回新生成的图片的相对值路径
	 * @param thumbnail
	 * @param targetAddr
	 * @return
	 */
	public static String generateThumbnail(InputStream thumbnailInputStream,String fileName,String targetAddr) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(fileName);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		logger.debug("current relativeAddr is"+relativeAddr);
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("vurrent complete addr is" + PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnailInputStream).size(200, 200)
			.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/shuiyin.jpg")), 0.25f)
			.outputQuality(0.8f).toFile(dest);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error(e.toString());
			e.printStackTrace();
		}
		return relativeAddr;
	}
	
	/**
	 * 创建目标路径上所涉及到的路径，即F:/eclipsetupian/image/xxx.jpg
	 * 那么eclipsetupian/image两个文件夹都要创建
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		// TODO Auto-generated method stub
		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if(!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/**
	 * 获取输入文件流的扩张名
	 * @param thumbnail
	 * @return
	 */
	private static String getFileExtension(String fileName) {
		// TODO Auto-generated method stub
		
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 生成随机文件名，当前年月日小时分钟秒+五位数
	 * @return
	 */
	public static String getRandomFileName() {
		// TODO Auto-generated method stub
		//获取随机五位数
		int rannum = r.nextInt(89999)+10000;
		String nowTimeStr = sDateFormat.format(new Date());
		
		return nowTimeStr + rannum;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		System.out.println(basePath);
		
		Thumbnails.of(new File("F:/eclipsetupian/liu.jpg")).size(200, 200)
		.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/shuiyin.jpg")), 0.75f)
		.outputQuality(0.8f).toFile("F:/eclipsetupian/liu2.jpg");
	}
	
	/**
	 * storePath是文件的路径还是目录的路径
	 * 如果是storePath是文件路径者删除改文件
	 * 如果storePath为目录路径者删除该目录下的所有文件
	 * @param storePath
	 */
	public static void deleteFileOrPath(String storePath) {
		File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
		if(fileOrPath.exists()) {
			if(fileOrPath.isDirectory()) {
				File files[] = fileOrPath.listFiles();
				for(int i =0;i<files.length;i++) {
					files[i].delete();
				}
			}
			fileOrPath.delete();
		}
	}
	
}
