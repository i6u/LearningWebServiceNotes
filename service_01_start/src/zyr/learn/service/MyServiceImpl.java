package zyr.learn.service;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouweitao on 2016/10/12.
 *
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

    @Override
    public User getUser(int id) {
        User user = new User();
        user.setId(id);
        user.setUsername("张三丰");
        System.out.println("...service user:"+id);
        return user;
    }

    @Override
    public User getUserInfo(int id, String name) {
        User user = new User();
        user.setId(id);
        user.setUsername(name);
        System.out.println("...service userInfo:"+id+"/"+name);
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        list.add(new User(1,"apache"));
        list.add(new User(2,"spring"));
        list.add(new User(3,"w3c"));
        return list;
    }

    @Override
    public Map<Integer, User> getUserMap() {
        Map<Integer, User> map = new HashMap<>();
        map.put(1, new User(11,"php"));
        map.put(2, new User(22,"python"));
        map.put(3, new User(33,"javascript"));
        map.put(4, new User(33,"javascript1"));
        map.put(5, new User(33,"javascript2"));
        map.put(6, new User(33,"javascript3"));
        return map;
    }


}
