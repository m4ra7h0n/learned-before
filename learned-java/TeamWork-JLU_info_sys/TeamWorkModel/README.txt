说下我从啥也不知道然后通过搜集资料搭建这个结构的过程。
首先我把学校讲的各种名词列了出来，把能找到的全都列出来，例如JPA，EJB等。然后上网搜，了解一下。总结了他们的大概含义之后我写在了README.md上，然后把讲的好的文章的URL写了上去。
接着我去找优秀的架构。发现阿里的架构体系，理解了一下。
之后去github上翻了翻现成的javaweb项目。结合了一下成品的目录结构。然后规划出这个。
目录里的Example都是别人的代码，复制粘贴到自己的项目中做例子。
之后还会有更多的目录，但那是在我们团队一起讨论之后，共同决定的事情。


举两个小例子

比如用户登录。
首先前端jsp表单输入用户名密码提交到controller(servlet)用来处理流程
controller进行过滤，判断输入是否为空，是否有敏感字符等。然后调用service层判断是否存在用户。捕获service异常，最后返回结果
service层调用dao层的数据库操作，来判断用户是否存在。


在比如用户上传资料
前端jsp选择上传文件点击按钮，提交到controller层进行流程处理
controller进行过滤，上传文件名，文件后缀是否合法，然后调用service层处理。捕获service异常,最后返回结果
service层处理业务逻辑，判断用户是否存在,登录是否过期等。将文件上传到指定目录，更新数据库，将此用户上传的文件名字增加到数据库中等等吧。



至于controller中的过滤上传文件名，文件后缀是否合法等为什么不放到service中，我的理解是这些不算是业务逻辑。具体参照百度https://baike.baidu.com/item/%E4%B8%9A%E5%8A%A1%E9%80%BB%E8%BE%91%E5%B1%82
controller层不要和dao层相连接，比如servlet直接操作数据库查询,因为这样职责划分就不明确了。只有service层才能调用dao
而controller本身的代码可以优化并重用,参照https://www.jianshu.com/p/654f4589eb8e的设计，是因为他几乎不涉及到业务逻辑



我们先写b/s架构，虽然用到的技术不多，但是仍然学到了Servlet, JavaBean, JSF，JPA，JNDI，Web Service和目录结构
一个小组大家学习能力不同，课余时间不同，还有基础不同，稳妥起见这样最好. c/s架构和SSH框架，等有余力再学习，到时会用到其余的技术，比如EJB，RMI，JMS。以及企业中最常见的SpringMVC，Strusts2，Hibernate


