package com.util;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.LimitFactory;
import org.extremecomponents.table.limit.TableLimit;
import org.extremecomponents.table.limit.TableLimitFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * 
 * @author peng
 * @since 2013-9-14下午12:37:47
 */
public class ExtremeTablePage {
	static public Limit getLimit(HttpServletRequest request, int defaultPageSize) {
		Context context = new HttpServletRequestContext(request);
		LimitFactory limitFactory = new TableLimitFactory(context);
		TableLimit limit = new TableLimit(limitFactory);
		limit.setRowAttributes(Integer.MAX_VALUE, defaultPageSize);
		return limit;
	}
	/**
	 * 分页查询工具类
	 * @param offset 开始查询的位置
	 * @param length 一次获得的记录数
	 * @param hql 查询sql
	 * @param objects 条件数组
	 * @param dao
	 * @author peng 
	 * @return 结果list
	 */
	public static List queryListByPage(final int offset,final int length,final String hql,final Object[] objects,HibernateTemplate dao){
		List list = dao.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)throws HibernateException,SQLException{
				Query query=session.createQuery(hql);
				if(objects!=null && objects.length>0){
					for (int i = 0; i < objects.length; i++) {
						if(objects[i] instanceof String){
							query.setString(i, (String) objects[i]);
						}else if(objects[i] instanceof Integer){
							query.setInteger(i, (Integer) objects[i]);
						}
					}
				}
				query.setFirstResult(offset);
				query.setMaxResults(length);
				List list=query.list();
				
				return list;
			}
		});
		return list;
	}
	
}
