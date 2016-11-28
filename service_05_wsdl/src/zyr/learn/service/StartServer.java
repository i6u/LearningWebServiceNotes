package zyr.learn.service;

import javax.xml.ws.Endpoint;

/**
 * Created by zhouweitao on 2016/11/28.
 */
public class StartServer {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/ms", new MyServiceImpl());
    }
}
