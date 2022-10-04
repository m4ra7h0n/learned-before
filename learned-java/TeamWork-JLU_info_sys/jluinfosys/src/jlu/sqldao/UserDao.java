package jlu.sqldao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jlu.entity.User;

public class UserDao extends BaseDao{
	
	public List<User> findall() throws Exception{//������ݿ��е������û����ȫ����Ϣ
		
		List<User> users=new ArrayList<User>();
		String sql="select * from JLU_USER";
		try{
		this.openconnection();
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		if(rs!=null){
			while(rs.next()){
				User user=new User();
				user.setUserid(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));   
				user.setPasswd(rs.getString("passwd"));
				user.setPermission(rs.getInt("permission"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				users.add(user);
			}
		}		
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		
		return users;
	}
	
	public void addUser(User user) throws ClassNotFoundException, SQLException{//����ݿ������û�
		if(user != null){
			String sql = "insert into JLU_USER values(?,?,?,?,?,?)";
			this.openconnection();			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getUserid());
			ps.setString(2, user.getUsername());		
			ps.setString(3, user.getPasswd());
			ps.setInt(4, user.getPermission());
			ps.setString(5, user.getName());
			ps.setString(6, user.getEmail());
			ps.executeUpdate();				
		}
	}
	
	public void delUser(User user) throws ClassNotFoundException, SQLException{//ɾ���û�
		if(user != null){
			String sql = "delete from JLU_USER where userid=?";//��Ϊ�û���userid������Ψһ��ʶһ���û��������������ֻͨ��userid���в��Ҳ�ɾ��
			this.openconnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getUserid());
			ps.executeUpdate();				
		}
	}
	
	public User getUser (String username)throws Exception{//����û�������뷵��ĳһ�û���ȫ����Ϣ
		User user=null;
		String sql="select * from JLU_USER WHERE name='" + username+"'";
		this.openconnection();
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		if(rs!=null){
			while(rs.next()){
				user=new User();
				user.setUserid(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));   
				user.setPasswd(rs.getString("passwd"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPermission(rs.getInt("permission"));
			}
		}				
		
		return user;
	}
	
	public User login (String username,String passwd)throws Exception{
		User user=null;
		String sql="select * from JLU_USER WHERE name='" + username + "' and passwd='" + passwd + "'";
		this.openconnection();
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		if(rs!=null){
			while(rs.next()){
				user=new User();
				user.setUserid(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));   
				user.setPasswd(rs.getString("passwd"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPermission(rs.getInt("permission"));
			}
		}
		return user;
	}
	
	

}


