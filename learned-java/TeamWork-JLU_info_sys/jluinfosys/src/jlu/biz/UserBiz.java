package jlu.biz;
import jlu.sqldao.UserDao;
import jlu.entity.User;
public class UserBiz {
         public boolean isUser(String username) throws Exception{//ͨ���û����ж��û��Ƿ���ڣ����ڷ���true,���򷵻�false.
        	 UserDao userdao=new UserDao();
        	 User uuser=userdao.getUser(username);
        	 if(uuser!=null){
        		 return true;}
        	 else{
        		 return false;}
         }
         public boolean isAccess(String username,String passwd) throws Exception{//ͨ���û����ж��û��Ƿ���ڣ����ڷ���true,���򷵻�false.
        	 UserDao userdao=new UserDao();
        	 User uuser=userdao.login(username, passwd);
        	 if(uuser!=null){
        		 return true;}
        	 else{
        		 return false;}
         }
}
