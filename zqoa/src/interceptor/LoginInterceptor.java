package interceptor;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 登录检测拦截器
 * @author peng
 * @since 2013-11-5下午07:10:16
 */
public class LoginInterceptor extends AbstractInterceptor {
	private static final Logger LOG = Logger.getLogger(LoginInterceptor.class);
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		
		 
	        // 取得请求相关的ActionContext实例
	        ActionContext ctx = invocation.getInvocationContext();  
	        Object session = (Object) ctx.getContext().getSession().get("user");  
	        //String user = (String) session.get(Constants.USER_SESSION);  
	        
	        // 如果没有登录，返回重新登陆  
	        if (session != null ) {
	        	Object class1= session.getClass().cast(session);
	        	
	        	String class2="com.login.vo.ZqUserModel";
	            LOG.info("class:" + class1.getClass().getName());
	           // System.out.println("test");
	            if(class1.getClass().getName().equals(class2)){
	            	return invocation.invoke();
	            }
	        }  
	 
	      //System.out.println("-------------denglu--------------");
	        //ctx.put("tip", "你还没有登录");  
	        LOG.info("用户未登录111111111!");
	        return "toLogin";
	}

}
