package com.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5加密工具类
 * @author peng
 * @since 2013-9-1下午06:17:24
 */
public class MD5Encryption {
	
	/**
	 * 获取32位md5加密结果
	 * @param encri 要加密的字符
	 */
	public static String getMD5(String encri){ 
	         MessageDigest messageDigest = null;  
	  
	        try {  
	             messageDigest = MessageDigest.getInstance("MD5");  
	  
	             messageDigest.reset();  
	  
	             messageDigest.update(encri.getBytes("UTF-8"));  
	         } catch (NoSuchAlgorithmException e) {  
	             System.out.println("NoSuchAlgorithmException caught!");
	             //Log4j.logMess(e.getMessage());
	             System.exit(-1);  
	         } catch (UnsupportedEncodingException e) {  
	             e.printStackTrace();
	             //Log4j.logMess(e.getMessage());
	         }  
	  
	        byte[] byteArray = messageDigest.digest();  
	  
	        StringBuffer md5StrBuff = new StringBuffer();  
	  
	        for (int i = 0; i < byteArray.length; i++) {              
	            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
	                 md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
	            else  
	                 md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
	         }  
	  
	        return md5StrBuff.toString();    
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getMD5("myPwd"));
	}

}
