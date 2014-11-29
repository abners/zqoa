package com.util;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.extremecomponents.table.limit.Limit;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * ec帮助类
 * @author peng
 * @since 2013-9-14下午01:23:25
 */
public abstract class EcPageHelper {
	
	public PageBean getPageBean(Map conditionMap){
		Limit limit = (Limit)conditionMap.get("_limit");
		//int count = Integer.parseInt(conditionMap.get("_defaultRowsDisplayed").toString()) ;
		
		return new PageBean(limit);
	}
	
}
