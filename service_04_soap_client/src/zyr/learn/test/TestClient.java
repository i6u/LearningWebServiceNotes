package zyr.learn.test;

import org.junit.Test;
import zyr.learn.service.*;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
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
            URL url = new URL("http://localhost:9999/ms?wsdl");
            QName qName = new QName("http://server.learn.zyr/", "MyServiceService");
            //MyServiceService ms = new MyServiceService(url, qName);


            MyServiceService ms = new MyServiceService();
            IMyService ims = ms.getMyServicePort();
            User user = ims.login("root", "root");
            System.out.println(user);
            User user1 = new User();
            user1.setUid(2);
            user1.setUsername("大圣");
            user1.setPassword("free");
            ims.addUser(new Holder<>(user1));


            ListResponse listResponse = ims.list(null, null);
            for (User u : listResponse.getUser()) {
                System.out.println(u);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UserException_Exception e) {
            System.out.println(e.getMessage());
        } catch (SOAPFaultException e) {
            System.out.println(e.getMessage());
        }

    }
}
