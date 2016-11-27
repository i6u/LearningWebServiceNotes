package zyr.learn.test;

import org.junit.Test;
import zyr.learn.model.Classroom;
import zyr.learn.model.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * Created by zhouweitao on 2016/11/26.
 *
 */
public class TestJAXB {

    /** 编排*/
    @Test
    public void test01(){
        FileWriter fw = null;
        try {
            //1.创建JAXBContext对象，传入需要编排的对象
            JAXBContext jax = JAXBContext.newInstance(Student.class);
            //2.创建marshaller对象
            Marshaller marshaller = jax.createMarshaller();
            Student student = new Student(1, "悟空", 500, new Classroom(1, "GoLong", 2016));
            //File file = new File(TestJAXB.class.getResource("/").getPath()+"/stu.xml");
            //if (!file.exists()) {
            //    file.createNewFile();
            //}
            // fw = new FileWriter(file.getName(),false);
            ////3.把对象，编排到一个io流
            //marshaller.marshal(student,fw);

            //marshaller.marshal(student,System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        //catch (IOException e) {
        //    e.printStackTrace();
        //}finally {
        //    try {
        //        fw.close();
        //    } catch (IOException e) {
        //        e.printStackTrace();
        //    }
        //}
    }

    /** 反编排*/
    @Test
    public void test02() throws Exception {

        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><student><age>500</age><classroom><cid>1</cid><grade>2016</grade><name>GoLong</name></classroom><sid>1</sid><username>悟空</username></student>";
        JAXBContext jaxbc = JAXBContext.newInstance(Student.class);
        Unmarshaller unmarshaller = jaxbc.createUnmarshaller();
        try {
            Student student = (Student) unmarshaller.unmarshal(new StringReader(str));
            System.out.println(student.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

