package jlu.control.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import jlu.control.auxiliary.*;
/**
 * Servlet Filter implementation class XssFilter
 */


//@WebFilter("/XssFilter")
public class XssFilter implements Filter {

    //过滤xss，输出页面的时候把关键字符实体化编码
    public XssFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//伪造一个过滤好的request
		chain.doFilter(new RequestWrapper((HttpServletRequest)request), response);
    }

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

