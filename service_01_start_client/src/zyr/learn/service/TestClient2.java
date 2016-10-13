package zyr.learn.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouweitao on 2016/10/13.
 */
public class TestClient2 {
    public static void main(String[] args) {
        MyServiceImplService service = new MyServiceImplService();
        IMyService myService = service.getMyServiceImplPort();
        List<String> ints = new ArrayList<String>();
        ints.add("a");
        ints.add("b");
        ints.add("c");
        ints.add("d");
        System.out.println(myService.minus(ints));
    }
}
