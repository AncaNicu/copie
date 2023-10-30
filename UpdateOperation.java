package library;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class UpdateOperation extends Operation{

	   //pt a actualiza o carte avand date titlul si autorul sau
    public void updateBookByTitleAndAuthor(String targetTitle, String targetAuthor, String updatedTitle, String updatedAuthor, 
    		String[] updatedKeywords, int updatedYear, String[] UpdatedDomains, String updatedPublisher) 
    {
    	
    	boolean bookExists = false;
    	
        try {
        	//daca in biblioteca e o carte (diferita de cea pe care vrem s-o
        	//actualizam) cu ac title si autor ca cele noi => nu se poate face update
        	if((!targetTitle.equals(updatedTitle) || !targetAuthor.equals(updatedAuthor)) && bookExists(updatedTitle, updatedAuthor))
        	{
        		System.out.println("A book with the same author and title already exists!");
        	}
        	else
        	{
        		Document document = xmlDocument.getDocument();
            	NodeList books = document.getElementsByTagName("book");
                for (int i = 0; i < books.getLength(); i++) 
                {
                    Element book = (Element) books.item(i);
                    Element titleElement = (Element) book.getElementsByTagName("title").item(0);
                    String currentTitle = titleElement.getTextContent().trim();
                    Element authorElement = (Element) book.getElementsByTagName("author").item(0);
                    String currentAuthor = authorElement.getTextContent().trim();
                    
                    //cartea crt are titlul si autorul date => actualizam cartea cu noile date
                    if (currentTitle.equals(targetTitle) && currentAuthor.equals(targetAuthor)) 
                    {
                    	bookExists = true;
                    	
                        // actualizeaza datele cartii
                        Element publisherElement = (Element) book.getElementsByTagName("publisher").item(0);
                        Element keywordsElement = (Element) book.getElementsByTagName("keywords").item(0);
                        Element yearElement = (Element) book.getElementsByTagName("year").item(0);
                        Element domainsElement = (Element) book.getElementsByTagName("domains").item(0);
                        
                        authorElement.setTextContent(updatedAuthor);
                        publisherElement.setTextContent(updatedPublisher);
                        keywordsElement.setTextContent(String.join(", ", updatedKeywords));
                        titleElement.setTextContent(updatedTitle);
                        domainsElement.setTextContent(String.join(", ", UpdatedDomains));
                        yearElement.setTextContent(Integer.toString(updatedYear));

                        break; //daca s-a gasit cartea => parasim bucla
                    }
                    
                }
                
                //daca exista cartea pe care vrem s-o actualizam => se salveaza documentul
                if(bookExists == true)
                {
                	saveDocument();
                	System.out.println("Book has been updated successfully!");
                }
                else
                {
                	System.out.println("Book doesn't exists!");
                }
                
        	}
            
        }catch(Exception e)
        {
        	System.out.println("Updating Error!!!");
        }
    }
}
