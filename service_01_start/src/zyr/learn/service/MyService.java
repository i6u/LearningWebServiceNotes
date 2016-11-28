package zyr.learn.service;

import javax.xml.ws.Endpoint;

/**
 * Created by zhouweitao on 2016/10/12.
 *
 */
public class MyService {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8989/ns", new MyServiceImpl());
    }
}
