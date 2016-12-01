package zyr.learn.dao;

import zyr.learn.Exception.UserException;
import zyr.learn.model.User;

import java.util.*;

/**
 * Created by zhouweitao on 2016/11/28.
 */
public class UserBiz implements IUserBiz {

    //private static Map<String, User> users = new HashMap<>();
    //private static UserBiz dao;
    //
    //private UserBiz() {
    //    users.put("root", new User("root", "root", "super man"));
    //    users.put("user", new User("user", "user", "普通用户"));
    //}
    //
    //public static UserBiz newInstance() {
    //    return dao != null ? dao : new UserBiz();
    //}
    //
    //@Override
    //public void add(User user) throws UserException {
    //    if (user==null) throw new UserException("用户不能为空");
    //    users.put(user.getUsername(), user);
    //}
    //
    //@Override
    //public void delete(String username) throws UserException {
    //    if (username.isEmpty()) throw new UserException("用户名不能为空");
    //    users.remove(username);
    //}
    //
    //@Override
    //public List<User> list() {
    //    List<User> list = new ArrayList<>();
    //    for (Object o : users.keySet()) {
    //        list.add(users.get(o));
    //    }
    //    return list;
    //
    //}
    //
    //@Override
    //public User login(String username, String password) throws UserException {
    //    if (!users.containsKey(username)) throw new UserException("用户不存在");
    //    User user = users.get(username);
    //    if (!password.equals(user.getPassword())) throw new UserException("用户密码不正确");
    //    return user;
    //}
    //
    //@Override
    //public User loadByUsername(String username) {
    //    return users.get(username);
    //}

    private static final Map<String,User> users = new HashMap<String,User>();
    private static UserBiz dao = null;

    private UserBiz() {
        users.put("admin",new User("admin","123","超级管理员"));
    }

    public static UserBiz newInstance() {
        if(dao==null) dao =new UserBiz();
        return dao;
    }

    public Set<String> listUsers() {
        return users.keySet();
    }

    public User loadByUsername(String username) {
        return users.get(username);
    }


    public void add(User user) throws UserException {
        if(users.containsKey(user.getUsername()))throw new UserException("用户已经 存在了");
        users.put(user.getUsername(), user);
    }

    public void delete(String username) {
        users.remove(username);
    }

    public List<User> list() {
        Set<String> keys = users.keySet();
        List<User> list = new ArrayList<User>();
        for(String key:keys) {
            list.add(users.get(key));
        }
        return list;
    }

    public User login(String username,String password) throws UserException {
        if(!users.containsKey(username))throw new UserException("用户不存在");
        User u = users.get(username);
        if(!password.equals(u.getPassword()))throw new UserException("用户密码不正确!");
        return u;
    }
}
