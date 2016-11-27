package zyr.learn.test;

/**
 * Created by zhouweitao on 2016/11/26.
 */

import org.junit.Test;
import org.w3c.dom.*;
import zyr.learn.model.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 模拟发送soap消息
 */
public class TestSOAP {


    /**
     * 创建soap消息
     * */
    @Test
    public void test01() {
        try {
            //1.创建messageFactory工厂
            MessageFactory factory = MessageFactory.newInstance();
            //2.创建soap消息
            SOAPMessage message = factory.createMessage();
            //3.创建soap part
            SOAPPart part = message.getSOAPPart();
            //4.创建soap 信封
            SOAPEnvelope envelope = part.getEnvelope();
            //5.根据soap envelope就可以得到soap body，soap header

            SOAPBody body = envelope.getBody();
            /**
             * QName 就是一个带有命名空间的name
             *
             * */
            //
            QName Qname = new QName("http://server.learn.zyr/", "add", "ns");
            SOAPElement element = body.addBodyElement(Qname);
            element.addChildElement("a").setValue("22");
            element.addChildElement("b").setValue("33");
            message.writeTo(System.out);
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 处理soap消息
     *
     * 基于MESSAGE--》soap 消息
     */

    @Test
    public void test02() {
        String wsdl = "http://192.168.199.178:9999/ms?wsdl";
        String namespace = "http://server.learn.zyr/";

        try {
            //1. 创建soap消息
            URL url = new URL(wsdl);
            //1.1注意这里是 <service name="MyServiceService"/>  节点所在的命名空间
            QName qName = new QName(namespace, "MyServiceService");
            Service service = Service.create(url,qName);

            //2.创建dispatch
            //2.1这里是 <port name="MyServicePort"/>  节点所在的命名空间
            Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(namespace,"MyServicePort"), SOAPMessage.class, Service.Mode.MESSAGE);
            //3.创建soap message
            SOAPMessage message = MessageFactory.newInstance().createMessage();
            SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
            SOAPBody body = envelope.getBody();
            //3.1这里是<xs:element name="add" type="tns:add"/>  节点所在的命名空间
            QName name = new QName(namespace,"add","ax");
            SOAPElement element = body.addBodyElement(name);
            element.addChildElement("a").setValue("33");
            element.addChildElement("b").setValue("33");
            //打印创建的soap消息
            //message.writeTo(System.out);
            System.out.println();
            //4.发送soap消息，接收返回的soap消息
            SOAPMessage soapMessage = dispatch.invoke(message);
            //4.1打印返回的soap消息
            //soapMessage.writeTo(System.out);

            //获得返回消息的document对象
            //Document document = soapMessage.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
            //System.out.println(document.getElementsByTagName("addResult").item(0).getTextContent());

            String str = soapMessage.getSOAPPart().getEnvelope().getBody().getElementsByTagName("addResult").item(0).getTextContent();
            System.out.println(str);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 处理soap消息
     * 基于PAYLOAD--》XML文本
     * */
    @Test
    public void test03() throws Exception {
        String wsdl = "http://192.168.199.178:9999/ms?wsdl";
        String namespace = "http://server.learn.zyr/";

        try {
            //1. 创建soap消息
            URL url = new URL(wsdl);
            QName qName = new QName(namespace, "MyServiceService");
            Service service = Service.create(url,qName);

            //2.创建dispatch,通过元数据的方式
            Dispatch<Source> dispatch = service.createDispatch(new QName(namespace,"MyServicePort"), Source.class, Service.Mode.PAYLOAD);

            //3.根据对象创建xml，即编排对象
            User user = new User(1, "悟空", "admin");
            //3.1通过JAXBContext方式编排对象
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT,true);
            StringWriter writer = new StringWriter();
            marshaller.marshal(user, writer);

            //4.封装相应的part
            String payload = "<nn:addUser xmlns:nn=\""+namespace+"\">"+writer.toString()+"</nn:addUser>";

            StreamSource streamSource = new StreamSource(new StringReader(payload));

            //5.传递payload
            Source source = dispatch.invoke(streamSource);
            //6.处理响应
            //6.1 通过Transformer将source转换为DOMResult
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMResult result = new DOMResult();
            transformer.transform(source, result);
            //6.2 通过xPath查找user节点
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList list = (NodeList) xPath.evaluate("//user", result.getNode(), XPathConstants.NODESET);

            //6.2 反编排Node
            User u = (User) jaxbContext.createUnmarshaller().unmarshal(list.item(0));
            System.out.println(u);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 处理list
     * 通过header传递信息
     *
     * */

    @Test
    public void test04() throws Exception {
        String wsdl = "http://192.168.199.178:9999/ms?wsdl";
        String namespace = "http://server.learn.zyr/";

        try {
            //1. 创建soap消息
            URL url = new URL(wsdl);
            //1.1注意这里是 <service name="MyServiceService"/>  节点所在的命名空间
            QName qName = new QName(namespace, "MyServiceService");
            Service service = Service.create(url,qName);

            //2.创建dispatch
            //2.1这里是 <port name="MyServicePort"/>  节点所在的命名空间
            Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(namespace,"MyServicePort"), SOAPMessage.class, Service.Mode.MESSAGE);
            //3.创建soap message
            SOAPMessage message = MessageFactory.newInstance().createMessage();
            SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
            //3.1获取头信息
            SOAPHeader header = envelope.getHeader();
            if (header==null) header=envelope.addHeader();
            QName name1 = new QName(namespace, "authInfo", "nn");
            /**
             * 添加头部信息
             * */
            header.addHeaderElement(name1).setValue("abc");
            SOAPBody body = envelope.getBody();
            QName name = new QName(namespace,"list","nn");
            body.addBodyElement(name);

            //message.writeTo(System.out);//输入发生的soap消息

            //4.发送soap消息，接收返回的soap消息
            SOAPMessage soapMessage = dispatch.invoke(message);

            //5.处理返回的soap消息

            Document document = soapMessage.getSOAPBody().extractContentAsDocument();
            NodeList list = document.getElementsByTagName("user");
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            for (int i = 0; i < list.getLength(); i++) {
                org.w3c.dom.Node n1 = list.item(i);
                User u1 = (User) jaxbContext.createUnmarshaller().unmarshal(n1);
                System.out.println(u1);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
