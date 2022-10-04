package jlu.control.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import jlu.sqldao.FileDao;
import jlu.entity.FileMessage;
import jlu.sqldao.RedisDao;
/**
 * Servlet implementation class AdminAjaxServlet
 */
@WebServlet("/AdminAjax")
public class AdminAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static List<FileMessage> files = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        //获取ajax传递的参数，和获取表单数据的方式一样
        String type=request.getParameter("type");
        if(type.equals("files")){
        	files(writer);
        }else if (type.equals("welcomepage")){
        	welcomepage(writer);
        }else if(type.equals("rbac")){
        	rbac(writer);
        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	private void rbac(PrintWriter writer) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//welcome页面请求数据，admin首页展示，使用redis
	private void welcomepage(PrintWriter writer){
		try {
			RedisDao rDao = new RedisDao();
			JSONArray array = new JSONArray();
			JSONObject object = new JSONObject();
			object.put("userNum", rDao.userNum());
			object.put("fileNum", rDao.fileNum());
			array.add(object);
			writer.print(array);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//file-list页面请求所有的文件信息
	private void files(PrintWriter writer){
		files=new ArrayList<FileMessage>();
		System.out.println("ok1");
    	FileDao fd = new FileDao();
    	JSONArray files = new JSONArray();
    	try {
    		System.out.println("ok2");
			for (FileMessage f: fd.AllImg()){
				//数据都转化成json然后返回
				System.out.println("ok3");
				JSONObject tmp = new JSONObject();
				tmp.put("id", f.getId());
				tmp.put("filename", f.getFileName());
				tmp.put("date", f.getDate());
				tmp.put("filepath", f.getFilePath());
				tmp.put("userid", f.getUserId());
				files.add(tmp);
			}
			writer.print(files);
    		
    		//测试成功的例子**************
//    		JSONArray array = new JSONArray();
//    		
//    		JSONObject object = new JSONObject();
//    		object.put("id", 1);
//    		object.put("filename", "xxx.txt");
//    		object.put("date", "2021/xx/xx");
//    		object.put("filepath", "/files/xxx/xxx");
//    		object.put("auther", "xjj");
//    		object.put("status", "normal");
//    		
//    		JSONObject object2 = new JSONObject();
//    		object2.put("id", 1);
//    		object2.put("filename", "xxx.txt");
//    		object2.put("date", "2021/xx/xx");
//    		object2.put("filepath", "/files/xxx/xxx");
//    		object2.put("auther", "xjj");
//    		object2.put("status", "normal");
//    		
//    		
//    		array.add(object);
//    		array.add(object2);
//    		
//    	    writer.print(array);
			
			//**************
			
			//json返回大概样子
			//[{"id":1, "auther":"xjj", "filename": "xxx", "filepath": "xxx", "date":"xxx", "status":"normal"},
			//{"id":1, "userid":1, "filename": "xxx", "filepath": "xxx", "date":"xxx"}]
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
