
package jlu.control.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SessionFilter
 */
//@WebFilter("/*")
public class SessionFilter implements Filter {
	//防止失效会话访问，访问先登录设置session。配置web.xml加入更多检测页面
	
//	  private String excludedPages;
//    private String[] excludedPageArray;
    
    public SessionFilter() {
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
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		boolean isExcludedPage = false;
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        
//        System.out.println("=======================LoginFilter==============================" + request.getContextPath() + "  " + request.getServletPath());
//        for (String page : excludedPageArray) {//�ж��Ƿ��ڹ���url֮��
//            if(req.getServletPath().matches(page)){
//                isExcludedPage = true;
//                break;
//            }
//        }
        if (isExcludedPage) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = req.getSession();
            if (session.getAttribute("sessionid") != null) {
                chain.doFilter(request, response);
            } else {
//                System.out.println("=======================LoginFilter==============================");
            	res.setContentType("text/html;charset=utf-8");
//                res.sendRedirect("/jluinfosys/html/index.html");
                String ss="<script type='text/javascript'>alert('please login first');window.location.href='/jluinfosys/html/index.jsp';</script>";
                res.getWriter().print(ss);
                return;
            }
        }
    }
 
    public void init(FilterConfig config) throws ServletException {
//        excludedPages = config.getInitParameter("excludedPages");
//        if (excludedPages != null) {
//            excludedPageArray = excludedPages.split(",");
//        }
 
    }


}
