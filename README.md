idea 配置tomcat 部署运行web项目

版本需求:

需要本地mysql版本为5.x版本，最好在5.7版本以上

需要idea配置tomcat

需要tomcat编辑配置 > 部署 中应用程序上下文为/library

需要配置JDK，版本一般不低于1.8

需要确认连接的密码和本地数据库密码一致，数据库连接参数在library\src\com\demo\javabean\DBAccess.java中，打开后修改数据库密码即可

用户登录账号：

20200002	123
20200005	123
20240001	123456
20240002	123456
2024003	123456

管理员登录账号：admin 密码 admin



导入Mysql本地数据:

新建mysql数据库library:

![image-20251229234814651](./assets/image-20251229234814651.png)

必须叫library

设置如图:

![image-20251229235041016](./assets/image-20251229235041016.png)

如图所示运行SQL脚本:

![image-20251229235112779](./assets/image-20251229235112779.png)

导入library即可



1、Tomcat下载
（1）Tomcat官网：Tomcat官方网址，可以在此处下载Tomcat。
（2）点击左侧Download下的对应版本。
注意有zip和exe两种格式的，zip是免安装版的，exe是安装版。同时观察自己的电脑是64位系统还是32位系统。
例如进入：Tomcat 9 版本下载页面，下滑，到core处选择自己需要的版本，如图所示：

https://tomcat.apache.org/download-90.cgi

![image-20251229221617196](./assets/image-20251229221617196.png)

我们使用exe版本安装 

一直下一步即可。

中间的注意事项：

这一步记得选中Service

![这里写图片描述](./assets/SouthEast.png)

##### 点击Next后会出现下图，它会自动找到JRE位置，如果用户没有安装JRE，可以修改指向JDK目录（很多用户安装后无法编译JSP，就是这里没找到JRE，请务必先要安装JDK，并把这个目录正确指向JRE或者JDK的目录）。

![这里写图片描述](./assets/SouthEast-1767017886086-4.png)

##### 打开浏览器 键入 [http://localhost:8080](http://localhost:8080/) 进入如下页面则表示安装成功

![这里写图片描述](./assets/SouthEast-1767017908743-7.png)

配置Tomcat之前要保证jdk已经配置完成。
右击，计算机——属性——高级系统设置——打开环境变量的配置窗口，在系统环境变量一栏点击新建。变量名为TOMCAT_HOME，变量值为Tomcat安装目录。点击确定即可。



# 解决启动tomcat服务器时，报错：地址 localhost:1099 已在使用中

问题：idea启动tomcat服务器报——**地址 localhost:1099 已在使用中**

1.进入终端

![image-20251229221353957](./assets/image-20251229221353957.png)

2.输入[netstat](https://zhida.zhihu.com/search?content_id=202465784&content_type=Article&match_order=1&q=netstat&zhida_source=entity) -aon|findstr 1099 命令，查看什么程序占用端口

3.找到占用端口号的Pid xxx，输入 [taskkill](https://zhida.zhihu.com/search?content_id=202465784&content_type=Article&match_order=1&q=taskkill&zhida_source=entity) /pid xxx /f 命令，结束进程

![Snipaste_2025-12-29_22-05-07](./assets/Snipaste_2025-12-29_22-05-07.png)

4.最后在启动Tomcat服务器