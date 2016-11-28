package zyr.learn.test;

import org.junit.Test;
import zyr.learn.service.IUserService;
import zyr.learn.service.User;
import zyr.learn.service.UserException_Exception;
import zyr.learn.service.UserService;

import java.util.List;

/**
 * Created by zhouweitao on 2016/11/28.
 */
public class TestUMClient {

    @Test
    public void test01(){
        UserService service = new UserService();
        IUserService ius = service.getUserServicePort();

        List<User> users = ius.list();
        for (User user : users) {
            System.out.println(user.getNickname());
        }

        try {
            //ius.add(null);
            ius.login("root", "ro1ot");
        } catch (UserException_Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
