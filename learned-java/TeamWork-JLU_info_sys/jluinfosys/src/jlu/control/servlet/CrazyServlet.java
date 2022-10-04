package jlu.control.servlet;

//import java.io.File;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
//import java.io.File;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import jlu.entity.FileMessage;
//import java.util.Collection;
import java.lang.String;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
//import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import jlu.sqldao.FileDao;
import jlu.Img.PDFTOPICTURES;
/**
 * Servlet implementation class CrazyServlet
 */
@WebServlet(name="CrazyServlet",value="/upload")
@MultipartConfig
public class CrazyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrazyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		request.setCharacterEncoding("UTF-8");
		//获取普通请求参数
		String name =request.getParameter("file");
		String kind =request.getParameter("kind");
		String uname =request.getParameter("uname");
		out.println("普通的name参数为："+name+"<br/>");
		//获取文件上传域
		Part part=request.getPart("file");
		//获取文件上传的类型
		out.println("上传的文件类型为："+part.getContentType()+"<br/>");
		//获取上传文件的大小
		out.println("上传文件的大小为："+part.getSize()+"<br/>");
		//获取该文件上传域的Header Name
		//Collection<String> headerNames=part.getHeaderNames();
		//获取包含原始文件名的字符串
		String fileNameInfo=part.getHeader("content-disposition");
		//提取上传文件的原始文件名
		String fileName=fileNameInfo.substring(fileNameInfo.indexOf("filename=")+10,fileNameInfo.length()-1);
		out.println("文件的原始文件名为："+fileName+"<br/>");
		//将上传的文件写入服务器
		
		String savePath1=request.getServletContext().getRealPath("/uploadFiles/");
		String savePath=savePath1+File.separator+fileName;
	    part.write(savePath);
	    out.println("上传成功");
	    System.out.println("上传成功");
		System.out.println(savePath);
		String temp = savePath.replaceAll( "\\\\","\\\\\\\\");
		System.out.println(temp);
		transform(temp);
		//out.println("怎么回事");
		Date date =new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		String time_string=sdf.format(date);
		out.println(time_string);
		
		FileMessage fims=new FileMessage();
		fims.setDate(time_string);
		fims.setFileName(fileName);
		fims.setFilePath(savePath);
		fims.setUserId(2);
		fims.setId(0);
		fims.setName(uname);
		fims.setKind(kind);
		FileDao fd=new FileDao();
		try {
			out.println("您传上去了吗？??");
			fd.UpImg(fims,response);
			out.println("您传上去了");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	void transform(String path) throws InvalidPasswordException, IOException{
		PDFTOPICTURES pdff = new PDFTOPICTURES(path);
		pdff.toPictures(1920,1080);
		System.out.println("成功生成首页图片");
	}
}