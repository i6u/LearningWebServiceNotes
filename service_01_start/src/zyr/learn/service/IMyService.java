package zyr.learn.service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouweitao on 2016/10/12.
 */
@WebService
public interface IMyService {
    @WebResult(name = "addResult")
    int add(@WebParam(name = "ns") int... ns);

    @WebResult(name = "minusResult")
    List<String> minus(@WebParam(name = "str") String... str);

    @WebResult(name = "getUser")
    User getUser(@WebParam(name = "id") int id);

    @WebResult(name = "getUser")
    User getUserInfo(@WebParam(name = "id") int id, @WebParam(name = "name") String name);

    @WebResult(name = "getUsers")
    List<User> getUsers();

    @WebResult(name = "getMaps")
    Map<Integer, User> getUserMap();

}
