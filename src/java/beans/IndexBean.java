/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import ejb.DomParserXML;
import javax.ejb.EJB;

/**
 *
 * @author Sergey
 */
@ManagedBean
@SessionScoped
public class IndexBean {

    private String rootElement;
    private Part fileXml;
    
    @EJB
    private DomParserXML domParserXML;
    
    public IndexBean() {
    }
    
    public void testElements () {
        domParserXML.elements();
    }
    
    public void uploadFile () {
        domParserXML.upload(fileXml);
    }

    public String getRootElement() {
        return domParserXML.getRootElement();
    }

    public void setRootElement(String rootElement) {
        this.rootElement = rootElement;
    }

    public Part getFileXml() {
        return fileXml;
    }

    public void setFileXml(Part fileXml) {
        this.fileXml = fileXml;
    }
    
}
