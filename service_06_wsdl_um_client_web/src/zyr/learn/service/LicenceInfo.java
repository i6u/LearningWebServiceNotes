
package zyr.learn.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for licenceInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="licenceInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="registerUser" type="{http://service.learn.zyr}user"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "licenceInfo", propOrder = {
    "registerUser"
})
public class LicenceInfo {

    @XmlElement(required = true)
    protected User registerUser;

    /**
     * Gets the value of the registerUser property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getRegisterUser() {
        return registerUser;
    }

    /**
     * Sets the value of the registerUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setRegisterUser(User value) {
        this.registerUser = value;
    }

}
