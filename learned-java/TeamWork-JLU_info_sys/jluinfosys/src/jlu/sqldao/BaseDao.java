package jlu.sqldao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BaseDao {

	protected Connection conn = null;

	protected void openconnection() throws ClassNotFoundException,SQLException{		
		try {
			if( conn == null || conn.isClosed() ){
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String dbURL ="jdbc:oracle:thin:@localhost:1521:xe";
				conn = DriverManager.getConnection(dbURL,"system","YUMITOU111");
				System.out.println("连接=======================================================================================成功");
			}
		} catch (ClassNotFoundException e) {
			//System.out.println("连接失败了=================================================");
		} catch (SQLException e) {
			System.out.println("连接失败了=================================================");
			e.printStackTrace();
			throw e;
		}
	}


	public void beginTransaction() throws Exception{
	
		this.openconnection();		
		conn.setAutoCommit(false);		
	}
	
	public void rollback() throws Exception{
		
		if(conn != null){			
				conn.rollback();			
		}		
	}
	
	public void commit() throws Exception{
		if(conn != null){			
			conn.commit();				
		}	
	}

	/*
	 * �ر���Դ
	 */
	public void closeResource() throws Exception{		
		if(conn != null){
			conn.close();
		}			
		
	}
	
	//��ݷ�ҳ��������Ҫ�Ľ��  iStart��1��ʼ>=iStart; <iEnd
		public  ResultSet executeQuery(String strSql,int iStart,int iEnd) throws Exception{	
			ResultSet rs = null;				
			Statement st = conn.createStatement();	
			if(st != null){				
				String strNew = "select * from  (select rownum numrow,tb.* from ( " + strSql + " ) tb ) where numrow >=" + iStart + " and numrow<" + iEnd ;					
				rs = st.executeQuery(strNew);
			}			
			
			return rs;
		}   
		
	public  int getRecCount(String strSql) throws Exception{
		int iRec = 0;
		
		//mysql �б���ӱ���w
		String strExe = "SELECT count(*) FROM (" + strSql + ") w";	
		Object obj = executeScalar(strExe);
		if(obj != null){
			iRec = Integer.parseInt(obj.toString());		
		}
		
		return iRec;
	}
	
	//���ز�ѯ���ĵ�һ�е�һ�е�ֵ(---���û�в�ѯ����򷵻�null)
		public  Object executeScalar(String strSql) throws Exception{
			ResultSet rs;			
			Object obj;
			
			obj = null;
			rs = null;			
				
			Statement st = conn.createStatement();						
			rs = st.executeQuery(strSql);
			if( rs != null ){
				while( rs.next()){
					if(rs.isFirst()){
						obj = rs.getObject(1);  //columnIndex - ��һ������ 1
						break;
					}						
				}
				rs.close();
			}							
			
			return obj;
		}    
	
}
