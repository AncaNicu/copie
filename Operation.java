package library;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Operation {

	protected XMLDocument xmlDocument = XMLDocument.getInstance();
    
    //pt a vedea daca o carte deja exista in biblioteca (are acelasi titlu si autor)
    protected boolean bookExists(String title, String author) {
        try {
        	Document document = xmlDocument.getDocument();
            NodeList booksList = document.getElementsByTagName("book");

            for (int i = 0; i < booksList.getLength(); i++) 
            {
                Node book = booksList.item(i);

                if (book.getNodeType() == Node.ELEMENT_NODE) 
                {
                    Element bookElement = (Element) book;
                    
                    // obtine elementele title si author 
                    Element titleElement = (Element) bookElement.getElementsByTagName("title").item(0);
                    Element authorElement = (Element) bookElement.getElementsByTagName("author").item(0);
                    
                    // obtine datele ca stringuri si verifica daca ele coincid cu titlul si autorul primite ca param
                    String bookTitle = titleElement.getTextContent().trim();
                    String bookAuthor = authorElement.getTextContent().trim();
                    
                    if (bookTitle.equals(title) && bookAuthor.equals(author)) 
                    {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    //pt a salva documentul
    protected void saveDocument() throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        Document document = xmlDocument.getDocument();
        String filePath = xmlDocument.getFilePath();
        
        // creeaza sursa DOM
        DOMSource source = new DOMSource(document);

        // creeaza StreamResult cu locatia fisierului
        StreamResult result = new StreamResult(new File(filePath));

        // transforma si salveaza documentul
        transformer.transform(source, result);
    }
    
    //afiseaza toate cartile
    public void displayBooks()
	{
    	try
    	{
    		Document document = xmlDocument.getDocument();
            NodeList booksList = document.getElementsByTagName("book");

            for (int i = 0; i < booksList.getLength(); i++) 
            {
    			Node book = booksList.item(i);
    			
    			if(book.getNodeType() == Node.ELEMENT_NODE)
    			{
    				Element bookElement = (Element) book;
    				System.out.println("Book " + i);
    				
    				//pt a obtine datele cartii
    				NodeList bookDetails = book.getChildNodes();
    				for(int j = 0; j < bookDetails.getLength(); j++)
    				{
    					Node detail = bookDetails.item(j);
    					
    					if(detail.getNodeType() == Node.ELEMENT_NODE)
    					{
    						Element detailElement = (Element) detail;
    						System.out.println("	" + detailElement.getTagName() + ": " + detailElement.getTextContent());
    					}
    				}
    				System.out.println();
    			}
            }
    	}catch(Exception e){
    		
    		System.out.println("There was an error!");
    	}
	}
}
