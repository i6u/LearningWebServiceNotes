package zyr.learn.service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by zhouweitao on 2016/10/12.
 */
@WebService
public interface IMyService {
    @WebResult(name = "addResult")
    public int add(@WebParam(name = "ns") int...ns);

    @WebResult(name = "minusResult")
    public List<String> minus(@WebParam(name = "str") String... str);

    @WebResult(name = "getUser")
    public User getUser(@WebParam(name = "id") int id);
}
