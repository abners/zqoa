package com.util;

import java.util.Map;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

/**
 * 自定义类型转换拦截器
 * 
 * @author peng
 * @since 2013-10-7下午08:47:34
 */
public class IntegerConverter extends DefaultTypeConverter {
	public Object convertValue(Map<String, Object> context, Object value,
			Class toType) {
		try {
			//要转换为Integer
			if (Integer.class == toType || Integer.TYPE == toType) {
				String[] parameters = (String[])value;  //相当于request.getParameterValues();
				return Integer.valueOf((int) longValue(parameters[0]));
			}else if(toType == String.class){
				Integer integer = (Integer)value;
				return integer.toString();
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
