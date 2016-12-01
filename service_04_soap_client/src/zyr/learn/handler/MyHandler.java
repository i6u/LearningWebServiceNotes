package zyr.learn.handler;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;

/**
 * Created by zhouweitao on 2016/11/27.
 */
public class MyHandler implements SOAPHandler<SOAPMessageContext> {
    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        System.out.println("handle...");
        String ns = "http://server.learn.zyr/";
        try {
            /**
             * MESSAGE_OUTBOUND_PROPERTY
             *
             * 对于客户端`OUTBOUND`表示发送给服务端的消息
             * */

            //处理发送给服务端的消息
            Boolean out = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
            if (out) {
                SOAPMessage message = context.getMessage();
                SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
                SOAPHeader header = envelope.getHeader();
                header = header == null ? envelope.addHeader() : header;

                QName qn = new QName(ns, "licenseInfo", "ns");
                header.addHeaderElement(qn).setValue("ab");
                System.out.println(header.getTextContent());
            }
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        System.out.println("error...");
        return false;
    }

    @Override
    public void close(MessageContext context) {

    }
}
