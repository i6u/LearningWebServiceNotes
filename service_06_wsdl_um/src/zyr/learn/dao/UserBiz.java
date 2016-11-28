package zyr.learn.dao;

import zyr.learn.Exception.UserException;
import zyr.learn.model.User;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhouweitao on 2016/11/28.
 */
public class UserBiz implements IUserBiz {

    private static Map<String, User> users = new HashMap<>();
    private static UserBiz dao;

    private UserBiz() {
        users.put("root", new User("root", "root", "super man"));
        users.put("user", new User("user", "user", "普通用户"));
    }

    public static UserBiz newInstance() {
        return dao != null ? dao : new UserBiz();
    }

    @Override
    public void add(User user) throws UserException {
        if (user==null) throw new UserException("用户不能为空");
        users.put(user.getUsername(), user);
    }

    @Override
    public void delete(String username) throws UserException {
        if (username.isEmpty()) throw new UserException("用户名不能为空");
        users.remove(username);
    }

    @Override
    public List<User> list() {
        List<User> list = new ArrayList<>();
        for (Object o : users.keySet()) {
            list.add(users.get(o));
        }
        return list;

    }

    @Override
    public User login(String username, String password) throws UserException {
        if (!users.containsKey(username)) throw new UserException("用户不存在");
        User user = users.get(username);
        if (!password.equals(user.getPassword())) throw new UserException("用户密码不正确");
        return user;
    }
}
