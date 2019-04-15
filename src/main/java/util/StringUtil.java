package util;


import java.security.MessageDigest;

import java.text.SimpleDateFormat;
import java.util.*;


public class StringUtil {
	/**
	 * @name 转换字符串
	 * @description 将 string 类型 转换成 date 类型，返回"yyyy/M/d HH:mm:ss"格式
	 * @author 赵丰登
	 * @return
	 */
	public static Date parseTimestamp(String dateText) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			return sdf.parse(dateText);
		} catch (Exception e) {
		}

		sdf = new SimpleDateFormat("yyyy/M/d HH:mm:ss");
		try {
			return sdf.parse(dateText);
		} catch (Exception e) {
		}
		sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			return sdf.parse(dateText);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * @name 转换时间类型
	 * @description 将 date 类型转换成 string 类型，返回format格式"yyyy-MM-dd HH:mm:ss"
	 * @author 赵丰登
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	private static String lastFileDate = "0";
	private static int fileIndex = 0;

	/**
	 * 得到文件名称
	 * 
	 * @return
	 */
	public static String getFileNameBySysDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String curDate = sdf.format(new Date());
		if (curDate.equals(lastFileDate)) {
			fileIndex++;
		} else {
			fileIndex = 0;
		}
		lastFileDate = curDate;
		return curDate + fileIndex;
	}

	/**
	 * @Description:加密-16位大写
	 * @author:liuyc
	 * @time:2016年5月23日 上午11:15:33
	 */
	public static String encrypt16(String encryptStr) {
		return MD5(encryptStr).substring(8, 24);
	}



	/**
	 * @name MD5加密
	 * @description MD5加密
	 * @author 赵丰登
	 * @return
	 */
	public static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * @name 判断 string 类型是否为空
	 * @description 判断 string 类型是否为空，是则返回 true ，否则返回 false
	 * @author 赵丰登
	 * @return
	 */
	public static boolean isNullOrEmpty(String text) {

		return text == null || text.isEmpty() || text.equals("null") || "".equals(text.trim());
	}
	
	

	public static Boolean isNotNullOrEmpty(String str) {
		return !isNullOrEmpty(str);
	}
	

	/**
	 * @name 转换字符串
	 * @description 将 string 类型 转换成 date 类型
	 * @author 赵丰登
	 * @return
	 */
	public static Date parseDate(String dateText) {
		String[] formats = { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "yyyy/M/d", "yyyy年MM月dd日" };
		SimpleDateFormat sdf;
		for (String format : formats) {
			try {
				sdf = new SimpleDateFormat(format);
				return sdf.parse(dateText);
			} catch (Exception e) {
			}
		}
		return null;
	}
}
