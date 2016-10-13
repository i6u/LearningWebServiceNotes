package zyr.learn.service;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouweitao on 2016/10/12.
 */
@WebService(endpointInterface = "zyr.learn.service.IMyService")
public class MyServiceImpl implements IMyService {
    @Override
    public int add(int... ns) {
        int count = 0;
        for (int i = 0; i < ns.length; i++) {
            count += ns[i];
        }
        System.out.println("...service add:"+count);
        return count;
    }

    @Override
    public List<String> minus(String... str) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            list.add(str[i]);
        }
        System.out.println("...service minus:"+list.size());
        return list;
    }
}
