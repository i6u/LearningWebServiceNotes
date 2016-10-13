package zyr.learn.service;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by zhouweitao on 2016/10/12.
 */
public class TestClient {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8888/ns?wsdl");
            QName qName = new QName("http://service.learn.zyr/","MyServiceImplService");
            Service service = Service.create(url, qName);

            IMyService myService = service.getPort(IMyService.class);
            System.out.println(myService.add(1,2,3,4,5,10,15));
            List<String> list = myService.minus("a","b","c");
            System.out.println(list instanceof List);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
