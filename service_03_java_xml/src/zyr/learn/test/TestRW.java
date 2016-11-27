package zyr.learn.test;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhouweitao on 2016/11/26.
 */
public class TestRW {
    @Test
    public void testWriter(){

        /** 写XML*/
        try {

            XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter(System.out);//写到一个输出流中
            writer.writeStartDocument("UTF-8","1.0");
            writer.writeEndDocument();
            writer.writeStartElement("person");
            /** 添加命名空间,注意命名空间的位置*/
            writer.writeNamespace("ns","http://www.event.org/ns");
            writer.writeAttribute("pid","1122");
            writer.writeStartElement("name");
            writer.writeCharacters("悟空");
            writer.writeEndElement();
            /** ns 添加命名空间*/
            writer.writeStartElement("ns","gender","male");
            writer.writeEndElement();
            writer.writeEndElement();
            writer.flush();
            writer.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

    }
    /** 改XML*/

    @Test
    public void test02() throws Exception {
        InputStream in=null;

        try {
            in = TestXPath.class.getClassLoader().getResourceAsStream("books.xml");
            //创建文档对象
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            /**
             * 通过DocumentBuilder创建Document对象*
             * */
            Document doc = db.parse(in);
            XPath xPath = XPathFactory.newInstance().newXPath();
            Transformer tran = TransformerFactory.newInstance().newTransformer();
            tran.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
            tran.setOutputProperty(OutputKeys.INDENT,"yes");

            NodeList nodeList = (NodeList) xPath.evaluate("//book[title='Learning XML']", doc, XPathConstants.NODESET);

            Element be = (Element) nodeList.item(0);
            Element e = (Element)(be.getElementsByTagName("price").item(0));
            e.setTextContent("1234.1234");
            Result result = new StreamResult(System.out);
            tran.transform(new DOMSource(doc),result);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

    }
}
