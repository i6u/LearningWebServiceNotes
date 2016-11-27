# LearningWebServiceNotes
学习目标
* 什么是Webservice
* Webservice解决的问题
* Java中Webservice的使用
* DTD/Schema规范，XML
* 关于WSDL详解先看这个[WSDL详解 0](http://blog.csdn.net/liguocai2005/article/details/4402350) 再看这个[WSDL详解 1](http://blog.sina.com.cn/s/blog_63eb3eec0101gv5z.html) / [WSDL详解 2](http://blog.sina.com.cn/s/blog_63eb3eec0101gv62.html)
    - 了解wsdl之前，需要熟悉dtd/schema、xml
* open

## 项目概述

### service_01 -》 基于JWS的web service实现

* wsimport 的使用
* tcpmon使用

### 02 --》dtd schema

* [w3c school schema](http://www.w3school.com.cn/schema/index.asp)
* [XML 模式：了解命名空间 ](http://www.oracle.com/technetwork/cn/articles/srivastava-namespaces-098626-zhs.html)
* schema的作用在于使用xml本身定义元素和属性，用来验证xml的合法性，其中涉及到的两个比较重要的知识点，一个是命名空间，另一个就是语法！

第一，Schema是如何使用本身来约束XML格式
第二，Schema命名空间的概念和作用，以及如何定义和引用，

> 想要了解Schema是如何被定义如何被引用的，就必须理解清楚Schema的命名空间，Schema的命名空间是如何被定义，如何被外部/内部引用，常用几种引用方式有几种，他们的区别，在被内部/外部引用的时候，有哪些限制等，在理解清楚Schema的命名空间后，在具体的使用中
Schema中元素是如何被定义的，属性是如何被定义的，元素和属性的规范是如何定义的等，书写Schema文件的三种模式，各自的优缺点，最后我们日常书写Schema文件的习惯等。还有模式模板和自定义模板


### 03 Java中对XML文件的操作

* 使用`JAXBContext`进行xml和bean的转换
* 使用`Stax`处理xml  
* 使用`XPath`处理xml
* `XML`写和修改，`XMLStreamWriter`，`Transformer`

### 04 SOAP消息 SAAJ(SOAP with Attachments API for JAVA) 

* 发布`webservice`服务
* 在实现类指定服务接口后，在接口上声明的注解才会有效，`MyService`注释
* 模拟soap消息，具体查看`TestSOAP.test01`注释
* soap消息处理，基于`MESSAGE`和`PAYLOAD`,具体查看`TestSOAP.test02,03`注释

### 04-client soap handler处理

* soap 消息异常处理
* 客户端`handler`信息处理，（LogicalHandler 只能获取soap body的信息/SOAPHandler 可以获得soap message的信息）
    * 创建`MyHandler`实现`SOAPHandler`接口
    * 创建`handler-chain.xml`文件--》可以配置多个
    * 在`MyServiceService`上添加`@HandlerChain(file = "handler-chain.xml")`
* 服务端`handler`信息处理
    * 在服务端显示声明一个参数来获取头部信息`@WebParam(header = true,name = "authInfo")`实例`service_04`声明`MyService.list`测试`TestSOAP.test04`
    * 在服务端创建`MyHandler`实现`SOAPHandler`接口，通过`MyHandler`截取`handler`信息--》实例`service_04`
    
**通过`SOAPHandler`传递soap头信息需要注意的地方**

1. 如果客户端指定了头信息的命名空间，服务端有需要指定一致的命名空间，一般都是指点的前缀
2. 不同版本的JDK可以能存在一些问题，如果碰到诡异的异常，切换个JDK试试