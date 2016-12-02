package zyr.learn.servlet;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;


import com.sun.xml.ws.api.message.Headers;
import com.sun.xml.ws.developer.WSBindingProvider;
import zyr.learn.service.IUserService;
import zyr.learn.service.LicenceInfo;
import zyr.learn.service.User;

/**
 * Created by zhouweitao on 2016/12/2.
 */
public class LicenceHeandler {
    public static void addLicenceHeader(HttpServletRequest request,IUserService port) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(LicenceInfo.class);
            User ru = (User)request.getSession().getAttribute("loginUser");
            if(ru==null) return;
            LicenceInfo info = new LicenceInfo();
            info.setRegisterUser(ru);
            QName name = new QName("http://service.learn.zyr","licenceInfo");
            JAXBElement<LicenceInfo> jele = new JAXBElement<LicenceInfo>(name,LicenceInfo.class,info);
            Marshaller mars = ctx.createMarshaller();
            mars.setProperty(Marshaller.JAXB_FRAGMENT,true);
            mars.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            mars.marshal(jele, doc);

            WSBindingProvider wsb = (WSBindingProvider)port;
            wsb.setOutboundHeaders(Headers.create(doc.getDocumentElement()));
        } catch (PropertyException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
