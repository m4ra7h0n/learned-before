
设计网页的时候要考虑安全问题，比如，用户上传文档，那就限制他只能上传txt，pdf，doc等类型，绝对禁止上传jsp，java类型的文件，否则后果不堪设想。再比如，用户留言的地方，会插入数据库，这个时候如果没有对用户输入的内容进行过滤，用户进行恶意拼接，极有可能执行非法的数据库操作，毁坏数据库或者盗取用户数据。

我列出常见的安全问题，大家设计的时候尽量考虑进去，我承认这是一个很大很大的课题，加油
[Java常见漏洞](https://www.bookstack.cn/read/anbai-inc-javaweb-sec/java-source-code-audit-vuls-README.md)

SQL注入：

XSS：

文件上传漏洞：

CSRF:

SSRF:

失效的身份认证和会话管理:

敏感信息泄露:

XXE:

等等自行百度
