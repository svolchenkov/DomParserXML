/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import com.oracle.jrockit.jfr.ContentType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 *
 * @author Sergey
 */
@Stateless
@LocalBean
public class DomParserXML {

    private String rootElement;
    private Part fileXml;
    private File file;
    String path = "/Users/Sergey/Desktop/temt.tmp";

    public DomParserXML() {
    }

    public Map<String, String> elements() {
        Map<String, String> map = new LinkedHashMap<>();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(path));
            document.getDocumentElement().normalize();
            map.put("rootElement", document.getDocumentElement().getNodeName());
//            System.out.println("* " + document.getElementsByTagName("student").item(0).getTextContent());
//            System.out.println("*" + document.getElementsByTagName("firstname").item(0).getTextContent());
            NodeList nList = document.getElementsByTagName("student");
            for ( int index = 0; index < nList.getLength(); index++ ) {
                Node node = nList.item(index);
            System.out.println("node: " + node.getNodeName());
            }
            
            System.out.println("*****XML Elements*****");
            for ( String s : map.keySet() ) {
                System.out.println("key: " + s + "value: " + map.get(s));
            }
        } catch (Exception ex) {
            System.out.println("" + ex.getMessage());
        } finally {
            try {
                Files.delete(Paths.get(path));
            } catch (IOException ex) {
                Logger.getLogger(DomParserXML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return map;
    }

    public void upload(Part fileXml) {
        this.fileXml = fileXml;
        try (InputStream is = fileXml.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                PrintWriter outputStream = new PrintWriter(path);) {
            String line;
            while ((line = br.readLine()) != null) {
                outputStream.write(line);
            }
            setFile(new File(path));
        } catch (IOException ex) {
            Logger.getLogger(DomParserXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Part getFileXml() {
        return fileXml;
    }

    public void setFileXml(Part fileXml) {
        this.fileXml = fileXml;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getRootElement() {
        return rootElement;
    }

    public void setRootElement(String rootElement) {
        this.rootElement = rootElement;
    }

}
