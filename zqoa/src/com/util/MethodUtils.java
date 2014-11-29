package com.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.login.vo.ZqUserModel;
import com.opensymphony.xwork2.ActionContext;

/**
 * 工具类
 * @author peng
 * @since 2013-9-7下午08:37:31
 */
public class MethodUtils {
	/**
	 * 获取登录用户的信息
	 * @return
	 */
	public static ZqUserModel getUserInfoModel(){
		ZqUserModel userModel = (ZqUserModel)ActionContext.getContext().getSession().get("user");
		return userModel;
	}
	/**
	 * 获取当前日期
	 * @param pattern 要生成的日期格式
	 * @return
	 */
	public static String getToDayDate(String pattern){
		Date date = new Date();
		DateFormat format = new SimpleDateFormat(pattern);
		
		return format.format(date);
	}
	/**
	 * 获取uuid
	 * @return
	 */
	public static String getUUID(){
		
		String s = UUID.randomUUID().toString();
		//去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
	}
	
	/*public static void main(String args[]){
		String attachMentFileName = "upload/2012103011040036.pdf";
		//deltFiles(new String[]{attachMentFileName});
		System.out.println();
	}*/
	/**
	 * 根据文件位置批量删除磁盘文件
	 * @param address_array
	 * @throws Exception 
	 */
	public static void deltFiles(String[] address_array) throws Exception {
		// TODO Auto-generated method stub
		try {
			for (String address : address_array) {
				//获取文件
				File file = new File(ServletActionContext.getServletContext()
						.getRealPath(address));
				//文件存在
				if (file.exists()) {
					// /删除文件
					file.delete();
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			Log4j.errorLog(MethodUtils.class, e);
			throw e;
		}

	}
}
