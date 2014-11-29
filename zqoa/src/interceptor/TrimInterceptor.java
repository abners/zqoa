package interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 自定义去除空格拦截器
 *
 * @author peng
 * @since 2013-10-9上午09:11:44
 */
public class TrimInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(TrimInterceptor.class);
   /**
    * <p>方法描述: [trime掉空格]</p>
    *
    * @param invocation 参数说明
    *
    * @return 返回结果的说明
    *
    * @throws Exception 抛出异常的原因
    */
    public String intercept(ActionInvocation invocation) throws Exception {
    	LOG.info("过滤请求数据的首尾空格");
        Map<String, Object> parameters = invocation.getInvocationContext().getParameters();
        Set entrySet = parameters.entrySet();
        String[] strings = null;
        String[] values = null;
        int strLength = 0;
        for (Iterator it = entrySet.iterator(); it.hasNext();) {
            Entry entry = (Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof String[]) {
            	values = (String[]) value;//类型转换
            	strLength = values.length;
            	strings = new String[strLength];
                for (int i = 0; i < strLength; i++) {
                    strings[i] = values[i].trim();
                }

                parameters.put((String) key, strings);
            }
        }
        
        invocation.getInvocationContext().setParameters(parameters);
        
        return invocation.invoke();

        
    }
}
