package zyr.learn.test;

import com.sun.xml.ws.api.message.Headers;
import com.sun.xml.ws.developer.WSBindingProvider;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import zyr.learn.service.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by zhouweitao on 2016/11/28.
 */
public class TestUMClient {



    private IUserService port;
    private UserService us;
    private String ns = "http://service.learn.zyr";

    @Before
    public void init() {
        try {
            URL url = new URL("http://localhost:7887/um/services?wsdl");
            QName name = new QName(ns,"UserService");
            us = new UserService(url,name);
            //us = new UserService();
            port = us.getUserServicePort();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test01(){
        UserService service = new UserService();
        IUserService ius = service.getUserServicePort();

        List<User> users = ius.list();
        for (User user : users) {
            System.out.println(user.getNickname());
        }

        try {
            //ius.add(null);
            ius.login("root", "root");
        } catch (UserException_Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void testList() {
        List<User> list = port.list();

        for(User u:list) {
            System.out.println(u.getNickname());
        }
    }


    @Test
    public void testAdd01() throws Exception {
        try {
            User u = new User();
            u.setNickname("沙僧1");
            u.setPassword("1231");
            u.setUsername("shs1");
            port.add(u);
        } catch (UserException_Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testAdd() {
        try {
            //1、将一个对象转换为xml通过JAXB
            JAXBContext ctx = JAXBContext.newInstance(LicenceInfo.class);
            //1.1设置licenseInfo对象
            User ru = new User();
            ru.setNickname("超级管理");
            ru.setUsername("admin");
            ru.setPassword("123");
            LicenceInfo info = new LicenceInfo();
            info.setRegisterUser(ru);

            //以下两行，效果等同于在LicenceInfo类加注解@XMLRootElement
            QName name = new QName(ns,"licenceInfo");
            JAXBElement<LicenceInfo> jele = new JAXBElement<>(name,LicenceInfo.class,info);

            Marshaller mars = ctx.createMarshaller();
            mars.setProperty(Marshaller.JAXB_FRAGMENT,true); //取出XML头信息
            mars.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");//设置XML编码

            //2、jele转换为DOM
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            mars.marshal(jele, doc);

            //mars.marshal(jele,System.out);
            //3、通过Headers.create方法完成header的添加
            //获取WSBindingProvider
            WSBindingProvider wsb = (WSBindingProvider)port;
            wsb.setOutboundHeaders(Headers.create(doc.getDocumentElement()));

            User u = new User();
            u.setNickname("测试11");
            u.setPassword("abc11");
            u.setUsername("test0111");
            port.add(u);
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testLogin() {
        try {
            User u = port.login("root", "root");
            System.out.println(u);
        } catch (UserException_Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testDelete() {
        try {
            port.delete("zs");
        } catch (UserException_Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //@Test
    //public void testUpload() {
    //    try {
    //        byte[] file = FileUtils.readFileToByteArray(new File("/Users/zhouweitao/Pictures/Background/m.jpg"));
    //        port.upload(file);
    //    } catch (UserException_Exception e) {
    //        e.printStackTrace();
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //
    //}
}
