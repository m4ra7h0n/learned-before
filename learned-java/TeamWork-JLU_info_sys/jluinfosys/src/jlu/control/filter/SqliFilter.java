package jlu.control.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class SqiCheck
 */
//@WebFilter("/*")
public class SqliFilter implements Filter {
	
	
	//防止sql注入，配置web.xml加入更多检测页面

	
    public SqliFilter() {
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
		HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse)response;
        
        Enumeration params = req.getParameterNames();
        String sql = "";
        while (params.hasMoreElements()) {
            String name = params.nextElement().toString();

            String[] value = req.getParameterValues(name);
            for (int i = 0; i < value.length; i++) {
                sql = sql + value[i];
            }
        }
        //System.out.println("============================SQL"+sql);
        if (sqlValidate(sql)) {
        	res.setContentType("text/html;charset=utf-8");
        	String ss="<script type='text/javascript'>alert('Invaild symbol... are you a stupid hacker?');window.location.href='/jluinfosys/html/index.jsp';</script>";
            res.getWriter().print(ss);
            //String ip = req.getRemoteAddr();
        } else {
        	try {  
        		chain.doFilter(request,response); 
            } catch (RuntimeException e) { 
                request.setAttribute("BsException", e);
                request.getRequestDispatcher("/jluinfosys/html/error.html").forward(request, response);
                e.printStackTrace();  
            } 
            
        }
    }
     

    protected static boolean sqlValidate(String str) {
        str = str.toLowerCase();//统一转为小写
        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
                "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
                "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#|&|&&|";
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf(badStrs[i]) >= 0) {
                return true;
            }
        }
        return false;
    }

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
