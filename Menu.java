package library;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	private static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
        int choice = -1;
        while (choice != 8) {
            displayMenu();
            choice = getUserChoice();
            handleChoice(choice);
        }
        scanner.close();
	}
	
    private static void displayMenu() {
		System.out.println("Choose an action by pressing the corresponding key:");
		System.out.println("	1. Add a book");
		System.out.println("	2. Update a book");
		System.out.println("	Search a book:");
		System.out.println("		3. by author");
		System.out.println("		4. by keywords");
		System.out.println("		5. by year");
		System.out.println("		6. by publisher");
		System.out.println("		7. by domain");
		System.out.println("	8. Quit");
		
        System.out.print("Choose an action: ");
    }
    
    private static int getUserChoice() {
        int choice = -1;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // consuma inputul invalid pt a evita o bucla infinita
        }
        return choice;
    }

    private static void handleChoice(int choice)
    {
		InsertOperation insertOp = new InsertOperation();
		SearchOperation searchOp = new SearchOperation();
		UpdateOperation updateOp = new UpdateOperation();
		
		String title = "";
		String author ="",publisher = "";
		String[] keywords = {""};
		String[] domains = {""};
		int year = -1;
		
		String searchedAuthor = "";
		String searchedDomain = "";
		String searchedPublisher = "";
		int year1 = -1;
		int year2 = -1;
		String[] searchedKeywords = {""};
		
		String targetTitle = "";
		String targetAuthor = "";
		
		boolean okInput = false;
    	switch(choice)
		{
			case 1: 
				scanner.nextLine();
				System.out.print("Title: ");
				title = scanner.nextLine();
				System.out.print("Author: ");
				author = scanner.nextLine();
				System.out.print("Keywords (separated by comma): ");
				keywords = scanner.nextLine().split(", ");
				
				okInput = false;
				while(!okInput)
				{
					boolean ok = true;
    		        try {
        				System.out.print("Year: ");
        				year = scanner.nextInt();
    		        } catch (InputMismatchException e) 
    		        {
    		        	ok = false;
    		            System.out.println("Invalid input. Please enter an integer.");
    		            scanner.next();
    		        }
    		        if(ok == true)
    		        {
    		        	if(year > 0)
    		        	{
    		        		okInput = true;
    		        	}
    		        	else
    		        	{
    		        		System.out.println("Please enter a positive number!");
    		        	}
    		        	
    		        }
				}
				scanner.nextLine();
				System.out.print("Domains (separated by comma): ");
				domains = scanner.nextLine().split(", ");
				System.out.print("Publisher: ");
				publisher = scanner.nextLine();
				insertOp.insertNewBook(title, author, keywords, year, domains, publisher);
				break;
			case 2: 
				scanner.nextLine();
				System.out.print("Target title: ");
				targetTitle = scanner.nextLine();
				System.out.print("Target author: ");
				targetAuthor = scanner.nextLine();
				
				System.out.println();

				System.out.print("New title: ");
				title = scanner.nextLine();
				System.out.print("New author: ");
				author = scanner.nextLine();
				System.out.print("New keywords (separated by comma): ");
				keywords = scanner.nextLine().split(", ");
				okInput = false;
				while(okInput == false)
				{
					boolean ok = true;
    		        try {
        				System.out.print("New year: ");
        				year = scanner.nextInt();
    		        } catch (InputMismatchException e) 
    		        {
    		        	ok = false;
    		            System.out.println("Invalid input. Please enter an integer.");
    		            scanner.next();
    		        }
    		        if(ok == true)
    		        {
    		        	if(year > 0)
    		        	{
    		        		okInput = true;
    		        	}
    		        	else
    		        	{
    		        		System.out.println("Please enter a positive number!");
    		        	}
    		        	
    		        }
				}
				scanner.nextLine();
				System.out.print("New domains (separated by comma): ");
				domains = scanner.nextLine().split(", ");
				System.out.print("New publisher: ");
				publisher = scanner.nextLine();

				updateOp.updateBookByTitleAndAuthor(targetTitle, targetAuthor, title, author, keywords, year, domains, publisher);
				break;
			case 3: 
				scanner.nextLine();
				System.out.print("Searched author: ");
				searchedAuthor = scanner.nextLine();
				searchOp.searchBooksByAuthor(searchedAuthor);
				break;
			case 4: 
				scanner.nextLine();
				System.out.print("Searched keywords (separated by comma): ");
				keywords = scanner.nextLine().split(", ");
				searchOp.searchBooksByKeywords(keywords);
				break;
			case 5:
				okInput = false;
				while(okInput == false)
				{
					boolean ok = true;
    		        try {
    		            System.out.print("Year 1: ");
    		            year1 = scanner.nextInt();
    		            System.out.print("Year 2: ");
    		            year2 = scanner.nextInt();
    		        } catch (InputMismatchException e) 
    		        {
    		        	ok = false;
    		            System.out.println("Invalid input. Please enter integers.");
    		            scanner.next();
    		        }
    		        if(ok == true)
    		        {
    		        	if(year1 < 0 || year2 < 0)
    		        	{
    		        		System.out.println("Please enter positive numbers!");
    		        	}
    		        	else
    		        	{
        		        	if(year1 > year2)
        		        	{
        		        		System.out.println("Year 1 should be smaller than year 2!");
        		        	}
        		        	else
        		        	{
        		        		okInput = true;
        		        	}
    		        	}
    		        }
				}
				searchOp.searchBooksBetweenYears(year1, year2);
				break;
			case 6: 
				scanner.nextLine();
				System.out.print("Searched publisher: ");
				searchedPublisher = scanner.nextLine();
				searchOp.searchBooksByPublisher(searchedPublisher);
				break;
			case 7: 
				scanner.nextLine();
				System.out.print("Searched domain: ");
				searchedDomain = scanner.nextLine();
				searchOp.searchBooksByDomain(searchedDomain);
				break;
			case 8: break;
			default:break;
		}
    }
}
