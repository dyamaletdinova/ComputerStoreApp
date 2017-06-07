package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

 /**
  * A class that loads the info from a file  
  * and handles the exception FileNotFoundException
  *
  * @author Diana Yamaletdinova
  * @date 4/8/2015
  *
  */
  
public class InventoryManager {

   private String fileName = "products_and_services.csv";


    /**
     * Constructs a InventoryManager class that takes no arguments
     *
     */
     
   public InventoryManager() {
   }
    /**
     * Method that loads up the Inventory from the 
     * products_and_services.csv file 
     *
     * @return services the services and relevant products
     */

   public ArrayList<Service> loadInventory() throws FileNotFoundException {
   
      Scanner in = new Scanner(new File(fileName)); //Creates scanner object. This constructor can throw a FileNotFoundException
      
      ArrayList<Service> services = new ArrayList<Service>(); // Creates an Array List of services that holds object Service
   
      
       // Create a while loop that reads file while there is a next line
       // nextLine() returns true if there is another line of input available
      while ( in.hasNextLine() ){ 
            
         String dataElement = in.nextLine();  // reads each input line of services into a string
          
         String[] serviceInformation = dataElement.split(",");// returns an array of strings, using a split method
         
         String serviceName = serviceInformation[0]; //sets the position 0 to serviceName
         Double servicePrice = Double.parseDouble(serviceInformation[1]);  //sets the position 1 to servicePrice   
      
         // Creates an Array List of products that holds object Product
         ArrayList<Product> products = new ArrayList<Product>(); 
         
         while ( in.hasNextLine() ){ 
           
            String productElement = in.nextLine(); // reads each input line of products into a string
            
            if(productElement == null || productElement.isEmpty()) { // checks if the product element is null OR productElement is empty
               break;                                                // then breaks out of the the loop and exits
            }
            
            String[] productInformation = productElement.split(","); // creates an array of strings, using a split method
            
            String productName = productInformation[0]; //sets the position 0 to productName
            Double productPrice = Double.parseDouble(productInformation[1]); //sets the position 1 to productPrice 
            Double laborTime = Double.parseDouble(productInformation[2]); //sets the position 2 to laborTime
            String inventoryNumber = productInformation[3]; //sets the position 3 to inventoryNumber
                        
            Product p = new Product(productName, productPrice, inventoryNumber, laborTime); // passing the var from Product object
            products.add(p); //adds products to an array             
              
         }    
         
         // adds service name, price and products to services
         services.add(new Service(serviceName, servicePrice, products));
      }   
       
      in.close(); // close file
     
      return services;
   
   } 
       
}