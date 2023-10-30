package library;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class XMLDocument {
	private Document document;
	private String filePath = "C:\\\\Users\\\\ASUS\\\\Desktop\\\\master1\\\\MSIC\\\\Proiect\\\\library\\\\src\\\\library\\\\library_xml.xml";
	
	private static XMLDocument instance = null;
	
	public XMLDocument()
	{
        try {
        	//se creeaza o instanta a cls DocumentBuilderFactory
        	//DocumentBuilder e fol pt a parcurge fisiere xml si pt a crea ob. Document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            //document este continutul fisierului xml
            document = builder.parse(new File(filePath));
          //se asigura ca doc. este consistent si usor pt a lucra cu el
            document.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static XMLDocument getInstance()
	{
		if(instance == null)
		{
			instance = new XMLDocument();
		}
		return instance;
	}

	public Document getDocument() {
		return document;
	}

	public String getFilePath() {
		return filePath;
	}
}
