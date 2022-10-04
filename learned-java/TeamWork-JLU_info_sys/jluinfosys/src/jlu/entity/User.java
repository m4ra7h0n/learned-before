package jlu.entity;

public class User {
	
		private int userid;
		private String username;
		private String passwd;
		private int permission;
		private String name;
		private String email;
		
		public int getUserid() {
			return userid;
		}
		public void setUserid(int userid) {
			this.userid = userid;
		}
		
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPasswd() {
			return passwd;
		}
		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}
		public int getPermission() {
			return permission;
		}
		public void setPermission(int permission) {
			this.permission = permission;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public User(){}
		public User(int userid,String username,String passwd,int permission,String name,String email){
			this.userid=userid;
			this.username=username;
			this.passwd=passwd;
			this.permission=permission;
			this.name=name;
			this.email=email;
		}
	

}
