# 配置
## oracle连接eclipse
[eclipse连接参考](https://blog.csdn.net/xuejian0616/article/details/46849665)  
[代码连接参考](https://blog.csdn.net/u012062455/article/details/52442838)  
默认最高权限登录:sqlplus.exe / as sysdba  
增加用户：create user xjj identified by 1234  
给用户权限：grant connect,resource,dba to xjj  
连接url：jdbc:oracle:thin:@localhost:1521:xe  
localhost是地址在C:\Windows\System32\drivers\etc\host修改  
1521是端口netstat -an看1521端口是否监听  
xe是sid获取sid：select value from v$parameter where name=‘instance_name’;  
oraclexe\app\oracle\product\11.2.0\server\jdbc\lib\ojdbc6粘贴到WEB-INF/lib下作为driver  

