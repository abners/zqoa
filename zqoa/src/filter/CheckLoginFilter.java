package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.vo.ZqUserModel;
import com.opensymphony.xwork2.ActionContext;
import com.util.Log4j;

/**
 * 登录及编码过滤器
 * 
 * @author peng
 * @since 2013-9-3下午07:00:13
 */
public class CheckLoginFilter implements Filter {
	// encoding way
	private String encoding = null;
	private FilterConfig config;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		this.encoding = null;
		this.config = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 应用指定了编码格式
		if (this.encoding != null) {
			req.setCharacterEncoding(this.encoding);
			res.setCharacterEncoding(this.encoding);
		} else {
			// 采用默认的编码方式
			req.setCharacterEncoding("utf-8");
			res.setCharacterEncoding("utf-8");
		}
		String url = req.getRequestURL().toString();
		// 用户访问的不是登录链接
		if (url.indexOf("login.html") == -1 && url.indexOf("Login.htm") == -1) {
			if (req.getSession() != null) {
				Object userModel = req.getSession().getAttribute("user");
				if (userModel == null) {
					Log4j.logMess("用户未登录....");
					res.sendRedirect(req.getContextPath() + "/login.html");
				}
			}else{
				Log4j.logMess("session异常....");
				res.sendRedirect(req.getContextPath() + "/login.html");
			}
		}
		chain.doFilter(request, response);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		// 获取web编码方式
		this.encoding = config.getInitParameter("encoding");

		this.config = config;
	}

}
