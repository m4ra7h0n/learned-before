package jlu.sqldao;

import redis.clients.jedis.Jedis;
import jlu.sqldao.UserDao;
import jlu.sqldao.FileDao;

public class RedisDao {
	Jedis jedis=null;
	
    public void connection() {
        //连接本地的 Redis 服务
        jedis = new Jedis("localhost",6379);
        // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
        // jedis.auth("123456"); 
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
    }
    public RedisDao() {
		// TODO Auto-generated constructor stub
    	connection();
    	update();
	}
    public void update() {
//    	UserDao uDao = new UserDao();
//    	FileDao fDao = new FileDao();
//		郑凯林没写这两个方法，暂时先这样吧
//    	jedis.set("userNum", uDao.userNum());
//    	jedis.set("fileNum", fDao.fileNum());
    	
    	//测试成功的例子*****************
    	this.jedis.set("userNum", "12");
    	this.jedis.set("fileNum", "23");
    	//*********************
	}
    public String userNum() {
    	return this.jedis.get("userNum");
	}
    public String fileNum(){
    	return  this.jedis.get("fileNum") ;
    }
    public int commit(){
    	return 1;
    }
//    public static void main(String[] args) {
//		RedisDao rDao = new RedisDao();
//		System.out.println(rDao.userNum());
//	}
}
