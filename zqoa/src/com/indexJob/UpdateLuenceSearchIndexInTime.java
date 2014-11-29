package com.indexJob;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.util.Log4j;

public class UpdateLuenceSearchIndexInTime extends QuartzJobBean {

	private int timeout;
	private static int i = 0;
	//Log4j log4j = Log4j.

	// 调度工厂实例化后，经过timeout时间开始执行调度
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
 
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		 System.out.println("定时任务执行中…"); 
		 Log4j.logMess("定时任务执行中…");
		 
		 
	}

}
