package jlu.control.servlet;

import java.io.IOException;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jlu.biz.UserBiz;
import jlu.entity.*;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		String type = request.getParameter("type");
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		res.setContentType("text/html;charset=utf-8");
		String ss="";
		
		System.out.println("okok=====================================");
		if(uname != null && pwd !=null && !uname.equals("") && !pwd.equals("")){
			try {
				//调用登录函数
				System.out.println("okkkk=====================================");
				UserBiz biz = new UserBiz();
				System.out.println("ok=====================================");
				//存在用户并且用户名密码正确
				if(biz.isAccess(uname, pwd) ){
					//弹窗登录成功
					ss="<script type='text/javascript'>alert('登录成功');</script>";
					//res.getWriter().print(ss);
					if(type.equals("cus")){
						request.getSession().setAttribute("sessionid", "customer");
						request.getSession().setAttribute("name", uname);
						ss+="<script>window.location.href='/jluinfosys/html/mine.jsp'</script>";
					}
				    else if(type.equals("admin")){
					    request.getSession().setAttribute("sessionid", "admin");
					    ss+="<script>window.location.href='/jluinfosys/html/admin/index/index.html'</script>";
					}
					res.getWriter().print(ss);
				}else{
					System.out.println("ok3=====================================");
					ss="<script type='text/javascript'>alert('用户名或密码不存在');</script>";
					if(type == "admin"){
						ss+="<script>window.location.href='/jluinfosys/html/admin/index/login.html'</script>";
					}else if(type == "cus"){
						ss+="<script>window.location.href='/jluinfosys/html/index.jsp'</script>";
					}
					res.getWriter().print(ss);
				}	
			} catch (Exception e) {
				ss="<script type='text/javascript'>alert('exception error');</script>";
				res.getWriter().print(ss);
			}
		}else{
			//用户名密码为空弹窗
			request.setAttribute("msg","用户名或密码不能为空");
			ss="<script type='text/javascript'>alert('用户名或密码不能为空');</script>";
			//跳转回原来的页面
			if(type == "admin"){
				ss+="<script>window.location.href='/jluinfosys/html/admin/index/login.html'</script>";
			}else if(type == "cus"){
				ss+="<script>window.location.href='/jluinfosys/html/index.jsp'</script>";
			}
			res.getWriter().print(ss);
		}
	}

}
