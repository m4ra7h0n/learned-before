package jlu.control.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet Filter implementation class UploadFilter
 */
//@WebFilter("/upload")
public class UploadFilter implements Filter {

    //设置白名单，禁止上传无关的文件，配置web.xml增加更多检测路径
    public UploadFilter() {
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
		//文件上传漏洞防御，没写，但是比较重要，设置白名单，限制上传文件类型。
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpServletResponse res = (HttpServletResponse)response;
		HttpServletRequest req = (HttpServletRequest)request;
		Part part=req.getPart("file");
		String fileNameInfo=part.getHeader("content-disposition");
		String fileName=fileNameInfo.substring(fileNameInfo.indexOf("filename=")+10,fileNameInfo.length()-1);
		String allow = new String(".pdf|.test");
		int i = 0;
		for(String each : allow.split("\\|")){
			if(fileName.contains(each)){
				i=1;
				break;
			}
		}
		if(i==0){
			out.print("<script>alert('只允许上传pdf类型文件');window.location.href='/jluinfosys/html/mine.jsp';</script>");
		}else if(i==1){
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
