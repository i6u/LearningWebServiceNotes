package zyr.learn.service;


import zyr.learn.Exception.UserException;
import zyr.learn.model.User;

import javax.activation.DataHandler;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

/**
 * 使用以下两者皆可
 * @MTOM
 * @BindingType(SOAPBinding.SOAP11HTTP_MTOM_BINDING)
 * */
@BindingType(SOAPBinding.SOAP11HTTP_MTOM_BINDING)

public class UserServiceImpl implements IUserService {

    @Override
    public void add(User user) throws UserException {

    }

    @Override
    public void delete(String username) throws UserException {

    }

    @Override
    public List<User> list() {
        return null;
    }

    @Override
    public User login(String username, String password) throws UserException {
        return null;
    }

    @Override
    public void upload(DataHandler dataHandler) throws UserException {
        String type = dataHandler.getDataSource().getContentType();
        String name = dataHandler.getDataSource().getName();
        System.out.println(name+"---"+type+"---"+dataHandler.getName());

        FileOutputStream fos = null;
        InputStream is = null;
        try {
            fos = new FileOutputStream("/Users/zhouweitao/Desktop/a.jpg");
            is = dataHandler.getDataSource().getInputStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = is.read(buf)) > 0) {
                fos.write(buf,0,len);
            }
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
