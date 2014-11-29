package com.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 系统读取信息类
 * @author peng
 * @since 2013-9-15下午01:04:15
 */
public class SystemProperties {
	/**
	 * 配置文件
	 */
	private static String FILE_NAME="system.properties";
	
	private static Properties props = new Properties(); ;
	
	static {
		try {
			InputStream is = SystemProperties.class.getClassLoader().getResourceAsStream(FILE_NAME);
			props.load(is);
			is.close();
		} catch (FileNotFoundException e1) {
		    e1.printStackTrace();
		} catch (IOException e1) {
		    e1.printStackTrace();
		}
		
	}
	/**
	 * 根据key值从system.properties文件读取对应得value值
	 * @param key
	 * @return
	 */
	public static String getPropsValue(String key){
		
		try {
			//解决中文乱码
			return new String(props.getProperty(key).getBytes("ISO-8859-1"),"UTF-8") ;
		}catch (NullPointerException e) {
			e.printStackTrace() ;
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
