package zyr.learn.service;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by zhouweitao on 2016/10/12.
 */
@WebService
public interface IMyService {
    public int add(int...ns);

    public List<String> minus(String... str);
}
