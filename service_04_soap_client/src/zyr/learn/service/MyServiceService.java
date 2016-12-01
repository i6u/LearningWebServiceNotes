
package zyr.learn.service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.jws.HandlerChain;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "MyServiceService", targetNamespace = "http://server.learn.zyr/", wsdlLocation = "http://localhost:9999/ms?wsdl")
@HandlerChain(file = "handler-chain.xml")
public class MyServiceService
    extends Service
{

    private final static URL MYSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException MYSERVICESERVICE_EXCEPTION;
    private final static QName MYSERVICESERVICE_QNAME = new QName("http://server.learn.zyr/", "MyServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:9999/ms?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MYSERVICESERVICE_WSDL_LOCATION = url;
        MYSERVICESERVICE_EXCEPTION = e;
    }

    public MyServiceService() {
        super(__getWsdlLocation(), MYSERVICESERVICE_QNAME);
    }

    public MyServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), MYSERVICESERVICE_QNAME, features);
    }

    public MyServiceService(URL wsdlLocation) {
        super(wsdlLocation, MYSERVICESERVICE_QNAME);
    }

    public MyServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MYSERVICESERVICE_QNAME, features);
    }

    public MyServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MyServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IMyService
     */
    @WebEndpoint(name = "MyServicePort")
    public IMyService getMyServicePort() {
        return super.getPort(new QName("http://server.learn.zyr/", "MyServicePort"), IMyService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IMyService
     */
    @WebEndpoint(name = "MyServicePort")
    public IMyService getMyServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server.learn.zyr/", "MyServicePort"), IMyService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MYSERVICESERVICE_EXCEPTION!= null) {
            throw MYSERVICESERVICE_EXCEPTION;
        }
        return MYSERVICESERVICE_WSDL_LOCATION;
    }

}
