package zyr.learn.server;

import zyr.learn.Exception.UserException;
import zyr.learn.model.User;

import javax.jws.HandlerChain;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouweitao on 2016/11/26.
 *
 */

/**
 * endpointInterface = "zyr.learn.server.IMyService"
 * 在实现类指定服务接口后，在接口上声明的注解才会有效
 */
@WebService(endpointInterface = "zyr.learn.server.IMyService")
@HandlerChain(file = "server-handler-chain.xml")
public class MyService implements IMyService {
    private static List<User> users = new ArrayList<>();

    public MyService() {
        users.add(new User(0, "root", "root"));
    }

    @Override
    public int add(int a, int b) {
        System.out.println(a + b + "...");
        return a + b;
    }

    @Override
    public User addUser(User user){
        users.add(user);
        return user;
    }

    @Override
    public User login(String username, String password)  throws UserException {
        for (User user : users) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                System.out.println(user);
                return user;
            }
        }
        throw new UserException("用户不存在...");
    }

    @Override
    public List<User> list(String authInfo) {
        System.out.println(authInfo + "...");
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }
}
