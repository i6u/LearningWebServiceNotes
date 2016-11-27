package zyr.learn.test;

import org.junit.Test;
import zyr.learn.service.IMyService;
import zyr.learn.service.MyServiceService;
import zyr.learn.service.User;
import zyr.learn.service.UserException_Exception;

import javax.xml.namespace.QName;
import javax.xml.ws.soap.SOAPFaultException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhouweitao on 2016/11/27.
 */
public class TestClient {
    @Test
    public void test01(){
        try {
            URL url = new URL("http://192.168.199.178:8989/ms?wsdl");
            QName qName = new QName("http://server.learn.zyr/", "MyServiceService");
            MyServiceService ms = new MyServiceService(url, qName);


            //MyServiceService ms = new MyServiceService();
            IMyService ims = ms.getMyServicePort();
            User user = ims.login("root", "root");
            System.out.println(user);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UserException_Exception e) {
            System.out.println(e.getMessage());
        } catch (SOAPFaultException e) {
            System.out.println(e.getMessage());
        }

    }
}
