package zyr.learn.service;

import zyr.learn.Exception.UserException;
import zyr.learn.dao.IUserBiz;
import zyr.learn.dao.UserBiz;
import zyr.learn.model.User;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by zhouweitao on 2016/11/28.
 *
 */

@WebService(endpointInterface = "zyr.learn.service.IUserService",
        wsdlLocation = "WEB-INF/wsdl/user.wsdl",
        targetNamespace = "http://service.learn.zyr",
        serviceName="UserService",
        portName="UserServicePort")

public class UserServiceImpl implements IUserService {
    private IUserBiz userBiz = UserBiz.newInstance();
    @Override
    public void add(User user) throws UserException {
        userBiz.add(user);
    }

    @Override
    public void delete(String username) throws UserException {
        userBiz.delete(username);
    }

    @Override
    public List<User> list() {
        return userBiz.list();
    }

    @Override
    public User login(String username, String password) throws UserException {
        return userBiz.login(username, password);
    }

    @Override
    public void upload(byte[] file) throws UserException {
        System.out.println("upload...");
    }
}
