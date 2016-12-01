package zyr.learn.service;


import com.sun.xml.ws.api.message.Header;
import com.sun.xml.ws.api.message.HeaderList;
import com.sun.xml.ws.developer.JAXWSProperties;
import zyr.learn.Exception.UserException;
import zyr.learn.dao.IUserBiz;
import zyr.learn.dao.UserBiz;
import zyr.learn.model.User;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.soap.MTOM;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhouweitao on 2016/11/28.
 *
 */

@WebService(endpointInterface = "zyr.learn.service.IUserService",
        wsdlLocation = "WEB-INF/wsdl/user.wsdl",
        //wsdlLocation = "META-INF/wsdl/user.wsdl",
        targetNamespace = "http://service.learn.zyr",
        serviceName="UserService",
        portName="UserServicePort")

@MTOM
public class UserServiceImpl implements IUserService {
    private IUserBiz userDao = UserBiz.newInstance();

    @Resource
    private WebServiceContext ctx;


    @Override
    public void add(User user) throws UserException {
        checkRegister();
        userDao.add(user);
    }

    private void checkRegister() throws UserException {
        try {
            HeaderList headers = (HeaderList)ctx.getMessageContext().get(JAXWSProperties.INBOUND_HEADER_LIST_PROPERTY);
            QName qname = new QName("http://service.learn.zyr","licenceInfo");
            if(headers==null) throw new UserException("该功能需要进行权限控制");
            Header header = headers.get(qname,true);
            if(header==null) throw new UserException("该功能需要进行权限控制");
            XMLStreamReader xsr = header.readHeader();
            User u = x2user(xsr);
            User tu = userDao.loadByUsername(u.getUsername());
            if(tu==null) throw new UserException("你所使用的用户不是系统的授权用户");
            if(!tu.getPassword().equals(u.getPassword()))
                throw new UserException("授权用户的密码信息不正确!");
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private User x2user(XMLStreamReader xsr) throws XMLStreamException {
        User u = new User();
        while(xsr.hasNext()) {
            int event = xsr.next();
            if(event==XMLEvent.START_ELEMENT) {
                String name = xsr.getName().toString();
                if(name.equals("username")) {
                    u.setUsername(xsr.getElementText());
                } else if(name.equals("password")) {
                    u.setPassword(xsr.getElementText());
                } else if(name.equals("nickname")) {
                    u.setNickname(xsr.getElementText());
                }
            }
        }
        return u;
    }

    @Override
    public void delete(String username) throws UserException {
        checkRegister();
        userDao.delete(username);
    }

    @Override
    public List<User> list() {
        return userDao.list();
    }

    @Override
    public User login(String username, String password) throws UserException {
        return userDao.login(username, password);
    }

    @Override
    public void upload(byte[] file) {
        FileOutputStream fos = null;
        System.out.println(file);
        try {
            fos = new FileOutputStream("/Users/zhouweitao/Desktop/temp/1.jpg");
            fos.write(file);
            fos.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
