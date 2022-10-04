package jlu.sqldao;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 











import javax.servlet.http.HttpServletResponse;

import jlu.entity.FileMessage;
import jlu.sqldao.BaseDao;
 
public class FileDao extends BaseDao {//�ļ��ϴ�
	
	FileMessage tb_Img=null;
	List<FileMessage> tb_Imgs=null;
	/**
	 * �ϴ��ļ�ͼƬ
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * */
	public void UpImg(FileMessage tb_Img, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException{
		
		String sql="insert into img values(?,?,?,?,?,?,?)";
		this.openconnection();		
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1, tb_Img.getId() );
		ptmt.setInt(2, tb_Img.getUserId());
		ptmt.setString(3, tb_Img.getFileName());
		ptmt.setString(4, tb_Img.getFilePath());
		ptmt.setString(5, tb_Img.getDate());
		ptmt.setString(6, tb_Img.getName());
		ptmt.setString(7, tb_Img.getKind());
		try {
			PrintWriter out=response.getWriter();
			out.println("MMP!");
		
		   ptmt.execute();
		
			out.println("QWQ!");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			PrintWriter out=response.getWriter();
			e.printStackTrace(out);
		}finally{
			PrintWriter out1=response.getWriter();
			out1.println("QQQQ");}
		
		//conn.commit();
	}
	/**
	 *�õ��������
	 * */
	public List<FileMessage> AllImg() throws SQLException{
		tb_Imgs=new ArrayList<FileMessage>();
		String sql="select * from IMG";
		try {
			this.openconnection();
			PreparedStatement ptmt=conn.prepareStatement(sql);
			ResultSet rs=ptmt.executeQuery();
			System.out.println("ok3");
			if(rs!=null){
				System.out.println("ok4");
				while(rs.next()){
					FileMessage tb_Img=new FileMessage();
					tb_Img.setId(rs.getInt("id"));
					tb_Img.setUserId(rs.getInt("userid"));
					tb_Img.setFileName(rs.getString("filename"));
					tb_Img.setFilePath(rs.getString("filepath"));
					tb_Img.setDate(rs.getString("uptime"));
					tb_Img.setName(rs.getString("name"));
					tb_Img.setKind(rs.getString("kind"));
					tb_Imgs.add(tb_Img);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ok5");
		return tb_Imgs;
	}
	public List<FileMessage> ImgOfOnePerson(String name) throws SQLException{
		tb_Imgs=new ArrayList<FileMessage>();
		String sql="select * from IMG where name='" + name+"'";
		try {
			this.openconnection();
			PreparedStatement ptmt=conn.prepareStatement(sql);
			ResultSet rs=ptmt.executeQuery();
			System.out.println("ok3");
			if(rs!=null){
				System.out.println("ok4");
				while(rs.next()){
					FileMessage tb_Img=new FileMessage();
					tb_Img.setId(rs.getInt("id"));
					tb_Img.setUserId(rs.getInt("userid"));
					tb_Img.setFileName(rs.getString("filename"));
					tb_Img.setFilePath(rs.getString("filepath"));
					tb_Img.setDate(rs.getString("uptime"));
					tb_Img.setName(rs.getString("name"));
					tb_Img.setKind(rs.getString("kind"));
					tb_Imgs.add(tb_Img);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ok5");
		return tb_Imgs;
	}
	/**
	 *���ز鿴�ļ� 
	 * */
	public List<FileMessage> FindImg(String context) throws SQLException{
		tb_Imgs=new ArrayList<FileMessage>();
		String sql="select * from img where flieName like '%"+context +"%' or fliePath like '%"+context +"%' or id like '%"+context +"%'";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()){
			FileMessage tb_Img=new FileMessage();
			tb_Img.setId(rs.getInt("id"));
			tb_Img.setUserId(rs.getInt("userid"));
			tb_Img.setFileName(rs.getString("fileName"));
			tb_Img.setFilePath(rs.getString("filePath"));
			ptmt.setString(5,  tb_Img.getDate());
			tb_Imgs.add(tb_Img);
		}
		return tb_Imgs;
	}
}
