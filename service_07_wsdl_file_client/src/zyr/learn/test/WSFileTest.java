package zyr.learn.test;

import org.junit.*;
import zyr.learn.service.IUserService;
import zyr.learn.service.UserException_Exception;
import zyr.learn.service.UserService;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.MTOMFeature;
import javax.xml.ws.soap.SOAPBinding;
import java.io.File;

/**
 * Created by zhouweitao on 2016/12/3.
 */
public class WSFileTest {

    private IUserService port;
    private UserService userService;

    @Before
    public void beforeTest() {
        userService = new UserService();
        //使用MTOM
        //第一种方式
//        port = userService.getUserServicePort(new MTOMFeature());
        //第二种方式
        port = userService.getUserServicePort();
        BindingProvider bp = (BindingProvider)port;
        SOAPBinding soapBinding = (SOAPBinding) bp.getBinding();
        soapBinding.setMTOMEnabled(true);

    }

    @Test
    public void test01() {

        try {
            File file = new File("/Users/zhouweitao/Pictures/Background/i.jpg");
            DataHandler dataHandler = new DataHandler(new FileDataSource(file));
            port.upload(dataHandler);
        } catch (UserException_Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
