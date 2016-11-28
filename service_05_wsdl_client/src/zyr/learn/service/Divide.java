
package zyr.learn.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>divide complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="divide">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="c" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="d" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "divide", propOrder = {
    "c",
    "d"
})
public class Divide {

    protected int c;
    protected int d;

    /**
     * 获取c属性的值。
     * 
     */
    public int getC() {
        return c;
    }

    /**
     * 设置c属性的值。
     * 
     */
    public void setC(int value) {
        this.c = value;
    }

    /**
     * 获取d属性的值。
     * 
     */
    public int getD() {
        return d;
    }

    /**
     * 设置d属性的值。
     * 
     */
    public void setD(int value) {
        this.d = value;
    }

}
