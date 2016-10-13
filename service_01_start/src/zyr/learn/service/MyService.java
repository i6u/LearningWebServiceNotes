package zyr.learn.service;

import javax.xml.ws.Endpoint;

/**
 * Created by zhouweitao on 2016/10/12.
 */
public class MyService {
    public static void main(String[] args) {
        String addr = "http://localhost:8888/ns";
        Endpoint.publish(addr, new MyServiceImpl());
    }
}
