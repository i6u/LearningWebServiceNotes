package zyr.learn.dao;

import zyr.learn.Exception.UserException;
import zyr.learn.model.User;

import java.util.List;

/**
 * Created by zhouweitao on 2016/11/28.
 */
public interface IUserBiz {
    void add(User user) throws UserException;

    void delete(String username) throws UserException;

    List<User> list();

    User login(String username, String password) throws UserException;


}
