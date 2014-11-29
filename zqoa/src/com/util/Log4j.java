package com.util;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

/**
 * 日志信息记录工具类
 * @author peng
 * @since 2013-9-1下午10:09:22
 */
public final class Log4j {
	private static final Logger logger = Logger.getLogger("Log");
	/**
	 * 记录调试信息
	 * @param debugMess
	 */
	public static final void debugLog(String debugMess){
		logger.debug(debugMess);
	}
	/**
	 * 记录错误信息
	 * @param object
	 * @param e 
	 */
	public static final void  errorLog(Object object, Exception e) {
		logger.error(object.getClass(),e);
	}
	/**
	 * 记录系统日志
	 * @param logMess
	 */
	public static final void logMess(String logMess){
		logger.info(logMess);
	}
	
}
