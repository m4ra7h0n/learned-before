package jlu.control.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jlu.entity.User;
import jlu.sqldao.UserDao;

/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/reg")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		String pwd2 = request.getParameter("pwd2");
		String email = request.getParameter("email");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		res.setContentType("text/html;charset=utf-8");
		String ss = "";
		try {
			if (uname == null || pwd == null || uname.equals("") || pwd.equals("")) {
				request.setAttribute("msg", "用户名密码为空");
				ss = "<script type='text/javascript'>alert('用户名密码为空');</script>";
			} else if (!pwd.equals(pwd2)) {
				request.setAttribute("msg", "第二次输入密码错误");
				ss = "<script type='text/javascript'>alert('第二次输入密码错误'</script>);";
			} else {
				UserDao uDao = new UserDao();
				User user = new User();
				user.setEmail(email);
				user.setName(uname);
				System.out.println("set=================================================================");
				user.setPasswd(pwd);
				user.setUserid(1000);
				user.setUsername("xjj");
				user.setPermission(1);
				System.out.println("add===============================================================");
				uDao.addUser(user);
				System.out.println("getsession===============================================================");
				ss = "<script type='text/javascript'>alert('注册成功');</script>";
			}
			System.out.println("执行到这里了没有=================================================");
			ss+="<script>window.location.href='/jluinfosys/html/index.jsp'</script>";
			res.getWriter().print(ss);
			System.out.println("注册成功了注册成功了==============================================================================================");
		} catch (Exception e) {
			ss+="<script>window.location.href='/jluinfosys/html/error.html'</script>";
			res.getWriter().print(ss);
		}
	}

}
