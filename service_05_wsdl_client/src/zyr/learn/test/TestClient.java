package zyr.learn.test;

import org.junit.Test;
import zyr.learn.service.IMyService;
import zyr.learn.service.MyServiceImplService;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhouweitao on 2016/11/28.
 */
public class TestClient {

    @Test
    public void test01(){
        try {
            /**
             * 使用tcp mon的时候如果监听没有正常启动会报
             * com.sun.xml.internal.ws.client.ClientTransportException: HTTP 传输错误: java.net.ConnectException: Connection refused
             * 异常
             * */
            URL url = new URL("http://localhost:9898/ms?wsdl");
            QName qName = new QName("http://www.example.org/mywsdl/","MyServiceImplService");
            //MyServiceImplService myServiceImplService = new MyServiceImplService(url,qName);
            MyServiceImplService myServiceImplService = new MyServiceImplService();
            IMyService ims = myServiceImplService.getMyServiceImplPort();
            int c = ims.add(33 , 33);
            System.out.println(c);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test02() throws Exception {
        /**
         * 添加头信息
         * 模拟soap message
         * 添加头信息，验证头信息
         * */

        try {
            String ns = "http://www.example.org/mywsdl/";

            URL url = new URL("http://localhost:8888/ms?wsdl");
            QName serviceQName = new QName(ns,"MyServiceImplService");
            Service service = Service.create(url, serviceQName);

            QName dispatchQName = new QName(ns,"MyServiceImplPort");
            Dispatch<SOAPMessage> dispatch = service.createDispatch(dispatchQName, SOAPMessage.class, Service.Mode.MESSAGE);

            SOAPMessage message = MessageFactory.newInstance().createMessage();
            SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();

            SOAPHeader header = envelope.getHeader();
            SOAPBody body = envelope.getBody();

            if (header==null) header = envelope.addHeader();

            QName headerQName = new QName(ns, "licenseInfo","ns");
            header.addHeaderElement(headerQName).setValue("yep...123456");

            QName addQName = new QName(ns, "add", "ns");
            SOAPElement element = body.addBodyElement(addQName);
            element.addChildElement("a").setValue("22");
            element.addChildElement("b").setValue("33");

            message.writeTo(System.out);
            System.out.println("\n...invoke......");

            SOAPMessage response = dispatch.invoke(message);

            response.writeTo(System.out);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }
}
