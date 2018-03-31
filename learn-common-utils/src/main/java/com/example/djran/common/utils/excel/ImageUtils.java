package com.example.djran.common.utils.excel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created on 2018/3/28
 * 图片工具类
 * @author d.djran@gmail.com
 */
@Slf4j
public class ImageUtils {

    /**
     * 生成二维码
	 * @param url
     * @param title
     * @return
     */
	public static BufferedImage genQrcode(String url, String title) {
		// 生成二维码图片
		BufferedImage qrcode = ImageUtils.createImage(url);
		try {
			return ImageUtils.addWord(qrcode, title);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 给图二维码上增加文字
	 * @param image
	 * @param string
	 * @return
	 */
	public static BufferedImage addWord(BufferedImage image,
			String string) {

		// 获取Graphics对象,便于对图像进行各种绘制操作
		image.getWidth();
		image.getHeight();
		Graphics g = image.getGraphics();
		g.setColor(Color.black);
		g.setFont(new Font("宋体", Font.BOLD, 20));
		g.drawString(string, 30, image.getHeight() - 10);
		g.dispose();
		return image;
	}

	/**
	 * 合并图片（第二张图正中间插入）
	 * 
	 * @param first
	 *            第一张图 从url地址从获取
	 * @param second
	 *            第二张图 从url地址中获取
	 *            输出的图
	 */
	public static BufferedImage createPicTwo2(String first, String second) {
		try {
			// 读取第一张图片
			// File fileOne = new File(first);
			BufferedImage ImageOne = ImageIO.read(new URL(first));
			
			BufferedImage ImageTwo = ImageIO.read(new URL(second));
			return createPicTwo2(ImageOne,ImageTwo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 合并图片（第二张图正中间插入）
	 * 
	 * @param first
	 *            第一张图 从url地址从获取
	 * @param second
	 *            第二张图 从url地址中获取
	 *            输出的图
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public static BufferedImage createPicTwo2(BufferedImage first, String second) throws MalformedURLException, IOException{
		return createPicTwo2(first,ImageIO.read(new URL(second)));
	}
	/**
	 * 合并图片（第二张图正中间插入）
	 * 
	 * @param first
	 *            第一张图 从url地址从获取
	 * @param second
	 *            第二张图 从url地址中获取
	 *            输出的图
	 */
	public static BufferedImage createPicTwo2(BufferedImage first, BufferedImage second) {
		try {
			// 读取第一张图片
			// File fileOne = new File(first);
			BufferedImage ImageOne = first;
			int width = ImageOne.getWidth();// 图片宽度
			int height = ImageOne.getHeight();// 图片高度
			// 从图片中读取RGB
			int[] ImageArrayOne = new int[width * height];
			ImageArrayOne = ImageOne.getRGB(0, 0, width, height, ImageArrayOne,
					0, width);
			// 对第二张图片做相同的处理
			// File fileTwo = new File(second);
			BufferedImage ImageTwo = second;
			int widthTwo = ImageTwo.getWidth();// 图片宽度
			int heightTwo = ImageTwo.getHeight();// 图片高度
			int[] ImageArrayTwo = new int[widthTwo * heightTwo];
			ImageArrayTwo = ImageTwo.getRGB(0, 0, widthTwo, heightTwo,
					ImageArrayTwo, 0, widthTwo);
			// 生成新图片
			BufferedImage imgageNew = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			imgageNew.setRGB(0, 0, width, height, ImageArrayOne, 0, width);// 设置左半部分的RGB
			imgageNew.setRGB((width - widthTwo) / 2, (height - heightTwo) / 2,
					widthTwo, heightTwo, ImageArrayTwo, 0, widthTwo);// 设置右半部分的RGB
			return imgageNew;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 创建图片流(默认430px)
	 * @param content 二维码内容
	 */
	public static BufferedImage createImage(String content){
		return createImage(content,430,430);
	}
	/**
	 * 创建图片流
	 * @param content 二维码内容
	 */
	public static BufferedImage createImage(String content,int width,int height) {
		BufferedImage imagenew =new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				imagenew.setRGB(x, y, 0xFFFFFFFF);
			}
		}
		BufferedImage image = null;
		try {
			Map<EncodeHintType, Object> hints = new ConcurrentHashMap<EncodeHintType, Object>();
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			hints.put(EncodeHintType.MARGIN, 1);
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width-12, height-12, hints);
			int width_ = bitMatrix.getWidth();
			int height_ = bitMatrix.getHeight();
			image = new BufferedImage(width_, height_, BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < width_; x++) {
				for (int y = 0; y < height_; y++) {
					image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
				}
			}
			
			return createPicTwo2(imagenew,image);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("=QRCodeUtil=createImage=>" + e.getMessage());
		}
		return image;
	}


	/**
	 * 将图片转换为base64字符串
	 * @param image
	 * @return
	 */
	public static String base64Encode(BufferedImage image){
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "png", outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return Base64Utils.encodeToString(outputStream.toByteArray());
	}
	/**
	 * 将图片转换为base64字符串
	 * @param image
	 * @return
	 */
	public static BufferedImage base64Decode(String image){
		byte[] imgageByte= Base64Utils.decodeFromString(image);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(imgageByte);
		try {
			return ImageIO.read(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


//	public static void main(String[] args) throws MalformedURLException,
//			IOException {
//		String first = "http://xp.xiaxiaw.com/hlman-pic/goods/201512/1450063359830.jpg";
//		String second = "http://xp.xiaxiaw.com/hlman-pic/goods/201512/1450063446984.jpg?s=220x220";
//		// BufferedImage ImageOne = ImageIO.read(new URL(first));
//		BufferedImage img=createPicTwo2(first, second);
//		img = addWord(img, "你好");
//		ImageIO.write(img, "jpg", new FileOutputStream(new File(
//				"D:\\pic\\aaa.jpg")));
//		// BufferedImage img=createPicTwo2(first, second);
//		// tt.writeImageLocal("D:\\cc.jpg", tt.modifyImagetogeter(b, d));
//		// 将多张图片合在一起
//		System.out.println("success");
//	}

}