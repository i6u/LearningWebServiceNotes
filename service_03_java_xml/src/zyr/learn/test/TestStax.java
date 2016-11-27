package zyr.learn.test;

import org.junit.Test;

import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhouweitao on 2016/11/26.
 */
public class TestStax {

    /**
     * Stax
     * 基于光标
     */
    @Test
    public void test() {
        //创建XMLInputFactory
        XMLInputFactory factory = XMLInputFactory.newInstance();
        InputStream in = null;
        try {
            in = TestStax.class.getClassLoader().getResourceAsStream("books.xml");
            XMLStreamReader reader = factory.createXMLStreamReader(in);
            while (reader.hasNext()) {
                int type = reader.next();
                /**
                 * 判断节点类型
                 * */
                if (type == XMLStreamConstants.START_ELEMENT) {
                    System.out.println(reader.getName());//节点名称
                } else if (type == XMLStreamConstants.CHARACTERS) { //文本节点
                    System.out.println(reader.getText().trim()); //文本内容
                } else if (type == XMLStreamConstants.END_ELEMENT) {
                    System.out.println("/" + reader.getName());
                }

            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void test01() {

        /**
         * 基友迭代模型的方式
         * */
        //创建XMLInputFactory
        XMLInputFactory factory = XMLInputFactory.newInstance();
        InputStream in = null;
        try {
            in = TestStax.class.getClassLoader().getResourceAsStream("books.xml");

            XMLEventReader reader = factory.createXMLEventReader(in);
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    /**
                     * 获得节点名称
                     * */
                    String name = event.asStartElement().getName().toString();
                    if ("title".equals(name)) {
                        System.out.print(reader.getElementText()+":");
                    }
                    if ("price".equals(name)) {
                        System.out.println(reader.getElementText());
                    }
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void test02() throws Exception {
        /**
         *
         * 基于过滤器的方式
         * */
        XMLInputFactory factory = XMLInputFactory.newInstance();

        InputStream in = null;

        in = TestStax.class.getClassLoader().getResourceAsStream("books.xml");
        XMLEventReader reader = factory.createFilteredReader(factory.createXMLEventReader(in), new EventFilter() {
            @Override
            public boolean accept(XMLEvent event) {
                if (event.isStartElement()) return true;
                return false;
            }
        });

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                String name = event.asStartElement().getName().toString();
                String a;
                if ("title".equals(name)) {
                    System.out.print(reader.getElementText()+":");
                }
                if ("price".equals(name)) {
                    System.out.println(reader.getElementText());
                }
            }
        }


    }
}
