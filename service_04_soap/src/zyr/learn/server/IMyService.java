package zyr.learn.server;

import zyr.learn.Exception.UserException;
import zyr.learn.model.User;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by zhouweitao on 2016/11/26.
 */
@WebService
public interface IMyService {
    @WebResult(name = "addResult")
    int add(@WebParam(name = "a")int a, @WebParam(name = "b") int b);

    @WebResult(name = "user")
    User addUser(@WebParam(name = "user") User user);

    @WebResult(name = "user")
    User login(@WebParam(name = "username")String username,@WebParam(name = "password") String password) throws UserException;

    @WebResult(name = "user")
    List<User> list(@WebParam(header = true,name = "authInfo") String authInfo);
}
