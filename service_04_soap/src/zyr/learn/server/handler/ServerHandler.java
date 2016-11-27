package zyr.learn.server.handler;

import org.w3c.dom.*;
import org.w3c.dom.Node;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhouweitao on 2016/11/27.
 */
public class ServerHandler implements SOAPHandler<SOAPMessageContext> {
    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        //boolean out = (boolean) context.get(MessageContext.INBOUND_MESSAGE_ATTACHMENTS);
        try {
            Boolean out = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

            if (!out) {
                SOAPMessage message = context.getMessage();
                SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
                SOAPHeader header = envelope.getHeader();
                SOAPBody body = envelope.getBody();


                Node node = body.getChildNodes().item(0);
                String partname = node.getLocalName();

                //为list，addUser添加头部信息验证
                if ("list".equals(partname) || "addUser".equals(partname)) {

                    if (header == null) {
                        SOAPFault fault = body.addFault();
                        fault.setFaultString("头部信息不能为空。。。");
                        throw new SOAPFaultException(fault);
                    }
                    Iterator<SOAPHeaderElement> iterator = header.examineAllHeaderElements();
                    if (!iterator.hasNext()) {
                        SOAPFault fault = body.addFault();
                        fault.setFaultString("缺少头部信息。。。");
                        throw new SOAPFaultException(fault);
                    }

                    while (iterator.hasNext()) {
                        SOAPHeaderElement element = iterator.next();
                        System.out.println(element.getTextContent());
                    }

                }
            }
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {

    }
}
