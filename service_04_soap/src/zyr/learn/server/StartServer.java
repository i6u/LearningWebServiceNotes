package zyr.learn.server;

import javax.xml.ws.Endpoint;

/**
 * Created by zhouweitao on 2016/11/26.
 */
public class StartServer {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9999/ms", new MyService());
        System.out.println("http://localhost:9999/ms?wsdl");
    }
}
