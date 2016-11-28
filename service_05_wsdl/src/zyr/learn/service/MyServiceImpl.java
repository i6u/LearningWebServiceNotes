package zyr.learn.service;

import javax.jws.WebService;

/**
 * Created by zhouweitao on 2016/11/28.
 */

@WebService(endpointInterface = "zyr.learn.service.IMyService",
        targetNamespace = "http://www.example.org/mywsdl/",
        wsdlLocation = "META-INF/wsdl/mywsdl.wsdl")
public class MyServiceImpl implements IMyService {
    @Override
    public int add(int a, int b,String licenseInfo) {
        System.out.println(licenseInfo+"...");
        System.out.println(a + "+" + b + "=" + (a + b));
        return a + b;
    }

    @Override
    public int divide(int c, int d) {
        System.out.println(c + "/" + d + "=" + (c / d));
        return c / d;
    }
}
