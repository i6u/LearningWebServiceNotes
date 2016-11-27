package zyr.learn.service;

import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouweitao on 2016/10/12.
 *
 */
public class TestClient {
    @Test
    public void test(String[] args) {
        try {
            URL url = new URL("http://127.0.0.1:8888/ns?wsdl");
            QName qName = new QName("http://service.learn.zyr/","MyServiceImplService");
            Service service = Service.create(url, qName);
            IMyService myService = service.getPort(IMyService.class);
            //System.out.println(myService.add(10,15));

            List<User> list = myService.getUsers();

            for (User user : list) {
                System.out.println(user);
            }


            Map<Integer, User> map = myService.getUserMap();

            for (Object obj : map.keySet()) {
                System.out.println(obj+"-->"+map.get(obj));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
