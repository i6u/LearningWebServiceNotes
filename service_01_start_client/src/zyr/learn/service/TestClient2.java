package zyr.learn.service;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouweitao on 2016/10/13.
 */
public class TestClient2 {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:9988/ns?wsdl");
        QName qName = new QName("http://service.learn.zyr/","MyServiceImplService");
        MyServiceImplService service = new MyServiceImplService(url,qName);
        IMyService myService = service.getMyServiceImplPort();
        List<String> ints = new ArrayList<String>();
        ints.add("a");
        ints.add("b");
        ints.add("c");
        ints.add("d");
        System.out.println(myService.minus(ints).get(1));
    }
}
