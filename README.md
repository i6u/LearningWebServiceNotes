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

### service_05_wsdl -->契约优先的开发模式-->服务端的开发

** [Web Service中RPC和Document的区别](http://www.xuebuyuan.com/1897903.html) **

> RPC和Document Web服务之间的一个重要区别是，使用RPC Web服务时，只会为复杂类型参数创建XML模式。 因此，不可能验证SOAP主体中包含的整个XML片段。 但是，使用Document Web服务，XML模式需要定义SOAP主体中包含的ENTIRE XML片段。因此，可以针对XML模式验证整个消息。

* 制定规范，编写Schema、wsdl文件
* 根据规范生成客户端代码
* 编写实现类，并指定`wsdlLocation`为我们定义的`mywsdl.wsdl`
* 发布服务

> 想要了解`wsdl`那就写一个`wsdl`

### service_05_wsdl_client -->契约优先的开发模式-->客户端的开发***

**应为jax-ws版本的问题在根据`wsdl`生成`java类`的时候确实会导致如下异常，换个版本试试**

```Exception in thread "main" java.lang.AssertionError: org.xml.sax.SAXParseException; systemId: jar:file:/Users/zhouweitao/work/Workspace/IDEA/LearningWebServiceNotes/service_05_wsdl_client/lib/jaxb-xjc.jar!/com/sun/tools/xjc/reader/xmlschema/bindinfo/binding.xsd; lineNumber: 52; columnNumber: 88; schema_reference: 由于 accessExternalSchema 属性设置的限制而不允许 'file' 访问, 因此无法读取方案文档 'xjc.xsd'。```

> 基于契约优先的模式，在`wsdl`文件中约定好头信息，生成的客户端代码不会出现异常的方法参数，这样在服务端的方法中显示的声明头信息也不会影响到生成的客户端代码  

### service_06_wsdl_um--》在web项目中发布webservice服务 --》基于契约优先

[jax-ws jaxws-war官方配置文档](http://docs.oracle.com/cd/E17802_01/webservices/webservices/docs/2.0/jaxws/jaxws-war.html)

* 导入`jax-ws`包
* 在`WEB-INF`中创建`wsdl\*.wsdl`文件
* 在`WEB-INF`文件夹下创建`sun-jaxws.xml`文件，具体看项目中的注释
* 配置`web.xml`文件，配置监听，和servlet
* ok启动服务

### service_06_wsdl_um_client --》基于jax-ws RI的方式传递消息头

**传递`header`信息的三种方式**

* 通过`SAAJ`创建SOAPBody
* 通过`SAOPHandler`处理
* 通过`JAX-WS RI`的方式（`Headers.create(element)`）直接把一个元素转换为`handler`

```javax.xml.ws.soap.SOAPFaultException:webFault.messageName()方法异常，这是应为jdk也提供了这个jax-ws ri的实现，但是应为版本的问题，没有webFault.messageName()这个方法，项目默认却调用的jdk中jax-ws ri，没有调用我们手动导入的jax-ri包中的webFault.messageName()方法，解决方法是在jdk的jre\lib中endorsed文件夹，把我们导入的高版本的jax-ws ri包拷贝到其中，jdk默认就会去加载这个包；如果项目时发布到tomcat中endorsed应放在tomcat根目录下```

[java endorsed问题](http://blog.csdn.net/bbirdsky/article/details/11921843)
[java endorsed 官方](http://docs.oracle.com/javase/7/docs/technotes/guides/standards/)

### service_06_wsdl_um_client_web --》一个简单的用户管理系统

* 基于`jax-ws`的远程调用，服务端使用的是`service_06_wsdl_um`
* 基于`JAX-WS RI`的头处理
* 注意包的引用问题

